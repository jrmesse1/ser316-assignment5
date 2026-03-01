import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WeightedCoinTest {
    @Test
    void flip() {
        WeightedCoin alwaysCoin = new WeightedCoin(1);
        assertTrue(alwaysCoin.flip());

        WeightedCoin neverCoin = new WeightedCoin(0);
        assertFalse(neverCoin.flip());
    }
}