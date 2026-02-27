import java.util.ArrayList;

public abstract class Subject {
    private ArrayList<Observer> observers = new ArrayList<>();

    public void attach(Observer observer) {
        // copy the observer list to avoid ConcurrentModificationException when we add a new observer during update
        ArrayList<Observer> newObservers = new ArrayList<>(observers);
        newObservers.add(observer);
        observers = newObservers;
    }

    public void detach(Observer observer) {
        // copy the observer list to avoid ConcurrentModificationException when we remove an observer during update
        ArrayList<Observer> newObservers = new ArrayList<>(observers);
        newObservers.remove(observer);
        observers = newObservers;
    }

    public void notifyObservers(String event) {
        for (Observer observer : observers) {
            observer.update(event);
        }
    }
}
