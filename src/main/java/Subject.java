import java.util.ArrayList;

public abstract class Subject {
    private ArrayList<Observer> observers = new ArrayList<>();

    /**
     * Connect an Observer to receive notifications from the Subject.
     * @param observer The Observer that should be notified.
     */
    public void attach(Observer observer) {
        // copy the observer list to avoid ConcurrentModificationException when we add a new observer during update
        ArrayList<Observer> newObservers = new ArrayList<>(observers);
        newObservers.add(observer);
        observers = newObservers;
    }

    /**
     * Disconnect an Observer so it stops getting notifications from the Subject.
     * @param observer The Observer that should not be notified.
     */
    public void detach(Observer observer) {
        // copy the observer list to avoid ConcurrentModificationException when we remove an observer during update
        ArrayList<Observer> newObservers = new ArrayList<>(observers);
        newObservers.remove(observer);
        observers = newObservers;
    }

    /**
     * Loop through the list of registered Observers and notify each one. This is called by the Subject.
     * @param event String representing the event that the Observers should be notified of.
     */
    public void notifyObservers(String event) {
        for (Observer observer : observers) {
            observer.update(event);
        }
    }
}
