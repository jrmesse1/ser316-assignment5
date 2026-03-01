public class StaffStateAway extends StaffState {
    @Override
    void update(String event, Staff employee) {
        // it's a new day, reset working capacity of staff and transition into working state
        if (event.equals("day_start")) employee.resetCapacity();
    }
}
