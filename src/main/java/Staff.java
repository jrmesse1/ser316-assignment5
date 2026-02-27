public class Staff implements Observer {
    private int id;
    private String name;
    private StaffRole role;
    private TaskList taskList;

    public Staff(int id, String name, StaffRole role, TaskList taskList) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.taskList = taskList;
    }

    public void update (String event) {
        // if the staff member is unassigned, check the task board and pick up a new task
        Task currentTask = taskList.getAssignedTask(this);
        if (currentTask == null) {
            currentTask = taskList.assignAvailableTask(this);
            if (currentTask != null) {
                System.out.printf("[STAFF] %s started working on %s\n", name, currentTask.toString());
            }
        }

        // if time has passed, then do work on the current task
        if (currentTask != null && event == "clock") {
            currentTask.decrementTimeRemaining();
            if (currentTask.isComplete()) {
                System.out.printf("[STAFF] %s completed %s after %d hour(s) of work\n", name, currentTask.toString(), currentTask.getDuration());
            }
        }
    }
}
