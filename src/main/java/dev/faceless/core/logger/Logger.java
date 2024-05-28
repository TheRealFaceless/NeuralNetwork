package dev.faceless.core.logger;

@SuppressWarnings("unused")
public class Logger {
    private static Logger logger;

    private Logger(){}

    public static Logger getLogger() {
        return logger == null ? logger = new Logger() : logger;
    }

    public void logInfo(String info) {
        System.out.println("[INFO] " + info);
    }

    public void logWarning(String warning) {
        System.out.println("[WARNING] " + warning);
    }

    public void logError(String error) {
        System.err.println("[ERROR] " + error);
    }
}
