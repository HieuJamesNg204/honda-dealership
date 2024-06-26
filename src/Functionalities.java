import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Functionalities {
    private static Scanner scanner = new Scanner(System.in);

    public static int getMenuChoice() {
        System.out.println("1. Add a new Honda car");
        System.out.println("2. List all Honda cars");
        System.out.println("3. List Honda cars by model");
        System.out.println("4. Get Honda cars by price range");
        System.out.println("5. Get a Honda car by ID");
        System.out.println("6. List Honda models");
        System.out.println("7. Update a Honda car");
        System.out.println("8. Delete a Honda car");
        System.out.println("9. Register a customer");
        System.out.println("10. List all customers");
        System.out.println("11. Get a customer by ID");
        System.out.println("12. Update a customer");
        System.out.println("13. Delete a customer");
        System.out.println("14. Add a purchase");
        System.out.println("15. List all purchases");
        System.out.println("16. List purchases by customer id");
        System.out.println("0. Quit");

        int choice;
        while (true) {
            try {
                System.out.print("Your choice: ");
                choice = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: Input was not a valid integer.");
                scanner.next();
            }
        }

        scanner.nextLine();
        return choice;
    }

    public static void addANewHonda() {
        Honda honda = new Honda();

        System.out.print("Model: ");
        String model = scanner.nextLine();
        System.out.print("Version: ");
        String version = scanner.nextLine();

        long listedPrice;
        while (true) {
            try {
                System.out.print("Listed price: ");
                listedPrice = scanner.nextLong();

                if (listedPrice <= 0) {
                    System.out.println("Error: Listed price was not positive.");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: Input was not a valid decimal number.");
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

        scanner.nextLine();
    }

    public static void listAllHondaCars() {
        List<Honda> hondaList = HondaDAO.getAllHondas();
        hondaList.forEach(System.out::println);
        scanner.nextLine();
    }

    public static void listHondaCarsByModel() {
        System.out.print("Model: ");
        String model = scanner.nextLine();

        List<Honda> hondaList = HondaDAO.getHondasByModel(model);
        hondaList.forEach(System.out::println);

        scanner.nextLine();
    }

    public static void listHondaCarsByPriceRange() {
        long minPrice, maxPrice;
        while (true) {
            try {
                System.out.print("Price from: ");
                minPrice = scanner.nextLong();
                System.out.print("Price to: ");
                maxPrice = scanner.nextLong();

                if (minPrice > maxPrice) {
                    long temp = minPrice;
                    minPrice = maxPrice;
                    maxPrice = temp;
                }

                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: Input was not a valid decimal number.");
                scanner.next();
            }
        }
        scanner.nextLine();

        List<Honda> hondaList = HondaDAO.getHondasByPriceRange(minPrice, maxPrice);
        hondaList.forEach(System.out::println);

        scanner.nextLine();
    }

    public static void getAHondaCarById() {
        int hondaId;
        while (true) {
            try {
                System.out.print("Honda ID: ");
                hondaId = scanner.nextInt();

                if (hondaId <= 0) {
                    System.out.print("Error: The ID was not positive.");
                    continue;
                }
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: Input was not a valid integer.");
                scanner.next();
            }
        }

        Honda honda = HondaDAO.getHondaById(hondaId);
        System.out.println(honda);

        scanner.nextLine();
    }

    public static void listHondaModels() {
        List<String> modelList = HondaDAO.getModelList();
        modelList.forEach(System.out::println);
        scanner.nextLine();
    }

    public static void updateAHondaCar() {
        System.out.print("Model: ");
        String model = scanner.nextLine();
        System.out.print("Version: ");
        String version = scanner.nextLine();

        long newListedPrice;
        while (true) {
            try {
                System.out.print("New listed price: ");
                newListedPrice = scanner.nextLong();

                if (newListedPrice <= 0) {
                    System.out.println("Error: Input was not positive.");
                    continue;
                }

                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: Input was not a valid decimal integer.");
                scanner.next();
            }
        }

        int updated = HondaDAO.updateHonda(model, version, newListedPrice);
        if (updated != 0) {
            System.out.println("Successfully updated the Honda car");
        } else {
            System.out.println("Failed to update the Honda car");
        }

        scanner.nextLine();
    }

    public static void deleteAHondaCar() {
        System.out.print("Model: ");
        String model = scanner.nextLine();
        System.out.print("Version: ");
        String version = scanner.nextLine();

        int deleted = HondaDAO.deleteHonda(model, version);
        if (deleted != 0) {
            System.out.println("Successfully deleted the Honda car");
        } else {
            System.out.println("Failed to delete the Honda car");
        }

        scanner.nextLine();
    }

    public static void registerACustomer() {
        Customer customer = new Customer();

        System.out.print("First name: ");
        customer.setFirstName(scanner.nextLine());
        System.out.print("Last name: ");
        customer.setLastName(scanner.nextLine());
        System.out.print("Email: ");
        customer.setEmail(scanner.nextLine());
        System.out.print("Phone: ");
        customer.setPhone(scanner.nextLine());
        System.out.print("Address: ");
        customer.setAddress(scanner.nextLine());

        int added = CustomerDAO.addCustomer(customer);
        if (added != 0) {
            System.out.println("Successfully registered the customer");
        } else {
            System.out.println("Fail to register the customer");
        }

        scanner.nextLine();
    }

    public static void listAllCustomers() {
        List<Customer> customerList = CustomerDAO.getAllCustomers();
        customerList.forEach(System.out::println);
        scanner.nextLine();
    }

    public static void getACustomerById() {
        int customerId;
        while (true) {
            try {
                System.out.print("Customer ID: ");
                customerId = scanner.nextInt();

                if (customerId <= 0) {
                    System.out.print("Error: The ID was not positive.");
                    continue;
                }
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: Input was not a valid integer.");
                scanner.next();
            }
        }

        Customer customer = CustomerDAO.getCustomerById(customerId);
        System.out.println(customer);

        scanner.nextLine();
    }

    public static void updateACustomer() {
        int customerId;
        while (true) {
            try {
                System.out.print("Customer ID: ");
                customerId = scanner.nextInt();

                if (customerId <= 0) {
                    System.out.print("Error: The ID was not positive.");
                    continue;
                }
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: Input was not a valid integer.");
                scanner.next();
            }
        }

        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Phone: ");
        String phone = scanner.nextLine();
        System.out.println("Address: ");
        String address = scanner.nextLine();

        int updated = CustomerDAO.updateCustomer(customerId, email, phone, address);
        if (updated != 0) {
            System.out.println("Successfully updated the customer");
        } else {
            System.out.println("Failed to update the customer");
        }

        scanner.nextLine();
    }

    public static void deleteACustomer() {
        int customerId;
        while (true) {
            try {
                System.out.print("Customer ID: ");
                customerId = scanner.nextInt();

                if (customerId <= 0) {
                    System.out.print("Error: The ID was not positive.");
                    continue;
                }
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: Input was not a valid integer.");
                scanner.next();
            }
        }

        int deleted = CustomerDAO.deleteCustomer(customerId);
        if (deleted != 0) {
            System.out.println("Successfully deleted the customer");
        } else {
            System.out.println("Failed to delete the customer");
        }

        scanner.nextLine();
    }

    public static void addAPurchase() {
        int customerId;
        int hondaId;
        while (true) {
            try {
                System.out.print("Customer ID: ");
                customerId = scanner.nextInt();
                System.out.print("Honda ID: ");
                hondaId = scanner.nextInt();

                if (customerId <= 0 || hondaId <= 0) {
                    System.out.print("Error: The ID was not positive.");
                    continue;
                }

                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: Input was not a valid integer.");
                scanner.next();
            }
        }

        System.out.print("Purchase location: ");
        String purchaseLocation = scanner.nextLine();

        long onTheRoadPrice;
        while (true) {
            try {
                System.out.print("On-the-road price: ");
                onTheRoadPrice = scanner.nextLong();

                if (onTheRoadPrice <= 0) {
                    System.out.println("Error: Input was not positive.");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: Input was not a valid decimal number.");
                scanner.next();
            }
        }

        int recorded = HondaService.recordPurchase(
                customerId,
                hondaId,
                purchaseLocation,
                onTheRoadPrice,
                LocalDate.now()
        );

        if (recorded != 0) {
            System.out.println("Successfully added the record");
        } else {
            System.out.println("Failed to add the record");
        }

        scanner.nextLine();
    }

    public static void listAllPurchases() {
        List<String> purchaseList = HondaService.getAllPurchaseRecords();
        purchaseList.forEach(System.out::println);
        scanner.nextLine();
    }

    public static void listPurchasesByCustomerId() {
        int customerId;
        while (true) {
            try {
                System.out.print("Customer ID: ");
                customerId = scanner.nextInt();

                if (customerId <= 0) {
                    System.out.print("Error: The ID was not positive.");
                    continue;
                }
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: Input was not a valid integer.");
                scanner.next();
            }
        }

        List<String> purchaseList = HondaService.getRecordsByCustomerId(customerId);
        purchaseList.forEach(System.out::println);
        scanner.nextLine();
    }
}