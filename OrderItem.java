import java.util.*;
import java.io.*;

public class OrderItem implements Serializable{
	private String name;
	private int quantity;
	
	public OrderItem(String item_name) {
		this.name = item_name;
		this.quantity = 1;
	}
	
	public void increment() {
		this.quantity++;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public boolean equals(Object o) {
		if (this == o) {
	        return true; 
	    }
	    if (o == null || getClass() != o.getClass()) {
	        return false;
	    }
	    OrderItem other = (OrderItem) o;
	    return this.name.equals(other.name);
	}
	
}
