public class Main {
    public static void main(String[] args) {
        int choice;
        boolean done = false;

        while (!done) {
            choice = Functionalities.getMenuChoice();

            switch (choice) {
                case 0:
                    done = true;
                    break;

                case 1:
                    Functionalities.addANewHonda();
                    break;

                case 2:
                    Functionalities.listAllHondaCars();
                    break;

                case 3:
                    Functionalities.listHondaCarsByModel();
                    break;

                case 4:
                    Functionalities.listHondaCarsByPriceRange();
                    break;

                case 5:
                    Functionalities.getAHondaCarById();
                    break;

                case 6:
                    Functionalities.listHondaModels();
                    break;

                case 7:
                    Functionalities.updateAHondaCar();
                    break;

                case 8:
                    Functionalities.deleteAHondaCar();
                    break;

                case 9:
                    Functionalities.registerACustomer();
                    break;

                case 10:
                    Functionalities.listAllCustomers();
                    break;

                case 11:
                    Functionalities.getACustomerById();
                    break;

                case 12:
                    Functionalities.updateACustomer();
                    break;

                case 13:
                    Functionalities.deleteACustomer();
                    break;

                case 14:
                    Functionalities.addAPurchase();
                    break;

                case 15:
                    Functionalities.listAllPurchases();
                    break;

                case 16:
                    Functionalities.listPurchasesByCustomerId();
                    break;

                default:
                    System.out.println("Error: Not a valid choice");
                    break;
            }
        }
    }
}