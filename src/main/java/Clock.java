public class Clock extends Subject {
    private static final int MINUTES_IN_HOUR = 60;
    private static final int MINUTES_IN_DAY = 24 * MINUTES_IN_HOUR;

    private static Clock singletonClock;
    private int currentMinute = -1;

    public static Clock getInstance() {
        if (singletonClock == null) singletonClock = new Clock();
        return singletonClock;
    }

    public void incrementCurrentTime() {
        currentMinute++;
        if (currentMinute % MINUTES_IN_HOUR == 0) {
            // print time if we're at the hour mark
            System.out.printf("\n[CLOCK] Day: %d Time: %s\n", currentMinute / MINUTES_IN_DAY + 1, getTime());
        }
        if (currentMinute % MINUTES_IN_DAY == 0) notifyObservers("day");
        if (currentMinute % MINUTES_IN_HOUR == 0) notifyObservers("hour");
        notifyObservers("minute");
    }

    public String getTime() {
        if (currentMinute < 0) {
            // simulation has not started yet
            return "SETUP";
        }
        return String.format("%02d:%02d", (currentMinute / MINUTES_IN_HOUR) % 24, currentMinute % MINUTES_IN_HOUR);
    }
}
