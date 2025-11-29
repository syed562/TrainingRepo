package EgovernanceApp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomerLogin {
    private static final Logger logger = LogManager.getLogger(CustomerLogin.class);

    public boolean login(String username, String password) {
        logger.info("Attempting login for user: {}", username);
        if ("admin".equals(username) && "1234".equals(password)) {
            logger.info("Login successful for user: {}", username);
            return true;
        } else {
            logger.error("Login failed for user: {}", username);
            return false;
        }
    }
}
