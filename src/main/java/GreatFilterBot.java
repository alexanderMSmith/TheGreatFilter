/**
 * This class runs the bot, parses commands to generate characters based on 
 * randomly selected traits from JSON files. The default traits are races,
 * feats, backgrounds, stats, and languages. 
 * 
 * @author Alexander Smith
 * @version 1.0
 */

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;


public class GreatFilterBot extends ListenerAdapter{
    private static JDA api;
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
     *  The main method initialises the bot, setting up the commands it can use.
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException{
        api = JDABuilder.createDefault("[Bot Token]")
                .addEventListeners(new GreatFilterBot())
                .build();

        CommandListUpdateAction commands = api.updateCommands(); //Everything from here on out makes the commands.
        commands.addCommands(
            Commands.slash("newvictim", "Creates another soul for The Great Filter")
        );
        commands.queue();
    }

    /**
     * This class handles when a user uses a slash action, getting information based on the name
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
                newCharacter(event);
                break;
            default:
                event.reply("**Unauthorised**").setEphemeral(true).queue();
        }
    }

    /**
     * This method generates a new character with random traits and replies to the event with the character's details.
     * @param event The slash command interaction event triggering the character creation.
     */
    public void newCharacter(SlashCommandInteractionEvent event){
        String message = CharacterCreator.createCharacter(RACE_RANGE, FEAT_RANGE, BACKGROUND_RANGE,
            LANGUAGE_PRIORITY_RANGE, LANGUAGE_RANGE, STAT_PRIORITY_RANGE, STAT_RANGE);
        event.reply(message).queue();
    }
}



