import java.util.ArrayList;

public class Shelter {
    private static final int INITIAL_ANIMALS = 5;
    private static final int INITIAL_STAFF = 3;
    private ArrayList<Animal> animals;
    private ArrayList<Staff> staff;
    private NameGenerator animalNamer = new NameGenerator("animal_names.txt");
    private NameGenerator staffNamer =  new NameGenerator("staff_names.txt");

    /**
     * Create shelter and add all the animals and staff at startup.
     */
    public Shelter() {
        animals = new ArrayList<>();
        staff = new ArrayList<>();
        for (int i = 0; i < INITIAL_STAFF; i++) {
            hireStaff();
        }
        for (int i = 0; i < INITIAL_ANIMALS; i++) {
            intakeAnimal();
        }
    }

    /**
     * Event where we hire a staff member.
     */
    public void hireStaff() {
        int id = staff.size();
        String name = staffNamer.getName();
        String role = "Vet";
        staff.add(new Staff(id, name, role));
        System.out.printf("[SHELTER] Hired %s as a %s\n", name, role);
    }

    /**
     * Event where a random new animal joins the Shelter.
     */
    public void intakeAnimal() {
        int id = animals.size();
        String name = animalNamer.getName();
        String species = "Dog";
        animals.add(new Animal(id, name, species, 99, 99));
        System.out.printf("[SHELTER] Took in a %s named %s\n", species, name);
    }
}
