public class StaffStateWorking extends StaffState {
    // Number of minutes of capacity left for the rest of the day
    // This covers R2.2 (each staff member has a capacity)
    private int capacityRemaining;

    StaffStateWorking(int dailyWorkCapacity) {
        capacityRemaining = dailyWorkCapacity;
    }

    @Override
    void update(String event, Staff employee) {
        TaskList taskList = TaskList.getInstance();

        // new task may have come in via Observer, check if there is something to work on
        Task currentTask = taskList.getAssignedTask(employee);
        if (currentTask == null) {
            // if the staff member is unassigned, check the task board and pick up a new task
            currentTask = taskList.assignAvailableTask(employee);
            if (currentTask != null) {
                Logger.log("STAFF", String.format("%s started working on %s", employee.getName(), currentTask));
            }
        }

        if (event.equals("day_end")) {
            // didn't use up capacity today, but it's time to go home
            employee.goHomeEndOfDay();
            return;
        }

        if (currentTask != null && event.equals("minute")) {
            // if time has passed, then do work on the current task
            currentTask.decrementTimeRemaining();
            double duration = currentTask.getTimeWorkedHours();
            if (currentTask.isDiscarded()) {
                String formatString = "%s quit working on %s after %.1fh of work. No longer needs to be done";
                Logger.log("STAFF", String.format(formatString, employee.getName(), currentTask, duration));
            } else if (currentTask.isComplete()) {
                String formatString = "%s completed %s after %.1fh of work";
                Logger.log("STAFF", String.format(formatString, employee.getName(), currentTask, duration));
            }

            // doing work uses up capacity
            capacityRemaining--;
            if (capacityRemaining == 0) {
                // capacity used up, go home
                employee.goHomeTired();
            }
        }
    }
}
