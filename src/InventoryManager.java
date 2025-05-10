import java.util.ArrayList;
import java.util.Scanner;

public class InventoryManager {
    
    static ArrayList<String> inventory = new ArrayList<>();
    static ArrayList<String> soldOutItems = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        loadInventory();  // Load existing inventory data from a file
        
        while (true) {
            System.out.println("\nInventory Management System:");
            System.out.println("1. View Inventory");
            System.out.println("2. Add Item");
            System.out.println("3. Sell Item");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            
            int choice = InputValidator.getUserInput();
            
            switch (choice) {
                case 1:
                    viewInventory();
                    break;
                case 2:
                    addItem();
                    break;
                case 3:
                    sellItem();
                    break;
                case 4:
                    saveInventory();  // Save inventory and sold-out items to files
                    System.out.println("Exiting program...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void viewInventory() {
        System.out.println("\nCurrent Inventory:");
        for (String item : inventory) {
            System.out.println("- " + item);
        }
    }

    public static void addItem() {
        System.out.print("Enter the item to add: ");
        String item = new Scanner(System.in).nextLine();
        inventory.add(item);
        System.out.println("Item added: " + item);
    }

    public static void sellItem() {
        System.out.print("Enter the item to sell: ");
        String item = new Scanner(System.in).nextLine();
        
        if (inventory.contains(item)) {
            inventory.remove(item);
            soldOutItems.add(item);
            System.out.println("Item sold: " + item);
        } else {
            System.out.println("Item not found in inventory.");
        }
    }
    
    public static void loadInventory() {
        inventory = FileHandler.loadData("inventory.txt");
        soldOutItems = FileHandler.loadData("soldOut.txt");
    }

    public static void saveInventory() {
        FileHandler.saveData(inventory, "inventory.txt");
        FileHandler.saveData(soldOutItems, "soldOut.txt");
    }
}
