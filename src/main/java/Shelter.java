import java.util.ArrayList;
import java.util.Random;

public class Shelter implements Observer {
    private static final int INITIAL_ANIMALS = 5;
    private static final int INITIAL_STAFF = 3;
    private final ArrayList<Animal> animals;
    private final ArrayList<Staff> staff;
    private final NameGenerator animalNamer = new NameGenerator("animal_names.txt");
    private final NameGenerator humanNamer = new NameGenerator("staff_names.txt");
    private final WeightedCoin newAnimalCoin = new WeightedCoin(0.0007);
    private final WeightedCoin animalAdoptionCoin = new WeightedCoin(0.0004);

    private static Random rand = new Random();

    /**
     * Create shelter and populate it with animals and staff at startup. This is called by Main. It is expected that
     * only one Shelter exists in the simulation.
     *
     * This covers requirement R1.1 (start with at least 5 animals)
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
     * Get a string containing statistics about the animals in the shelter. This is printed out at the start of each
     * day. It is also used in tests.
     *
     * @return String describing status of animals in the shelter.
     */
    public String getStats() {
        int adopted = 0;
        int inShelter = 0;
        int readyForAdoption = 0;
        for (Animal animal : animals) {
            if (animal.getStatus() == AnimalStatus.ADOPTED) {
                adopted++;
            } else {
                inShelter++;
                if (animal.getStatus() == AnimalStatus.AVAILABLE) readyForAdoption++;
            }
        }
        String formatString = "%d animal(s) have been adopted. " +
                "There are %d animal(s) in the shelter and %d are available for adoption";
        return String.format(formatString, adopted, inShelter, readyForAdoption);
    }

    /**
     * Event where we hire a staff member with a random name and role.
     */
    public void hireStaff() {
        int id = staff.size();
        String name = humanNamer.getName();

        // get random role
        int i = rand.nextInt(StaffRole.values().length);
        StaffRole role = StaffRole.values()[i];

        Staff employee = new Staff(id, name, role);
        staff.add(employee);
        Logger.log("SHELTER", String.format("Hired %s as a %s", name, role.toString().toLowerCase()));
    }

    /**
     * Event where a random new random animal joins the Shelter.
     *
     * @return The animal that arrived.
     */
    public Animal intakeAnimal() {
        int id = animals.size();
        String name = animalNamer.getName();
        Animal animal = new Animal(id, name);
        animals.add(animal);
        String formatString = "A new %s arrived. Their name is %s." +
                "They are %d year(s) old, weigh %.1f lbs, and are in %s health.";
        Logger.log("SHELTER", String.format(formatString, animal.getSpecies(), name, animal.getAge(),
                animal.getWeight(), animal.getHealth()));

        // schedule an intake exam
        TaskList.getInstance().addTask(new TaskIntakeExam(animal));

        return animal;
    }

    /**
     * Gives each animal in the shelter a random chance for adoption. No more than one animal will be adopted within a
     * single function call, but the chance of some animal getting adopted increases as the population of the shelter
     * increases.
     */
    public void maybeAdoptAnimals() {
        for (Animal animal : animals) {
            if (animal.getStatus() == AnimalStatus.AVAILABLE && animalAdoptionCoin.flip()) {
                animal.setStatus(AnimalStatus.ADOPTED);
                String adopterName = humanNamer.getName();
                Logger.log("SHELTER", String.format("%s the %s was adopted by a person named %s",
                        animal.getName(), animal.getSpecies(), adopterName));
                return;
            }
        }
    }

    /**
     * Process an event from a Subject that this Observer is attached to.
     * @param event String describing the source of the event.
     */
    @Override
    public void update(String event) {
        if (event.equals("day_start")) {
            Logger.log("SHELTER", getStats());
            addDailyTaskAssignment();
            TaskList.getInstance().printStats();
        } else if (event.equals("minute")) {
            if (newAnimalCoin.flip()) intakeAnimal();
            maybeAdoptAnimals();
        }
    }

    /**
     * Assign daily tasks for all the animals.
     *
     * This covers requirement R4.1 (provide basic care actions) and R4.2 (schedule a simple medical action)
     */
    public void addDailyTaskAssignment() {
        for (Animal animal : animals) {
            if (animal.getStatus() != AnimalStatus.ADOPTED) {
                TaskList.getInstance().addTask(new TaskDailyExercise(animal));
                TaskList.getInstance().addTask(new TaskDailyFeeding(animal));
                TaskList.getInstance().addTask(new TaskEnclosureCleaning(animal));
            }

            if (animal.getStatus() == AnimalStatus.NEEDS_VACCINATION) {
                TaskList.getInstance().addTask(new TaskVaccination(animal));
            }
        }
    }
}
