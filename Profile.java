import java.io.*;
import java.util.*;

public class Profile implements Serializable{
	private String name;
	private String phone;
	private String location;
	private String vehicle;
	
	public Profile(String n, String p, String l, String v) {
		name = n;
		phone = p;
		location = l;
		vehicle = v;
	}


	public void edit(String n, String p, String l, String v) {
		name = n;
		phone = p;
		location = l;
		vehicle = v;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getVehicle() {
		return vehicle;
	}


	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}
	
	
	
	
}
