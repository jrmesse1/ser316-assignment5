public class Clock extends Subject {
    private static Clock singletonClock;
    private int currentHour = -1;

    public static Clock getInstance() {
        if (singletonClock == null) singletonClock = new Clock();
        return singletonClock;
    }

    public void incrementCurrentHour() {
        currentHour++;
        System.out.printf("\n[CLOCK] Day: %d Time: %02d:00\n", currentHour / 24 + 1, currentHour % 24);
        notifyObservers("clock");
    }

    public String getTime() {
        return String.format("%02d:00", currentHour % 24);
    }
}
