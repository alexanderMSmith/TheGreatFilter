/**
 * This class runs the bot, parses commands to generate characters based on 
 * randomly selected traits from JSON files. The default traits are races,
 * feats, backgrounds, stats, and languages. 
 * 
 * @author Alexander Smith
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;


public class TheFiltersWill extends ListenerAdapter{
    private static JDA api; 

    private static final TraitLookup feats = new TraitLookup("/data/feats.json");
    private static final TraitLookup races = new TraitLookup("data/raceTable.json");
    private static final TraitLookup stats = new TraitLookup("/data/stats.json"); 
    private static final TraitLookup backgrounds = new 
        TraitLookup("/data/backgrounds.json"); 
    private static final TraitLookup languages = new 
        TraitLookup("/data/languages.json"); 
    private static final TraitLookup settings = new 
        TraitLookup("/data/settings.json");

    public static final int RACE_RANGE = Integer.parseInt(settings.getCharacteristic(1));
    public static final int FEAT_RANGE = Integer.parseInt(settings.getCharacteristic(2));
    public static final int BACKGROUND_RANGE = Integer.parseInt(settings.getCharacteristic(3));
    public static final int LANGUAGE_RANGE = Integer.parseInt(settings.getCharacteristic(4));
    public static final int LANGUAGE_PRIORITY_RANGE = Integer.parseInt(settings.getCharacteristic(5));
    public static final int STAT_RANGE = Integer.parseInt(settings.getCharacteristic(6));
    public static final int STAT_PRIORITY_RANGE = Integer.parseInt(settings.getCharacteristic(7));
    /**
     * @brief The main method initialises the bot, setting up the commands it can use.
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException{
        api = JDABuilder.createDefault("")
                .addEventListeners(new TheFiltersWill())
                .build();

        CommandListUpdateAction commands = api.updateCommands(); //Everything from here on out makes the commands.

        commands.addCommands(
            Commands.slash("newvictim", "Creates another soul for The Great Filter")
        );

        commands.queue();

    }

    /**
     * @brief This class handles when a user uses a slash action, getting information based on the name
     * of the slash command then initialising the appropriate commands. Contains a few failsafe errors
     * in case an error is immediately found.
     * @param event The command the user inputted and some relevant info. 
     */
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event){
        if (event.getGuild() == null)
            return;

        switch(event.getName()){
            case "newvictim":
                victim(event);
                break;
            default:
                event.reply("**Unauthorised**").setEphemeral(true).queue();
        }
    }


    /**
     * This method generates a new character with random traits and replies to the event with the character's details.
     * @param event The slash command interaction event triggering the character creation.
     */
    public void victim(SlashCommandInteractionEvent event){
        int race = (int) (Math.random() * RACE_RANGE + 1);
        ArrayList<String> charaFeats = new ArrayList<>();
        boolean keepGoing = true;
        int featCount = 1;
        while(keepGoing){
            int number = (int) (Math.random()*FEAT_RANGE+1);
            if(number != FEAT_RANGE && !charaFeats.contains(getCharacteristic(number, feats))){
                String copy = getCharacteristic(number, feats);
                charaFeats.add(copy);
            }else{
                featCount++;
            }
            if(charaFeats.size() >= featCount){
                keepGoing = false;
            }
        }
        int back = (int) (Math.random() * BACKGROUND_RANGE + 1);
        String background = getCharacteristic(back, backgrounds);
        ArrayList<Integer> stats = new ArrayList<>();
        while(stats.size() < 6){
            ArrayList<Integer> dice = new ArrayList<>();
            while(dice.size() < 4){
                int roll =  (int) (Math.random() * 6 + 1);
                dice.add(roll);
            }
            dice.sort(Comparator.reverseOrder());
            int sum = dice.get(0) + dice.get(1) +dice.get(2);
            stats.add(sum);
        }
        String message = "STR: " + stats.get(0) + "\nDEX: " + stats.get(1) +
            "\nCON: " + stats.get(2) + "\nWIS: " + stats.get(3) + "\nINT: " +
            stats.get(4) + "\nCHA: " + stats.get(5);
        message += "\nFeat: " + charaFeats.toString() + "\nBackground: " + background;
        message += "\nRace: " + getCharacteristic(race, races);
        message += "\nStat Priority: " + getStatPriority().toString();
        message += "\nLanguage Priority " + getLanguagePriority().toString();
        event.reply(message).queue();
    }
    /**
     * This method generates a set of prioritized stats for a character, up to a predefined range.
     * @return A HashSet containing the prioritized stats.
     */
    public HashSet<String> getStatPriority(){
        HashSet<String> statPrio = new HashSet<>();
        int i = 0;
        while(i < STAT_PRIORITY_RANGE){
            int choice = (int) (Math.random()*STAT_RANGE+1);
            boolean added = statPrio.add(getCharacteristic(choice, stats));
            if(added){
                i++;
            }
        }
        return statPrio;
    }
    /**
     * This method generates a set of prioritized languages for a character, up
     * to a predefined range.
     * @return A HashSet containing the prioritized languages.
     */
    public HashSet<String> getLanguagePriority(){
        HashSet<String> langPrio = new HashSet<>();
        int i = 0;
        while (i < LANGUAGE_PRIORITY_RANGE){
            int choice = (int) (Math.random()*LANGUAGE_RANGE+1);
            boolean added = langPrio.add(getCharacteristic(choice, languages));
            if(added){
                i++;
            }
        }

        return langPrio;
    }
    /**
     * This method handles the logic for basic lookup of traits in the json files.
     * @param id The ID of the trait to be looked up.
     * @param type The trait type being looked up.
     * @return The characteristic name corresponding to the given ID and type.
     */
    public String getCharacteristic(int id, TraitLookup type){
        return type.getCharacteristic(id);
    }
}


