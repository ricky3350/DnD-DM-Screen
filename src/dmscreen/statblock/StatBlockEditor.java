package dmscreen.statblock;

import java.lang.reflect.InvocationTargetException;
import java.util.TreeSet;
import java.util.function.Supplier;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import dmscreen.data.base.Ability;
import dmscreen.data.base.Size;
import dmscreen.data.base.Skill;
import dmscreen.data.creature.Alignment;
import dmscreen.data.creature.Condition;
import dmscreen.data.creature.Creature;
import dmscreen.data.creature.CreatureType;
import dmscreen.data.creature.SpeedType;
import dmscreen.data.creature.VisionType;
import dmscreen.data.spell.Spell;
import dmscreen.data.spell.SpellType;

public class StatBlockEditor<T> extends VBox {

	private T originalValue;
	private Supplier<T> newValueGetter;

	private StatBlockEditor(final T originalValue, final Supplier<T> newValueGetter) {
		super(2);

		this.originalValue = originalValue;
		this.newValueGetter = newValueGetter;

		setPadding(new Insets(8));
	}

	private StatBlockEditor(final T originalValue) {
		this(originalValue, () -> null);
	}

	private StatBlockEditor(final Supplier<T> newValueGetter) {
		this(null, newValueGetter);
	}

	private StatBlockEditor() {
		this(null, () -> null);
	}

	public T getOriginalValue() {
		return originalValue;
	}

	public T getNewValue() {
		return newValueGetter.get();
	}

	public static boolean isEditable(final Object obj) {
		return isEditable(obj.getClass());
	}

	public static boolean isEditable(final Class<?> c) {
		try {
			StatBlockEditor.class.getMethod("getEditor", c);
			return true;
		} catch (NoSuchMethodException | SecurityException e) {
			return false;
		}
	}

	public static StatBlockEditor<? extends Object> getEditor(final Object obj) {
		try {
			if (obj == null || obj.getClass() == Object.class) return new StatBlockEditor<>(null);

			return (StatBlockEditor<?>) StatBlockEditor.class.getMethod("getEditor", obj.getClass()).invoke(null, obj);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			return new StatBlockEditor<>(null);
		}
	}

	public static StatBlockEditor<Creature> getEditor(final Creature creature) {
		final StatBlockEditor<Creature> editor = new StatBlockEditor<>(creature);

		final StringEditor name = new StringEditor("Name", creature.name);
		final StringEditor shortName = new StringEditor("General Name", creature.shortName);
		final EnumEditor<Size> size = new EnumEditor<>(Size.class, "Size", creature.size);
		final EnumEditor<CreatureType> type = new EnumEditor<>(CreatureType.class, "Type", creature.type);
		final StringEditor subtype = new StringEditor("Subtype", creature.subtype);
		final EnumEditor<Alignment> alignment = new EnumEditor<>(Alignment.class, "Alignment", creature.alignment);
		final IntegerEditor ac = new IntegerEditor("Armor Class", 0, 30, creature.ac);
		final StringEditor armorNote = new StringEditor("Armor Description", creature.armorNote);
		final DiceRollEditor hitDice = new DiceRollEditor("Hit Dice", creature.hitDice);
		final IntegerEditor speed = new IntegerEditor("Speed (ft.)", 0, 500, creature.speed, 5);
		final MapEnumIntegerEditor<SpeedType> speeds = new MapEnumIntegerEditor<>(SpeedType.class, "Other Speeds", "Type", "Speed (ft.)", 0, 500, 5, creature.speeds);
		final MapEnumIntegerEditor<Ability> abilityScores = new MapEnumIntegerEditor<Ability>(Ability.class, "Ability Scores", "Ability", "Score", 0, 40, creature.abilityScores);
		final EditableMapEnumIntegerEditor<Ability> savingThrows = new EditableMapEnumIntegerEditor<Ability>(Ability.class, "Saving Throws", "Ability", "Modifier", -10, 20, creature.savingThrows);
		final EditableMapEnumIntegerEditor<Skill> skills = new EditableMapEnumIntegerEditor<>(Skill.class, "Skills", "Skill", "Modifier", -10, 20, creature.skills);
		final CollectionEnumEditor<Condition> conditionImmunities = new CollectionEnumEditor<Condition>(Condition.class, "Condition Immunities", creature.conditionImmunities);
		final MapEnumIntegerEditor<VisionType> senses = new MapEnumIntegerEditor<VisionType>(VisionType.class, "Senses", "Type", "Range (ft.)", 0, 5000, 5, creature.senses);

		editor.getChildren().addAll(name, shortName, size, type, subtype, alignment, ac, armorNote, hitDice, speed, speeds, abilityScores, savingThrows, skills, conditionImmunities, senses);

		editor.newValueGetter = () -> {
			final Creature newCreature = new Creature();

			newCreature.name = name.getValue();
			newCreature.shortName = shortName.getValue();
			newCreature.size = size.getValue();
			newCreature.type = type.getValue();
			newCreature.subtype = subtype.getValue();
			newCreature.alignment = alignment.getValue();
			newCreature.ac = ac.getValue();
			newCreature.armorNote = armorNote.getValue();
			newCreature.hitDice = hitDice.getValue();
			newCreature.speed = speed.getValue();
			newCreature.speeds = speeds.getValue();
			newCreature.abilityScores = abilityScores.getValue();
			newCreature.savingThrows = savingThrows.getValue();
			newCreature.skills = skills.getValue();
			newCreature.conditionImmunities = new TreeSet<>(conditionImmunities.getValue());
			newCreature.senses = senses.getValue();

			return newCreature;
		};
		return editor;
	}

	public static StatBlockEditor<Spell> getEditor(final Spell spell) {
		final StatBlockEditor<Spell> editor = new StatBlockEditor<>(spell);

		final StringEditor name = new StringEditor("Name", spell.name);
		final IntegerEditor level = new IntegerEditor("Level", 0, 9, spell.level);
		final EnumEditor<SpellType> type = new EnumEditor<SpellType>(SpellType.class, "Type", spell.type);
		final BooleanEditor ritual = new BooleanEditor("Ritual", spell.ritual);
		final StringEditor castingTime = new StringEditor("Casting Time", spell.castingTime);
		final StringEditor range = new StringEditor("Range", spell.range);
		final BooleanEditor verbal = new BooleanEditor("Verbal Components", spell.verbal);
		final BooleanEditor somatic = new BooleanEditor("Somatic Components", spell.somatic);
		final StringEditor materialComponents = new StringEditor("Material Components", spell.materialComponents);
		final StringEditor duration = new StringEditor("Duration", spell.duration);
		final BooleanEditor concentration = new BooleanEditor("Concentration", spell.concentration);
		final SpellDescriptionEditor description = new SpellDescriptionEditor("Features", spell.description);

		editor.getChildren().addAll(name, level, type, ritual, castingTime, range, verbal, somatic, materialComponents, duration, concentration, description);

		editor.newValueGetter = () -> {
			final Spell newSpell = new Spell();

			newSpell.name = name.getValue();
			newSpell.level = level.getValue();
			newSpell.type = type.getValue();
			newSpell.ritual = ritual.getValue();
			newSpell.castingTime = castingTime.getValue();
			newSpell.range = range.getValue();
			newSpell.verbal = verbal.getValue();
			newSpell.somatic = somatic.getValue();
			newSpell.materialComponents = materialComponents.getValue();
			newSpell.duration = duration.getValue();
			newSpell.concentration = concentration.getValue();
			newSpell.description = description.getValue();

			return newSpell;
		};
		return editor;
	}
}
