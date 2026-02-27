public class Task {
    private final Animal animal;
    private Staff assignee;
    private final TaskType type;
    // how long the task should take
    private final int duration;
    // how long is left on the task
    private int timeRemainingHours;

    Task(Animal animal, TaskType type) {
        this.animal = animal;
        this.type = type;

        if (type == TaskType.INTAKE_EXAM || type == TaskType.ENCLOSURE_CLEANING) {
            this.duration = 2;
        } else if (type == TaskType.VACCINATION) {
            this.duration = 3;
        } else {
            // feeding, exercise
            this.duration = 1;
        }
        this.timeRemainingHours = this.duration;
    }

    public int getDuration() {
        return duration;
    }

    public Staff getAssignee() {
        return this.assignee;
    }

    public void setAssignee(Staff staff) {
        this.assignee = staff;
    }

    public boolean isComplete() {
        return timeRemainingHours == 0;
    }

    public void decrementTimeRemaining() {
        timeRemainingHours--;
    }

    @Override
    public String toString() {
        return type.toString().toLowerCase().replace("_", " ") + " for " + animal.getName();
    }
}
