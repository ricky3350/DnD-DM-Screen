package dmscreen.statblock;

import java.util.Map;

import javafx.scene.control.TextField;

public class EditableMapStringStringEditor extends EditableMapEditor<String, String> {

	public EditableMapStringStringEditor(final String name, final String keyTitle, final String valueTitle, final Map<String, String> initialValue) {
		super(name, keyTitle, valueTitle);

		initialValue.forEach((k, v) -> {
			addMapRow(makeRow(k, v));
		});
	}

	private static MapRow<String, String> makeRow(final String initialKey, final String initialValue) {
		final TextField keyNode = new TextField(initialKey);
		final TextField valueNode = new TextField(initialValue);
		return new MapRow<>(keyNode, valueNode, keyNode::getText, valueNode::getText);
	}

	@Override
	protected MapRow<String, String> makeRow() {
		return makeRow(null, null);
	}

}
