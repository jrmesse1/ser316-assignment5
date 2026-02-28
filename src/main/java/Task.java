public abstract class Task {
    // animal that the task is for
    private final Animal animal;
    // how long the task should take
    int duration;
    private Staff assignee;
    // how long is left on the task
    private int timeRemaining;

    Task(Animal animal) {
        this.animal = animal;
        timeRemaining = getDuration();
    }

    public Staff getAssignee() {
        return this.assignee;
    }

    public void setAssignee(Staff staff) {
        this.assignee = staff;
    }

    public boolean isComplete() {
        return timeRemaining == 0;
    }

    public void decrementTimeRemaining() {
        timeRemaining--;
        if (isComplete()) onCompletion(animal);
    }

    abstract String getName();

    abstract int getDuration();

    abstract void onCompletion(Animal animal);

    @Override
    public String toString() {
        return getName() + " for " + animal.getName();
    }
}
