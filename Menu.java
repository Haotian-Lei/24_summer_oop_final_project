
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class Menu implements Serializable {
	private final List<MenuItem> menuitems;
	public Menu() {
		this.menuitems = new ArrayList<>();
	}
	public void addMenuItem(MenuItem mi) {
		this.menuitems.add(mi);
	}
	public  void deleteMenuItem(int index) {
		this.menuitems.remove(index);
	}
	public List<MenuItem> getAllMenuItems(){
		return menuitems;
	}
	public String getNameMenuItem(int index) {
		return menuitems.get(index).getName();
	}
	public double getPriceMenuItem(int index) {
		return menuitems.get(index).getPrice();
	}
	public void editMenuItem(int index, String name, double price) {
		MenuItem mi = menuitems.get(index);
		mi.setName(name);
		mi.setPrice(price);
	}
	public String toString() {
		String str = "";
		for (MenuItem mi: menuitems) {
			str+= mi.getName()+ " "+mi.getPrice()+"\n";
		}
		return str;
	}
	
}

