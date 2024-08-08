import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataStorage {
    // Save list of customers to a binary file
    public static void saveCustomers(List<Customer> customers, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(customers);
            System.out.println("Customer list saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load list of customers from a binary file
    public static List<Customer> loadCustomers(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<Customer>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    public static List<Restaurant> loadRestaurants(String fileName) {
        List<Restaurant> restaurants = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            restaurants = (List<Restaurant>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return restaurants;
    }

    public static void addOrderToRestaurant(String fileName, Order order) {
        List<Restaurant> restaurants = loadRestaurants(fileName);
        // Assuming restaurant name matches with customer order, update the new order list
        for (Restaurant r : restaurants) {
            if (r.getName().equals(order.getCustomer().getUsername())) {  // Just for example; adjust as needed
                r.getNewOrderList().add(order);
                break;
            }
        }
        saveRestaurants(fileName, restaurants);
    }

    public static void saveRestaurants(String fileName, List<Restaurant> restaurants) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(restaurants);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Initialize test data
    public static void initializeTestData(String fileName) {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("alice", "password123", "123-456-7890", "Alice's Location"));
        customers.add(new Customer("bob", "password456", "234-567-8901", "Bob's Location"));
        customers.add(new Customer("charlie", "password789", "345-678-9012", "Charlie's Location"));

        saveCustomers(customers, fileName);
        List<Restaurant> restaurants = new ArrayList<>();

        Restaurant restaurant1 = new Restaurant("Italian Bistro");
        restaurant1.addMenuItem(new MenuItem("Pasta Carbonara", 12.99));
        restaurant1.addMenuItem(new MenuItem("Margherita Pizza", 10.99));

        Restaurant restaurant2 = new Restaurant("Sushi Place");
        restaurant2.addMenuItem(new MenuItem("California Roll", 8.99));
        restaurant2.addMenuItem(new MenuItem("Spicy Tuna Roll", 9.99));

        restaurants.add(restaurant1);
        restaurants.add(restaurant2);

        // Save to file
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("restaurants.dat"))) {
            oos.writeObject(restaurants);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
