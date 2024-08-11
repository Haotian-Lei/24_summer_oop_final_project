import java.util.*;
import java.io.*;

public class Order implements Serializable{
	private ArrayList<OrderItem> items;
	private String status = "N/A";
	private String origin = "N/A";
	private String destination = "N/A";
	public Order() {
		this.items = new ArrayList<OrderItem>();
	}
	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
	public ArrayList<OrderItem> getItems() {
		return items;
	}
	
	//??????????
	public void add(OrderItem new_oi) {
		for(OrderItem oi: items) {
			if(oi.equals(new_oi)) {
				oi.increment();
				return;
			}
		}
		items.add(new_oi);
		return;
	}
	//for Yukying's code 
	public void add1(OrderItem new_oi) {
		for(OrderItem oi: items) {
			if(oi.getName().equals(new_oi.getName())) {
				oi.increment();
				return;
			}
		}
		items.add(new_oi);
		return;
	}
	
	public void delete(OrderItem target) {
		for(OrderItem oi: items) {
			if(oi.equals(target)) {
				items.remove(oi);
				return;
			}
		}
	}

	public String getStatus() {
		return status;
	}

	public void setPaid() {
		this.status = "Paid";
	}
	
	public void setConfirmed() {
		this.status = "Confirmed";
	}
	
	public void setReadyPickUp() {
		this.status = "Ready for pick up";
	}
	
	public void setOnTheWay() {
		this.status = "On the way";
	}
	
	public void setDelivered() {
		this.status = "Delivered";
	}
	public void setDeclined() {
		this.status = "Declined";
	}
	public String getAllOrderitems_info(){
		String str = "";
		for (OrderItem oi:items) {
			str+= oi.getName()+" "+oi.getQuantity()+"|";
		}
		return str;
	}
	
	
}
