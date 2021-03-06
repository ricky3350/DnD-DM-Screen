package dmscreen.misc;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dmscreen.Screen;
import dmscreen.data.Data;
import dmscreen.data.adventure.CreatureSet;
import dmscreen.data.adventure.Encounter;
import dmscreen.data.adventure.RandomEncounter;
import dmscreen.data.base.Ability;
import dmscreen.data.base.DamageType;
import dmscreen.data.base.DiceRoll;
import dmscreen.data.base.Size;
import dmscreen.data.base.Skill;
import dmscreen.data.creature.Alignment;
import dmscreen.data.creature.Condition;
import dmscreen.data.creature.Creature;
import dmscreen.data.creature.CreatureType;
import dmscreen.data.creature.MovementType;
import dmscreen.data.creature.VisionType;
import dmscreen.data.creature.feature.Action;
import dmscreen.data.creature.feature.Attack;
import dmscreen.data.creature.feature.Attack.Type;
import dmscreen.data.creature.feature.Feature;
import dmscreen.data.creature.feature.InnateSpellcasting;
import dmscreen.data.creature.feature.LegendaryAction;
import dmscreen.data.creature.feature.Spellcasting;

public class DeadInThayGenerator {

	private DeadInThayGenerator() {}

	public static void main(final String[] args) {
		try {
			Files.createDirectories(Paths.get("resources/dead_in_thay/"));

			final List<Object> creatures = new ArrayList<>();
			for (final Field field : DeadInThayGenerator.class.getDeclaredFields()) {
				if (Modifier.isStatic(field.getModifiers()) && field.getType().isAssignableFrom(Creature.class)) {
					try {
						creatures.add(field.get(null));
					} catch (IllegalArgumentException | IllegalAccessException e) {}
				}
			}
			creatures.add(TestCreatures.zombie);
			creatures.add(TestCreatures.ogreZombie);
			creatures.add(TestCreatures.otyugh);
			creatures.add(TestCreatures.grick);
			creatures.add(TestCreatures.grell);
			creatures.add(TestCreatures.gibberingMouther);
			creatures.add(TestCreatures.orc);
			creatures.add(TestCreatures.gnoll);
			creatures.add(TestCreatures.troll);
			creatures.add(TestCreatures.wight);
			creatures.add(TestCreatures.skeleton);
			creatures.add(TestCreatures.behir);
			creatures.add(TestCreatures.peryton);
			creatures.add(TestCreatures.gelatinousCube);
			creatures.add(TestCreatures.efreeti);
			creatures.add(TestCreatures.gorgon);
			creatures.add(TestCreatures.helmedHorror);
			creatures.add(TestCreatures.blackPudding);
			creatures.add(TestCreatures.grayOoze);
			creatures.add(TestCreatures.ochreJelly);
			creatures.add(TestCreatures.specter);
			creatures.add(TestCreatures.sahuagin);
			creatures.add(TestCreatures.sahuaginBaron);
			creatures.add(TestCreatures.sahuaginPriestess);
			creatures.add(TestCreatures.hunterShark);
			creatures.add(TestCreatures.commoner);
			creatures.add(TestCreatures.ghoul);
			creatures.add(TestCreatures.stoneGiant);
			creatures.add(TestCreatures.vampire);
			creatures.add(TestCreatures.wraith);
			creatures.add(TestCreatures.succubusIncubus);
			creatures.add(TestCreatures.fireElemental);
			creatures.add(TestCreatures.quasit);
			creatures.add(TestCreatures.manes);
			creatures.add(TestCreatures.hezrou);
			creatures.add(TestCreatures.vrock);
			creatures.add(TestCreatures.glabrezu);
			creatures.add(TestCreatures.giantSpider);
			creatures.add(TestCreatures.giantCentipede);
			creatures.add(TestCreatures.remorhaz);
			creatures.add(TestCreatures.basilisk);
			creatures.add(TestCreatures.darkmantle);
			creatures.add(TestCreatures.ettercap);
			creatures.add(TestCreatures.carrionCrawler);
			creatures.add(TestCreatures.hookHorror);
			creatures.add(TestCreatures.wyvern);
			creatures.add(TestCreatures.blackDragonWyrmling);
			creatures.add(TestCreatures.dragonTurtle);
			creatures.add(TestCreatures.spiritNaga);
			creatures.add(TestCreatures.kuoToa);
			creatures.add(TestCreatures.merrow);
			creatures.add(TestCreatures.seaHag);
			creatures.add(TestCreatures.giantCrab);

			Files.write(Paths.get("resources/dead_in_thay/creatures.json"), Data.GSON.toJson(creatures).getBytes());

			final List<Object> encounters = new ArrayList<>();
			for (final Field field : DeadInThayGenerator.class.getDeclaredFields()) {
				if (Modifier.isStatic(field.getModifiers()) && field.getType().isAssignableFrom(RandomEncounter.class)) {
					try {
						encounters.add(field.get(null));
					} catch (IllegalArgumentException | IllegalAccessException e) {}
				}
			}

			Files.write(Paths.get("resources/dead_in_thay/encounters.json"), Data.GSON.toJson(encounters).getBytes());
		} catch (final IOException e) {
			e.printStackTrace();
		}

		Screen.main(args);
	}

	private static final RandomEncounter minor, dreadLegion, thayan, abyssalPrisons, bloodPens, mastersDomain, farRealmCysts, forestsOfSlaughter, oozeGrottos, predatorPools, golemLabs;

	private static final Creature thalaxia, aboleth, unarmedSkeleton, youngOtyugh, thayanWarrior, thayanApprentice, evoker, dreadWarrior, necromancer, deathlockWight, wight31, choker, leucrotta, perytonYoung, transmuter, //
			vampiricMist, fourArmedGargoyle, conjurer, lumalia, barghest, sentientOchreJelly, sentientGrayOoze, whiteMaw, oozeMaster, elderBlackPudding, reducedHelmedHorror, reducedFleshGolem, gorvanIronheart, reducedClayGolem, //
			reducedStoneGolem, cockatrice, shalendraFloshin, reducedHezrou, reducedVrock, tarulVar, sorlan, drevin, reducedBasilisk, reducedDarkmantle, reducedEttercap, reducedCarrionCrawler, reducedBehir, //
			reducedHookHorror, reducedWyvern, enchanter, malformedKraken, reducedDragonTurtle;

	static {
		minor = new RandomEncounter();
		minor.name = "Minor Encounter";
		minor.encounters.add(new Encounter(1, new CreatureSet("Thayan apprentice"), new CreatureSet(new DiceRoll(4, 1), "Thayan warrior", "Thayan warriors"), new CreatureSet(new DiceRoll(2, 4), "commoner", "prisoners (commoners)")));
		minor.encounters.add(new Encounter(2, new CreatureSet("wight")));
		final Map<String, String> skeletonZombie = new HashMap<>();
		skeletonZombie.put("skeleton", "skeletons");
		skeletonZombie.put("zombie", "zombies");
		minor.encounters.add(new Encounter(1, new CreatureSet("wight"), new CreatureSet(new DiceRoll(2, 4), skeletonZombie)));

		dreadLegion = new RandomEncounter();
		dreadLegion.name = "Dread Legion Patrol";
		dreadLegion.encounters.add(new Encounter(1, new CreatureSet(new DiceRoll(2, 4), "gnoll", "gnolls")));
		dreadLegion.encounters.add(new Encounter(1, new CreatureSet("dread warrior"), new CreatureSet(new DiceRoll(2, 6), "zombie", "zombies")));
		dreadLegion.encounters.add(new Encounter(1, new CreatureSet(new DiceRoll(2, 4), "orc", "orcs")));
		dreadLegion.encounters.add(new Encounter(1, new CreatureSet("troll")));

		thayan = new RandomEncounter();
		thayan.name = "Thayan Patrol";
		thayan.encounters.add(new Encounter(1, new CreatureSet("deathlock wight"), new CreatureSet(new DiceRoll(2, 4), "thayan warrior", "Thayan warriors"), new CreatureSet(new DiceRoll(2, 4), "commoner", "prisoners (commoners)")));
		thayan.encounters.add(new Encounter(2, new CreatureSet("deathlock wight"), new CreatureSet(new DiceRoll(1, 3), "thayan apprentice", "Thayan apprentices"), new CreatureSet(new DiceRoll(2, 4), "thayan warrior", "Thayan warriors")));
		thayan.encounters.add(new Encounter(2, new CreatureSet("evoker", "Red Wizard evoker"), new CreatureSet("thayan apprentice"), new CreatureSet(new DiceRoll(1, 4), "dread warrior", "dread warriors")));
		thayan.encounters.add(new Encounter(2, new CreatureSet("wight"), new CreatureSet(new DiceRoll(1, 4), "dread warrior", "dread warriors")));
		thayan.diceRoll = new DiceRoll(2, 4);

		final CreatureSet accompanyingApprentice = new CreatureSet("thayan apprentice");
		final CreatureSet accompanyingWarriors = new CreatureSet(new DiceRoll(2, 4), "thayan warrior", "thayan warriors");

		abyssalPrisons = new RandomEncounter();
		abyssalPrisons.name = "Sector: Abyssal Prisons";
		abyssalPrisons.encounters.add(new Encounter(1, new CreatureSet("reduced-threat hezrou"), accompanyingApprentice, accompanyingWarriors));
		abyssalPrisons.encounters.add(new Encounter(1, new CreatureSet(new DiceRoll(2, 8), "manes"), accompanyingApprentice, accompanyingWarriors));
		abyssalPrisons.encounters.add(new Encounter(1, new CreatureSet(new DiceRoll(1, 4), "quasit", "quasits"), accompanyingApprentice, accompanyingWarriors));
		abyssalPrisons.encounters.add(new Encounter(1, new CreatureSet("reduced-threat vrock"), accompanyingApprentice, accompanyingWarriors));

		bloodPens = new RandomEncounter();
		bloodPens.name = "Sector: Blood Pens";
		bloodPens.encounters.add(new Encounter(1, new CreatureSet(new DiceRoll(2, 6), "giant centipede", "giant centipedes")));
		bloodPens.encounters.add(new Encounter(1, new CreatureSet(new DiceRoll(2, 6), "giant spider", "giant spiders")));
		bloodPens.encounters.add(new Encounter(1, "Thayan Patrol (roll on the Thayan Patrol table)"));
		bloodPens.encounters.add(new Encounter(1, new CreatureSet("shambling mound"), accompanyingApprentice, accompanyingWarriors));

		mastersDomain = new RandomEncounter();
		mastersDomain.name = "Sector: Master's Domain";
		mastersDomain.encounters.add(new Encounter(1, new CreatureSet(new DiceRoll(1, 4), "shadow", "shadows")));
		mastersDomain.encounters.add(new Encounter(3, "Dread Legion Patrol (roll on the Dread Legion Patrol table)"));

		farRealmCysts = new RandomEncounter();
		farRealmCysts.name = "Sector: Far Realm Cysts";
		farRealmCysts.encounters.add(new Encounter(1, new CreatureSet("gibbering mouther"), accompanyingApprentice, accompanyingWarriors));
		farRealmCysts.encounters.add(new Encounter(1, new CreatureSet("grell"), accompanyingApprentice, accompanyingWarriors));
		farRealmCysts.encounters.add(new Encounter(1, new CreatureSet(new DiceRoll(1, 4), "grick", "gricks"), accompanyingApprentice, accompanyingWarriors));
		farRealmCysts.encounters.add(new Encounter(1, new CreatureSet("otyugh"), accompanyingApprentice, accompanyingWarriors));

		forestsOfSlaughter = new RandomEncounter();
		forestsOfSlaughter.name = "Sector: Forests of Slaughter";
		forestsOfSlaughter.encounters.add(new Encounter(1, new CreatureSet(new DiceRoll(1, 4), "hook horror", "hook horrors"), accompanyingApprentice, accompanyingWarriors));
		forestsOfSlaughter.encounters.add(new Encounter(1, new CreatureSet(new DiceRoll(1, 4), "cockatrice", "cockatrices"), accompanyingApprentice, accompanyingWarriors));
		forestsOfSlaughter.encounters.add(new Encounter(1, new CreatureSet("displacer beast"), accompanyingApprentice, accompanyingWarriors));
		forestsOfSlaughter.encounters.add(new Encounter(1, new CreatureSet("troll"), accompanyingApprentice, accompanyingWarriors));

		oozeGrottos = new RandomEncounter();
		oozeGrottos.name = "Sector: Ooze Grottos";
		oozeGrottos.encounters.add(new Encounter(1, new CreatureSet("black pudding"), accompanyingApprentice, accompanyingWarriors));
		oozeGrottos.encounters.add(new Encounter(1, new CreatureSet("gelatinous cube"), accompanyingApprentice, accompanyingWarriors));
		oozeGrottos.encounters.add(new Encounter(2, new CreatureSet(new DiceRoll(1, 4), "gray ooze", "gray oozes"), accompanyingApprentice, accompanyingWarriors));

		predatorPools = new RandomEncounter();
		predatorPools.name = "Sector: Predator Pools";
		predatorPools.encounters.add(new Encounter(1, new CreatureSet(new DiceRoll(2, 4), "giant crab", "giant crabs"), accompanyingApprentice, accompanyingWarriors));
		predatorPools.encounters.add(new Encounter(1, new CreatureSet(new DiceRoll(2, 4), "kuo-toa")));
		predatorPools.encounters.add(new Encounter(1, new CreatureSet(new DiceRoll(1, 2), "merrow", "merrow (in water globes)"), accompanyingApprentice, accompanyingWarriors));
		predatorPools.encounters.add(new Encounter(1, new CreatureSet("troll"), accompanyingApprentice, accompanyingWarriors));

		golemLabs = new RandomEncounter();
		golemLabs.name = "Sector: Golem Laboratories";
		golemLabs.encounters.add(new Encounter(1, new CreatureSet("reduced-threat flesh golem"), accompanyingApprentice, accompanyingWarriors));
		golemLabs.encounters.add(new Encounter(1, new CreatureSet("flesh golem"), accompanyingApprentice, accompanyingWarriors));
		golemLabs.encounters.add(new Encounter(1, new CreatureSet("reduced-threat clay golem"), accompanyingApprentice, accompanyingWarriors));
		golemLabs.encounters.add(new Encounter(1, new CreatureSet("clay golem"), accompanyingApprentice, accompanyingWarriors));

		thalaxia = new Creature();
		thalaxia.name = "Thalaxia, the Beholder";
		thalaxia.shortName = "Thalaxia";
		thalaxia.size = Size.LARGE;
		thalaxia.type = CreatureType.ABERRATION;
		thalaxia.alignment = Alignment.LAWFUL_EVIL;
		thalaxia.ac = 18;
		thalaxia.armorNote = "natural armor";
		thalaxia.hitDice = new DiceRoll(19, 10, 76);
		thalaxia.speed = 0;
		thalaxia.speeds.put(MovementType.FLY, 20);
		thalaxia.abilityScores.put(Ability.STRENGTH, 10);
		thalaxia.abilityScores.put(Ability.DEXTERITY, 14);
		thalaxia.abilityScores.put(Ability.CONSTITUTION, 18);
		thalaxia.abilityScores.put(Ability.INTELLIGENCE, 17);
		thalaxia.abilityScores.put(Ability.WISDOM, 15);
		thalaxia.abilityScores.put(Ability.CHARISMA, 17);
		thalaxia.savingThrows.put(Ability.INTELLIGENCE, 8);
		thalaxia.savingThrows.put(Ability.WISDOM, 7);
		thalaxia.savingThrows.put(Ability.CHARISMA, 8);
		thalaxia.skills.put(Skill.PERCEPTION, 12);
		thalaxia.conditionImmunities.add(Condition.PRONE);
		thalaxia.senses.put(VisionType.DARKVISION, 120);
		thalaxia.languages.add("Deep Speech");
		thalaxia.languages.add("Undercommon");
		thalaxia.challengeRating = 13;
		thalaxia.features = Arrays.asList(new Feature("Antimagic Cone", "Thalaxia's central eye creates an area of antimagic, as in the [antimagic field] spell, in a 150 foot cone. At the start of each of its turns, Thalaxia decides which way the cone faces and whether the cone is active. The cone works against Thalaxia's own eye rays."));
		thalaxia.actions = Arrays.asList(new Attack("Bite", Type.MELEE_WEAPON, 5, "reach 5 ft., one target", null, new Attack.Damage(new DiceRoll(4, 6), DamageType.PIERCING)), //
				new Action("Eye Rays",
						"Thalaxia shoots three of the following magical eye rays at random (reroll duplicates), choosing one to three targets it can see within 120 feet of it:\n\n    _1. Charm Ray._ The targeted creature must succeed on a DC 16 Wisdom saving throw or be charmed by Thalaxia for 1 hour, or until Thalaxia harms the creature.\n    _2. Paralyzing Ray._ The target must succeed on a DC 16 Constitution saving throw or be paralyzed for 1 minute. The target can repeat the saving throw at the end of each of its turns, ending the effect on itself on a success.\n    _3. Fear Ray._ The targeted creature must succeed on a DC 16 Wisdom saving throw or be frightened for 1 minute. The target can repeat the saving throw at the end of each of its turns, ending the effect on itself on a success.\n    _4. Slowing Ray._ The targeted creature must make a DC 16 Dexterity saving throw. On a failed save, the target's speed is halved for 1 minute. In addition, the creature can't take reactions, and it can take either an action or a bonus action on its turn, but not both. The creature can repeat the saving throw at the end of each of its turns, ending the effect on itself on the success.\n    _5. Enervation Ray._ The targeted creature must make a DC 16 Constitution saving throw, taking 36 (8d8) necrotic damage on a failed save, or half as much damage on a sucessful one.\n    _6. Telekinetic Ray._ If the target is a creature, it must succeed on a DC 16 Strength saving throw, or the beholder moves it up to 30 feet in any direction. It is restrained by the beholder's telekinetic grip until the start of the beholder's next turn or until the beholder is incapactiated.\nIf the target is an object weighting less than 300 pounds or less that isn't being worn or carried, it is moved up to 30 feet in any direction. The beholder can also exert fine control over objects, such as manipulating a simple tool or opening a door or a container.\n    _7. Sleep Ray._ The targeted creature must succeed on a DC 16 Wisdom saving throw or fall asleep and remain unconscious for 1 minute. The target awakens if it takes any damage or another creature takes an action to wake it. This ray has no effect on constructs and undead.\n    _8. Petrification Ray._ The targeted creature must make a DC 16 Dexterity saving throw. On a failed save, the creature begins to turn to stone, and is restrained. It must make the saving throw at the end of its next turn. On a success, the effect ends. On a failure, the creature is petrified until freed by the [greater restoration] spell or other magic."));
		thalaxia.legendaryActions = Arrays.asList(new LegendaryAction("Eye Ray", "Thalaxia uses one random eye ray."));

		aboleth = new Creature();
		aboleth.name = "Aboleth (Reduced Threat)";
		aboleth.shortName = "The aboleth";
		aboleth.size = Size.LARGE;
		aboleth.type = CreatureType.ABERRATION;
		aboleth.alignment = Alignment.LAWFUL_EVIL;
		aboleth.ac = 17;
		aboleth.armorNote = "natural armor";
		aboleth.hitDice = new DiceRoll(16, 10, 16);
		aboleth.speed = 10;
		aboleth.speeds.put(MovementType.SWIM, 40);
		aboleth.abilityScores.put(Ability.STRENGTH, 19);
		aboleth.abilityScores.put(Ability.DEXTERITY, 7);
		aboleth.abilityScores.put(Ability.CONSTITUTION, 13);
		aboleth.abilityScores.put(Ability.INTELLIGENCE, 16);
		aboleth.abilityScores.put(Ability.WISDOM, 13);
		aboleth.abilityScores.put(Ability.CHARISMA, 16);
		aboleth.savingThrows.put(Ability.CONSTITUTION, 4);
		aboleth.savingThrows.put(Ability.INTELLIGENCE, 6);
		aboleth.savingThrows.put(Ability.WISDOM, 4);
		aboleth.skills.put(Skill.HISTORY, 9);
		aboleth.skills.put(Skill.PERCEPTION, 7);
		aboleth.senses.put(VisionType.DARKVISION, 120);
		aboleth.languages.add("Deep Speech");
		aboleth.languages.add("telepathy 120 ft.");
		aboleth.challengeRating = 8;
		aboleth.features = Arrays.asList(new Feature("Amphibious", "The aboleth can breathe both air and water."), //
				new Feature("Mucous Cloud", "While underwater, the aboleth is surrounded by transformative mucus. A creature that touches the aboleth or that hits it with a melee attack while withi n 5 feet of it must make a DC 14 Constitution saving throw. On a failure, the creature is diseased for 1d4 hours. The diseased creature can breathe only underwater."), //
				new Feature("Probing Telepathy", "If a creature communicates telepathically with the aboleth, the aboleth learns the creature's greatest desires if the aboleth can see the creature."));
		aboleth.actions = Arrays.asList(new Action("Multiattack", "The aboleth makes three tentacle attacks."), //
				new Attack("Tentacle", Type.MELEE_WEAPON, 7, "reach 10 ft., one target",
						". If the target is a creature, it must succeed on a DC 14 Constitution saving throw or become diseased. The disease has no effect for 1 minute and can be removed by any effect that cures disease. After 1 minute, the creature's skin becomes translucent and slimy, the creature can't regain hit points unless it is underwater, and the disease can only be removed by heal or another disease-curing spell of 6th level or higher. When the creature is outside a body of water, it takes 6 (1d12) acid damage every 10 minutes unless moisture is applied to the skin before 10 minutes have passed.",
						new Attack.Damage(new DiceRoll(2, 6, 4), DamageType.BLUDGEONING)), //
				new Attack("Tail", Type.MELEE_WEAPON, 7, "reach 10 ft., one target", null, new Attack.Damage(new DiceRoll(3, 6, 4), DamageType.BLUDGEONING)), //
				new Action("Enslave", "3/Day",
						"The aboleth targets one creature it can see within 30 feet of it. The target must succeed on a DC 14 Wisdom saving throw or be magically charmed by the aboleth until the aboleth dies or until it is on a different plane of existence from the target. The charmed target is under the aboleth's control and can't take reactions, and the aboleth and the target can communicate telepathically with each other over any distance.\n    Whenever the charmed target takes damage, the target can repeat the saving throw. On a success, the effect ends. No more than once every 24 hours, the target can also repeat the saving throw when it is at least 1 mile away from the aboleth."));
		aboleth.legendaryActions = Arrays.asList(new LegendaryAction("Detect", "The aboleth makes a Wisdom (Perception) check."), //
				new LegendaryAction("Tail Swipe", "The aboleth makes one tail attack."), //
				new LegendaryAction("Psychic Drain", "One creature charmed by the aboleth takes 10 (3d6) psychic damage, and the aboleth regains hit points equal to the damage the creature takes."));

		unarmedSkeleton = new Creature();
		unarmedSkeleton.name = "Skeleton (Unarmed)";
		unarmedSkeleton.shortName = "The skeleton";
		unarmedSkeleton.size = Size.MEDIUM;
		unarmedSkeleton.type = CreatureType.UNDEAD;
		unarmedSkeleton.alignment = Alignment.LAWFUL_EVIL;
		unarmedSkeleton.ac = 13;
		unarmedSkeleton.armorNote = "armor scraps";
		unarmedSkeleton.hitDice = new DiceRoll(2, 8, 4);
		unarmedSkeleton.speed = 30;
		unarmedSkeleton.abilityScores.put(Ability.STRENGTH, 10);
		unarmedSkeleton.abilityScores.put(Ability.DEXTERITY, 14);
		unarmedSkeleton.abilityScores.put(Ability.CONSTITUTION, 15);
		unarmedSkeleton.abilityScores.put(Ability.INTELLIGENCE, 6);
		unarmedSkeleton.abilityScores.put(Ability.WISDOM, 8);
		unarmedSkeleton.abilityScores.put(Ability.CHARISMA, 5);
		unarmedSkeleton.vulnerabilities.put(null, new HashSet<>(Arrays.asList(DamageType.BLUDGEONING)));
		unarmedSkeleton.immunities.put(null, new HashSet<>(Arrays.asList(DamageType.POISON)));
		unarmedSkeleton.conditionImmunities.add(Condition.POISONED);
		unarmedSkeleton.conditionImmunities.add(Condition.EXHAUSTION);
		unarmedSkeleton.senses.put(VisionType.DARKVISION, 60);
		unarmedSkeleton.languages.add("understands all languages it knew in life but can't speak");
		unarmedSkeleton.challengeRating = -2;
		unarmedSkeleton.features = Arrays.asList(new Feature("Reassembly", "If damage of any type besides radiant damage reduces the skeleton to 0 hit points, it reassembles in 1d6 + 4 rounds, returning to life at its hit point maximum. It has no memory of its past fate."));
		unarmedSkeleton.actions = Arrays.asList(new Attack("Unarmed Strike", Type.MELEE_WEAPON, 2, "reach 5 ft., one target", null, new Attack.Damage(new DiceRoll(1, 1, 0), DamageType.BLUDGEONING)));

		youngOtyugh = new Creature();
		youngOtyugh.name = "Otyugh (Young)";
		youngOtyugh.shortName = "The otyugh";
		youngOtyugh.size = Size.MEDIUM;
		youngOtyugh.type = CreatureType.ABERRATION;
		youngOtyugh.alignment = Alignment.NEUTRAL;
		youngOtyugh.ac = 14;
		youngOtyugh.armorNote = "natural armor";
		youngOtyugh.hitDice = new DiceRoll(6, 10, 18);
		youngOtyugh.speed = 30;
		youngOtyugh.abilityScores.put(Ability.STRENGTH, 14);
		youngOtyugh.abilityScores.put(Ability.DEXTERITY, 9);
		youngOtyugh.abilityScores.put(Ability.CONSTITUTION, 17);
		youngOtyugh.abilityScores.put(Ability.INTELLIGENCE, 4);
		youngOtyugh.abilityScores.put(Ability.WISDOM, 11);
		youngOtyugh.abilityScores.put(Ability.CHARISMA, 4);
		youngOtyugh.savingThrows.put(Ability.CONSTITUTION, 5);
		youngOtyugh.senses.put(VisionType.DARKVISION, 120);
		youngOtyugh.languages.add("Otyugh");
		youngOtyugh.challengeRating = 3;
		youngOtyugh.features = Arrays.asList(new Feature("Limited Telepathy", "The otyugh can magically transmit simple messages and images to any creature within 120 feet of it that can understand a language. This form of telepathy doesn't allow the receiving creature to telepathically respond."));
		youngOtyugh.actions = Arrays.asList(new Action("Multiattack", "The otyugh makes three attacks: one with its bite, and two with its tentacles"), //
				new Attack("Bite", Type.MELEE_WEAPON, 4, "reach 5 ft., one target",
						". If the target is a creature, it must succeed on a DC 13 Constitution saving throw against disease or become poisoned until the disease is cured. Every 24 hours that elapse, the target must repeat the saving throw, reducing its hit point maximum by 5 (1d10) on a failure. The disease is cured on a success. The target dies if the disease reduces its hit point maximum to 0. This reduction to the target's hit point maximum lasts until the disease is cured.",
						new Attack.Damage(new DiceRoll(2, 8, 2), DamageType.PIERCING)), //
				new Attack("Tentacle", Type.MELEE_WEAPON, 4, "reach 10 ft., one target", null, new Attack.Damage(new DiceRoll(1, 8, 2), DamageType.BLUDGEONING), new Attack.Damage(new DiceRoll(1, 8), DamageType.PIERCING)));

		thayanWarrior = new Creature();
		thayanWarrior.name = "Thayan Warrior";
		thayanWarrior.shortName = "The warrior";
		thayanWarrior.size = Size.MEDIUM;
		thayanWarrior.type = CreatureType.HUMANOID;
		thayanWarrior.subtype = "human";
		thayanWarrior.alignment = Alignment.LAWFUL_EVIL;
		thayanWarrior.ac = 16;
		thayanWarrior.armorNote = "chain shirt, shield";
		thayanWarrior.hitDice = new DiceRoll(8, 8, 16);
		thayanWarrior.speed = 30;
		thayanWarrior.abilityScores.put(Ability.STRENGTH, 16);
		thayanWarrior.abilityScores.put(Ability.DEXTERITY, 13);
		thayanWarrior.abilityScores.put(Ability.CONSTITUTION, 14);
		thayanWarrior.abilityScores.put(Ability.INTELLIGENCE, 10);
		thayanWarrior.abilityScores.put(Ability.WISDOM, 11);
		thayanWarrior.abilityScores.put(Ability.CHARISMA, 11);
		thayanWarrior.skills.put(Skill.PERCEPTION, 2);
		thayanWarrior.languages.add("Common");
		thayanWarrior.languages.add("Thayan");
		thayanWarrior.challengeRating = 2;
		thayanWarrior.features = Arrays.asList(new Feature("Doomvault Devotion", "Within the Doomvault, the warrior has advantage on saving throws against being charmed or frightened."), //
				new Feature("Pack Tactics", "The warrior has advantage on an attack roll if at least one of the warrior's allies is within 5 feet of the creature and the ally isn't incapacitated."));
		thayanWarrior.actions = Arrays.asList(new Action("Multiattack", "The warrior makes two melee attacks"), //
				new Attack("Longsword", Type.MELEE_WEAPON, 5, "reach 5 ft., one target", ", or 8 (1d10 + 3) slashing damage if used with two hands.", new Attack.Damage(new DiceRoll(1, 8, 3), DamageType.SLASHING)), //
				new Attack("Javelin", Type.MELEE_OR_RANGED_WEAPON, 5, "reach 5 ft. or range 30/120 ft., one target", null, new Attack.Damage(new DiceRoll(1, 6, 3), DamageType.PIERCING)));

		thayanApprentice = new Creature();
		thayanApprentice.name = "Thayan Apprentice";
		thayanApprentice.shortName = "The apprentice";
		thayanApprentice.size = Size.MEDIUM;
		thayanApprentice.type = CreatureType.HUMANOID;
		thayanApprentice.subtype = "human";
		thayanApprentice.alignment = Alignment.LAWFUL_EVIL;
		thayanApprentice.ac = 12;
		thayanApprentice.armorNote = "15 with mage armor";
		thayanApprentice.hitDice = new DiceRoll(5, 8, 5);
		thayanApprentice.speed = 30;
		thayanApprentice.abilityScores.put(Ability.STRENGTH, 10);
		thayanApprentice.abilityScores.put(Ability.DEXTERITY, 14);
		thayanApprentice.abilityScores.put(Ability.CONSTITUTION, 12);
		thayanApprentice.abilityScores.put(Ability.INTELLIGENCE, 15);
		thayanApprentice.abilityScores.put(Ability.WISDOM, 13);
		thayanApprentice.abilityScores.put(Ability.CHARISMA, 11);
		thayanApprentice.skills.put(Skill.ARCANA, 4);
		thayanApprentice.languages.add("Common");
		thayanApprentice.languages.add("Thayan");
		thayanApprentice.challengeRating = 2;

		final Map<String, Map<String, String>> apprenticeSpells = new LinkedHashMap<>();

		final Map<String, String> apprenticeCantrips = new HashMap<>();
		Stream.of("fire bolt", "mage hand", "prestidigitation", "shocking grasp").forEach(s -> apprenticeCantrips.put(s, ""));
		apprenticeSpells.put("Cantrips (at will)", apprenticeCantrips);

		final Map<String, String> apprenticeLevel1 = new HashMap<>();
		Stream.of("burning hands", "detect magic", "mage armor", "shield").forEach(s -> apprenticeLevel1.put(s, ""));
		apprenticeSpells.put("1st level (4 slots)", apprenticeLevel1);

		final Map<String, String> apprenticeLevel2 = new HashMap<>();
		Stream.of("blur", "scorching ray").forEach(s -> apprenticeLevel2.put(s, ""));
		apprenticeSpells.put("2nd level (3 slots)", apprenticeLevel2);

		thayanApprentice.features = Arrays.asList(new Feature("Doomvault Devotion", "Within the Doomvault, the apprentice has advantage on saving throws against being charmed or frightened."), //
				new Spellcasting(null, "the apprentice", 4, Ability.INTELLIGENCE, 12, "its", 4, null, "wizard", null, apprenticeSpells));
		thayanApprentice.actions = Arrays.asList(new Attack("Dagger", Type.MELEE_OR_RANGED_WEAPON, 4, "reach 5 ft. or range 20/60 ft., one target", null, new Attack.Damage(new DiceRoll(1, 4, 2), DamageType.PIERCING)));

		evoker = new Creature();
		evoker.name = "Evoker";
		evoker.shortName = "The evoker";
		evoker.size = Size.MEDIUM;
		evoker.type = CreatureType.HUMANOID;
		evoker.subtype = "any race";
		evoker.alignment = Alignment.LAWFUL_EVIL;
		evoker.ac = 12;
		evoker.armorNote = "15 with mage armor";
		evoker.hitDice = new DiceRoll(12, 8, 12);
		evoker.speed = 30;
		evoker.abilityScores.put(Ability.STRENGTH, 9);
		evoker.abilityScores.put(Ability.DEXTERITY, 14);
		evoker.abilityScores.put(Ability.CONSTITUTION, 12);
		evoker.abilityScores.put(Ability.INTELLIGENCE, 17);
		evoker.abilityScores.put(Ability.WISDOM, 12);
		evoker.abilityScores.put(Ability.CHARISMA, 11);
		evoker.savingThrows.put(Ability.INTELLIGENCE, 7);
		evoker.savingThrows.put(Ability.WISDOM, 5);
		evoker.skills.put(Skill.ARCANA, 7);
		evoker.skills.put(Skill.HISTORY, 7);
		evoker.languages.add("Common");
		evoker.languages.add("Thayan");
		evoker.challengeRating = 9;

		final Map<String, Map<String, String>> evokerSpells = new LinkedHashMap<>();

		final Map<String, String> evokerCantrips = new HashMap<>();
		Stream.of("fire bolt*", "light*", "prestidigitation", "shocking grasp*").forEach(s -> evokerCantrips.put(s, ""));
		evokerSpells.put("Cantrips (at will)", evokerCantrips);

		final Map<String, String> evokerLevel1 = new HashMap<>();
		Stream.of("burning hands*", "mage armor", "magic missile*").forEach(s -> evokerLevel1.put(s, ""));
		evokerSpells.put("1st level (4 slots)", evokerLevel1);

		final Map<String, String> evokerLevel2 = new HashMap<>();
		Stream.of("mirror image", "misty step", "shatter*").forEach(s -> evokerLevel2.put(s, ""));
		evokerSpells.put("2nd level (3 slots)", evokerLevel2);

		final Map<String, String> evokerLevel3 = new HashMap<>();
		Stream.of("counterspell", "fireball*", "lightning bolt*").forEach(s -> evokerLevel3.put(s, ""));
		evokerSpells.put("3rd level (3 slots)", evokerLevel3);

		final Map<String, String> evokerLevel4 = new HashMap<>();
		Stream.of("ice storm*", "stoneskin").forEach(s -> evokerLevel4.put(s, ""));
		evokerSpells.put("4th level (3 slots)", evokerLevel4);

		final Map<String, String> evokerLevel5 = new HashMap<>();
		Stream.of("Bigby's hand*", "cone of cold*").forEach(s -> evokerLevel5.put(s, ""));
		evokerSpells.put("5th level (2 slots)", evokerLevel5);

		final Map<String, String> evokerLevel6 = new HashMap<>();
		Stream.of("chain lightning*", "wall of ice*").forEach(s -> evokerLevel6.put(s, ""));
		evokerSpells.put("6th level (1 slot)", evokerLevel6);

		evoker.features = Arrays.asList(new Spellcasting(null, "the evoker", 12, Ability.INTELLIGENCE, 15, "its", 7, null, "wizard", null, evokerSpells), //
				new Feature("Sculpt Spells", "When the evoker casts an evocation spell (marked above with *) that forces other creatures it can see to make a saving throw, it can choose a number of them equal to 1 + the spell's level. These creatures automatically succeed on their saving throws against the spell. If a successful save means a chosen creature would take half damage from the spell, it instead takes no damage from it."));
		evoker.actions = Arrays.asList(new Attack("Dagger", Type.MELEE_OR_RANGED_WEAPON, 4, "reach 5 ft. or range 20/60 ft., one target", null, new Attack.Damage(new DiceRoll(1, 4, 2), DamageType.PIERCING)));

		dreadWarrior = new Creature();
		dreadWarrior.name = "Dread Warrior";
		dreadWarrior.shortName = "The dread warrior";
		dreadWarrior.size = Size.MEDIUM;
		dreadWarrior.type = CreatureType.UNDEAD;
		dreadWarrior.alignment = Alignment.NEUTRAL_EVIL;
		dreadWarrior.ac = 18;
		dreadWarrior.armorNote = "chain mail, shield";
		dreadWarrior.hitDice = new DiceRoll(5, 8, 15);
		dreadWarrior.speed = 30;
		dreadWarrior.abilityScores.put(Ability.STRENGTH, 15);
		dreadWarrior.abilityScores.put(Ability.DEXTERITY, 11);
		dreadWarrior.abilityScores.put(Ability.CONSTITUTION, 16);
		dreadWarrior.abilityScores.put(Ability.INTELLIGENCE, 10);
		dreadWarrior.abilityScores.put(Ability.WISDOM, 12);
		dreadWarrior.abilityScores.put(Ability.CHARISMA, 10);
		dreadWarrior.savingThrows.put(Ability.WISDOM, 3);
		dreadWarrior.skills.put(Skill.ATHLETICS, 4);
		dreadWarrior.skills.put(Skill.PERCEPTION, 3);
		dreadWarrior.immunities.put(null, new HashSet<>(Arrays.asList(DamageType.POISON)));
		dreadWarrior.conditionImmunities.add(Condition.POISONED);
		dreadWarrior.conditionImmunities.add(Condition.EXHAUSTION);
		dreadWarrior.senses.put(VisionType.DARKVISION, 60);
		dreadWarrior.languages.add("Common");
		dreadWarrior.challengeRating = 1;
		dreadWarrior.features = Arrays.asList(new Feature("Undead Fortitude", "If damage reduces the dread warrior to 0 hit points, it must make a Constitution saving throw with a DC of 5 + the damage taken, unless the damage is radiant or from a critcal hit. On a success, the dread warrior drops to 1 hit point instead."));
		dreadWarrior.actions = Arrays.asList(new Action("Multiattack", "The dread warrior makes two melee attacks."), //
				new Attack("Battleaxe", Type.MELEE_WEAPON, 4, "reach 5 ft., one target", ", or 7 (1d10 + 2) slashing damage if wielded with two hands.", new Attack.Damage(new DiceRoll(1, 8, 2), DamageType.SLASHING)), //
				new Attack("Javelin", Type.MELEE_OR_RANGED_WEAPON, 4, "reach 5 ft. or range 30/120 ft., one target", null, new Attack.Damage(new DiceRoll(1, 6, 2), DamageType.PIERCING)));

		necromancer = new Creature();
		necromancer.name = "Necromancer";
		necromancer.shortName = "The necromancer";
		necromancer.size = Size.MEDIUM;
		necromancer.type = CreatureType.HUMANOID;
		necromancer.subtype = "any race";
		necromancer.alignment = Alignment.ANY_ALIGNMENT;
		necromancer.ac = 12;
		necromancer.armorNote = "15 with mage armor";
		necromancer.hitDice = new DiceRoll(12, 8, 12);
		necromancer.speed = 30;
		necromancer.abilityScores.put(Ability.STRENGTH, 9);
		necromancer.abilityScores.put(Ability.DEXTERITY, 14);
		necromancer.abilityScores.put(Ability.CONSTITUTION, 12);
		necromancer.abilityScores.put(Ability.INTELLIGENCE, 17);
		necromancer.abilityScores.put(Ability.WISDOM, 12);
		necromancer.abilityScores.put(Ability.CHARISMA, 11);
		necromancer.savingThrows.put(Ability.INTELLIGENCE, 7);
		necromancer.savingThrows.put(Ability.WISDOM, 5);
		necromancer.skills.put(Skill.ARCANA, 7);
		necromancer.skills.put(Skill.HISTORY, 7);
		necromancer.resistances.put(null, new HashSet<>(Arrays.asList(DamageType.NECROTIC)));
		necromancer.languages.add("any four languages");
		necromancer.challengeRating = 9;

		final Map<String, Map<String, String>> necromancerSpells = new LinkedHashMap<>();

		final Map<String, String> necromancerCantrips = new HashMap<>();
		Stream.of("chill touch", "dancing lights", "mage hand", "mending").forEach(s -> necromancerCantrips.put(s, ""));
		necromancerSpells.put("Cantrips (at will)", necromancerCantrips);

		final Map<String, String> necromancerLevel1 = new HashMap<>();
		Stream.of("false life*", "mage armor", "ray of sickness*").forEach(s -> necromancerLevel1.put(s, ""));
		necromancerSpells.put("1st level (4 slots)", necromancerLevel1);

		final Map<String, String> necromancerLevel2 = new HashMap<>();
		Stream.of("blindness/deafness*", "ray of enfeeblement*", "web").forEach(s -> necromancerLevel2.put(s, ""));
		necromancerSpells.put("2nd level (3 slots)", necromancerLevel2);

		final Map<String, String> necromancerLevel3 = new HashMap<>();
		Stream.of("animate dead*", "bestow curse*", "vampiric touch*").forEach(s -> necromancerLevel3.put(s, ""));
		necromancerSpells.put("3rd level (3 slots)", necromancerLevel3);

		final Map<String, String> necromancerLevel4 = new HashMap<>();
		Stream.of("blight*", "dimension door", "stoneskin").forEach(s -> necromancerLevel4.put(s, ""));
		necromancerSpells.put("4th level (3 slots)", necromancerLevel4);

		final Map<String, String> necromancerLevel5 = new HashMap<>();
		Stream.of("Bigby's hand", "cloudkill").forEach(s -> necromancerLevel5.put(s, ""));
		necromancerSpells.put("5th level (2 slots)", necromancerLevel5);

		final Map<String, String> necromancerLevel6 = new HashMap<>();
		Stream.of("circle of death*").forEach(s -> necromancerLevel6.put(s, ""));
		necromancerSpells.put("6th level (1 slot)", necromancerLevel6);

		necromancer.features = Arrays.asList(new Spellcasting(null, "the necromancer", 12, Ability.INTELLIGENCE, 15, "its", 7, null, "wizard", null, necromancerSpells), //
				new Feature("Grim Harvest", "1/Turn", "When the necromancer kills a creature that is neither a construct nor undead with a spell of 1st level or higher, the necromancer regains hit points equal to twice the spell's level, or three times if it is a necromancy spell (marked above with *)."));
		necromancer.actions = Arrays.asList(new Attack("Withering Touch", Type.MELEE_SPELL, 7, "reach 5 ft., one creature", null, new Attack.Damage(new DiceRoll(2, 4, 0), DamageType.NECROTIC)));

		deathlockWight = new Creature();
		deathlockWight.name = "Deathlock Wight";
		deathlockWight.shortName = "The wight";
		deathlockWight.size = Size.MEDIUM;
		deathlockWight.type = CreatureType.UNDEAD;
		deathlockWight.alignment = Alignment.NEUTRAL_EVIL;
		deathlockWight.ac = 12;
		deathlockWight.armorNote = "15 with mage armor";
		deathlockWight.hitDice = new DiceRoll(5, 8, 15);
		deathlockWight.speed = 30;
		deathlockWight.abilityScores.put(Ability.STRENGTH, 11);
		deathlockWight.abilityScores.put(Ability.DEXTERITY, 14);
		deathlockWight.abilityScores.put(Ability.CONSTITUTION, 16);
		deathlockWight.abilityScores.put(Ability.INTELLIGENCE, 12);
		deathlockWight.abilityScores.put(Ability.WISDOM, 14);
		deathlockWight.abilityScores.put(Ability.CHARISMA, 16);
		deathlockWight.savingThrows.put(Ability.WISDOM, 4);
		deathlockWight.skills.put(Skill.ARCANA, 3);
		deathlockWight.skills.put(Skill.PERCEPTION, 4);
		deathlockWight.resistances.put(null, new HashSet<>(Arrays.asList(DamageType.NECROTIC)));
		deathlockWight.resistances.put("nonmagical weapons", new HashSet<>(Arrays.asList(DamageType.BLUDGEONING, DamageType.PIERCING, DamageType.SLASHING)));
		deathlockWight.immunities.put(null, new HashSet<>(Arrays.asList(DamageType.POISON)));
		deathlockWight.conditionImmunities.add(Condition.POISONED);
		deathlockWight.conditionImmunities.add(Condition.EXHAUSTION);
		deathlockWight.languages.add("the languages it knew in life");
		deathlockWight.challengeRating = 3;

		final Map<String, Map<String, String>> deathlockSpells = new LinkedHashMap<>();

		final Map<String, String> deathlockAtWill = new HashMap<>();
		Stream.of("detect magic", "disguise self", "mage armor").forEach(s -> deathlockAtWill.put(s, ""));
		deathlockSpells.put("At will", deathlockAtWill);

		final Map<String, String> deathlock1Day = new HashMap<>();
		Stream.of("fear", "hold person", "misty step").forEach(s -> deathlock1Day.put(s, ""));
		deathlockSpells.put("1/day each", deathlock1Day);

		deathlockWight.features = Arrays.asList(new InnateSpellcasting(null, "the wight", Ability.CHARISMA, 13, Integer.MIN_VALUE, "it", null, deathlockSpells), //
				new Feature("Sunlight Sensitivity", "While in sunlight, the wight has disadvantage on attack rolls, as well as on Wisdom (Perception) checks that rely on sight."));
		deathlockWight.actions = Arrays.asList(new Action("Multiattack", "The wight attacks twice with grave bolt."), //
				new Attack("Grave Bolt", Type.RANGED_SPELL, 5, "range 120 ft., one target", null, new Attack.Damage(new DiceRoll(1, 8, 3), DamageType.NECROTIC)), //
				new Attack("Life Drain", Type.MELEE_WEAPON, 4, "reach 5 ft., one creature",
						". The target must succeed on a DC 13 Constitution saving throw or its hit point maximum is reduced by an amount equal to the damage taken . This reduction lasts until the target finishes a long rest. The target dies if this effect reduces its hit point maximum to 0.\n    A humanoid slain by this attack rises 24 hours later as a zombie under the wight's control, unless the humanoid is restored to life or its body is destroyed. The wight can have no more than twelve zombies under its control at one time.",
						new Attack.Damage(new DiceRoll(2, 6, 2), DamageType.NECROTIC)));

		wight31 = new Creature();
		wight31.name = "Wight (Undying Laboratory)";
		wight31.shortName = "The wight";
		wight31.size = Size.MEDIUM;
		wight31.type = CreatureType.UNDEAD;
		wight31.alignment = Alignment.NEUTRAL_EVIL;
		wight31.ac = 14;
		wight31.armorNote = "studded leather";
		wight31.hitDice = new DiceRoll(3, 8, 6);
		wight31.speed = 30;
		wight31.abilityScores.put(Ability.STRENGTH, 13);
		wight31.abilityScores.put(Ability.DEXTERITY, 12);
		wight31.abilityScores.put(Ability.CONSTITUTION, 14);
		wight31.abilityScores.put(Ability.INTELLIGENCE, 8);
		wight31.abilityScores.put(Ability.WISDOM, 11);
		wight31.abilityScores.put(Ability.CHARISMA, 13);
		wight31.skills.put(Skill.STEALTH, 3);
		wight31.skills.put(Skill.PERCEPTION, 2);
		wight31.resistances.put(null, new HashSet<>(Arrays.asList(DamageType.NECROTIC)));
		wight31.resistances.put("nonmagical weapons that aren't silvered", new HashSet<>(Arrays.asList(DamageType.BLUDGEONING, DamageType.PIERCING, DamageType.SLASHING)));
		wight31.immunities.put(null, new HashSet<>(Arrays.asList(DamageType.POISON)));
		wight31.conditionImmunities.add(Condition.POISONED);
		wight31.conditionImmunities.add(Condition.EXHAUSTION);
		wight31.languages.add("the languages it knew in life");
		wight31.challengeRating = 3;
		wight31.features = Arrays.asList(new Feature("Sunlight Sensitivity", "While in sunlight, the wight has disadvantage on attack rolls, as well as on Wisdom (Perception) checks that rely on sight."));
		wight31.actions = Arrays.asList(new Action("Multiattack", "The wight makes two longsword attacks or two longbow attacks. It can use its life drain im place of one longsword attack."), //
				new Attack("Life Drain", Type.MELEE_WEAPON, 3, "reach 5 ft., one creature",
						". The target must succeed on a DC 12 Constitution saving throw or its hit point maximum is reduced by an amount equal to the damage taken . This reduction lasts until the target finishes a long rest. The target dies if this effect reduces its hit point maximum to 0.\n    A humanoid slain by this attack rises 24 hours later as a zombie under the wight's control, unless the humanoid is restored to life or its body is destroyed. The wight can have no more than twelve zombies under its control at one time.",
						new Attack.Damage(new DiceRoll(1, 6, 1), DamageType.NECROTIC)), //
				new Attack("Longsword", Type.MELEE_WEAPON, 3, "reach 5 ft., one target", ", or 6 (1d10 + 1) slashing damage if used with two hands.", new Attack.Damage(new DiceRoll(1, 8, 1), DamageType.SLASHING)), //
				new Attack("Longbow", Type.RANGED_WEAPON, 3, "range 150/600 ft., one target", null, new Attack.Damage(new DiceRoll(1, 8, 1), DamageType.PIERCING)), //
				new Attack("Urn", Type.RANGED_WEAPON, 3, "range 10/20 ft., one target", ". Unless the target is undead, it must suceed on a DC 17 Consitution saving throw or take 10 (3d6) necrotic damage and be paralyzed for 1 minute. If this damage reduces the creature to 0 hit points, the target dies and turns to dust. The target can repeat the saving throw at the end of each of its turns, ending the effect on itself on a success.", new Attack.Damage(new DiceRoll(1, 6, 1), DamageType.BLUDGEONING)));

		choker = new Creature();
		choker.name = "Choker";
		choker.shortName = "The choker";
		choker.size = Size.SMALL;
		choker.type = CreatureType.ABERRATION;
		choker.alignment = Alignment.CHAOTIC_EVIL;
		choker.ac = 16;
		choker.armorNote = "natural armor";
		choker.hitDice = new DiceRoll(3, 6, 3);
		choker.speed = 30;
		choker.abilityScores.put(Ability.STRENGTH, 16);
		choker.abilityScores.put(Ability.DEXTERITY, 14);
		choker.abilityScores.put(Ability.CONSTITUTION, 13);
		choker.abilityScores.put(Ability.INTELLIGENCE, 4);
		choker.abilityScores.put(Ability.WISDOM, 12);
		choker.abilityScores.put(Ability.CHARISMA, 7);
		choker.skills.put(Skill.STEALTH, 6);
		choker.senses.put(VisionType.DARKVISION, 60);
		choker.languages.add("Deep Speech");
		choker.challengeRating = 1;
		choker.features = Arrays.asList(new Feature("Aberrant Quickness", "Recharges after a Short or Long Rest", "The choker can take an extra action on its turn."), //
				new Feature("Spider Climb", "The choker can climb difficult surfaces, including upside down on ceilings, without needing to make an ability check."));
		choker.actions = Arrays.asList(new Action("Multiattack", "The choker makes two tentacle attacks"), //
				new Attack("Tentacle", Type.MELEE_WEAPON, 5, "reach 10 ft., one target", ". If the target is a large or smaller creature, it is grappled (escape DC 15). Until this grapple ends, the target is restrained, and the choker can't use this tentacle on another target (the choker has two tentacles). If this attack is a critical hit, the target also cannot breathe or speak until the grapple ends.", new Attack.Damage(new DiceRoll(1, 4, 3), DamageType.BLUDGEONING),
						new Attack.Damage(new DiceRoll(1, 6), DamageType.PIERCING)));

		leucrotta = new Creature();
		leucrotta.name = "Leucrotta";
		leucrotta.shortName = "The leucrotta";
		leucrotta.size = Size.LARGE;
		leucrotta.type = CreatureType.MONSTROSITY;
		leucrotta.alignment = Alignment.CHAOTIC_EVIL;
		leucrotta.ac = 14;
		leucrotta.armorNote = "natural armor";
		leucrotta.hitDice = new DiceRoll(9, 10, 18);
		leucrotta.speed = 50;
		leucrotta.abilityScores.put(Ability.STRENGTH, 18);
		leucrotta.abilityScores.put(Ability.DEXTERITY, 14);
		leucrotta.abilityScores.put(Ability.CONSTITUTION, 15);
		leucrotta.abilityScores.put(Ability.INTELLIGENCE, 9);
		leucrotta.abilityScores.put(Ability.WISDOM, 12);
		leucrotta.abilityScores.put(Ability.CHARISMA, 6);
		leucrotta.skills.put(Skill.DECEPTION, 2);
		leucrotta.skills.put(Skill.PERCEPTION, 3);
		leucrotta.senses.put(VisionType.DARKVISION, 60);
		leucrotta.languages.add("Abyssal");
		leucrotta.languages.add("Gnoll");
		leucrotta.challengeRating = 3;
		leucrotta.features = Arrays.asList(new Feature("Keen Smell", "The leucrotta has advantage on Wisdom (Perception) checks that rely on smell."), //
				new Feature("Kicking Retreat", "If the leucrotta attacks with its hooves, it can take the Disengage action as a bonus action."), //
				new Feature("Mimicry", "The leucrotta can mimic animal sounds and homainoid voices. A creature that hears the sounds can tell they are imitations with a successful DC 14 Wisdom (Insight) check."), //
				new Feature("Rampage", "When the leucrotta reduces a creature to 0 hit points with a melee attack on its turn, it can take a bonus action to move up to half its speed and make an attack with its hooves."));
		leucrotta.actions = Arrays.asList(new Action("Multiattack", "The leucrotta makes two attacks: one with its bite, and one with its hooves."), //
				new Attack("Bite", Type.MELEE_WEAPON, 6, "reach 5 ft., one target", ". If the leucrotta scores a critical hit, it rolls the damage dice three times, instead of twice.", new Attack.Damage(new DiceRoll(1, 8, 4), DamageType.PIERCING)), //
				new Attack("Hooves", Type.MELEE_WEAPON, 6, "reach 5 ft., one target", null, new Attack.Damage(new DiceRoll(2, 6, 4), DamageType.BLUDGEONING)));

		perytonYoung = new Creature();
		perytonYoung.name = "Peryton Young";
		perytonYoung.shortName = "The peryton";
		perytonYoung.size = Size.MEDIUM;
		perytonYoung.type = CreatureType.MONSTROSITY;
		perytonYoung.alignment = Alignment.CHAOTIC_EVIL;
		perytonYoung.ac = 13;
		perytonYoung.armorNote = "natural armor";
		perytonYoung.hitDice = new DiceRoll(3, 8, 0);
		perytonYoung.speed = 20;
		perytonYoung.speeds.put(MovementType.FLY, 60);
		perytonYoung.abilityScores.put(Ability.STRENGTH, 14);
		perytonYoung.abilityScores.put(Ability.DEXTERITY, 10);
		perytonYoung.abilityScores.put(Ability.CONSTITUTION, 11);
		perytonYoung.abilityScores.put(Ability.INTELLIGENCE, 7);
		perytonYoung.abilityScores.put(Ability.WISDOM, 10);
		perytonYoung.abilityScores.put(Ability.CHARISMA, 8);
		perytonYoung.skills.put(Skill.PERCEPTION, 4);
		perytonYoung.languages.add("understands Common and Elvish but can't speak");
		perytonYoung.challengeRating = 1;
		perytonYoung.features = Arrays.asList(new Feature("Dive Attack", "If the peryton is flying and dives at least 30 feet straight toward a target and then hits it with a melee weapon attack, the attack deals an extra 9 (2d8) damage to the target."), //
				new Feature("Flyby", "The peryton doesn't provoke an opportunity attack when it flies out of an enemy's reach."), //
				new Feature("Keen Sight and Smell", "The peryton has advantage on Wisdom (Perception) checks that rely on sight or smell."));
		perytonYoung.actions = Arrays.asList(new Action("Multiattack", "The peryton makes one gore attack, and one talon attack."), //
				new Attack("Gore", Type.MELEE_WEAPON, 4, "reach 5 ft., one target", null, new Attack.Damage(new DiceRoll(1, 8, 2), DamageType.PIERCING)), //
				new Attack("Talons", Type.MELEE_WEAPON, 4, "reach 5 ft., one target", null, new Attack.Damage(new DiceRoll(2, 4, 2), DamageType.PIERCING)));

		transmuter = new Creature();
		transmuter.name = "Transmuter";
		transmuter.shortName = "The transmuter";
		transmuter.size = Size.MEDIUM;
		transmuter.type = CreatureType.HUMANOID;
		transmuter.subtype = "any race";
		transmuter.alignment = Alignment.ANY_ALIGNMENT;
		transmuter.ac = 12;
		transmuter.armorNote = "15 with mage armor";
		transmuter.hitDice = new DiceRoll(9, 8);
		transmuter.speed = 30;
		transmuter.abilityScores.put(Ability.STRENGTH, 9);
		transmuter.abilityScores.put(Ability.DEXTERITY, 14);
		transmuter.abilityScores.put(Ability.CONSTITUTION, 11);
		transmuter.abilityScores.put(Ability.INTELLIGENCE, 17);
		transmuter.abilityScores.put(Ability.WISDOM, 12);
		transmuter.abilityScores.put(Ability.CHARISMA, 11);
		transmuter.savingThrows.put(Ability.INTELLIGENCE, 6);
		transmuter.savingThrows.put(Ability.WISDOM, 4);
		transmuter.skills.put(Skill.ARCANA, 6);
		transmuter.skills.put(Skill.HISTORY, 6);
		transmuter.languages.add("any four languages");
		transmuter.challengeRating = 5;

		final Map<String, Map<String, String>> transmuterSpells = new LinkedHashMap<>();

		final Map<String, String> transmuterCantrips = new HashMap<>();
		Stream.of("light", "mending", "prestidigitation", "ray of frost").forEach(s -> transmuterCantrips.put(s, ""));
		transmuterSpells.put("Cantrips (at will)", transmuterCantrips);

		final Map<String, String> transmuterLevel1 = new HashMap<>();
		Stream.of("chromatic orb", "disguise self*", "mage armor").forEach(s -> transmuterLevel1.put(s, ""));
		transmuterSpells.put("1st level (4 slots)", transmuterLevel1);

		final Map<String, String> transmuterLevel2 = new HashMap<>();
		Stream.of("alter self*", "hold person", "knock*").forEach(s -> transmuterLevel2.put(s, ""));
		transmuterSpells.put("2nd level (3 slots)", transmuterLevel2);

		final Map<String, String> transmuterLevel3 = new HashMap<>();
		Stream.of("blink*", "fireball", "slow*").forEach(s -> transmuterLevel3.put(s, ""));
		transmuterSpells.put("3rd level (3 slots)", transmuterLevel3);

		final Map<String, String> transmuterLevel4 = new HashMap<>();
		Stream.of("polymorph*", "stoneskin*").forEach(s -> transmuterLevel4.put(s, ""));
		transmuterSpells.put("4th level (3 slots)", transmuterLevel4);

		final Map<String, String> transmuterLevel5 = new HashMap<>();
		Stream.of("telekinesis").forEach(s -> transmuterLevel5.put(s, ""));
		transmuterSpells.put("5th level (1 slot)", transmuterLevel5);

		transmuter.features = Arrays.asList(new Spellcasting(null, "the transmuter", 9, Ability.INTELLIGENCE, 14, "its", 6, null, "wizard", null, transmuterSpells), //
				new Feature("Transmuter's Stone",
						"The transmuter carries a magic stone it crafted that grants its bearer one of the following effects:\n\n \u2022  Darkvision out to a range of 60 feet\n \u2022  An extra 10 feet of speed while the bearer is unencumbered\n \u2022  Proficiency with Constitution saving throws\n \u2022  Resistance to acid, cold, fire, lightning, or thunder damage (transmuter's choice whenever the transmuter chooses this benefit)\n\n If the transmuter has the stone and casts a transmutation spell of lst level or higher (marked above with *), it can change the effect of the stone."));
		transmuter.actions = Arrays.asList(new Attack("Quarterstaff", Type.MELEE_WEAPON, 2, "reach 5 ft., one target", ", or 3 (1d8 - 1) bludgeoning damage if used with two hands.", new Attack.Damage(new DiceRoll(1, 6, -1), DamageType.BLUDGEONING)));

		vampiricMist = new Creature();
		vampiricMist.name = "Vampiric Mist";
		vampiricMist.shortName = "The vampiric mist";
		vampiricMist.size = Size.MEDIUM;
		vampiricMist.type = CreatureType.UNDEAD;
		vampiricMist.alignment = Alignment.CHAOTIC_EVIL;
		vampiricMist.ac = 13;
		vampiricMist.hitDice = new DiceRoll(6, 8, 18);
		vampiricMist.speed = 0;
		vampiricMist.speeds.put(MovementType.FLY, 30);
		vampiricMist.abilityScores.put(Ability.STRENGTH, 6);
		vampiricMist.abilityScores.put(Ability.DEXTERITY, 16);
		vampiricMist.abilityScores.put(Ability.CONSTITUTION, 16);
		vampiricMist.abilityScores.put(Ability.INTELLIGENCE, 6);
		vampiricMist.abilityScores.put(Ability.WISDOM, 12);
		vampiricMist.abilityScores.put(Ability.CHARISMA, 7);
		vampiricMist.savingThrows.put(Ability.WISDOM, 3);
		vampiricMist.resistances.put(null, Stream.of(DamageType.ACID, DamageType.LIGHTNING, DamageType.COLD, DamageType.NECROTIC).collect(Collectors.toSet()));
		vampiricMist.resistances.put("nonmagical weapons", new HashSet<>(Arrays.asList(DamageType.BLUDGEONING, DamageType.PIERCING, DamageType.SLASHING)));
		vampiricMist.immunities.put(null, new HashSet<>(Arrays.asList(DamageType.POISON)));
		Stream.of(Condition.CHARMED, Condition.EXHAUSTION, Condition.GRAPPLED, Condition.PARALYZED, Condition.PETRIFIED, Condition.POISONED, Condition.PRONE, Condition.RESTRAINED).forEach(vampiricMist.conditionImmunities::add);
		vampiricMist.senses.put(VisionType.DARKVISION, 60);
		vampiricMist.challengeRating = 3;
		vampiricMist.features = Arrays.asList(new Feature("Blood Sense", "The vampiric mist can sense living creatures that have blood or similar vital fluids in a radius of 60 feet."), //
				new Feature("Forbiddance", "The vampiric mist can't enter a residence eithout an invitation from one of the occupants."), //
				new Feature("Misty Form", "The vampiric mist can occupy another creature's space and vice versa. In addition, if air can pass through a space, the mist can pass through it without squeezing. Each foot of movement in water costs it 2 extra feet, rather than l extra foot. The mist can't manipulate objects in any way that requires hands; it can apply simple force only."), //
				new Feature("Sunlight Hypersensitivity", "The vampiric mist takes 20 radiant damage when it starts its turn in sunlight. While in sunlight, the mist has disadvantage on attack rolls and ability checks."));
		vampiricMist.actions = Arrays.asList(new Action("Blood Drain",
				"One creature in the vampiric mist's space must make a DC 13 Constitution saving throw (undead and constructs automatically succeed). On a failed save, the target takes 10 (2d6 + 3) necrotic damage, its hit point maximum is reduced by an amount equal to the necrotic damage taken, and the mist regains hit points equal to that amount. This reduction to the target's hit point maximum lasts until the target finishes a long rest. It dies if this effect reduces its hit point maximum to 0."));

		fourArmedGargoyle = new Creature();
		fourArmedGargoyle.name = "Gargoyle (Four-armed)";
		fourArmedGargoyle.shortName = "The gargoyle";
		fourArmedGargoyle.size = Size.MEDIUM;
		fourArmedGargoyle.type = CreatureType.ELEMENTAL;
		fourArmedGargoyle.alignment = Alignment.CHAOTIC_EVIL;
		fourArmedGargoyle.ac = 15;
		fourArmedGargoyle.armorNote = "natural armor";
		fourArmedGargoyle.hitDice = new DiceRoll(9, 8, 27);
		fourArmedGargoyle.speed = 30;
		fourArmedGargoyle.speeds.put(MovementType.FLY, 60);
		fourArmedGargoyle.abilityScores.put(Ability.STRENGTH, 15);
		fourArmedGargoyle.abilityScores.put(Ability.DEXTERITY, 11);
		fourArmedGargoyle.abilityScores.put(Ability.CONSTITUTION, 16);
		fourArmedGargoyle.abilityScores.put(Ability.INTELLIGENCE, 6);
		fourArmedGargoyle.abilityScores.put(Ability.WISDOM, 11);
		fourArmedGargoyle.abilityScores.put(Ability.CHARISMA, 7);
		fourArmedGargoyle.resistances.put("nonmagical weapons that aren't adamantine", new HashSet<>(Arrays.asList(DamageType.BLUDGEONING, DamageType.PIERCING, DamageType.SLASHING)));
		fourArmedGargoyle.immunities.put(null, new HashSet<>(Arrays.asList(DamageType.POISON)));
		fourArmedGargoyle.conditionImmunities.add(Condition.EXHAUSTION);
		fourArmedGargoyle.conditionImmunities.add(Condition.PETRIFIED);
		fourArmedGargoyle.conditionImmunities.add(Condition.POISONED);
		fourArmedGargoyle.senses.put(VisionType.DARKVISION, 60);
		fourArmedGargoyle.languages.add("Terran");
		fourArmedGargoyle.challengeRating = 2;
		fourArmedGargoyle.features = Arrays.asList(new Feature("False Appearance", "While the gargoyle is motionless, it is indistinguishable from an inanimate statue."));
		fourArmedGargoyle.actions = Arrays.asList(new Action("Multiattack", "The gargoyle makes three attacks: one with its bite, and two with its claws."), //
				new Attack("Bite", Type.MELEE_WEAPON, 4, "reach 5 ft., one target", null, new Attack.Damage(new DiceRoll(1, 6, 2), DamageType.PIERCING)), //
				new Attack("Claws", Type.MELEE_WEAPON, 4, "reach 5 ft., one target", null, new Attack.Damage(new DiceRoll(1, 6, 2), DamageType.SLASHING)));

		conjurer = new Creature();
		conjurer.name = "Conjurer";
		conjurer.shortName = "The conjurer";
		conjurer.size = Size.MEDIUM;
		conjurer.type = CreatureType.HUMANOID;
		conjurer.subtype = "any race";
		conjurer.alignment = Alignment.ANY_ALIGNMENT;
		conjurer.ac = 12;
		conjurer.armorNote = "15 with mage armor";
		conjurer.hitDice = new DiceRoll(9, 8);
		conjurer.speed = 30;
		conjurer.abilityScores.put(Ability.STRENGTH, 9);
		conjurer.abilityScores.put(Ability.DEXTERITY, 14);
		conjurer.abilityScores.put(Ability.CONSTITUTION, 11);
		conjurer.abilityScores.put(Ability.INTELLIGENCE, 17);
		conjurer.abilityScores.put(Ability.WISDOM, 12);
		conjurer.abilityScores.put(Ability.CHARISMA, 11);
		conjurer.savingThrows.put(Ability.INTELLIGENCE, 6);
		conjurer.savingThrows.put(Ability.WISDOM, 4);
		conjurer.skills.put(Skill.ARCANA, 6);
		conjurer.skills.put(Skill.HISTORY, 6);
		conjurer.languages.add("any four languages");
		conjurer.challengeRating = 6;

		final Map<String, Map<String, String>> conjurerSpells = new LinkedHashMap<>();

		final Map<String, String> conjurerCantrips = new HashMap<>();
		Stream.of("acid splash", "mage hand", "poison spray", "prestidigitation").forEach(s -> conjurerCantrips.put(s, ""));
		conjurerSpells.put("Cantrips (at will)", conjurerCantrips);

		final Map<String, String> conjurerLevel1 = new HashMap<>();
		Stream.of("mage armor", "magic missile", "unseen servant*").forEach(s -> conjurerLevel1.put(s, ""));
		conjurerSpells.put("1st level (4 slots)", conjurerLevel1);

		final Map<String, String> conjurerLevel2 = new HashMap<>();
		Stream.of("cloud of daggers*", "misty step*", "web*").forEach(s -> conjurerLevel2.put(s, ""));
		conjurerSpells.put("2nd level (3 slots)", conjurerLevel2);

		final Map<String, String> conjurerLevel3 = new HashMap<>();
		Stream.of("fireball", "stinking cloud*").forEach(s -> conjurerLevel3.put(s, ""));
		conjurerSpells.put("3rd level (3 slots)", conjurerLevel3);

		final Map<String, String> conjurerLevel4 = new HashMap<>();
		Stream.of("Evard's black tentacles*", "stoneskin").forEach(s -> conjurerLevel4.put(s, ""));
		conjurerSpells.put("4th level (3 slots)", conjurerLevel4);

		final Map<String, String> conjurerLevel5 = new HashMap<>();
		Stream.of("cloudkill*", "conjure elemental*").forEach(s -> conjurerLevel5.put(s, ""));
		conjurerSpells.put("5th level (2 slots)", conjurerLevel5);

		conjurer.features = Arrays.asList(new Spellcasting(null, "the conjurer", 9, Ability.INTELLIGENCE, 14, "its", 6, null, "wizard", null, conjurerSpells), //
				new Feature("Benign Transposition", "Recharges after the Conjurer Casts a Conjuration Spell of 1st Level or Higher", "As a bonus action, the conjurer teleports up to 30 feet to an unoccupied space that it can see. If it instead chooses a space within range that is occupied by a willing Small or Medium creature, they both teleport, swapping places."));
		conjurer.actions = Arrays.asList(new Attack("Dagger", Type.MELEE_OR_RANGED_WEAPON, 4, "reach 5 ft. or range 20/60 ft., one target", null, new Attack.Damage(new DiceRoll(1, 4, 2), DamageType.PIERCING)));

		lumalia = new Creature();
		lumalia.name = "Lumalia, the Deva";
		lumalia.shortName = "Lumalia";
		lumalia.size = Size.MEDIUM;
		lumalia.type = CreatureType.CELESTIAL;
		lumalia.alignment = Alignment.CHAOTIC_GOOD;
		lumalia.ac = 17;
		lumalia.armorNote = "natural armor";
		lumalia.hitDice = new DiceRoll(16, 8, 64);
		lumalia.speed = 30;
		lumalia.speeds.put(MovementType.FLY, 90);
		lumalia.abilityScores.put(Ability.STRENGTH, 18);
		lumalia.abilityScores.put(Ability.DEXTERITY, 18);
		lumalia.abilityScores.put(Ability.CONSTITUTION, 18);
		lumalia.abilityScores.put(Ability.INTELLIGENCE, 17);
		lumalia.abilityScores.put(Ability.WISDOM, 20);
		lumalia.abilityScores.put(Ability.CHARISMA, 20);
		lumalia.savingThrows.put(Ability.WISDOM, 9);
		lumalia.savingThrows.put(Ability.CHARISMA, 9);
		lumalia.skills.put(Skill.INSIGHT, 7);
		lumalia.skills.put(Skill.PERCEPTION, 9);
		lumalia.resistances.put(null, new HashSet<>(Arrays.asList(DamageType.RADIANT)));
		lumalia.resistances.put("nonmagical weapons", new HashSet<>(Arrays.asList(DamageType.BLUDGEONING, DamageType.PIERCING, DamageType.SLASHING)));
		lumalia.conditionImmunities.add(Condition.CHARMED);
		lumalia.conditionImmunities.add(Condition.EXHAUSTION);
		lumalia.conditionImmunities.add(Condition.FRIGHTENED);
		lumalia.languages.add("all");
		lumalia.languages.add("telepathy 120 ft.");
		lumalia.challengeRating = 10;

		final Map<String, Map<String, String>> lumaliaSpells = new LinkedHashMap<>();

		final Map<String, String> lumaliaAtWill = new HashMap<>();
		Stream.of("detect evil and good").forEach(s -> lumaliaAtWill.put(s, ""));
		lumaliaSpells.put("At will", lumaliaAtWill);

		final Map<String, String> lumalia1Day = new HashMap<>();
		Stream.of("commune", "raise dead").forEach(s -> lumalia1Day.put(s, ""));
		lumaliaSpells.put("1/day each", lumalia1Day);

		lumalia.features = Arrays.asList(new Feature("Angelic Weapons", "Lumalia's weapon attacks are magical. When Lumalia hits with any weapon, the attack deals an extra 4d8 radiant damage (included in the attack)."), //
				new InnateSpellcasting(null, "Lumalia", Ability.CHARISMA, 17, Integer.MIN_VALUE, "she", null, lumaliaSpells), //
				new Feature("Magic Resistance", "Lumalia has advantage on saving throws against spells and other magical effects."));
		lumalia.actions = Arrays.asList(new Action("Multiattack", "Lumalia makes two melee attacks."), //
				new Attack("Mace", Type.MELEE_WEAPON, 8, "reach 5 ft., one target", null, new Attack.Damage(new DiceRoll(1, 6, 4), DamageType.BLUDGEONING), new Attack.Damage(new DiceRoll(4, 8), DamageType.RADIANT)), //
				new Action("Healing Touch", "3/Day", "Lumalia touches another creature. The target magically regains 20 (4d8 + 2) hit points and is freed from any curse, disease, poison, blindness, or deafness."), new Action("Change Shape",
						"Lumalia magically polymorphs into a humanoid or beast that has a challenge rating equal to or less than her own, or back into her true form. She reverts to her true form if she dies. Any equipment she is wearing or carrying is absorbed or borne by the new form (Lumalia's choice).\n    In a new form, Lumalia retains her game statistics and ability to speak, but her AC, movement modes, Strength, Dexterity, and special senses are replaced by those of the new form, and she gains any statistics and capabilities (except class features, legendary actions, and lair actions) that the new form has but that she lacks."));

		barghest = new Creature();
		barghest.name = "Barghest";
		barghest.shortName = "The barghest";
		barghest.size = Size.LARGE;
		barghest.type = CreatureType.FIEND;
		barghest.subtype = "shapechanger";
		barghest.alignment = Alignment.NEUTRAL_EVIL;
		barghest.ac = 17;
		barghest.armorNote = "natural armor";
		barghest.hitDice = new DiceRoll(12, 10, 24);
		barghest.speed = 60;
		barghest.abilityScores.put(Ability.STRENGTH, 19);
		barghest.abilityScores.put(Ability.DEXTERITY, 15);
		barghest.abilityScores.put(Ability.CONSTITUTION, 14);
		barghest.abilityScores.put(Ability.INTELLIGENCE, 13);
		barghest.abilityScores.put(Ability.WISDOM, 12);
		barghest.abilityScores.put(Ability.CHARISMA, 14);
		barghest.skills.put(Skill.DECEPTION, 4);
		barghest.skills.put(Skill.INTIMIDATION, 4);
		barghest.skills.put(Skill.STEALTH, 4);
		barghest.skills.put(Skill.PERCEPTION, 5);
		barghest.immunities.put(null, new HashSet<>(Arrays.asList(DamageType.ACID, DamageType.POISON)));
		barghest.resistances.put(null, new HashSet<>(Arrays.asList(DamageType.COLD, DamageType.FIRE, DamageType.LIGHTNING)));
		barghest.resistances.put("nonmagical weapons", new HashSet<>(Arrays.asList(DamageType.BLUDGEONING, DamageType.PIERCING, DamageType.SLASHING)));
		barghest.conditionImmunities.add(Condition.POISONED);
		barghest.languages.addAll(Arrays.asList("Abyssal", "Common", "Goblin", "Infernal", "telepathy 60 ft."));
		barghest.challengeRating = 4;

		final Map<String, Map<String, String>> barghestSpells = new LinkedHashMap<>();

		final Map<String, String> barghestAtWill = new HashMap<>();
		Stream.of("levitate", "minor illusion", "pass without trace").forEach(s -> barghestAtWill.put(s, ""));
		barghestSpells.put("At will", barghestAtWill);

		final Map<String, String> barghest1Day = new HashMap<>();
		Stream.of("charm person", "dimension door", "suggestion").forEach(s -> barghest1Day.put(s, ""));
		barghestSpells.put("1/day each", barghest1Day);

		barghest.features = Arrays.asList(new Feature("Shapechanger", "The barghest can use its action to polymorph into a Small goblin or back into its true form. Other than its size and speed, its statistics are the same in each form. Any equipment it is wearing or carrying isn't transformed. The barghest reverts to its true form if it dies."), //
				new Feature("Fire Banishment", "When the barghest starts its turn engulfed in flames that are at least 10 feet high or wide, it must succeed on a DC 15 Charisma saving throw or be instantly banished to Gehenna. Instantaneous bursts of flame (such as a red dragon's breath or a fireball spell) don't have this effect on the barghest."), //
				new Feature("Keen Smell", "The barghest has advantage on Wisdom (Perception) checks that rely on smell."), //
				new InnateSpellcasting(null, "the barghest", Ability.CHARISMA, 12, Integer.MIN_VALUE, "it", null, barghestSpells));
		barghest.actions = Arrays.asList(new Attack("Bite", "true form only", Type.MELEE_WEAPON, 6, "reach 5 ft., one target", null, new Attack.Damage(new DiceRoll(2, 8, 4), DamageType.PIERCING)), //
				new Attack("Claws", Type.MELEE_WEAPON, 6, "reach 5 ft., one target", null, new Attack.Damage(new DiceRoll(1, 8, 4), DamageType.SLASHING)));

		sentientGrayOoze = new Creature();
		sentientGrayOoze.name = "Sentient Gray Ooze";
		sentientGrayOoze.shortName = "The ooze";
		sentientGrayOoze.size = Size.MEDIUM;
		sentientGrayOoze.type = CreatureType.OOZE;
		sentientGrayOoze.alignment = Alignment.UNALIGNED;
		sentientGrayOoze.ac = 8;
		sentientGrayOoze.hitDice = new DiceRoll(3, 8, 9);
		sentientGrayOoze.speed = 10;
		sentientGrayOoze.speeds.put(MovementType.CLIMB, 10);
		sentientGrayOoze.abilityScores.put(Ability.STRENGTH, 12);
		sentientGrayOoze.abilityScores.put(Ability.DEXTERITY, 6);
		sentientGrayOoze.abilityScores.put(Ability.CONSTITUTION, 16);
		sentientGrayOoze.abilityScores.put(Ability.INTELLIGENCE, 5);
		sentientGrayOoze.abilityScores.put(Ability.WISDOM, 6);
		sentientGrayOoze.abilityScores.put(Ability.CHARISMA, 2);
		sentientGrayOoze.skills.put(Skill.STEALTH, 2);
		sentientGrayOoze.immunities.put(null, Stream.of(DamageType.ACID, DamageType.COLD, DamageType.FIRE).collect(Collectors.toSet()));
		sentientGrayOoze.conditionImmunities.addAll(Arrays.asList(Condition.BLINDED, Condition.DEAFENED, Condition.EXHAUSTION, Condition.FRIGHTENED, Condition.PRONE));
		sentientGrayOoze.senses.put(VisionType.BLINDSIGHT, 60);
		sentientGrayOoze.languages.add("understands Common but can't speak");
		sentientGrayOoze.challengeRating = -1;
		sentientGrayOoze.features = Arrays.asList(new Feature("Amorphous", "The pudding can move through a space as narrow as 1 inch wide without squeezing."), //
				new Feature("Corrode Metal", "Any non magical weapon made of metal that hits the ooze corrodes. After dealing damage, the weapon takes a permanent and cumulative -1 penalty to damage rolls. If its penalty drops to -5, the weapon is destroyed. Nonmagical ammunition made of metal that hits the pudding is destroyed after dealing damage.\n    The pudding can eat through 2-inch-thick, nonmagical metal in 1 round."), //
				new Feature("False Appearance", "While the ooze remains motionless, it is indistinguishable from an oily pool or wet rock."));
		sentientGrayOoze.actions = Arrays.asList(new Attack("Pseudopod", Type.MELEE_WEAPON, 5, "reach 5 ft., one target", ", and if the target is wearing nonmagical metal armor, its armor is partly corroded and takes a permanent and cumulative -1 penalty to the AC it offers. The armor is destroyed if the penalty reduces its AC to 10.", new Attack.Damage(new DiceRoll(1, 6, 1), DamageType.BLUDGEONING), new Attack.Damage(new DiceRoll(2, 8), DamageType.ACID)));

		sentientOchreJelly = new Creature();
		sentientOchreJelly.name = "Sentient Ochre Jelly";
		sentientOchreJelly.shortName = "The jelly";
		sentientOchreJelly.size = Size.LARGE;
		sentientOchreJelly.type = CreatureType.OOZE;
		sentientOchreJelly.alignment = Alignment.UNALIGNED;
		sentientOchreJelly.ac = 8;
		sentientOchreJelly.hitDice = new DiceRoll(6, 10, 12);
		sentientOchreJelly.speed = 10;
		sentientOchreJelly.speeds.put(MovementType.CLIMB, 10);
		sentientOchreJelly.abilityScores.put(Ability.STRENGTH, 15);
		sentientOchreJelly.abilityScores.put(Ability.DEXTERITY, 6);
		sentientOchreJelly.abilityScores.put(Ability.CONSTITUTION, 14);
		sentientOchreJelly.abilityScores.put(Ability.INTELLIGENCE, 5);
		sentientOchreJelly.abilityScores.put(Ability.WISDOM, 6);
		sentientOchreJelly.abilityScores.put(Ability.CHARISMA, 1);
		sentientOchreJelly.resistances.put(null, Stream.of(DamageType.ACID).collect(Collectors.toSet()));
		sentientOchreJelly.immunities.put(null, Stream.of(DamageType.LIGHTNING, DamageType.SLASHING).collect(Collectors.toSet()));
		sentientOchreJelly.conditionImmunities.addAll(Arrays.asList(Condition.BLINDED, Condition.DEAFENED, Condition.EXHAUSTION, Condition.FRIGHTENED, Condition.PRONE));
		sentientOchreJelly.senses.put(VisionType.BLINDSIGHT, 60);
		sentientOchreJelly.languages.add("understands Common but can't speak");
		sentientOchreJelly.challengeRating = 2;
		sentientOchreJelly.features = Arrays.asList(new Feature("Amorphous", "The jelly can move through a space as narrow as 1 inch wide without squeezing."), //
				new Feature("Spider Climb", "The jelly can climb difficult surfaces, including upside down on ceilings, without needing to make an ability check."));
		sentientOchreJelly.actions = Arrays.asList(new Attack("Pseudopod", Type.MELEE_WEAPON, 5, "reach 5 ft., one target", null, new Attack.Damage(new DiceRoll(2, 6, 2), DamageType.BLUDGEONING), new Attack.Damage(new DiceRoll(1, 6), DamageType.ACID)));
		sentientOchreJelly.reactions = Arrays.asList(new Action("Split", "When a jelly that is Medium or larger is subjected to lightning or slashing damage, it splits into two new jellies if it has at least 10 hit points. Each new jelly has hit points equal to half the original jelly's, rounded down. New jellies are one size smaller than the original jelly."));

		whiteMaw = new Creature();
		whiteMaw.name = "White Maw";
		whiteMaw.shortName = "White Maw";
		whiteMaw.size = Size.GARGANTUAN;
		whiteMaw.type = CreatureType.OOZE;
		whiteMaw.alignment = Alignment.CHAOTIC_NEUTRAL;
		whiteMaw.ac = 5;
		whiteMaw.hitDice = new DiceRoll(14, 20, 70);
		whiteMaw.speed = 10;
		whiteMaw.abilityScores.put(Ability.STRENGTH, 18);
		whiteMaw.abilityScores.put(Ability.DEXTERITY, 1);
		whiteMaw.abilityScores.put(Ability.CONSTITUTION, 20);
		whiteMaw.abilityScores.put(Ability.INTELLIGENCE, 12);
		whiteMaw.abilityScores.put(Ability.WISDOM, 10);
		whiteMaw.abilityScores.put(Ability.CHARISMA, 3);
		whiteMaw.resistances.put(null, Stream.of(DamageType.ACID, DamageType.COLD, DamageType.FIRE).collect(Collectors.toSet()));
		whiteMaw.immunities.put(null, Stream.of(DamageType.POISON).collect(Collectors.toSet()));
		whiteMaw.conditionImmunities.addAll(Arrays.asList(Condition.BLINDED, Condition.DEAFENED, Condition.EXHAUSTION, Condition.FRIGHTENED, Condition.POISONED, Condition.PRONE));
		whiteMaw.senses.put(VisionType.BLINDSIGHT, 60);
		whiteMaw.languages.add("telepathy 50 ft.");
		whiteMaw.challengeRating = 10;
		whiteMaw.features = Arrays.asList(new Feature("Amorphous Form", "White Maw can occupy another creature's space and vice versa."), //
				new Feature("Corrode Metal", "Any non magical weapon made of metal that hits White Maw corrodes. After dealing damage, the weapon takes a permanent and cumulative -1 penalty to damage rolls. If its penalty drops to -5, the weapon is destroyed. Nonmagical ammunition made of metal that hits White Maw is destroyed after dealing damage.\n    White Maw can eat through 2-inch-thick, nonmagical metal in 1 round."), //
				new Feature("False Appearance", "While White Maw remains motionless, it is indistinguishable from white stone."), //
				new Feature("Killer Response", "Any creature that starts its turn in White Maw's space is targeted by a pseudopod attack if White Maw isn't incapacitated."));
		whiteMaw.actions = Arrays.asList(new Attack("Pseudopod", Type.MELEE_WEAPON, 8, "reach 10 ft., one target", ", and if the target is wearing nonmagical metal armor, its armor is partly corroded and takes a permanent and cumulative -1 penalty to the AC it offers. The armor is destroyed if the penalty reduces its AC to 10.", new Attack.Damage(new DiceRoll(4, 8, 4), DamageType.BLUDGEONING), new Attack.Damage(new DiceRoll(2, 8), DamageType.ACID)));

		oozeMaster = new Creature();
		oozeMaster.name = "Ooze Master";
		oozeMaster.shortName = "the Ooze Master";
		oozeMaster.size = Size.HUGE;
		oozeMaster.type = CreatureType.UNDEAD;
		oozeMaster.alignment = Alignment.LAWFUL_EVIL;
		oozeMaster.ac = 9;
		oozeMaster.armorNote = "natural armor";
		oozeMaster.hitDice = new DiceRoll(12, 12, 60);
		oozeMaster.speed = 30;
		oozeMaster.speeds.put(MovementType.CLIMB, 30);
		oozeMaster.abilityScores.put(Ability.STRENGTH, 16);
		oozeMaster.abilityScores.put(Ability.DEXTERITY, 1);
		oozeMaster.abilityScores.put(Ability.CONSTITUTION, 20);
		oozeMaster.abilityScores.put(Ability.INTELLIGENCE, 17);
		oozeMaster.abilityScores.put(Ability.WISDOM, 10);
		oozeMaster.abilityScores.put(Ability.CHARISMA, 16);
		oozeMaster.savingThrows.put(Ability.INTELLIGENCE, 7);
		oozeMaster.savingThrows.put(Ability.WISDOM, 4);
		oozeMaster.skills.put(Skill.ARCANA, 7);
		oozeMaster.skills.put(Skill.INSIGHT, 4);
		oozeMaster.resistances.put(null, Stream.of(DamageType.LIGHTNING, DamageType.NECROTIC).collect(Collectors.toSet()));
		oozeMaster.resistances.put("nonmagical weapons", new HashSet<>(Arrays.asList(DamageType.BLUDGEONING, DamageType.PIERCING, DamageType.SLASHING)));
		oozeMaster.immunities.put(null, Stream.of(DamageType.ACID, DamageType.COLD, DamageType.POISON).collect(Collectors.toSet()));
		oozeMaster.conditionImmunities.addAll(Arrays.asList(Condition.BLINDED, Condition.DEAFENED, Condition.EXHAUSTION, Condition.FRIGHTENED, Condition.PARALYZED, Condition.POISONED, Condition.PRONE));
		oozeMaster.senses.put(VisionType.BLINDSIGHT, 120);
		oozeMaster.languages.add("Common");
		oozeMaster.languages.add("Primordial");
		oozeMaster.languages.add("Thayan");
		oozeMaster.challengeRating = 10;

		final Map<String, Map<String, String>> oozeMasterSpells = new LinkedHashMap<>();

		final Map<String, String> oozeMasterCantrips = new HashMap<>();
		Stream.of("acid splash", "friends", "mage hand", "poison spray").forEach(s -> oozeMasterCantrips.put(s, ""));
		oozeMasterSpells.put("Cantrips (at will)", oozeMasterCantrips);

		final Map<String, String> oozeMasterLevel1 = new HashMap<>();
		Stream.of("charm person", "detect magic", "magic missile", "ray of sickness").forEach(s -> oozeMasterLevel1.put(s, ""));
		oozeMasterSpells.put("1st level (4 slots)", oozeMasterLevel1);

		final Map<String, String> oozeMasterLevel2 = new HashMap<>();
		Stream.of("detect thoughts", "Melf's acid arrow", "suggestion").forEach(s -> oozeMasterLevel2.put(s, ""));
		oozeMasterSpells.put("2nd level (3 slots)", oozeMasterLevel2);

		final Map<String, String> oozeMasterLevel3 = new HashMap<>();
		Stream.of("fear", "slow", "stinking cloud").forEach(s -> oozeMasterLevel3.put(s, ""));
		oozeMasterSpells.put("3rd level (3 slots)", oozeMasterLevel3);

		final Map<String, String> oozeMasterLevel4 = new HashMap<>();
		Stream.of("confusion", "Evard's black tentacles").forEach(s -> oozeMasterLevel4.put(s, ""));
		oozeMasterSpells.put("4th level (3 slots)", oozeMasterLevel4);

		final Map<String, String> oozeMasterLevel5 = new HashMap<>();
		Stream.of("cloudkill").forEach(s -> oozeMasterLevel5.put(s, ""));
		oozeMasterSpells.put("5th level (1 slot)", oozeMasterLevel5);

		oozeMaster.features = Arrays.asList(new Feature("Amorphous Form", "White Maw can occupy another creature's space and vice versa."), //
				new Feature("Corrosive Form",
						"A creature that touches the Ooze Master or hits it with a melee attack while within 5 feet of it takes 9 (2d8) acid damage. Any nonmagical weapon made of metal or wood that hits the Ooze Master corrodes. After dealing damage, the weapon takes a permanent cumulative -1 penalty to damage rolls. If its penalty drops to -5, the weapon is destroyed. Nonmagical ammunition made of metal or wood that hits the Ooze Master is destroyed after dealing damage.\n    The Ooze Master can eat through 2-inch-thick, non magical wood or metal in 1 round."), //
				new Feature("Instinctive Attack", "When the Ooze Master casts a spell with a casting time of 1 action, it can make one pseudopod attack as a bonus action."), //
				new Spellcasting(null, "the Ooze Master", 9, Ability.INTELLIGENCE, 15, "it", 7, null, "wizard", null, oozeMasterSpells), //
				new Feature("Spider Climb", "The Ooze Master can climb difficult surfaces, including upside down on ceilings, without needing to make an ability check."));
		oozeMaster.actions = Arrays.asList(new Attack("Pseudopod", Type.MELEE_WEAPON, 7, "reach 10 ft., one target", null, new Attack.Damage(new DiceRoll(3, 6, 3), DamageType.BLUDGEONING), new Attack.Damage(new DiceRoll(3, 6), DamageType.ACID)));
		oozeMaster.reactions = Arrays.asList(new Action("Instinctive Charm",
				"If a creature the Ooze Master can see makes an attack roll against it while within 30 feet of it, the Ooze Master can use a reaction to divert the attack if another creature is within the attack's range. The attacker must make a DC 15 Wisdom saving throw. On a failed save, the attacker targets the creature that is closest to it, not including itself or the Ooze Master. If multiple creatures are closest, the attacker chooses which one to target. On a successful save, the attacker is immune to this Instinctive Charm for 24 hours. Creatures that can't be charmed are immune to this effect."));

		elderBlackPudding = new Creature();
		elderBlackPudding.name = "Elder Black Pudding";
		elderBlackPudding.shortName = "The pudding";
		elderBlackPudding.size = Size.HUGE;
		elderBlackPudding.type = CreatureType.OOZE;
		elderBlackPudding.alignment = Alignment.UNALIGNED;
		elderBlackPudding.ac = 7;
		elderBlackPudding.hitDice = new DiceRoll(14, 12, 14 * 3);
		elderBlackPudding.speed = 20;
		elderBlackPudding.speeds.put(MovementType.CLIMB, 20);
		elderBlackPudding.abilityScores.put(Ability.STRENGTH, 16);
		elderBlackPudding.abilityScores.put(Ability.DEXTERITY, 5);
		elderBlackPudding.abilityScores.put(Ability.CONSTITUTION, 16);
		elderBlackPudding.abilityScores.put(Ability.INTELLIGENCE, 1);
		elderBlackPudding.abilityScores.put(Ability.WISDOM, 6);
		elderBlackPudding.abilityScores.put(Ability.CHARISMA, 1);
		elderBlackPudding.immunities.put(null, Stream.of(DamageType.ACID, DamageType.COLD, DamageType.LIGHTNING, DamageType.SLASHING).collect(Collectors.toSet()));
		elderBlackPudding.conditionImmunities.addAll(Arrays.asList(Condition.BLINDED, Condition.CHARMED, Condition.DEAFENED, Condition.EXHAUSTION, Condition.FRIGHTENED, Condition.PRONE));
		elderBlackPudding.senses.put(VisionType.BLINDSIGHT, 60);
		elderBlackPudding.challengeRating = 6;
		elderBlackPudding.features = Arrays.asList(new Feature("Amorphous", "The pudding can move through a space as narrow as 1 inch wide without squeezing."), //
				new Feature("Corrosive Form",
						"A creature that touches the pudding or hits it with a melee attack while within 5 feet of it takes 4 (1d8) acid damage. Any nonmagical weapon made of metal or wood that hits the pudding corrodes. After dealing damage, the weapon takes a permanent cumulative -1 penalty to damage rolls. If its penalty drops to -5, the weapon is destroyed. Nonmagical ammunition made of metal or wood that hits the pudding is destroyed after dealing damage.\n    The pudding can eat through 2-inch-thick, non magical wood or metal in 1 round."), //
				new Feature("Spider Climb", "The pudding can climb difficult surfaces, including upside down on ceilings, without needing to make an ability check."));
		elderBlackPudding.actions = Arrays.asList(new Attack("Pseudopod", Type.MELEE_WEAPON, 5, "reach 5 ft., one target", ". In addition, nonmagical armor worn by the target is partly dissolved and takes a permanent and cumulative -1 penalty to the AC it offers. The armor is destroyed if the penalty reduces its AC to 10.", new Attack.Damage(new DiceRoll(1, 6, 3), DamageType.BLUDGEONING), new Attack.Damage(new DiceRoll(4, 8), DamageType.ACID)));
		elderBlackPudding.reactions = Arrays.asList(new Action("Split", "When a pudding that is Medium or larger is subjected to lightning or slashing damage, it splits into two new puddings if it has at least 10 hit points. Each new pudding has hit points equal to half the original pudding's, rounded down. New puddings are one size smaller than the original pudding."));

		reducedHelmedHorror = new Creature();
		reducedHelmedHorror.name = "Reuced-threat Helmed Horror";
		reducedHelmedHorror.shortName = "The helmed horror";
		reducedHelmedHorror.size = Size.MEDIUM;
		reducedHelmedHorror.type = CreatureType.CONSTRUCT;
		reducedHelmedHorror.alignment = Alignment.NEUTRAL;
		reducedHelmedHorror.ac = 20;
		reducedHelmedHorror.armorNote = "plate, shield";
		reducedHelmedHorror.hitDice = new DiceRoll(4, 8, 8);
		reducedHelmedHorror.speed = 30;
		reducedHelmedHorror.speeds.put(MovementType.FLY, 30);
		reducedHelmedHorror.abilityScores.put(Ability.STRENGTH, 16);
		reducedHelmedHorror.abilityScores.put(Ability.DEXTERITY, 11);
		reducedHelmedHorror.abilityScores.put(Ability.CONSTITUTION, 14);
		reducedHelmedHorror.abilityScores.put(Ability.INTELLIGENCE, 8);
		reducedHelmedHorror.abilityScores.put(Ability.WISDOM, 8);
		reducedHelmedHorror.abilityScores.put(Ability.CHARISMA, 8);
		reducedHelmedHorror.skills.put(Skill.PERCEPTION, 3);
		reducedHelmedHorror.resistances.put("nonmagical weapons that aren't adamantine", new HashSet<>(Arrays.asList(DamageType.BLUDGEONING, DamageType.PIERCING, DamageType.SLASHING)));
		reducedHelmedHorror.conditionImmunities.addAll(Arrays.asList(Condition.BLINDED, Condition.CHARMED, Condition.DEAFENED, Condition.EXHAUSTION, Condition.FRIGHTENED, Condition.PARALYZED, Condition.PETRIFIED, Condition.POISONED, Condition.STUNNED));
		reducedHelmedHorror.senses.put(VisionType.BLINDSIGHT, 60);
		reducedHelmedHorror.languages.add("understands the languages of its creator but can't speak");
		reducedHelmedHorror.challengeRating = 2;
		reducedHelmedHorror.features = Arrays.asList(new Feature("Magic Resistance", "The helmed horror has advantage on saving throws against spells and other magical effects."), //
				new Feature("Spell Immunity", "The helmed horror is immune to three spells chosen by its creator. Typical immunities include fireball, heat metal, and lightning bolt."));
		reducedHelmedHorror.actions = Arrays.asList(new Action("Multiattack", "The helmed horror makes two longsword attacks."), //
				new Attack("Longsword", Type.MELEE_WEAPON, 5, "reach 5 ft., one target", ", or 8 (1d10 + 3) slashing damage if used with two hands.", new Attack.Damage(new DiceRoll(1, 8, 3), DamageType.SLASHING)));

		gorvanIronheart = new Creature();
		gorvanIronheart.name = "Gorvan Ironheart";
		gorvanIronheart.shortName = "Gorvan";
		gorvanIronheart.size = Size.MEDIUM;
		gorvanIronheart.type = CreatureType.HUMANOID;
		gorvanIronheart.alignment = Alignment.CHAOTIC_GOOD;
		gorvanIronheart.ac = 13;
		gorvanIronheart.armorNote = "chain shirt";
		gorvanIronheart.hitDice = new DiceRoll(5, 8, 10);
		gorvanIronheart.speed = 30;
		gorvanIronheart.abilityScores.put(Ability.STRENGTH, 12);
		gorvanIronheart.abilityScores.put(Ability.DEXTERITY, 10);
		gorvanIronheart.abilityScores.put(Ability.CONSTITUTION, 14);
		gorvanIronheart.abilityScores.put(Ability.INTELLIGENCE, 13);
		gorvanIronheart.abilityScores.put(Ability.WISDOM, 16);
		gorvanIronheart.abilityScores.put(Ability.CHARISMA, 13);
		gorvanIronheart.skills.put(Skill.MEDICINE, 7);
		gorvanIronheart.skills.put(Skill.PERSUASION, 3);
		gorvanIronheart.skills.put(Skill.RELIGION, 4);
		gorvanIronheart.resistances.put("nonmagical weapons that aren't adamantine", new HashSet<>(Arrays.asList(DamageType.BLUDGEONING, DamageType.PIERCING, DamageType.SLASHING)));
		gorvanIronheart.conditionImmunities.addAll(Arrays.asList(Condition.BLINDED, Condition.CHARMED, Condition.DEAFENED, Condition.EXHAUSTION, Condition.FRIGHTENED, Condition.PARALYZED, Condition.PETRIFIED, Condition.POISONED, Condition.STUNNED));
		gorvanIronheart.languages.add("Common");
		gorvanIronheart.languages.add("Dwarvish");
		gorvanIronheart.challengeRating = 2;

		final Map<String, Map<String, String>> priestSpells = new LinkedHashMap<>();

		final Map<String, String> priestCantrips = new HashMap<>();
		Stream.of("light", "sacred flame", "thaumaturgy").forEach(s -> priestCantrips.put(s, ""));
		priestSpells.put("Cantrips (at will)", priestCantrips);

		final Map<String, String> priestLevel1 = new HashMap<>();
		Stream.of("cure wounds", "guiding bolt", "sanctuary").forEach(s -> priestLevel1.put(s, ""));
		priestSpells.put("1st level (4 slots)", priestLevel1);

		final Map<String, String> priestLevel2 = new HashMap<>();
		Stream.of("lesser restoration", "spritual weapon").forEach(s -> priestLevel2.put(s, ""));
		priestSpells.put("2nd level (3 slots)", priestLevel2);

		final Map<String, String> priestLevel3 = new HashMap<>();
		Stream.of("dispel magic", "spirit guardians").forEach(s -> priestLevel3.put(s, ""));
		priestSpells.put("3rd level (2 slots)", priestLevel3);

		gorvanIronheart.features = Arrays.asList(new Feature("Divine Eminence", "As a bonus action, the priest can expend a spell slot to cause its melee weapon attacks to magically deal an extra 10 (3d6) radiant damage to a target on a hit. This benefit lasts until the end of the turn. If the priest expends a spell slot of 2nd level or higher, the extra damage increases by ld6 for each level above 1st."), //
				new Spellcasting(null, "Gorvan", 4, Ability.WISDOM, 13, "his", 5, null, "cleric", null, priestSpells));
		gorvanIronheart.actions = Arrays.asList(new Attack("Mace", Type.MELEE_WEAPON, 3, "reach 5 ft., one target", null, new Attack.Damage(new DiceRoll(1, 6, 1), DamageType.BLUDGEONING)));

		reducedClayGolem = new Creature();
		reducedClayGolem.name = "Reduced-threat Clay Golem";
		reducedClayGolem.shortName = "The golem";
		reducedClayGolem.size = Size.LARGE;
		reducedClayGolem.type = CreatureType.CONSTRUCT;
		reducedClayGolem.alignment = Alignment.UNALIGNED;
		reducedClayGolem.ac = 14;
		reducedClayGolem.armorNote = "natural armor";
		reducedClayGolem.hitDice = new DiceRoll(7, 10, 21);
		reducedClayGolem.speed = 20;
		reducedClayGolem.abilityScores.put(Ability.STRENGTH, 18);
		reducedClayGolem.abilityScores.put(Ability.DEXTERITY, 7);
		reducedClayGolem.abilityScores.put(Ability.CONSTITUTION, 16);
		reducedClayGolem.abilityScores.put(Ability.INTELLIGENCE, 1);
		reducedClayGolem.abilityScores.put(Ability.WISDOM, 6);
		reducedClayGolem.abilityScores.put(Ability.CHARISMA, 1);
		reducedClayGolem.immunities.put("nonmagical weapons that aren't adamantine", new HashSet<>(Arrays.asList(DamageType.BLUDGEONING, DamageType.PIERCING, DamageType.SLASHING)));
		reducedClayGolem.immunities.put(null, new HashSet<>(Arrays.asList(DamageType.ACID, DamageType.POISON, DamageType.PSYCHIC)));
		reducedClayGolem.conditionImmunities.addAll(Arrays.asList(Condition.CHARMED, Condition.EXHAUSTION, Condition.FRIGHTENED, Condition.PARALYZED, Condition.PETRIFIED, Condition.POISONED));
		reducedClayGolem.senses.put(VisionType.BLINDSIGHT, 60);
		reducedClayGolem.languages.add("understands the languages of its creator but can't speak");
		reducedClayGolem.challengeRating = 7;
		reducedClayGolem.features = Arrays.asList(new Feature("Acid Absorbtion", "Whenever the golem is subjected to acid damage, it takes no damage and instead regains a number of hit points equal to the acid damage dealt."), //
				new Feature("Berserk", "Whenever the golem starts its turn with 30 hit points or fewer, roll a d6. On a 6, the golem goes berserk. On each of its turns while berserk, the golem attacks the nearest creature it can see. If no creature is near enough to move to and attack, the golem attacks an object, with a preference for an object smaller than itself. Once the golem goes berserk, it continues to do so until it is dedstroyed or regains all its hit points."), //
				new Feature("Immutable Form", "The golem is immune to any spell or effect that would alter its form."), //
				new Feature("Magic Resistance", "The golem has advantage on saving throws against spells and other magical effects."), //
				new Feature("Magic Weapons", "The golem's attacks are magical."));
		reducedClayGolem.actions = Arrays.asList(new Action("Multiattack", "The golem makes two slam attacks."), //
				new Attack("Slam", Type.MELEE_WEAPON, 7, "reach 5 ft., one target", ". If the target is a creature, it must succeed on a DC 14 Constitution saving throw or have its hit point maximum reduced by an amount equal to the damage taken. The target dies if this attack reduces its hit point maximum to 0. The reduction lasts until removed by the greater restoration spell or other magic.", new Attack.Damage(new DiceRoll(2, 10, 4), DamageType.BLUDGEONING)), //
				new Action("Haste", "Recharge 5-6", "Until the end of its next turn, the golem magically gains a +2 bonus to its AC, has advantage on Dexterity saving throws, and can use its slam attack as a bonus action."));

		reducedFleshGolem = new Creature();
		reducedFleshGolem.name = "Reduced-threat Flesh Golem";
		reducedFleshGolem.shortName = "The golem";
		reducedFleshGolem.size = Size.MEDIUM;
		reducedFleshGolem.type = CreatureType.CONSTRUCT;
		reducedFleshGolem.alignment = Alignment.UNALIGNED;
		reducedFleshGolem.ac = 8;
		reducedFleshGolem.hitDice = new DiceRoll(6, 8, 18);
		reducedFleshGolem.speed = 30;
		reducedFleshGolem.abilityScores.put(Ability.STRENGTH, 17);
		reducedFleshGolem.abilityScores.put(Ability.DEXTERITY, 7);
		reducedFleshGolem.abilityScores.put(Ability.CONSTITUTION, 16);
		reducedFleshGolem.abilityScores.put(Ability.INTELLIGENCE, 4);
		reducedFleshGolem.abilityScores.put(Ability.WISDOM, 8);
		reducedFleshGolem.abilityScores.put(Ability.CHARISMA, 3);
		reducedFleshGolem.immunities.put("nonmagical weapons that aren't adamantine", new HashSet<>(Arrays.asList(DamageType.BLUDGEONING, DamageType.PIERCING, DamageType.SLASHING)));
		reducedFleshGolem.immunities.put(null, new HashSet<>(Arrays.asList(DamageType.LIGHTNING, DamageType.POISON)));
		reducedFleshGolem.conditionImmunities.addAll(Arrays.asList(Condition.CHARMED, Condition.EXHAUSTION, Condition.FRIGHTENED, Condition.PARALYZED, Condition.PETRIFIED, Condition.POISONED));
		reducedFleshGolem.senses.put(VisionType.BLINDSIGHT, 60);
		reducedFleshGolem.languages.add("understands the languages of its creator but can't speak");
		reducedFleshGolem.challengeRating = 4;
		reducedFleshGolem.features = Arrays.asList(new Feature("Berserk", "Whenever the golem starts its turn with 20 hit points or fewer, roll a d6. On a 6, the golem goes berserk. On each of its turns while berserk, the golem attacks the nearest creature it can see. If no creature is near enough to move to and attack, the golem attacks an object, with a preference for an object smaller than itself. Once the golem goes berserk, it continues to do so until it is dedstroyed or regains all its hit points."), //
				new Feature("Aversion of Fire", "Whenever the golem takes fire damage, it has disadvantage on attack rolls and ability checks until the end of its next turn."), //
				new Feature("Immutable Form", "The golem is immune to any spell or effect that would alter its form."), //
				new Feature("Lightning Absorbtion", "Whenever the golem is subjected to lightning damage, it takes no damage and instead regains a number of hit points equal to the lightning damage dealt."), //
				new Feature("Magic Resistance", "The golem has advantage on saving throws against spells and other magical effects."), //
				new Feature("Magic Weapons", "The golem's attacks are magical."));
		reducedFleshGolem.actions = Arrays.asList(new Action("Multiattack", "The golem makes two slam attacks."), //
				new Attack("Slam", Type.MELEE_WEAPON, 6, "reach 5 ft., one target", null, new Attack.Damage(new DiceRoll(2, 8, 3), DamageType.BLUDGEONING)));

		reducedStoneGolem = new Creature();
		reducedStoneGolem.name = "Reduced-threat Stone Golem";
		reducedStoneGolem.shortName = "The golem";
		reducedStoneGolem.size = Size.LARGE;
		reducedStoneGolem.type = CreatureType.CONSTRUCT;
		reducedStoneGolem.alignment = Alignment.UNALIGNED;
		reducedStoneGolem.ac = 17;
		reducedStoneGolem.armorNote = "natural armor";
		reducedStoneGolem.hitDice = new DiceRoll(9, 10, 36);
		reducedStoneGolem.speed = 30;
		reducedStoneGolem.abilityScores.put(Ability.STRENGTH, 20);
		reducedStoneGolem.abilityScores.put(Ability.DEXTERITY, 7);
		reducedStoneGolem.abilityScores.put(Ability.CONSTITUTION, 18);
		reducedStoneGolem.abilityScores.put(Ability.INTELLIGENCE, 2);
		reducedStoneGolem.abilityScores.put(Ability.WISDOM, 9);
		reducedStoneGolem.abilityScores.put(Ability.CHARISMA, 1);
		reducedStoneGolem.immunities.put("nonmagical weapons that aren't adamantine", new HashSet<>(Arrays.asList(DamageType.BLUDGEONING, DamageType.PIERCING, DamageType.SLASHING)));
		reducedStoneGolem.immunities.put(null, new HashSet<>(Arrays.asList(DamageType.POISON, DamageType.PSYCHIC)));
		reducedStoneGolem.conditionImmunities.addAll(Arrays.asList(Condition.CHARMED, Condition.EXHAUSTION, Condition.FRIGHTENED, Condition.PARALYZED, Condition.PETRIFIED, Condition.POISONED));
		reducedStoneGolem.senses.put(VisionType.BLINDSIGHT, 60);
		reducedStoneGolem.languages.add("understands the languages of its creator but can't speak");
		reducedStoneGolem.challengeRating = 8;
		reducedStoneGolem.features = Arrays.asList(new Feature("Immutable Form", "The golem is immune to any spell or effect that would alter its form."), //
				new Feature("Magic Resistance", "The golem has advantage on saving throws against spells and other magical effects."), //
				new Feature("Magic Weapons", "The golem's attacks are magical."));
		reducedStoneGolem.actions = Arrays.asList(new Action("Multiattack", "The golem makes two slam attacks."), //
				new Attack("Slam", Type.MELEE_WEAPON, 8, "reach 5 ft., one target", null, new Attack.Damage(new DiceRoll(3, 8, 5), DamageType.BLUDGEONING)), //
				new Action("Slow", "Recharge 5-6",
						"The golem targets one or more creatures that it can see within 10 feet of it. Each target must make a DC 16 Wisdom saving throw against this magic. On a failed save, a target can't use reactions, its speed is halved, and it can't make more than one attack on its turn. In addition, the target can take either an action or a bonus action on its turn, not both. These effects last for 1 minute. A target can repeat the saving throw at the end of each of its turns, ending the effect on itself on a success."));

		cockatrice = new Creature();
		cockatrice.name = "Cockatrice+";
		cockatrice.shortName = "The cockatrice";
		cockatrice.size = Size.SMALL;
		cockatrice.type = CreatureType.MONSTROSITY;
		cockatrice.alignment = Alignment.UNALIGNED;
		cockatrice.ac = 11;
		cockatrice.hitDice = new DiceRoll(7, 6, 14);
		cockatrice.speed = 30;
		cockatrice.abilityScores.put(Ability.STRENGTH, 8);
		cockatrice.abilityScores.put(Ability.DEXTERITY, 14);
		cockatrice.abilityScores.put(Ability.CONSTITUTION, 14);
		cockatrice.abilityScores.put(Ability.INTELLIGENCE, 2);
		cockatrice.abilityScores.put(Ability.WISDOM, 15);
		cockatrice.abilityScores.put(Ability.CHARISMA, 5);
		cockatrice.senses.put(VisionType.DARKVISION, 60);
		cockatrice.challengeRating = 1;
		cockatrice.actions = Arrays.asList(new Attack("Bite", Type.MELEE_WEAPON, 4, "reach 5 ft., one creature", ", and the target must succeed on a DC 12 Constitution saving throw against being magically petrified. On a failed save, the creature begins to turn to stone and is restrained. It must repeat the saving throw at the end of its next turn. On a success, the effect ends. On a failure, the creature is petrified for 24 hours.", new Attack.Damage(new DiceRoll(1, 4, 2), DamageType.PIERCING)));

		shalendraFloshin = new Creature();
		shalendraFloshin.name = "Shalendra Floshin";
		shalendraFloshin.shortName = "Shalendra";
		shalendraFloshin.size = Size.MEDIUM;
		shalendraFloshin.type = CreatureType.HUMANOID;
		shalendraFloshin.alignment = Alignment.NEUTRAL_GOOD;
		shalendraFloshin.ac = 18;
		shalendraFloshin.hitDice = new DiceRoll(8, 8, 16);
		shalendraFloshin.speed = 30;
		shalendraFloshin.abilityScores.put(Ability.STRENGTH, 16);
		shalendraFloshin.abilityScores.put(Ability.DEXTERITY, 13);
		shalendraFloshin.abilityScores.put(Ability.CONSTITUTION, 14);
		shalendraFloshin.abilityScores.put(Ability.INTELLIGENCE, 12);
		shalendraFloshin.abilityScores.put(Ability.WISDOM, 11);
		shalendraFloshin.abilityScores.put(Ability.CHARISMA, 15);
		shalendraFloshin.savingThrows.put(Ability.CONSTITUTION, 4);
		shalendraFloshin.savingThrows.put(Ability.WISDOM, 2);
		shalendraFloshin.senses.put(VisionType.DARKVISION, 60);
		shalendraFloshin.languages.add("Common");
		shalendraFloshin.languages.add("Elvish");
		shalendraFloshin.challengeRating = 3;
		shalendraFloshin.features = Arrays.asList(new Feature("Brave", "Shalendra has advantage on saving throws made against being frightened."), //
				new Feature("Fey Ancestry", "Shalendra has advantage on saving throws against being charmed, and magic can't put her to sleep."));
		shalendraFloshin.actions = Arrays.asList(new Action("Multiattack", "Shalendra makes two melee attacks."), //
				new Attack("Greatsword", Type.MELEE_WEAPON, 5, "reach 5 ft., one target", null, new Attack.Damage(new DiceRoll(2, 6, 3), DamageType.SLASHING)), //
				new Attack("Longbow", Type.RANGED_WEAPON, 3, "range 150/600 ft., one target.", null, new Attack.Damage(new DiceRoll(1, 8, 1), DamageType.PIERCING)), //
				new Action("Leadership", "Recharges after a Short or Long Rest", "For 1 minute, Shalendra can utter a special command or warning whenever a nonhostile creature that she can see within 30 feet of her makes an attack roll or a saving throw. The creature can add a d4 to its roll provided it can hear and understand Shalendra. A creature can benefit from only one Leadership die at a time. This effect ends if Shalendra is incapacitated."));

		reducedHezrou = new Creature();

		reducedVrock = new Creature();

		tarulVar = new Creature();

		sorlan = new Creature();

		drevin = new Creature();

		reducedBasilisk = new Creature();

		reducedDarkmantle = new Creature();

		reducedEttercap = new Creature();

		reducedCarrionCrawler = new Creature();

		reducedBehir = new Creature();
		reducedBehir.name = "Young Behir";
		reducedBehir.shortName = "The behir";
		reducedBehir.size = Size.LARGE;
		reducedBehir.type = CreatureType.MONSTROSITY;
		reducedBehir.alignment = Alignment.NEUTRAL_EVIL;
		reducedBehir.ac = 17;
		reducedBehir.armorNote = "natural armor";
		reducedBehir.hitDice = new DiceRoll(10, 10, 30);
		reducedBehir.speed = 50;
		reducedBehir.speeds.put(MovementType.CLIMB, 40);
		reducedBehir.abilityScores.put(Ability.STRENGTH, 21);
		reducedBehir.abilityScores.put(Ability.DEXTERITY, 14);
		reducedBehir.abilityScores.put(Ability.CONSTITUTION, 16);
		reducedBehir.abilityScores.put(Ability.INTELLIGENCE, 5);
		reducedBehir.abilityScores.put(Ability.WISDOM, 12);
		reducedBehir.abilityScores.put(Ability.CHARISMA, 10);
		reducedBehir.immunities.put(null, new HashSet<>(Arrays.asList(DamageType.LIGHTNING)));
		reducedBehir.senses.put(VisionType.DARKVISION, 90);
		reducedBehir.languages.add("Draconic");
		reducedBehir.challengeRating = 7;
		reducedBehir.actions = Arrays.asList(new Attack("Bite", Type.MELEE_WEAPON, 8, "reach 10 ft., one target", null, new Attack.Damage(new DiceRoll(3, 10, 5), DamageType.PIERCING)), //
				new Action("Lightning Breath", "Recharge 5-6", "The behir exhales a line of lightning that is 20 feet long and 5 feet wide. Each creature in that line must make a DC 15 Dexterity saving throw, taking 66 (12d10) lightning damage on a failed save, or half as much damage on a successful one."));

		reducedHookHorror = new Creature();

		reducedWyvern = new Creature();

		enchanter = new Creature();

		malformedKraken = new Creature();

		reducedDragonTurtle = new Creature();

	}

}
