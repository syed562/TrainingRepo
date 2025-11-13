package EgovernanceApp;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServiceMenu {
    private static final Logger logger = LogManager.getLogger(ServiceMenu.class);
    private final Scanner sc = new Scanner(System.in);

    public void showMenu() {
        int choice;
        do {
            System.out.println("\n====== E-Governance Services ======");
            System.out.println("1. Apply for Certificate");
            System.out.println("2. Check Application Status");
            System.out.println("3. Pay Tax");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    logger.info("User applied for certificate");
                    System.out.println("Your certificate request has been submitted!");
                    break;
                case 2:
                    logger.info("User checked application status");
                    System.out.println("Your application is under review.");
                    break;
                case 3:
                    logger.info("User accessed tax payment section");
                    System.out.println("Payment completed successfully!");
                    break;
                case 4:
                    logger.info("User logged out.");
                    System.out.println("Thank you for using E-Governance App!");
                    break;
                default:
                    logger.warn("Invalid menu choice entered: {}", choice);
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 4);
    }
}
