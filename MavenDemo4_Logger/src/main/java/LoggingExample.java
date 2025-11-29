import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingExample {
    private static final Logger logger = LogManager.getLogger(LoggingExample.class);

    public static void main(String[] args) {
        logger.info("Info: Application started.");
        logger.warn("Warning: This is a warning message.");
        logger.error("Error: This is an error message.");

        try {
            int result = 10 / 0; // This will cause ArithmeticException
        } catch (ArithmeticException e) {
            logger.error("Caught exception while dividing: ", e);
        }

        logger.info("Info: Application finished.");
    }
}
