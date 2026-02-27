public class Staff implements Observer {
    private final int id;
    private final String name;
    private final StaffRole role;
    private final TaskList taskList;

    public Staff(int id, String name, StaffRole role, TaskList taskList) {
        // connect observer to global clock
        Clock.getInstance().attach(this);

        this.id = id;
        this.name = name;
        this.role = role;
        this.taskList = taskList;
    }

    public void update(String event) {
        Task currentTask = taskList.getAssignedTask(this);
        if (currentTask == null) {
            // if the staff member is unassigned, check the task board and pick up a new task
            currentTask = taskList.assignAvailableTask(this);
            if (currentTask != null) {
                Logger.log("STAFF", String.format("%s started working on %s", name, currentTask));
            }
        }

        if (currentTask != null && event == "clock") {
            // if time has passed, then do work on the current task
            currentTask.decrementTimeRemaining();
            if (currentTask.isComplete()) {
                Logger.log("STAFF", String.format("%s completed %s after %d hour(s) of work", name, currentTask, currentTask.getDuration()));
            }
        }
    }
}
