# The Great Filter
A Discord bot that automatically creates a classless D&D 5e character with instructions on how to distribute stats, choose languages, and more. Meant to be used as part of the Great Filter rules set to make characters and get them through scenarios ASAP.

# 🌟 Highlights

* Rapid creation of classless characters for D&D 5e
* Customizable tables for races, languages, stats, and output settings to fit many use cases
* Simple, easy-to-use slash command interface
* A high variance in power level correlated with rarity by design

# ℹ️ Overview
The Great Filter bot uses two classes and a set of JSON files to create classless characters for D&D 5e. In the default settings, races are biased so that more common and less exotic races occur more often than the exotic races. 

The first class is GreatFilterBot.java, which runs the bot itself. The class runs the bot, parses commands to generate characters based on randomly selected traits from JSON files. This information is then passed to CharacterCreator.java, then the information is received and printed in the Discord channel that the slash command was activated in.

The second class is CharacterCreator.java. This class creates the character by combining outputs from the TraitsSelector, StatRoller, and TraitLookup classes. It compiles it into a single message, then returns it to GreatFilterBot.java.

The third class is TraitLookup.java. This class searches an associated JSON file decided during the creation of the instance. Each JSON file contains a mapping of integer IDs to characteristic names, except settings.json, which reuses the method in order to store integers that are parsed as int by the first class.

The fourth class is StatRoller.java. This class rolls 4d6 dice, drops the lowest, and assigns that to the first empty ability score. This operation is repeated until every ability score is filled out, then the total is returned.

The fifth class is TraitSelector.java. The generates the language priority, stat priority, background, race, and feats for characters.

The first JSON file, backgrounds.json, contains 45 official backgrounds from D&D 5e, including their variants. As a result, some backgrounds, such as Criminal/Spy and Sailor/Pirate, are slightly more common.

The second JSON file, feats.json, contains 124 official feats from D&D 5e. In addition, if a 125 is rolled, an additional feat is selected. This can occur multiple times.

The third JSON file, languages.json, contains 15 D&D languages. It is assumed all characters speak Common. In this case, these languages are taken from left to right if ordinarily it would be the player's choice.

The fourth JSON file, raceTable.json, contains many official D&D races and their subraces. Unlike other data files, races an amount of slots that is correlated with their expected rarity in the world. 

The fifth JSON file, settings.json. This contains settings for the main Discord bot. It selects how long you want the stat and language priority list you want it to be, the max value for each of the race, feats, and background tables.

The last JSON file is stats.json. This contains the 6 base 5e stats, which are rolled with 4d6, dropping the lowest value.

## ✍️ Author
[Alexander Smith](https://github.com/alexanderMSmith), a Software Engineer, tabletop roleplayer, and storyteller. 

# 🚀 Getting Started
I will be running this bot full-time for at least a few weeks after this repository goes public. If this support ceases, send me an email at thecomputingsmith@gmail.com and I will get the bot back online as soon as I'm able to. In addition, you will require the Java Discord API and its own dependencies.

## Inviting the bot to your Discord server
1) Click this [link](https://discord.com/oauth2/authorize?client_id=1440442322000154645) to invite the bot to a Discord server you have moderator permissions for.
2) Give the bot the permissions it requested.
3) Run the command with /newvictim once the bot has joined the server.

# 🔧 Support
If you need help or want to help, feel free to email me at thecomputingsmith@gmail.com.
