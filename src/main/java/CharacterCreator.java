import java.util.List;

public class CharacterCreator {
    public static String createCharacter(CharacterSettings chara) {
        String race = TraitsSelector.getRace(chara.getRaceRange());
        List<String> characterFeats = TraitsSelector.feats(chara.getFeatRange());
        String background = TraitsSelector.getBackground(chara.getFeatRange());
        List<Integer> rolledStats = StatRoller.rollStats();
        String message = printStats(rolledStats);
        message += "\nFeat: " + characterFeats.toString() + "\nBackground: " + background;
        message += "\nRace: " + race;
        message += "\nStat Priority: " + TraitsSelector.getStatPriority
            (chara.getStatPriorityRange(), chara.getStatRange()).toString();
        message += "\nLanguage Priority " + TraitsSelector.getLanguagePriority
            (chara.getLanguagePriorityRange(), chara.getLanguageRange()).toString();
        return message;
    }

    public static String printStats(List<Integer> rolledStats){
        String message = "STR: " + rolledStats.get(0) + "\nDEX: " + rolledStats.get(1) +
            "\nCON: " + rolledStats.get(2) + "\nWIS: " + rolledStats.get(3) + "\nINT: " +
            rolledStats.get(4) + "\nCHA: " + rolledStats.get(5);
        return message;
    }

    public static void main(String[] args){
        CharacterSettings chara = new CharacterSettings(225, 125, 45, 3, 15, 3, 6);
        String character = createCharacter(chara);
        System.out.println(character);
    }
}
