package dmscreen.data.creature;

public enum Condition {

	BLINDED(" \u2022  A blinded creature can't see and automatically fails any ability check that requires sight.", " \u2022  Attack rolls against the creature have advantage, and the creature's Attack rolls have disadvantage."),
	CHARMED(" \u2022  A charmed creature can't Attack the charmer or target the charmer with harmful Abilities or magical effects.", " \u2022  The charmer has advantage on any ability check to interact socially with the creature."),
	DEAFENED(" \u2022  A deafened creature can't hear and automatically fails any ability check that requires hearing."),
	EXHAUSTION("Some special abilities and environmental hazards, such as starvation and the long-term effects of freezing or scorching temperatures, can lead to a special condition called exhaustion. Exhaustion is measured in six levels. An effect can give a creature one or more levels of exhaustion, as specified in the effect's description.",
			"\n  Lv.\tEffect\n   1\tDisadvantage on ability checks\n   2\tSpeed halved\n   3\tDisadvantage on attack rolls and saving throws\n   4\tHit point maximum halved\n   5\tSpeed reduced to 0\n   6\tDeath\n", "If an already exhausted creature suffers another effect that causes exhaustion, its current level of exhaustion increases by the amount specified in the effect's description.",
			"A creature suffers the effect of its current level of exhaustion as well as all lower levels. For example, a creature suffering level 2 exhaustion has its speed halved and has disadvantage on ability checks.", "An effect that removes exhaustion reduces its level as specified in the effect's description, with all exhaustion effects ending if a creature's exhaustion level is reduced below 1.",
			"Finishing a long rest reduces a creature's exhaustion level by 1, provided that the creature has also ingested some food and drink."),
	FRIGHTENED(" \u2022  A frightened creature has disadvantage on Ability Checks and Attack rolls while the source of its fear is within line of sight.", " \u2022  The creature can't willingly move closer to the source of its fear."),
	GRAPPLED(" \u2022  A grappled creature's speed becomes 0, and it can't benefit from any bonus to its speed.", " \u2022  The condition ends if the Grappler is incapacitated (see the condition).", " \u2022  The condition also ends if an effect removes the grappled creature from the reach of the Grappler or Grappling effect, such as when a creature is hurled away by the Thunderwave spell."),
	INCAPACITATED(" \u2022  An incapacitated creature can't take actions or reactions."),
	INVISIBLE(" \u2022  An invisible creature is impossible to see without the aid of magic or a Special sense. For the purpose of Hiding, the creature is heavily obscured. The creature's location can be detected by any noise it makes or any tracks it leaves.", " \u2022  Attack rolls against the creature have disadvantage, and the creature's Attack rolls have advantage."),
	PARALYZED(" \u2022  A paralyzed creature is incapacitated (see the condition) and can't move or speak.", "The creature automatically fails Strength and Dexterity saving throws.", "Attack rolls against the creature have advantage.", " \u2022  Any Attack that hits the creature is a critical hit if the attacker is within 5 feet of the creature."),
	PETRIFIED(" \u2022  A petrified creature is transformed, along with any nonmagical object it is wearing or carrying, into a solid inanimate substance (usually stone). Its weight increases by a factor of ten, and it ceases aging.", " \u2022  The creature is incapacitated (see the condition), can't move or speak, and is unaware of its surroundings.", " \u2022  Attack rolls against the creature have advantage.", " \u2022  The creature automatically fails Strength and Dexterity saving throws.",
			" \u2022  The creature has Resistance to all damage.", " \u2022  The creature is immune to poison and disease, although a poison or disease already in its system is suspended, not neutralized."),
	POISONED(" \u2022  A poisoned creature has disadvantage on Attack rolls and Ability Checks."),
	PRONE(" \u2022  A prone creature's only Movement option is to crawl, unless it stands up and thereby ends the condition.", " \u2022  The creature has disadvantage on Attack rolls.", " \u2022  An Attack roll against the creature has advantage if the attacker is within 5 feet of the creature. Otherwise, the Attack roll has disadvantage."),
	RESTRAINED(" \u2022  A restrained creature's speed becomes 0, and it can't benefit from any bonus to its speed.", " \u2022  Attack rolls against the creature have advantage, and the creature's Attack rolls have disadvantage.", " \u2022  The creature has disadvantage on Dexterity saving throws."),
	STUNNED(" \u2022  A stunned creature is incapacitated (see the condition), can't move, and can speak only falteringly.", " \u2022  The creature automatically fails Strength and Dexterity saving throws.", " \u2022  Attack rolls against the creature have advantage."),
	UNCONSCIOUS(" \u2022  An unconscious creature is incapacitated (see the condition), can't move or speak, and is unaware of its surroundings.", " \u2022  The creature drops whatever it's holding and falls prone.", " \u2022  The creature drops whatever it's holding and falls prone.", " \u2022  The creature automatically fails Strength and Dexterity saving throws.", " \u2022  Attack rolls against the creature have advantage.",
			" \u2022  Any Attack that hits the creature is a critical hit if the attacker is within 5 feet of the creature.");

	public transient final String[] descriptions;

	private Condition(final String... descriptions) {
		this.descriptions = descriptions;
	}

}
