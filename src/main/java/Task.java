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

    /**
     * Decrease the amount of time remaining in the task by 1 minute. If the task reaches no time remaining the
     * onCompletion handler will be called. If the task isn't needed according to isStillNeeded the task will be
     * discarded without calling the onCompletion handler.
     */
    public void decrementTimeRemaining() {
        discardIfNotNeeded();
        if (!isDiscarded) {
            timeRemaining--;
            if (isComplete()) onCompletion(animal);
        }
    }

    /**
     * Call the isStillNeeded check and mark the task as discarded if the task no longer needs to be done.
     */
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
