import java.util.*;
import java.io.*;

public class WaitAcceptList implements Serializable{
    private List<Order> orders;

    public WaitAcceptList() {
        orders = new ArrayList<Order>();
    }
    
    public void addWaitAcceptOrder(Order order) {
    	orders.add(order);
    }
    
    public void delete(Order order) {
    	orders.remove(order);
    }
    
    public List<Order> getList() {
    	return orders;
    }
}
