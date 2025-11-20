import java.util.List;

public class CharacterCreator {
    public static String createCharacter(int raceRange, int featRange, int backgroundRange,
        int languagePriorityRange, int languageRange, int statPriorityRange, int statRange) {
        String race = TraitsSelector.getRace(raceRange);
        List<String> characterFeats = TraitsSelector.feats(featRange);
        String background = TraitsSelector.getBackground(backgroundRange);
        List<Integer> rolledStats = StatRoller.rollStats();
        String message = printStats(rolledStats);
        message += "\nFeat: " + characterFeats.toString() + "\nBackground: " + background;
        message += "\nRace: " + race;
        message += "\nStat Priority: " + TraitsSelector.getStatPriority
            (statPriorityRange, statRange).toString();
        message += "\nLanguage Priority " + TraitsSelector.getLanguagePriority
            (languagePriorityRange, languageRange).toString();
        return message;
    }

    public static String printStats(List<Integer> rolledStats){
        String message = "STR: " + rolledStats.get(0) + "\nDEX: " + rolledStats.get(1) +
            "\nCON: " + rolledStats.get(2) + "\nWIS: " + rolledStats.get(3) + "\nINT: " +
            rolledStats.get(4) + "\nCHA: " + rolledStats.get(5);
        return message;
    }

    public static void main(String[] args){
        String character = createCharacter(225, 125, 45, 3, 15, 3, 6);
        System.out.println(character);
    }
}
