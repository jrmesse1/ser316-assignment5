public class Main {
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
     * Shelter has at least 5 animals at startup.
     */
    private static Shelter populateShelter() {
        return new Shelter();
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
