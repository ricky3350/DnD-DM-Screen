package dmscreen.data.spell;

import java.util.ArrayList;
import java.util.List;

public class Spell {

	public String name;
	public int level;
	public SpellType type;
	public boolean ritual;
	public String castingTime;
	public String range;
	public boolean verbal, somatic;
	public String materialComponents;
	public String duration;
	public boolean concentration;
	public List<? extends SpellParagraph> description = new ArrayList<>();

}
