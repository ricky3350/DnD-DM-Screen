package dmscreen.statblock.editor;

import java.util.Arrays;

import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

public class StringSelectEditor extends Editor<String> {

	private final ComboBox<String> value;

	public StringSelectEditor(final String name, final int initialIndex, final String... values) {
		super(name);

		value = new ComboBox<>();
		Arrays.stream(values).forEach(value.getItems()::add);
		if (initialIndex >= 0) value.getSelectionModel().select(initialIndex);
		addRow(0, new Text(name + ":"), value);
	}

	@Override
	public String getValue() {
		return value.getValue();
	}

	@Override
	public void setValue(final String value) {
		this.value.setValue(value);
	}

}
