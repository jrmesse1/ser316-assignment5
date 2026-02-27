public class Staff implements Observer {
    int id;
    String name;
    StaffRole role;

    public Staff(int id, String name, StaffRole role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public void update (String event) {

    }
}
