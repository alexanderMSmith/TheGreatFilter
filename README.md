![Java](https://img.shields.io/badge/Java-21-blue)
![License](https://img.shields.io/badge/License-Apache_2.0-green)
![Discord](https://img.shields.io/badge/Discord-Bot-5865F2)

# The Great Filter
A Discord bot that automatically creates a classless D&D 5e character with instructions on how to distribute stats, choose languages, and more. Meant to be used as part of the Great Filter rules set to make characters and get them through scenarios ASAP.

# 🌟 Highlights

* Rapid creation of classless characters for D&D 5e
* Customizable tables for races, languages, stats, and output settings to fit many use cases
* Simple, easy-to-use slash command interface
* A high variance in power level correlated with rarity by design

## Example Output
<img width="536" height="310" alt="image" src="https://github.com/user-attachments/assets/c1f263e0-c904-4b1d-9007-1fd06a2cb470" />

  
# 🚀 Getting Started
I will be running this bot full-time for at least a few weeks after this repository goes public. If this support ceases, send me an email at thecomputingsmith@gmail.com, and I will get the bot back online as soon as I'm able to. 

## Inviting the bot to your Discord server
1) Click this [link](https://discord.com/oauth2/authorize?client_id=1440442322000154645) to invite the bot to a Discord server you have moderator permissions for.
2) Give the bot the permissions it requested.
3) Run the command with /newvictim once the bot has joined the server.
4) You will need to manually add ability score changes from races and feats. Racial feats must be determined manually as well.

## 🏗️ Build & Run (Maven)
If you want to change any of the settings the bot uses, you will need to download and run the bot yourself.

**Prerequisites**
* Java 21
* Maven 3.8+
* A valid Discord bot token.

## 📦 Building the Project
From the project root, run:
```
mvn clean package
```
This will compile the bot and create a runnable JAR file in:
```
target/FilterBot-0.1-beta.jar
```
## ▶️ Running the Bot
After building, run the JAR with:
```
java -jar target/FilterBot-0.1-beta.jar
```
Make sure your JSON configuration files are located in the /resources directory or alongside the JAR (depending on how your code loads resources).

The bot will start up using the token you configured inside GreatFilterBot.java.

## Step by Step Guide
1) Acquire a bot token from the [Discord Developer](https://discord.com/developers/) portal.
2) Build the project using the Build and Run instructions.
3) Place the token in the spot marked [Bot Token] in GreatFilterBot.java. Make sure that the quotes around the brackets remain.
4) Open the JSON files with a text editor. The quoted numbers on the left are the key slots.
   - If you want to make an outcome more likely, give it an additional slot at the end of the list.
   - If you want to make the outcome less likely, remove it from the list. 
   - You may also add additional slots for new outcomes.
5) Ensure there are no empty key slots between the first and last entry. If you delete Archeologist from the backgrounds, make sure  that the 3 key is replaced or every key is reduced in value by one.
6) Change settings.json to reflect these new changes. The numbers in order are the race range for the race table, the feat range for the feat table, the background range for the background table, the language range and amount of priority languages you want each character to have, and lastly the stat range and the stat priority range you want each character to have.

# Dependencies
This project utilizes Java version 21 and JDA 5.0.0-beta.21

# ℹ️ Overview
The Great Filter bot uses five classes and a set of JSON files to create classless characters for D&D 5e. In the default settings, races are biased so that more common and less exotic races occur more often than the exotic races. 

## Java Classes
The first class is GreatFilterBot.java, which runs the bot itself. The class additionally parses commands to generate characters based on randomly selected traits from JSON files. This information is then passed to CharacterCreator.java, and then the information is received and printed in the Discord channel where the slash command was activated.

The second class is CharacterCreator.java. This class creates the character by combining outputs from the TraitsSelector, StatRoller, and TraitLookup classes. It compiles it into a single message, then returns it to GreatFilterBot.java.

The third class is TraitLookup.java. This class searches an associated JSON file decided during the creation of the instance. Each JSON file contains a mapping of integer IDs to characteristic names, except settings.json, which reuses the method in order to store integers that are parsed as int by the first class.

The fourth class is StatRoller.java. This class rolls 4d6 dice, drops the lowest, and assigns that to the first empty ability score. This operation is repeated until every ability score is filled out, then the total is returned.

The fifth class is TraitSelector.java. It generates the language priority, stat priority, background, race, and feats for characters.


## JSON Files
backgrounds.json contains 45 official backgrounds from D&D 5e, including their variants. As a result, some backgrounds, such as Criminal/Spy and Sailor/Pirate, are slightly more common.

feats.json contains 124 official feats from D&D 5e. In addition, if a 125 is rolled, an additional feat is selected. This can occur multiple times.

languages.json contains 15 D&D languages. It is assumed all characters speak Common. In this case, these languages are taken from left to right, if ordinarily it would be the player's choice.

raceTable.json contains many official D&D races and their subraces. Unlike other data files, races have an amount of slots that is correlated with their expected rarity in the world. 

settings.json contains settings for the main Discord bot. The length of the stat and language priority list, and  the max value for each of the race, feats, and background tables.

stats.json contains the 6 base 5e stats, which are rolled with 4d6, dropping the lowest value.

# 🔧 Support
If you need help or want to help, feel free to email me at thecomputingsmith@gmail.com.

## ✍️ Author
[Alexander Smith](https://github.com/alexanderMSmith), a Software Engineer, tabletop roleplayer, and storyteller. 

# License
This project uses the Apache-2.0 license, which can be found in the main project folder. 
