public abstract class Task {
    // animal that the task is for
    private final Animal animal;
    // which staff member the task is assigned to
    private Staff assignee;
    // how long is left on the task
    private int timeRemaining;
    // task was abandoned without finishing
    private boolean isDiscarded;

    private static final int MINUTES_IN_HOUR = 60;

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
        return isDiscarded() || timeRemaining == 0;
    }

    public boolean isDiscarded() {
        return isDiscarded;
    }

    public void decrementTimeRemaining() {
        discardIfNotNeeded();
        if (!isDiscarded) {
            timeRemaining--;
            if (isComplete()) onCompletion(animal);
        }
    }

    public void discardIfNotNeeded() {
        if (!isStillNeeded(animal)) {
            // task isn't needed anymore. mark as discarded and don't run onCompletion
            isDiscarded = true;
        }
    }

    public double getTimeWorkedHours() {
        return ((double) getDuration() - timeRemaining) / MINUTES_IN_HOUR;
    }

    abstract String getName();

    abstract int getDuration();

    abstract boolean isStillNeeded(Animal animal);

    abstract void onCompletion(Animal animal);

    @Override
    public String toString() {
        return getName() + " for " + animal.getName();
    }
}
