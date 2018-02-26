package dmscreen.data.spell;

import dmscreen.StatBlock;
import javafx.scene.Node;

public class SpellParagraph {

	private final String text;

	public SpellParagraph(final String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public Node getNode() {
		return StatBlock.dataLine("", text + "\n");
	}

}
