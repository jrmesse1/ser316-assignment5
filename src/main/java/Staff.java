public class Staff implements Observer {
    private final int id;
    private final String name;
    private final StaffRole role;
    private final TaskList taskList;
    private StaffState state = new StaffStateAway();

    // daily work capacity is a little less than a full working day, so tired staff sometimes go home before day_end
    private final int dailyWorkCapacity = 60 * 7;
    private int capacityRemaining;

    public Staff(int id, String name, StaffRole role, TaskList taskList) {
        // connect observer to global clock
        Clock.getInstance().attach(this);

        this.id = id;
        this.name = name;
        this.role = role;
        this.taskList = taskList;
    }

    public void resetCapacity() {
        capacityRemaining = dailyWorkCapacity;
        state = new StaffStateWorking();
        Logger.log("STAFF", String.format("%s is back working at the shelter", name));
    }

    public void decrementCapacity() {
        capacityRemaining--;
        if (capacityRemaining == 0) {
            // capacity used up, go home
            Logger.log("STAFF", String.format("%s is tired and going home for the day", name));
            state = new StaffStateAway();
        }
    }

    public void goHomeEndOfDay() {
        // capacity not used up, but go home anyway
        Logger.log("STAFF", String.format("%s is done with their shift and going home for the day", name));
        state = new StaffStateAway();
    }

    public void update(String event) {
        // depending on current state they will either respond to new tasks or
        // do nothing because they're away
        state.update(event, this, taskList);
    }

    public String getName() {
        return name;
    }
}
