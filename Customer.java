import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private Profile profile;
    private List<Order> historyOrderList;

    public Customer(String username, String password, Profile profile) {
        this.username = username;
        this.password = password;
        this.profile = profile;
        this.historyOrderList = new ArrayList<>();
    }

    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Profile getProfile() { return profile; }
    public void setProfile(Profile profile) { this.profile = profile; }

    public List<Order> getHistoryOrderList() { return historyOrderList; }
    public void addOrderToHistory(Order order) {
        System.out.println("Adding order to history for customer: " + username); // Debug info
        if (historyOrderList == null) {
            System.out.println("historyOrderList is null!"); // Check if the list is properly initialized
        } else {
            historyOrderList.add(order);
            System.out.println("Order added successfully."); // Confirm that the order has been added
        }
    }

    // Additional method to update profile information
    public void updateProfile(String name, String phone, String location, String vehicle) {
        this.profile.edit(name, phone, location, vehicle);
    }
}
