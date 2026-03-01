import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClockTest {
    private static final int MINUTES_IN_HOUR = 60;
    private static final int MINUTES_IN_DAY = 24 * MINUTES_IN_HOUR;

    @BeforeEach
    void setUp() {
        Clock.resetInstance();
    }

    @Test
    void incrementCurrentTime() {
        assertEquals("SETUP", Clock.getInstance().toString());
        Clock.getInstance().incrementCurrentTime();
        assertEquals("00:00", Clock.getInstance().toString());

        // increment until a full day has passed
        for (int i = 0; i < MINUTES_IN_DAY; i++) {
            Clock.getInstance().incrementCurrentTime();
        }

        assertEquals("00:00", Clock.getInstance().toString());
    }
}