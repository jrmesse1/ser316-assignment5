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
        String formatString = "%d animal(s) have been adopted. There are %d animal(s) in the shelter and %d are available for adoption";
        return String.format(formatString, adopted, inShelter, readyForAdoption);
    }

    /**
     * Event where we hire a staff member.
     */
    public void hireStaff() {
        int id = staff.size();
        String name = humanNamer.getName();

        // get random role
        Random random = new Random();
        int i = random.nextInt(StaffRole.values().length);
        StaffRole role = StaffRole.values()[i];

        Staff employee = new Staff(id, name, role);
        staff.add(employee);
        Logger.log("SHELTER", String.format("Hired %s as a %s", name, role.toString().toLowerCase()));
    }

    /**
     * Event where a random new animal joins the Shelter.
     */
    public Animal intakeAnimal() {
        int id = animals.size();
        String name = animalNamer.getName();
        Animal animal = new Animal(id, name);
        animals.add(animal);
        Clock.getInstance().attach(animal);
        Logger.log("SHELTER", String.format("A new %s arrived. Their name is %s. They are %d year(s) old, weigh %d lbs, and are in %s health.", animal.getSpecies(), name, animal.getAge(), animal.getWeight(), animal.getHealth()));

        // schedule an intake exam
        TaskList.getInstance().addTask(new TaskIntakeExam(animal));

        return animal;
    }

    public void maybeAdoptAnimals() {
        for (Animal animal : animals) {
            if (animal.getStatus() == AnimalStatus.AVAILABLE && animalAdoptionCoin.flip()) {
                animal.setStatus(AnimalStatus.ADOPTED);
                String adopterName = humanNamer.getName();
                Logger.log("SHELTER", String.format("%s the %s was adopted by a person named %s", animal.getName(), animal.getSpecies(), adopterName));
                return;
            }
        }
    }

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
