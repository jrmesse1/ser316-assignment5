public class Main {

    private static final int SIMULATION_LENGTH_DAYS = 7;
    private static final int SIMULATION_LENGTH_HOURS = SIMULATION_LENGTH_DAYS * 24;

    public static void main(String[] args) {
        Shelter shelter = new Shelter();
        System.out.println("Starting Simulation");
        for (int currentHour = 0; currentHour < SIMULATION_LENGTH_HOURS; currentHour++) {
            runCycle(currentHour, shelter);
        }
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
