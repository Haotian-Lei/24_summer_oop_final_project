import java.util.*;
import java.io.*;

public class DriverList implements Serializable {
    private ArrayList<Driver> drivers;

    public DriverList() {
        this.drivers = new ArrayList<>();
    }

    public void addDriver(Driver d) {
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
}



