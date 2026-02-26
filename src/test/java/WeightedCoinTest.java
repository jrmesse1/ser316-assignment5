import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeightedCoinTest {
    @Test
    void flip() {
        WeightedCoin alwaysCoin = new WeightedCoin(100);
        assertTrue(alwaysCoin.flip());

        WeightedCoin neverCoin = new WeightedCoin(0);
        assertFalse(neverCoin.flip());
    }
}