import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class TraitsSelector {
    
    /*
    private TraitLookup feats = new TraitLookup("/data/feats.json");
    private  TraitLookup languages = new 
        TraitLookup("/data/languages.json");
    private TraitLookup stats = new TraitLookup("/data/stats.json"); 
    private TraitLookup races = new TraitLookup("/data/raceTable.json");
    private  TraitLookup backgrounds = new 
        TraitLookup("/data/backgrounds.json"); 
    */
    private TraitLookup feats;
    private TraitLookup languages;
    private TraitLookup stats; 
    private TraitLookup races;
    private TraitLookup backgrounds; 
    private RandomGenerator randomGenerator;

    public TraitsSelector(RandomGenerator generator){
        randomGenerator = generator;
    }

    public TraitsSelector(RandomGenerator generator, TraitLookup feats, TraitLookup languages, TraitLookup stats,
        TraitLookup races, TraitLookup backgrounds){
        this.randomGenerator = generator;
        this.feats = feats;
        this.languages = languages;
        this.stats = stats;
        this.races = races;
        this.backgrounds = backgrounds;
    }

    public List<String> feats(int featRange){
        List<String> characterFeats = new ArrayList<>();
        int featCount = 1;
        while(characterFeats.size() < featCount){
            int number = randomRange(featRange);
            if(number != featRange && !characterFeats.contains(feats.getCharacteristic(number))){
                String copy = feats.getCharacteristic(number);
                characterFeats.add(copy);
            }else{
                featCount++;
            }
        }
        return characterFeats;
    }
    /**
     * This method generates a set of prioritized languages for a character, up
     * to a predefined range.
     * @return A HashSet containing the prioritized languages.
     */
    public HashSet<String> getLanguagePriority(int languagePriorityRange, int languageRange){
        HashSet<String> languagePriority = new HashSet<>();
        int i = 0;
        while (i < languagePriorityRange){
            int choice = randomRange(languageRange);
            boolean added = languagePriority.add(languages.getCharacteristic(choice));
            if(added){
                i++;
            }
        }
        return languagePriority;
    }


     /**
     * This method generates a set of prioritized stats for a character, up to a predefined range.
     * @return A HashSet containing the prioritized stats.
     */
    public HashSet<String> getStatPriority(int statPriorityRange, int statRange){
        HashSet<String> statPrio = new HashSet<>();
        int i = 0;
        while(i < statPriorityRange){
            int choice = randomRange(statRange);
            boolean added = statPrio.add(stats.getCharacteristic(choice));
            if(added){
                i++;
            }
        }
        return statPrio;
    }

    public String getBackground(int max){
        return backgrounds.getCharacteristic(randomRange(max));
    }

    public String getRace(int max){
        return races.getCharacteristic(randomRange(max));
    }

    public int randomRange(int max){
        return randomGenerator.randomRange(max);
    }

    public void setRandomGenerator(RandomGenerator random){
        randomGenerator = random;
    }

}
