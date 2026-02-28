public class Clock extends Subject {
    private static final int MINUTES_IN_HOUR = 60;
    private static final int MINUTES_IN_DAY = 24 * MINUTES_IN_HOUR;

    private static Clock singletonClock;
    private int currentMinute = -1;

    public static Clock getInstance() {
        if (singletonClock == null) singletonClock = new Clock();
        return singletonClock;
    }

    /**
     * Used for tests so tasks are not shared between test runs.
     */
    public static void resetInstance() {
        singletonClock = null;
    }

    public void incrementCurrentTime() {
        currentMinute++;
        if (currentMinute % MINUTES_IN_DAY == 0) {
            // print time if we're starting a new day
            Logger.log("CLOCK", String.format("Starting Day %d", currentMinute / MINUTES_IN_DAY + 1));
        }
        if (getHourOfDay() == 9 && getMinuteOfHour() == 0) notifyObservers("day_start");
        if (getHourOfDay() == 17 && getMinuteOfHour() == 0) notifyObservers("day_end");
        if (getMinuteOfHour() == 0) notifyObservers("hour");
        notifyObservers("minute");
    }

    private int getHourOfDay() {
        return (currentMinute / MINUTES_IN_HOUR) % 24;
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
