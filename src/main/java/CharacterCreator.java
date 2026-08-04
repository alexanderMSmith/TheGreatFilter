import java.util.List;

public class CharacterCreator {
    public static String createCharacter(CharacterSettings chara) {
        RandomGenerator randomGen = new DefaultRandomGenerator();
        TraitsSelector defaultSelector = new TraitsSelector(    
            randomGen,
            new TraitLookup("/data/feats.json"),
            new TraitLookup("/data/languages.json"),
            new TraitLookup("/data/stats.json"),
            new TraitLookup("/data/raceTable.json"),
            new TraitLookup("/data/backgrounds.json")
        );
        String race = defaultSelector.getRace(chara.getRaceRange());
        List<String> characterFeats = defaultSelector.feats(chara.getFeatRange());
        String background = defaultSelector.getBackground(chara.getFeatRange());
        List<Integer> rolledStats = StatRoller.rollStats();
        String message = printStats(rolledStats);
        message += "\nFeat: " + characterFeats.toString() + "\nBackground: " + background;
        message += "\nRace: " + race;
        message += "\nStat Priority: " + defaultSelector.getStatPriority
            (chara.getStatPriorityRange(), chara.getStatRange()).toString();
        message += "\nLanguage Priority " + defaultSelector.getLanguagePriority
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
