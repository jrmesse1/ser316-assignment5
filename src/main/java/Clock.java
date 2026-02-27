public class Clock extends Subject {
    private int currentHour = -1;

    public void incrementCurrentHour() {
        currentHour++;
        System.out.printf("\n[CLOCK] Day: %d Time: %02d:00\n", currentHour / 24 + 1, currentHour % 24);
        notifyObservers("clock");
    }
}
