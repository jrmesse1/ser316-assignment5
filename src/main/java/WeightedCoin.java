import java.util.Random;

public class WeightedCoin {
    private int winPercentage;

    /**
     * Coin flip that will win a certain percentage of the time.
     * @param winPercentage
     */
    public WeightedCoin(int winPercentage) {
        this.winPercentage = winPercentage;
    }

    public boolean flip() {
        Random random = new Random();
        return winPercentage >= random.nextInt(101);
    }
}
