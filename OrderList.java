import java.util.*;O
import java.io.*;

public class OrderList implements Serializable{
	private List<Order> orders = null;
	
	public OrderList() {
		orders = new ArrayList<Order>();
	}
	
	public void add(Order o) {
		orders.add(o);
	}
	
	public void remove(Order o) {
		orders.remove(o);
	}
	
	public List<Order> getOrders(){
		return orders;
	}
	public Order getSingleOrder(int index) {
		return orders.get(index);
	}

	//loop over to find the order that wating for acce[t from restuarant
	public List<Order> getWaitingAcceptList(){
		List<Order> waitAcceptOrders = new ArrayList<>();
		for (Order order:orders) {
			if (order.getStatus().equals("Paid")) {
				waitAcceptOrders.add(order);
			}
		}
		return waitAcceptOrders;
	}
	
}
