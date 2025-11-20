import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TraitsSelector {
    
    private static final TraitLookup feats = new TraitLookup("/data/feats.json");
    private static final TraitLookup languages = new 
        TraitLookup("/data/languages.json");
    private static final TraitLookup stats = new TraitLookup("/data/stats.json"); 
    private static final TraitLookup races = new TraitLookup("/data/raceTable.json");
    private static final TraitLookup backgrounds = new 
        TraitLookup("/data/backgrounds.json"); 

    public static List<String> feats(int featRange){
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
    public static HashSet<String> getLanguagePriority(int languagePriorityRange, int languageRange){
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
    public static HashSet<String> getStatPriority(int statPriorityRange, int statRange){
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

    public static String getBackground(int max){
        return backgrounds.getCharacteristic(randomRange(max));
    }

    public static String getRace(int max){
        return races.getCharacteristic(randomRange(max));
    }

    public static int randomRange(int max){
        return ThreadLocalRandom.current().nextInt(1, max+1);
    }

        public static void main(String[] args) {
        System.out.println("=== Testing feats ===");
        List<String> featsList = feats(3);
        featsList.forEach(f -> System.out.println("Feat: " + f));

        System.out.println("\n=== Testing language priority ===");
        HashSet<String> langs = getLanguagePriority(3, 3);
        langs.forEach(l -> System.out.println("Language: " + l));

        System.out.println("\n=== Testing stat priority ===");
        HashSet<String> statsPrio = getStatPriority(3, 3);
        statsPrio.forEach(s -> System.out.println("Stat: " + s));

        System.out.println("\n=== Testing background ===");
        String background = getBackground(3);
        System.out.println("Background: " + background);

        System.out.println("\n=== Testing race ===");
        String race = getRace(3);
        System.out.println("Race: " + race);
    }
}
