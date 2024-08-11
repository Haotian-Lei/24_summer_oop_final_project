import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RestaurantList implements Serializable{
	private static RestaurantList instance;
	private List<Restaurant> restList;
	
	public RestaurantList() {
		//List is interface, we user ArrayList to implement and initialize 
		this.restList = new ArrayList<>(); 
	}
	//set class to singleton
	public static RestaurantList getInstance( ) {
		if (instance == null) {
			instance = new RestaurantList();
		}
		return instance;
	} 
	public void add(Restaurant restaurant) {
		restList.add(restaurant);
	}
	public List<Restaurant> getRestlist(){
		return restList;
	}
	
	public Restaurant Verify(String username, String password) {
        for (Restaurant restaurant : restList) {
            if (restaurant.getUsername().equals(username) && restaurant.getPassword().equals(password)) {
                return restaurant;
            }
        }
        return null;
    }
	
	public Restaurant getSingleRest(int index) {
		return restList.get(index);
	}
	public void addOrderReceived(int index, Order order) {
		restList.get(index).addOrder(order);
	}
	
	//first create toString inside Restaurant class then append it in RestList
	public String toString() {
		StringBuilder sb = new StringBuilder();
        for (Restaurant restaurant : restList) {
            sb.append(restaurant.toString()).append("\n");
        }
        return sb.toString();
	}
	
	public String get_profile_info(int index) {
		return restList.get(index).get_profile_info();
	}
	public void edit(int index, String new_username, String new_password, String new_name, String new_phone, String new_location) {
		restList.get(index).edit(new_username, new_password, new_name, new_phone, new_location);
	}
}