import java.util.LinkedList;
import java.util.Queue;

public class FakeRandomGenerator implements RandomGenerator{
    private final Queue<Integer> values;

    public FakeRandomGenerator(int... numbers) {
        values = new LinkedList<>();

        for (int number : numbers) {
            values.add(number);
        }
    }

    @Override
    public int randomRange(int max) {
        return values.remove();
    }
}
