import java.io.*;

import selfpart_final_project_oop.Menu;
public class Restaurant implements Serializable {
	private String username;
	private String password;
	private Profile profile;
	private Menu menu;
	private OrderList resOrderList;
	public Restaurant(String username, String password, Profile profile) {
		this.username = username;
		this.password = password;
		this.profile = profile;
		this.resOrderList = new OrderList();
		this.menu = new Menu();
	}
	public String getUsername() {
		return this.username;
	}
	public String getPassword() {
		return this.password;
	}
	public Profile getProfile() {
		return this.profile;
	}
	public Menu getMenu() {
		return this.menu;
	}
	public OrderList getOrderList() {
		return this.resOrderList;
	}
	public void addOrder(Order order) {
		resOrderList.add(order);
	}
	public void edit(String new_username, String new_password, String new_name, String new_phone, String new_location) {
		this.username = new_username;
		this.password = new_password;
		this.profile.setPhone(new_phone);
		this.profile.setName(new_name);
		this.profile.setLocation(new_location);
	}
	public String get_profile_info() {
		return this.profile.getName()+"\n"+this.profile.getPhone()+"\n"+this.profile.getLocation();
	}
	//for test only!!!!!
	public  String toString() {
		return "Restaurant: username-" + this.username+" name-"+profile.getName()+" location-"+profile.getLocation()+ "phone- "+profile.getPhone();
	}
}
