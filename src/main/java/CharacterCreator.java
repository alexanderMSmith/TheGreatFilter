import java.util.ArrayList;
import java.util.List;

public class CharacterCreator {

    private static final RandomGenerator randomGen = new DefaultRandomGenerator();
    private static final TraitsSelector defaultSelector = new TraitsSelector(    
            randomGen,
            new TraitLookup("/data/feats.json"),
            new TraitLookup("/data/languages.json"),
            new TraitLookup("/data/stats.json"),
            new TraitLookup("/data/raceTable.json"),
            new TraitLookup("/data/backgrounds.json")
        );
    public static String createCharacter(CharacterSettings chara) {
        String race = defaultSelector.getRace(chara.getRaceRange());
        List<String> characterFeats = defaultSelector.feats(chara.getFeatRange());
        String background = defaultSelector.getBackground(chara.getBackgroundRange());
        List<Integer> rolledStats = StatRoller.rollStats();
        List<String> charaDetail = new ArrayList<>();
        charaDetail.add(background);
        charaDetail.add(race);
        String statPriorty = defaultSelector.getStatPriority
            (chara.getStatPriorityRange(), chara.getStatRange()).toString();
        String langPriority = defaultSelector.getLanguagePriority
            (chara.getLanguagePriorityRange(), chara.getLanguageRange()).toString();

        Character character = new Character(charaDetail, rolledStats, characterFeats, statPriorty, langPriority);
        return character.toString();
    }


    public static void main(String[] args){
        CharacterSettings chara = new CharacterSettings(225, 125, 45, 3, 15, 3, 6);
        String character = createCharacter(chara);
        System.out.println(character);
    }
}
