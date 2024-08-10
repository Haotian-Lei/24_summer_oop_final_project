import java.util.*;
import java.io.*;

public class WaitAcceptList implements Serializable{
	private static WaitAcceptList instance;
    private List<Order> orders;

    private WaitAcceptList() {
        orders = new ArrayList<Order>();
    }
    
    public static WaitAcceptList getInstance() {
        if (instance == null) {
            synchronized (WaitAcceptList.class) {
                if (instance == null) {
                    instance = new WaitAcceptList();
                }
            }
        }
        return instance;
    }
    
    public void addWatAcceptOrder(Order order) {
    	orders.add(order);
    }
    
    public void delete(Order order) {
    	orders.remove(order);
    }
    
    public List<Order> getList() {
    	return orders;
    }
}
