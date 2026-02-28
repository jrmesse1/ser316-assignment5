import java.util.ArrayList;
import java.util.Random;

public class Shelter implements Observer {
    private static final int INITIAL_ANIMALS = 5;
    private static final int INITIAL_STAFF = 3;
    private final ArrayList<Animal> animals;
    private final ArrayList<Staff> staff;
    private final TaskList taskList = new TaskList();
    private final NameGenerator animalNamer = new NameGenerator("animal_names.txt");
    private final NameGenerator staffNamer = new NameGenerator("staff_names.txt");
    private final WeightedCoin newAnimalCoin = new WeightedCoin(0.0006);

    /**
     * Create shelter and add all the animals and staff at startup.
     */
    public Shelter() {
        // connect observer to global clock
        Clock.getInstance().attach(this);

        // create initial animals and staff
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

        // get random role
        Random random = new Random();
        int i = random.nextInt(StaffRole.values().length);
        StaffRole role = StaffRole.values()[i];

        Staff employee = new Staff(id, name, role, taskList);
        staff.add(employee);
        Logger.log("SHELTER", String.format("Hired %s as a %s", name, role.toString().toLowerCase()));
    }

    /**
     * Event where a random new animal joins the Shelter.
     */
    public void intakeAnimal() {
        int id = animals.size();
        String name = animalNamer.getName();
        Animal animal = new Animal(id, name);
        animals.add(animal);
        Clock.getInstance().attach(animal);
        Logger.log("SHELTER", String.format("A new %s arrived. Their name is %s. They are %d year(s) old, weigh %d lbs, and are in %s health.", animal.getSpecies(), name, animal.getAge(), animal.getWeight(), animal.getHealth()));

        // schedule an intake exam
        taskList.addTask(new TaskIntakeExam(animal));
    }

    @Override
    public void update(String event) {
        if (event.equals("day")) {
            addDailyTaskAssignment();
            taskList.printStats();
        } else if (event.equals("minute")) {
            if (newAnimalCoin.flip()) intakeAnimal();
        }
    }

    /**
     * Assign daily tasks for all the animals.
     */
    private void addDailyTaskAssignment() {
        for (Animal animal : animals) {
            if (animal.getStatus() != AnimalStatus.ADOPTED) {
                taskList.addTask(new TaskDailyExercise(animal));
                taskList.addTask(new TaskDailyFeeding(animal));
                taskList.addTask(new TaskEnclosureCleaning(animal));
            }
        }
    }
}
