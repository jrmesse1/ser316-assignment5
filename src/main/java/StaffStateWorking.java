public class StaffStateWorking extends StaffState {
    @Override
    void update(String event, Staff employee, TaskList taskList) {
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
        }

        if (currentTask != null && event.equals("minute")) {
            // if time has passed, then do work on the current task and use up capacity
            currentTask.decrementTimeRemaining();
            double duration = currentTask.getTimeWorkedHours();
            if (currentTask.isDiscarded()) {
                String formatString = "%s quit working on %s after %.1fh of work. No longer needs to be done";
                Logger.log("STAFF", String.format(formatString, employee.getName(), currentTask, duration));
            } else if (currentTask.isComplete()) {
                String formatString = "%s completed %s after %.1fh of work";
                Logger.log("STAFF", String.format(formatString, employee.getName(), currentTask, duration));
            }
            employee.decrementCapacity();
        }
    }
}
