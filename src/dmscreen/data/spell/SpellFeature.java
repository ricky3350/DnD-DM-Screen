package dmscreen.data.spell;

import dmscreen.StatBlock;
import javafx.scene.Node;

public class SpellFeature extends SpellParagraph {

	private final String title;

	public SpellFeature(final String title, final String text) {
		super(text);

		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public Node getNode() {
		return StatBlock.dataLine(title + ".", getText() + "\n", true);
	}

}
