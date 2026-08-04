import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class StatRollerTest {
    
    @Test
    void rollStatsShouldReturnSixStats(){
        List<Integer> stats = StatRoller.rollStats();
        assertEquals(6, stats.size());
    }

    @Test
    void statsShouldBeWithinValidRange(){
        List<Integer> stats = StatRoller.rollStats();
        assertTrue(stats.stream()
        .allMatch(stat -> stat >= 3 && stat <= 18));
    }

    @Test
    void averageRollShouldBeReasonable() {
        int total = 0;
        for(int i = 0; i < 10000; i++){
            total += StatRoller.rollStats()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
        }
        double average = total / 10000.0;
        assertTrue(average > 60 && average < 90);
    }

}
