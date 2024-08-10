import java.util.*;
import java.io.*;

public class DriverList implements Serializable {
    private ArrayList<Driver> drivers;
    private static DriverList instance;

    private DriverList() {
        this.drivers = new ArrayList<>();
    }

    public static DriverList getInstance() {
        if (instance == null) {
            instance = new DriverList();
        }
        return instance;
    }

    public void add(Driver d) {
        this.drivers.add(d);
    }

    public Driver verify(String account, String password) {
        for (Driver d : drivers) {
            if (account.equals(d.getUserName()) && password.equals(d.getPassword())) {
                return d;
            }
        }
        return null;
    }
    
    public String[] get_profile_info(Driver driver) {
    	String[] result = {driver.getUserName(),driver.getPassword(),driver.getProfile().getName(),driver.getProfile().getPhone(),driver.getProfile().getVehicle()};
    	return result;
    }
    
    public void edit(Driver driver,String username,String password, 
    		String name,String phone,String vehicle) {
    	driver.setUserName(username);
		driver.setPassword(password);
		driver.getProfile().edit(name, phone, "NA", vehicle);
    }
}

