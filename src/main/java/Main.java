public class Main {
    private static final int SHELTER_INITIAL_ANIMALS = 5;
    private static final int SHELTER_INITIAL_STAFF = 3;
    private static final int SIMULATION_LENGTH_DAYS = 7;
    private static final int SIMULATION_LENGTH_HOURS = SIMULATION_LENGTH_DAYS * 24;

    public static void main(String[] args) {
        System.out.println("Starting Simulation");
        Shelter shelter = populateShelter();
        for (int currentHour = 0; currentHour < SIMULATION_LENGTH_HOURS; currentHour++) {
            runCycle(currentHour, shelter);
        }
    }

    /**
     * Create shelter and add all the animals at startup.
     */
    private static Shelter populateShelter() {
        Shelter shelter = new Shelter();
        for (int i = 0; i < SHELTER_INITIAL_STAFF; i++) {
            shelter.hireStaff();
        }
        for (int i = 0; i < SHELTER_INITIAL_ANIMALS; i++) {
            shelter.intakeAnimal();
        }
        return shelter;
    }

    /**
     * Run a single cycle of the simulation (1 hour)
     *
     * @param currentHour
     * @param shelter
     */
    private static void runCycle(int currentHour, Shelter shelter) {
        System.out.printf("[CLOCK] Day: %d Time: %02d:00\n", currentHour / 24 + 1, currentHour % 24);
    }
}
