import java.util.concurrent.ThreadLocalRandom;

public class DefaultRandomGenerator implements RandomGenerator{
    @Override   
    public int randomRange(int max){
        return ThreadLocalRandom.current().nextInt(1, max + 1);
    }
}