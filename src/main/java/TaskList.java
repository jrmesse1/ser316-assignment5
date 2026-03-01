import java.util.ArrayList;

public class TaskList extends Subject {
    private static TaskList singletonTaskList;
    private final ArrayList<Task> tasks = new ArrayList<>();

    private TaskList() {}

    /**
     * Get the shared global instance of the TaskList. Used for the Singleton pattern.
     * @return TaskList instance shared throughout the simulation.
     */
    public static synchronized TaskList getInstance() {
        return maybeInitializeTaskList();
    }

    private static synchronized TaskList maybeInitializeTaskList() {
        if (singletonTaskList == null) singletonTaskList = new TaskList();
        return singletonTaskList;
    }

    /**
     * Used for tests so tasks are not shared between test runs.
     */
    public static void resetInstance() {
        singletonTaskList = null;
    }

    /**
     * Get a string containing statistics about the tasks and their current states.
     *
     * @return String describing the state of tasks in the list.
     */
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

    /**
     * Print the string returned by getStats. This is triggered at the start of each day during the simulation.
     */
    public void printStats() {
        Logger.log("TASKLIST", getStats());
    }

    /**
     * Add a new task to the list that can be picked up by Staff. This will trigger an event that Staff listen for.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
        notifyObservers("new_task");
    }

    /**
     * Find a task for the given staff member to work on. Completed and already assigned tasks will be
     * skipped. Unneeded tasks that have not already been picked up will be silently discarded.
     *
     * @param employee The staff member to assign the task to.
     * @return The task that was assigned or null if a task cannot be found.
     */
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
     * Get the task that the staff member is currently working on. Assume that each staff member is assigned to at most
     * one task.
     *
     * @param employee The staff member who may be assigned to a task
     * @return The task that the staff member is assigned to, or null if they aren't assigned to anything.
     */
    public Task getAssignedTask(Staff employee) {
        for (Task task : tasks) {
            if (task.getAssignee() == employee && !task.isComplete()) return task;
        }
        return null;
    }
}
