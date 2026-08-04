import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Character {
    private final String charaBackground;
    private final String charaRace;
    private final List<Integer> charaStats = new ArrayList<>();;
    private final List<String> charaFeats = new ArrayList<>();;
    private List<String> charaStatPrio = new ArrayList<>();;
    private List<String> charaLanguages = new ArrayList<>();;


    public Character(List<String> newTraits, List<Integer> newStats, List<String> newFeats, 
            String newStatPrio, String newLanguages){
        charaBackground = newTraits.get(0);
        charaRace = newTraits.get(1);
        for(int i = 0; i < newStats.size(); i++){
            charaStats.add(newStats.get(i));
        }
        for(int i = 0; i < newFeats.size(); i++){
            charaFeats.add(newFeats.get(i));
        }
        charaStatPrio = Arrays.asList(newStatPrio.split(","));
        charaLanguages = Arrays.asList(newLanguages.split(","));
    }

    @Override
    public String toString(){
        StringBuilder message = new StringBuilder();
        String stats = printStats(charaStats);
        message.append(stats);
        message.append("\nFeats: ");
        message.append(charaFeats);
        message.append("\nBackground: ");
        message.append(charaBackground);
        message.append("\nRace: ");
        message.append(charaRace);
        message.append("\nStat Priority: ");
        message.append(charaStatPrio);
        message.append("\nLanguage Priority: ");
        message.append(charaLanguages);
        return message.toString();
    }

    public static String printStats(List<Integer> rolledStats){
        StringBuilder message =new StringBuilder();
        message.append("STR: ");
        message.append(rolledStats.get(0));
        message.append("\nDEX: ");
        message.append(rolledStats.get(1));
        message.append("\nCON: ");
        message.append(rolledStats.get(2));
        message.append("\nWIS: ");
        message.append(rolledStats.get(3));
        message.append("\nINT: ");
        message.append(rolledStats.get(4));
        message.append("\nCHA: ");
        message.append(rolledStats.get(5));
        return message.toString();
    }

    public List<String> getCharaStatPrio() {
        return charaStatPrio;
    }

    public List<String> getCharaLanguages() {
        return charaLanguages;
    }

    public List<Integer> getCharaStats() {
        return charaStats;
    }

    public List<String> getCharaFeats() {
        return charaFeats;
    }

    public String getRace(){
        return charaRace;
    }

    public String getBackground(){
        return charaBackground;
    }


    

}
