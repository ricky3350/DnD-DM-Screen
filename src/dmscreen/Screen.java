package dmscreen;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import dmscreen.data.Data;
import dmscreen.data.base.DataSet;
import dmscreen.util.Util;
import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class Screen extends Application {

	private static Screen activeScreen;

	public static final String DEFAULT_FONT_NAME = "System";

	public static void main(final String[] args) {

		try {
			Font.loadFont(new FileInputStream(Paths.get("Cormorant_Garamond/CormorantGaramond-Medium.ttf").toFile()), 12);
			Font.loadFont(new FileInputStream(Paths.get("Cormorant_Garamond/CormorantGaramond-Bold.ttf").toFile()), 12);
		} catch (final FileNotFoundException e) {}

		Application.launch(args);
	}

	private TreeView<Object> dataTree;
	private final Map<TreeItem<Object>, TreeItem<Object>> parents = new LinkedHashMap<>();
	private TextField searchBar;
	private TabPane tabPane;
	private final Map<Object, Tab> openTabs = new HashMap<>();
	private SplitPane rootPane;

	@Override
	public void start(final Stage stage) throws Exception {
		activeScreen = this;

		try {
			Data.init(Paths.get("resources"));
		} catch (final IOException e) {}

		dataTree = createTree();
		searchBar = createSearchBar();

		tabPane = new TabPane();
		tabPane.setTabMaxWidth(96);
		tabPane.setSide(Side.RIGHT);

		rootPane = new SplitPane(new BorderPane(dataTree, searchBar, null, null, null), new StackPane(), tabPane);
		rootPane.setDividerPositions(0.25, 0.625);

		final Scene scene = new Scene(rootPane, 768, 960);
		scene.getStylesheets().add("dmscreen/statblock/statBlock.css");

		stage.setScene(scene);
		stage.setTitle("Dungeon Master's Screen");
		stage.show();

		final Stage playerStage = createPlayerStage();
		stage.showingProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue) // If the main window is closed, close the player window
				playerStage.hide();
		});
	}

	private TextField createSearchBar() {
		final TextField searchBar = new TextField("-");
		searchBar.setPromptText("Search");
		searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
			final Set<TreeItem<Object>> pars = new HashSet<>(parents.values()), hasChildren = new LinkedHashSet<>();
			pars.forEach(item -> item.getChildren().clear());

			parents.forEach((child, parent) -> {
				if (!pars.contains(child) && (searchBar.getText().isEmpty() || Util.getName(child.getValue()).toLowerCase().contains(searchBar.getText().toLowerCase()))) {
					parent.getChildren().add(child);

					TreeItem<Object> item = parent, par;
					while ((par = parents.get(item)) != null) {
						hasChildren.add(item);
						item = par;
					}
				}
			});

			hasChildren.forEach(parent -> parents.get(parent).getChildren().add(parent));
		});
		searchBar.setText("");

		return searchBar;
	}

	private TreeView<Object> createTree() {
		final TreeItem<Object> treeRoot = constructTreeValues();
		final TreeView<Object> dataTree = new TreeView<>(treeRoot);
		dataTree.setShowRoot(false);
		dataTree.setCellFactory(param -> {
			final TextFieldTreeCell<Object> cell = new TextFieldTreeCell<>(new StringConverter<Object>() {

				@Override
				public String toString(final Object object) {
					final String name = Util.getName(object);

					if (object instanceof Enum<?>) return Util.titleCase(name);
					if (object instanceof AccessibleObject) return Util.titleCaseFromCamelCase(name);

					return name;
				}

				@Override
				public Object fromString(final String string) {
					return new Object();
				}

			});

			cell.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && event.getButton() == MouseButton.PRIMARY) {
					openTab(cell.getTreeItem());
				}
			});

			return cell;
		});
		dataTree.setStyle("-fx-focus-color: transparent;");

		return dataTree;
	}

	private void openTab(final TreeItem<Object> value) {
		if (value != null && !parents.containsValue(value)) {
			if (openTabs.containsKey(value.getValue())) {
				tabPane.getSelectionModel().select(openTabs.get(value.getValue()));
			} else {
				final BlockPane<Object> pane = new BlockPane<Object>(value.getValue(), (DataSet) value.getParent().getParent().getValue());
				final Tab tab = new Tab(Util.getName(value.getValue()), pane);
				tab.setTooltip(new Tooltip(tab.getText()));
				tab.textProperty().bind(pane.titleProperty());
				tab.setOnCloseRequest(e -> {
					if (!pane.confirmClose()) e.consume();
				});
				tab.setOnClosed(e -> openTabs.remove(value.getValue()));
				openTabs.put(value.getValue(), tab);
				tabPane.getTabs().add(tab);
				tabPane.getSelectionModel().select(tabPane.getTabs().size() - 1);
				pane.setOnEditSaved((observable, oldValue, newValue) -> {
					value.setValue(newValue);
					Collections.sort(value.getParent().getChildren(), (o1, o2) -> Util.getName(o1.getValue()).compareToIgnoreCase(Util.getName(o2.getValue())));
					dataTree.getSelectionModel().select(value);
					dataTree.scrollTo(dataTree.getSelectionModel().getSelectedIndex());
				});
			}
		}
	}

	private TreeItem<Object> constructTreeValues() {
		parents.clear();

		final TreeItem<Object> treeRoot = new TreeItem<>();
		Data.getData().forEach((name, dataSet) -> {
			final TreeItem<Object> setRoot = new TreeItem<>(dataSet);
			parents.put(setRoot, treeRoot);

			for (final Field field : dataSet.getClass().getFields()) {
				try {
					if (field.getName().equals("name") || (Boolean) field.getType().getMethod("isEmpty").invoke(field.get(dataSet))) continue;

					final TreeItem<Object> sectionRoot = new TreeItem<>(field);
					parents.put(sectionRoot, setRoot);

					field.getType().getMethod("forEach", Consumer.class).invoke(field.get(dataSet), (Consumer<Object>) o -> {
						parents.put(new TreeItem<>(o), sectionRoot);
					});
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {}
			}
		});
		return treeRoot;
	}

	private Stage createPlayerStage() {
		final Stage playerStage = new Stage();

		// playerStage.show();

		return playerStage;
	}

	public static void openTab(final Object info) {
		for (final TreeItem<Object> leaf : activeScreen.parents.keySet()) {
			if (leaf.getChildren().isEmpty() && leaf.getValue() == info) {
				activeScreen.openTab(leaf);
				break;
			}
		}
	}

}
