public class Main {
    private static final int SIMULATION_LENGTH_DAYS = 7;
    private static final int SIMULATION_LENGTH_HOURS = SIMULATION_LENGTH_DAYS * 24;
    private static final int SIMULATION_LENGTH_MINUTES = SIMULATION_LENGTH_HOURS * 60;

    /**
     * Entrypoint for the application. This kicks off the whole simulation and runs it for 1 week. Each "cycle" of the
     * simulation is 1 minute and cycles are triggered by incrementing the Clock time.
     * @param args
     */
    public static void main(String[] args) {
        Clock clock = Clock.getInstance();
        new Shelter();
        Logger.log("WORLD", "Starting Simulation");

        for (int i = 0; i < SIMULATION_LENGTH_MINUTES; i++) {
            // Run a single cycle of the simulation by incrementing 1 minute
            clock.incrementCurrentTime();
        }
    }
}
