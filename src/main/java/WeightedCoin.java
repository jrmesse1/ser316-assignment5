import java.util.Random;

public class WeightedCoin {
    private final double winFraction;

    /**
     * Coin flip that will win a certain fraction of the time.
     *
     * @param winFraction
     */
    public WeightedCoin(double winFraction) {
        this.winFraction = winFraction;
    }

    public boolean flip() {
        Random random = new Random();
        return winFraction >= (random.nextDouble());
    }
}
