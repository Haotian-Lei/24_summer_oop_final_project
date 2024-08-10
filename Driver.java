import java.util.*;
import java.io.*;

public class Driver implements Serializable{
	private String userName;
	private String password;
	private Profile profile;
	private OrderList orders;
	
	public Driver(String username, String password, Profile profile) {
		this.userName = username;
		this.password = password;
		this.profile = profile;
		this.orders = new OrderList();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String username) {
		this.userName = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public OrderList getOrders() {
		return orders;
	}

	public void setOrders(OrderList orders) {
		this.orders = orders;
	}
	
	
}
