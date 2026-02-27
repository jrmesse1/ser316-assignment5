public class Main {
    private static final int SIMULATION_LENGTH_DAYS = 7;
    private static final int SIMULATION_LENGTH_HOURS = SIMULATION_LENGTH_DAYS * 24;

    public static void main(String[] args) {
        Clock clock = Clock.getInstance();
        Shelter shelter = new Shelter();
        System.out.println("\n[WORLD] Starting Simulation");

        for (int i = 0; i < SIMULATION_LENGTH_HOURS; i++) {
            // Run a single cycle of the simulation by incrementing 1 hour
            clock.incrementCurrentHour();
        }
    }
}
