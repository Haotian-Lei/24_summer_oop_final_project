import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String phoneNumber;
    private String location;
    private List<Order> historyOrderList;

    public Customer(String username, String password, String phoneNumber, String location) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.historyOrderList = new ArrayList<>();
    }

    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public List<Order> getHistoryOrderList() { return historyOrderList; }
    public void addOrderToHistory(Order order) {
        System.out.println("Adding order to history for customer: " + username); // 调试信息
        if (historyOrderList == null) {
            System.out.println("historyOrderList is null!"); // 检查列表是否正确初始化
        } else {
            historyOrderList.add(order);
            System.out.println("Order added successfully."); // 确认订单已添加
        }
    }

}
