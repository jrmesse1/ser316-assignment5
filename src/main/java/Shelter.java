import java.util.ArrayList;
import java.util.Random;

public class Shelter implements Observer {
    private static final int INITIAL_ANIMALS = 5;
    private static final int INITIAL_STAFF = 3;
    private int hoursUntilDailyTasksAssigned = 0;
    private ArrayList<Animal> animals;
    private ArrayList<Staff> staff;
    private TaskList taskList = new TaskList();
    private NameGenerator animalNamer = new NameGenerator("animal_names.txt");
    private NameGenerator staffNamer =  new NameGenerator("staff_names.txt");
    private Clock clock;
    private WeightedCoin newAnimalCoin = new WeightedCoin(6);

    /**
     * Create shelter and add all the animals and staff at startup.
     */
    public Shelter(Clock clock) {
        // connect observer
        this.clock = clock;
        clock.attach(this);

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
        clock.attach(employee);
        System.out.printf("[SHELTER] Hired %s as a %s\n", name, role.toString().toLowerCase());
    }

    /**
     * Event where a random new animal joins the Shelter.
     */
    public void intakeAnimal() {
        int id = animals.size();
        String name = animalNamer.getName();
        Animal animal = new Animal(id, name);
        animals.add(animal);
        clock.attach(animal);
        System.out.printf("[SHELTER] A new %s arrived. Their name is %s.\n", animal.getSpecies(), name);

        // schedule an intake exam
        taskList.addTask(animal, TaskType.INTAKE_EXAM);
    }

    @Override
    public void update(String event) {
        if (event.equals("clock")) {
            checkDailyTaskAssignment();
            taskList.printStats();
            if (newAnimalCoin.flip()) intakeAnimal();
        }
    }

    /**
     * Every 24 hours daily tasks need to be assigned for all the animals.
     */
    private void checkDailyTaskAssignment() {
        if (hoursUntilDailyTasksAssigned == 0) {
            // time for daily tasks
            hoursUntilDailyTasksAssigned = 24;
            for (Animal animal : animals) taskList.addTask(animal, TaskType.DAILY_EXERCISE);
            for (Animal animal : animals) taskList.addTask(animal, TaskType.DAILY_FEEDING);
            for (Animal animal : animals) taskList.addTask(animal, TaskType.ENCLOSURE_CLEANING);
        }
        hoursUntilDailyTasksAssigned--;
    }
}
