public class CharacterSettings{
    private final int raceRange;
    private final int featRange;
    private final int backgroundRange;
    private final int languagePriorityRange;
    private final int languageRange;
    private final int statPriorityRange;
    private final int statRange;

    public CharacterSettings(int newRaceRange, int newFeatRange, int newBackgroundRange, int languagePrio,
        int langRange, int statPrioRange, int newStatRange){
        raceRange = newRaceRange;
        featRange = newFeatRange; 
        backgroundRange = newBackgroundRange;  
        languagePriorityRange = languagePrio;
        languageRange = langRange;
        statPriorityRange = statPrioRange;
        statRange = newStatRange;
    }

    public int getRaceRange(){
        return raceRange;
    }
    
    public int getFeatRange(){
        return featRange;
    }

    public int getBackgroundRange() {
        return backgroundRange;
    }

    public int getLanguagePriorityRange() {
        return languagePriorityRange;
    }

    public int getLanguageRange() {
        return languageRange;
    }

    public int getStatPriorityRange() {
        return statPriorityRange;
    }

    public int getStatRange() {
        return statRange;
    }
    
}