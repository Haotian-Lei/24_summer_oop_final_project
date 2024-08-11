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
    
    public static List<Driver> loadDrivers(String fileName) {
        List<Driver> drivers = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            drivers = (List<Driver>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return drivers;
    }
    
    public static List<Order> loadWaitAcceptList(String fileName) {
        List<Order> orders = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            orders = (List<Order>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return orders;
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
    
    public static void loadDrivers1(String fileName, DriverList driverList) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            List<Driver> drivers = (List<Driver>) ois.readObject();
            for (Driver driver : drivers) {
                driverList.addDriver(driver);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static void loadWaitAcceptList1(String fileName, WaitAcceptList wal) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            List<Order> orders = (List<Order>) ois.readObject();
            for (Order o : orders) {
                wal.addWaitAcceptOrder(o);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void saveDrivers(String fileName, List<Driver> drivers) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(drivers);
        } catch (IOException e) {
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
    
    public static void saveWaitAcceptList(String fileName, List<Order> orders) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(orders);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Initialize test data
    public static void initializeTestData(String customerFileName, String restaurantFileName, String driverFileName, String walFileName) {
        // Initialize Customers
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
        
		List<Driver> drivers = new ArrayList<Driver>();
		Profile dp1 = new Profile("Sophia", "3129876543", "NA", "bicycle");
		Driver d1 = new Driver("SophiaRider", "060799", dp1);
		Profile dp2 = new Profile("Jackson", "7184567890", "NA", "car");
		Driver d2 = new Driver("JackOnWheels", "112588", dp2);
		Profile dp3 = new Profile("Olivia", "5101234567", "NA", "scooter");
		Driver d3 = new Driver("LivScoot", "091201", dp3);
		
		drivers.add(d1);
		drivers.add(d2);
		drivers.add(d3);
		
		saveDrivers(driverFileName, drivers);
		
		List<Order> wal = new ArrayList<Order>();
		saveWaitAcceptList(walFileName, wal);
    }

}

