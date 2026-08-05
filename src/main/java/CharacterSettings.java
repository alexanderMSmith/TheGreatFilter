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

    public CharacterSettings(){
        final TraitLookup settings = new 
            TraitLookup("/data/settings.json");
        raceRange = Integer.parseInt(settings.getCharacteristic(1));
        featRange = Integer.parseInt(settings.getCharacteristic(2));
        backgroundRange = Integer.parseInt(settings.getCharacteristic(3));
        languageRange = Integer.parseInt(settings.getCharacteristic(4));
        languagePriorityRange = Integer.parseInt(settings.getCharacteristic(5));
        statRange = Integer.parseInt(settings.getCharacteristic(6));
        statPriorityRange = Integer.parseInt(settings.getCharacteristic(7));
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
