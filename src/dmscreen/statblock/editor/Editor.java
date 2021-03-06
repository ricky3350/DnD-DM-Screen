package dmscreen.statblock.editor;

import javafx.geometry.Pos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public abstract class Editor<T> extends GridPane {

	private final String name;

	public Editor(final String name) {
		setHgap(2);
		setVgap(2);

		this.name = name;

		setAlignment(Pos.CENTER_LEFT);

		final ColumnConstraints cc40 = new ColumnConstraints();
		cc40.setPercentWidth(40);
		final ColumnConstraints cc60 = new ColumnConstraints();
		cc60.setPercentWidth(60);

		getColumnConstraints().addAll(cc40, cc60);
	}

	public abstract T getValue();

	public abstract void setValue(T value);

	public final String getName() {
		return name;
	}

}
