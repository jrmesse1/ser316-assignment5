public class Clock extends Subject {
    private static final int HOURS_IN_DAY = 24;
    private static final int MINUTES_IN_HOUR = 60;
    private static final int MINUTES_IN_DAY = HOURS_IN_DAY * MINUTES_IN_HOUR;
    private static final int START_OF_DAY_HOUR = 9;
    private static final int END_OF_DAY_HOUR = 17;

    private static Clock singletonClock;
    private int currentMinute = -1;

    private Clock() {}

    /**
     * Get the shared global instance of the Clock. Used for the Singleton pattern.
     * @return Clock instance shared throughout the simulation.
     */
    public static synchronized Clock getInstance() {
        if (singletonClock == null) singletonClock = new Clock();
        return singletonClock;
    }

    /**
     * Used for tests so tasks are not shared between test runs.
     */
    public static void resetInstance() {
        singletonClock = null;
    }

    /**
     * Increase the current time by 1 minute and notify observers that the simulation time has changed. Observers will
     * be told when a minute has passed, when an hour has passed, when a new day has started, and when a day ends.
     */
    public void incrementCurrentTime() {
        currentMinute++;
        if (currentMinute % MINUTES_IN_DAY == 0) {
            // print time if we're starting a new day
            Logger.log("CLOCK", String.format("Starting Day %d", currentMinute / MINUTES_IN_DAY + 1));
        }
        if (getHourOfDay() == START_OF_DAY_HOUR && getMinuteOfHour() == 0) notifyObservers("day_start");
        if (getHourOfDay() == END_OF_DAY_HOUR && getMinuteOfHour() == 0) notifyObservers("day_end");
        if (getMinuteOfHour() == 0) notifyObservers("hour");
        notifyObservers("minute");
    }

    private int getHourOfDay() {
        return (currentMinute / MINUTES_IN_HOUR) % HOURS_IN_DAY;
    }

    private int getMinuteOfHour() {
        return currentMinute % MINUTES_IN_HOUR;
    }

    @Override
    public String toString() {
        if (currentMinute < 0) {
            // simulation has not started yet
            return "SETUP";
        }
        return String.format("%02d:%02d", getHourOfDay(), getMinuteOfHour());
    }
}
