public class Staff implements Observer {
    private final int id;
    private final String name;
    private final StaffRole role;
    // daily work capacity is a little less than a full working day, so tired staff sometimes go home before day_end
    private static final int DAILY_WORK_CAPACITY = 60 * 7;
    private StaffState state = new StaffStateAway();

    /**
     * Create a new staff member.
     *
     * This sets the role for the staff, which covers R2.1 (define at least two staff roles).
     *
     * @param id Unique number identifying the staff member.
     * @param name Human-readable name for the staff member.
     * @param role What job the staff member has at the shelter.
     */
    public Staff(int id, String name, StaffRole role) {
        // connect observer to global clock
        Clock.getInstance().attach(this);
        TaskList.getInstance().attach(this);

        this.id = id;
        this.name = name;
        this.role = role;
    }

    /**
     * Transition the staff member back into a working state with a new capacity equal to DAILY_WORK_CAPACITY.
     */
    public void resetCapacity() {
        state = new StaffStateWorking(DAILY_WORK_CAPACITY);
        Logger.log("STAFF", String.format("%s is back working at the shelter", name));
    }

    /**
     * Transition the staff member to non-working state because they have run out of capacity.
     */
    public void goHomeTired() {
        Logger.log("STAFF", String.format("%s is tired and going home for the day", name));
        state = new StaffStateAway();
    }

    /**
     * Transition the staff member to non-working state without them having run out of capacity.
     */
    public void goHomeEndOfDay() {
        // capacity not used up, but go home anyway
        Logger.log("STAFF", String.format("%s is done with their shift and going home for the day", name));
        state = new StaffStateAway();
    }

    /**
     * Process an event from a Subject that this Observer is attached to.
     * @param event String describing the source of the event.
     */
    @Override
    public void update(String event) {
        // depending on current state they will either respond to new tasks or
        // do nothing because they're away
        state.update(event, this);
    }

    public String getName() {
        return name;
    }
}
