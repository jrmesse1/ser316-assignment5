import java.util.Random;

public class WeightedCoin {
    private final double winFraction;

    public WeightedCoin(double winFraction) {
        this.winFraction = winFraction;
    }

    /**
     * Perform a coin flip that will win a certain fraction of the time.
     * @return True for a winning flip, false otherwise.
     */
    public boolean flip() {
        Random random = new Random();
        return winFraction >= (random.nextDouble());
    }
}
