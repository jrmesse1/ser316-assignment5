import java.util.ArrayList;

public class TaskList extends Subject {
    private static TaskList singletonTaskList;
    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Get the shared global instance of the TaskList. Used for the Singleton pattern.
     * @return TaskList instance shared throughout the simulation.
     */
    public static TaskList getInstance() {
        if (singletonTaskList == null) singletonTaskList = new TaskList();
        return singletonTaskList;
    }

    /**
     * Used for tests so tasks are not shared between test runs.
     */
    public static void resetInstance() {
        singletonTaskList = null;
    }

    public String getStats() {
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
        String formatString = "Staff have completed %s tasks. They are working on %s and %s are unassigned.";
        return String.format(formatString, completed, inProgress, unassigned);
    }

    public void printStats() {
        Logger.log("TASKLIST", getStats());
    }

    public void addTask(Task task) {
        tasks.add(task);
        notifyObservers("new_task");
    }

    public Task assignAvailableTask(Staff employee) {
        for (Task task : tasks) {
            // skip already completed tasks
            if (task.isComplete()) continue;

            // skip already assigned tasks
            if (task.getAssignee() != null) continue;

            // make sure it still needs to be done
            task.discardIfNotNeeded();

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
