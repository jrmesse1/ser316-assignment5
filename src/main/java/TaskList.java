import java.util.ArrayList;

public class TaskList extends Subject {
    private final ArrayList<Task> tasks = new ArrayList<>();

    public void printStats() {
        int completed = 0;
        int inProgress = 0;
        int unassigned = 0;
        for (Task task : tasks) {
            if (task.isComplete()) {
                completed++;
            } else if (task.getAssignee() == null) {
                unassigned++;
            } else {
                inProgress++;
            }
        }
        Logger.log("TASKLIST", String.format("Staff have completed %s tasks. They are working on %s and %s are unassigned.", completed, inProgress, unassigned));
    }

    public void addTask(Animal animal, TaskType type) {
        tasks.add(new Task(animal, type));
        notifyObservers("new_task");
    }

    public Task assignAvailableTask(Staff employee) {
        for (Task task : tasks) {
            // skip already completed tasks
            if (task.isComplete()) continue;

            // skip already assigned tasks
            if (task.getAssignee() != null) continue;

            // found a task for the staff to take
            task.setAssignee(employee);
            return task;
        }

        // did not find a task for them to work on
        return null;
    }

    /**
     * Get the task that the staff member is currently working on.
     *
     * @param employee
     * @return
     */
    public Task getAssignedTask(Staff employee) {
        for (Task task : tasks) {
            if (task.getAssignee() == employee && !task.isComplete()) return task;
        }
        return null;
    }
}
