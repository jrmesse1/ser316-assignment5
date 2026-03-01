public class Staff implements Observer {
    private final int id;
    private final String name;
    private final StaffRole role;
    // daily work capacity is a little less than a full working day, so tired staff sometimes go home before day_end
    private static final int DAILY_WORK_CAPACITY = 60 * 7;
    private StaffState state = new StaffStateAway();

    public Staff(int id, String name, StaffRole role) {
        // connect observer to global clock
        Clock.getInstance().attach(this);
        TaskList.getInstance().attach(this);

        this.id = id;
        this.name = name;
        this.role = role;
    }

    public void resetCapacity() {
        state = new StaffStateWorking(DAILY_WORK_CAPACITY);
        Logger.log("STAFF", String.format("%s is back working at the shelter", name));
    }

    public void goHomeTired() {
        Logger.log("STAFF", String.format("%s is tired and going home for the day", name));
        state = new StaffStateAway();
    }

    public void goHomeEndOfDay() {
        // capacity not used up, but go home anyway
        Logger.log("STAFF", String.format("%s is done with their shift and going home for the day", name));
        state = new StaffStateAway();
    }

    public void update(String event) {
        // depending on current state they will either respond to new tasks or
        // do nothing because they're away
        state.update(event, this);
    }

    public String getName() {
        return name;
    }
}
