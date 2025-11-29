package EgovernanceApp;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainApp {
    private static final Logger logger = LogManager.getLogger(MainApp.class);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CustomerLogin login = new CustomerLogin();
        logger.info("Application started");

        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        if (login.login(username, password)) {
            logger.info("User successfully logged in.");
            ServiceMenu menu = new ServiceMenu();
            menu.showMenu();
        } else {
            logger.error("Login attempt failed.");
            System.out.println("Invalid credentials. Please restart and try again.");
        }

        logger.info("Application closed");
        sc.close();
    }
}
