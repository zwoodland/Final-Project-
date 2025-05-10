import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();
        FileHandler fileHandler = new FileHandler();

        // Load data at the start
        manager.setInventory(fileHandler.loadData("inventory.txt"));

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Inventory Management System");
            System.out.println("1. View Inventory");
            System.out.println("2. Add Item");
            System.out.println("3. Mark Item as Sold-Out");
            System.out.println("4. View Sold-Out Items");
            System.out.println("5. Save and Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    manager.displayInventory();
                    break;
                case 2:
                    scanner.nextLine(); // To consume the newline
                    System.out.print("Enter item name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    System.out.print("Enter price: ");
                    double price = scanner.nextDouble();
                    manager.addItem(new Item(name, quantity, price));
                    break;
                case 3:
                    System.out.print("Enter the index of the item to mark as sold-out: ");
                    int index = scanner.nextInt();
                    manager.markAsSoldOut(index);
                    break;
                case 4:
                    manager.displaySoldOutItems();
                    break;
                case 5:
                    fileHandler.saveData(manager.getInventory(), "inventory.txt");
                    System.out.println("Data saved. Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }
}
