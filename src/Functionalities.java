import java.util.InputMismatchException;
import java.util.Scanner;

public class Functionalities {
    public static int getMenuChoice() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Add a new Honda car");
        System.out.println("2. List all Honda cars");
        System.out.println("3. Get Honda cars by model");
        System.out.println("4. Get Honda cars by price range");
        System.out.println("5. Get Honda cars by ID");
        System.out.println("6. List Honda models");
        System.out.println("7. Update Honda");
        System.out.println("8. Delete Honda");
        System.out.println("9. Register a customer");
        System.out.println("10. List all customers");
        System.out.println("11. Get a customer by ID");
        System.out.println("12. Update customer");
        System.out.println("13. Delete user");
        System.out.println("14. Add a purchase");
        System.out.println("15. Get all purchases");
        System.out.println("16. Get purchases by customer id");

        int choice;
        while (true) {
            try {
                System.out.println("Your choice: ");
                choice = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: Input was not a valid integer");
                scanner.next();
            }
        }

        scanner.close();
        return choice;
    }
}