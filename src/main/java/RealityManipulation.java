import java.util.concurrent.ThreadLocalRandom;

public class RealityManipulation {

    public static int manusUsage(int anchor, int stat, int potency, int modifier, int power, int pillarRank, int diffMod){
        int roll = ThreadLocalRandom.current().nextInt(1, 21) + anchor + stat + modifier;
        if(roll >= 6+potency-diffMod){
            int totalPower = power + (pillarRank * 2);
            int leftSide = potency+anchor+stat+modifier;
            return totalPower * leftSide;
        }
        return -1;
    }

    public static double psychicUsage(int modifier, int vigorSpent, int vigorRequired, int severity, int rollType){
        int roll = ThreadLocalRandom.current().nextInt(1, 21) + severity + modifier;
        if(rollType == 1){
            roll = Math.max(roll, ThreadLocalRandom.current().nextInt(1, 21) + severity + modifier);
        }else if(rollType == -1){
            roll = Math.min(roll, ThreadLocalRandom.current().nextInt(1, 21) + severity + modifier);
        }
        double vigorResult = (double) vigorSpent / vigorRequired;
        return (roll * vigorResult);
    }

    public static int scienceCalc(int depth, int dominance, int skillMod, int modifier){
        int depthDominance = depth * dominance;
        return depthDominance + skillMod + modifier;
    }

    public static double magicCalc(double schoolMod, int manaSpent, int manaRequired, int level, int magicMod, int familiarity, int originMod, int attenuation, int rollMax, int rollMin, int rollType, int race){
        double manaResult = (double) manaSpent / manaRequired;
        int roll = ThreadLocalRandom.current().nextInt(rollMin, rollMax + 1);
        int secondRoll = ThreadLocalRandom.current().nextInt(rollMin, rollMax + 1);
        if(race == 1||race == 2){
            roll += 2;
            secondRoll +=2; 
        }else if(race == 4){
            roll -= 1;
            secondRoll -= 1;
        }
        if(roll <(10+level) && (race == 1|| race == 4)){
            roll += (ThreadLocalRandom.current().nextInt(1, 14)/2);
        }
        if(secondRoll <(10+level) && (race == 1 || race == 4)){
            secondRoll += (ThreadLocalRandom.current().nextInt(1, 14)/2);
        }
        if(rollType == 1 && race != 3){
            roll = Math.max(roll, secondRoll);
        }else if(rollType == -1 || (race == 3 && rollType != 1)){
            roll = Math.min(roll, secondRoll);
        }
        int rollTotal = roll+level+magicMod+familiarity;
        double center = manaResult * rollTotal;
        double result = schoolMod * center + originMod - attenuation;
        return result;
    }

}
