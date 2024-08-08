import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Restaurant implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private List<MenuItem> menu;
    private List<Order> newOrderList;

    public Restaurant(String name) {
        this.name = name;
        this.menu = new ArrayList<>();
        this.newOrderList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<MenuItem> getMenu() {
        return menu;
    }

    public void addMenuItem(MenuItem item) {
        menu.add(item);
    }

    public List<Order> getNewOrderList() {
        return newOrderList;
    }

    public void addOrder(Order order) {
        newOrderList.add(order);
    }

    @Override
    public String toString() {
        return "Restaurant{name='" + name + "', menu=" + menu + ", newOrderList=" + newOrderList + '}';
    }
}
