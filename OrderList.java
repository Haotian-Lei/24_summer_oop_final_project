import java.util.*;
import java.io.*;

public class OrderList implements Serializable{
	private ArrayList<Order> orders = null;
	
	public OrderList() {
		orders = new ArrayList<Order>();
	}
	
	public void add(Order o) {
		orders.add(o);
	}
	
	public void remove(Order o) {
		orders.remove(o);
	}
	
	public ArrayList<Order> getOrders(){
		return orders;
	}
}
