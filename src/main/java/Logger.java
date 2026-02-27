public class Logger {
    private static Logger singletonLogger;

    private static Logger getInstance() {
        if (singletonLogger == null) singletonLogger = new Logger();
        return singletonLogger;
    }

    public static void log(String entity, String message) {
        String time = Clock.getInstance().getTime();
        entity = "["  + entity + "]";
        System.out.printf("%10s %s %s\n", entity, time, message);
    }
}
