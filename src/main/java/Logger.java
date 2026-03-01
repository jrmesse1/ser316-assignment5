public class Logger {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

    /**
     * Print a log message. Message will be colored based on the source.
     * @param entity String representing the source of the log message.
     * @param message String containing the content of the log message.
     */
    public static void log(String entity, String message) {
        String time = Clock.getInstance().toString();
        String color = "";
        if (entity.equals("CLOCK")) {
            color = "\n" + ANSI_RED;
        } else if (entity.equals("WORLD")) {
            color = ANSI_BLUE;
        } else if (entity.equals("SHELTER")) {
            color = ANSI_GREEN;
        } else if (entity.equals("ANIMAL")) {
            color = ANSI_YELLOW;
        } else if (entity.equals("STAFF")) {
            color = ANSI_CYAN;
        } else if (entity.equals("TASKLIST")) {
            color = ANSI_PURPLE;
        }
        entity = "[" + entity + "]";
        System.out.printf(color + "%10s" + ANSI_RESET + " %s %s\n", entity, time, message);
    }
}
