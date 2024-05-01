import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Functionalities {
    private static Scanner scanner = new Scanner(System.in);

    public static int getMenuChoice() {
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
        System.out.println("0. Quit");

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

        scanner.nextLine();
        return choice;
    }

    public static void addNewHonda() {
        Honda honda = new Honda();

        System.out.print("Model: ");
        String model = scanner.nextLine();
        System.out.print("Version: ");
        String version = scanner.nextLine();

        double listedPrice;
        while (true) {
            try {
                System.out.print("Listed price: ");
                listedPrice = scanner.nextDouble();

                if (listedPrice <= 0) {
                    System.out.println("Listed price should be positive");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: The input type is not valid. Try again!");
                scanner.next();
            }
        }
        scanner.nextLine();

        honda.setModel(model);
        honda.setVersion(version);
        honda.setListedPrice(listedPrice);

        int added = HondaDAO.addHonda(honda);

        if (added != 0) {
            System.out.println("Successfully added the Honda car!");
        } else {
            System.out.println("Failed to add the Honda car");
        }
    }

    public static void listAllHondaCars() {
        List<Honda> hondaList = HondaDAO.getAllHondas();
        if (hondaList != null) {
            hondaList.forEach(System.out::println);
        } else {
            System.out.println("Error: Cannot list all Honda cars.");
        }
    }

    public static void listHondaCarsByModel() {
        System.out.print("Model: ");
        String model = scanner.nextLine();

        List<Honda> hondaList = HondaDAO.getHondasByModel(model);
        if (hondaList != null) {
            hondaList.forEach(System.out::println);
        } else {
            System.out.println("Error: Cannot list Honda cars.");
        }
    }

    public static void listHondaCarsByPriceRange() {
        //
    }
}