public class Main {
    private static final int SIMULATION_LENGTH_DAYS = 7;
    private static final int SIMULATION_LENGTH_HOURS = SIMULATION_LENGTH_DAYS * 24;
    private static final int SIMULATION_LENGTH_MINUTES = SIMULATION_LENGTH_HOURS * 60;

    public static void main(String[] args) {
        Clock clock = Clock.getInstance();
        Shelter shelter = new Shelter();
        Logger.log("WORLD", "Starting Simulation");

        for (int i = 0; i < SIMULATION_LENGTH_MINUTES; i++) {
            // Run a single cycle of the simulation by incrementing 1 minute
            clock.incrementCurrentTime();
        }
    }
}
