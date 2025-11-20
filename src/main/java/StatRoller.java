import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class StatRoller {
    
    public static List<Integer> rollStats() {
        final int ABILITY_SCORE_COUNT = 6;
        final int DICE_PER_ROLL = 4;
        final int DICE_SIDES = 6;
        List<Integer> rolledStats = new ArrayList<>();
        while(rolledStats.size() < ABILITY_SCORE_COUNT){
            List<Integer> dice = new ArrayList<>();
            while(dice.size() < DICE_PER_ROLL){
                int roll =  randomRange(DICE_SIDES);
                dice.add(roll);
            }
            dice.sort(Comparator.reverseOrder());
            int sum = dice.get(0) + dice.get(1) +dice.get(2);
            rolledStats.add(sum);
        }
        return rolledStats;
    }
    public static int randomRange(int max){
        return ThreadLocalRandom.current().nextInt(1, max+1);
    }
}
