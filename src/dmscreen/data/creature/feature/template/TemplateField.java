package dmscreen.data.creature.feature.template;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.BiFunction;

import dmscreen.Util;
import dmscreen.data.base.DiceRoll;
import dmscreen.statblock.BooleanEditor;
import dmscreen.statblock.CollectionEnumEditor;
import dmscreen.statblock.CollectionStringEditor;
import dmscreen.statblock.DiceRollEditor;
import dmscreen.statblock.EditableMapCollectionEditor;
import dmscreen.statblock.EditableMapEnumIntegerEditor;
import dmscreen.statblock.EditableMapStringStringEditor;
import dmscreen.statblock.Editor;
import dmscreen.statblock.EnumEditor;
import dmscreen.statblock.IntegerEditor;
import dmscreen.statblock.MapEnumIntegerEditor;
import dmscreen.statblock.StringEditor;
import dmscreen.statblock.StringSelectEditor;

public class TemplateField {

	public final String name;
	public final FieldType type;
	public final Object[] args;

	public TemplateField(final String name, final FieldType type, final Object... args) {
		this.name = name;
		this.type = type;
		this.args = args;
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	public static Editor<?> getEditor(final TemplateField field) {
		if (field == null) throw new NullPointerException("field cannot be null");

		final String name = Util.titleCaseFromCamelCase(field.name);
		final Object[] fieldArgs = new Object[field.args.length];

		for (int n = 0; n < fieldArgs.length; n++) {
			try {
				fieldArgs[n] = FieldType.valueOf(field.args[n].toString());
			} catch (final IllegalArgumentException e) {
				fieldArgs[n] = field.args[n];
			}
		}

		if (field.type.clazz.isEnum()) {
			return new EnumEditor(field.type.clazz, name, null);
		} else {
			if (field.type == FieldType.STRING) {
				if (fieldArgs.length > 1) {
					return new StringSelectEditor(name, -1, Arrays.stream(fieldArgs).map(Object::toString).toArray(size -> new String[size]));
				} else if (fieldArgs.length > 0) {
					return new StringEditor(name, null, fieldArgs[0].toString());
				} else {
					return new StringEditor(name, null);
				}
			} else if (field.type == FieldType.INTEGER) {

				final int[] args = Arrays.stream(fieldArgs).mapToInt(o -> o instanceof Number ? ((Number) o).intValue() : Integer.parseInt(o.toString())).toArray();

				final int min = args.length < 1 ? Short.MIN_VALUE : args[0];
				return new IntegerEditor(name, min, args.length < 2 ? Short.MAX_VALUE : args[1], args.length < 3 ? Math.min(0, min) : args[2], args.length < 4 ? 1 : args[3]);
			} else if (field.type == FieldType.BOOLEAN) {
				return new BooleanEditor(name, false);
			} else if (field.type == FieldType.DICE_ROLL) {
				return new DiceRollEditor(name, new DiceRoll(0, 1));
			} else if (field.type == FieldType.LIST) {
				if (fieldArgs.length < 1 || !(fieldArgs[0] instanceof FieldType)) return new CollectionStringEditor(name, null);

				final FieldType subtype = (FieldType) fieldArgs[0];
				if (subtype.clazz.isEnum()) {
					return new CollectionEnumEditor(subtype.clazz, name, null);
				} else if (subtype == FieldType.STRING) {
					if (fieldArgs.length > 1) {
						return new CollectionStringEditor(name, fieldArgs[1].toString(), null);
					} else {
						return new CollectionStringEditor(name, null);
					}
				} else {
					return new CollectionStringEditor(name, null);
				}
			} else if (field.type == FieldType.MAP) {
				try {
					final FieldType keyType = fieldArgs[0] == null ? FieldType.STRING : (FieldType) fieldArgs[0];
					final String keyTitle = fieldArgs[1] == null ? null : fieldArgs[1].toString();
					final FieldType valueType = fieldArgs[2] == null ? FieldType.STRING : (FieldType) fieldArgs[2];
					final FieldType listType = valueType == FieldType.LIST ? fieldArgs[3] == null ? FieldType.STRING : (FieldType) fieldArgs[3] : null;
					final String valueTitle = fieldArgs[4] == null ? null : fieldArgs[4].toString();
					final boolean editable = fieldArgs[5] == null ? false : (Boolean) fieldArgs[5];

					if (keyType.clazz.isEnum() && valueType == FieldType.INTEGER) {
						final int[] args = Arrays.stream(fieldArgs).skip(6).mapToInt(o -> o instanceof Number ? ((Number) o).intValue() : Integer.parseInt(o.toString())).toArray();

						final int min = args.length < 1 ? Short.MIN_VALUE : args[0];
						final int max = args.length < 2 ? Short.MAX_VALUE : args[1];
						final int step = args.length < 3 ? 1 : args[2];
						if (editable) {
							return new EditableMapEnumIntegerEditor(keyType.clazz, name, keyTitle, valueTitle, min, max, step, null);
						} else {
							return new MapEnumIntegerEditor(keyType.clazz, name, keyTitle, valueTitle, min, max, step, null);
						}
					} else if (valueType == FieldType.LIST) {
						BiFunction keySupplier, valueSupplier;
						if (keyType == FieldType.INTEGER) {
							final int[] args = Arrays.stream(fieldArgs).skip(6).mapToInt(o -> o instanceof Number ? ((Number) o).intValue() : Integer.parseInt(o.toString())).toArray();

							final int min = args.length < 1 ? Short.MIN_VALUE : args[0];
							final int max = args.length < 2 ? Short.MAX_VALUE : args[1];
							final int step = args.length < 3 ? 1 : args[2];

							keySupplier = (BiFunction<String, Integer, Editor<Integer>>) (n, v) -> new IntegerEditor(n, min, max, v, step);
						} else if (keyType.clazz.isEnum()) {
							keySupplier = (BiFunction<String, Enum, Editor<Enum>>) (n, v) -> new EnumEditor(keyType.clazz, n, v);
						} else {
							keySupplier = (BiFunction<String, String, Editor<String>>) (n, v) -> new StringEditor(n, v);
						}

						if (listType.clazz.isEnum()) {
							valueSupplier = (BiFunction<String, Collection<Enum>, Editor<Collection<Enum>>>) (n, v) -> new CollectionEnumEditor(listType.clazz, n, v);
						} else {
							valueSupplier = (BiFunction<String, Collection<String>, Editor<Collection<String>>>) (n, v) -> new CollectionStringEditor(n, v);
						}

						return new EditableMapCollectionEditor(name, keyTitle, valueTitle, null, keySupplier, valueSupplier);
					}
				} catch (IndexOutOfBoundsException | ClassCastException | NullPointerException e) {
					return new EditableMapStringStringEditor(name, null, null, null);
				}
			}
		}

		return new StringEditor(name, null);
	}

}
