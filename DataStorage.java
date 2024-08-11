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

    // Load list of customers from a binary file
    public static void loadCustomers1(String fileName, CustomerList customerList) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            List<Customer> customers = (List<Customer>) ois.readObject();
            for (Customer customer : customers) {
                customerList.addCustomer(customer);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void loadRestaurants1(String fileName, RestaurantList restaurantList) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            List<Restaurant> restaurants = (List<Restaurant>) ois.readObject();
            for (Restaurant restaurant : restaurants) {
                restaurantList.add(restaurant);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }




    public static void saveRestaurants(String fileName, List<Restaurant> restaurants) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(restaurants);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Initialize test data
    public static void initializeTestData(String customerFileName, String restaurantFileName) {
        // Initialize Customers
        File customerFile = new File(customerFileName);
        File restaurantFile = new File(restaurantFileName);

        if (customerFile.exists() && restaurantFile.exists()) {
            return; // 文件已存在，跳过初始化
        }
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("alice", "password123", new Profile("Alice", "123-456-7890", "Alice's Location", "Car")));
        customers.add(new Customer("bob", "password456", new Profile("Bob", "234-567-8901", "Bob's Location", "Bike")));
        customers.add(new Customer("charlie", "password789", new Profile("Charlie", "345-678-9012", "Charlie's Location", "Scooter")));

        saveCustomers(customers, customerFileName);

        // Initialize Restaurants
        List<Restaurant> restaurants = new ArrayList<>();

        Restaurant restaurant1 = new Restaurant("italian_bistro", "password1", new Profile("Italian Bistro", "111-222-3333", "123 Pasta St", ""));
        restaurant1.getMenu().addMenuItem(new MenuItem("Pasta Carbonara", 12.99));
        restaurant1.getMenu().addMenuItem(new MenuItem("Margherita Pizza", 10.99));

        Restaurant restaurant2 = new Restaurant("sushi_place", "password2", new Profile("Sushi Place", "444-555-6666", "456 Sushi Ave", ""));
        restaurant2.getMenu().addMenuItem(new MenuItem("California Roll", 8.99));
        restaurant2.getMenu().addMenuItem(new MenuItem("Spicy Tuna Roll", 9.99));

        restaurants.add(restaurant1);
        restaurants.add(restaurant2);

        saveRestaurants(restaurantFileName, restaurants);
    }

}
