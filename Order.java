import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    private Customer customer;
    private List<MenuItem> items;
    private String status;



    public Order(Customer customer, List<MenuItem> items) {
        this.customer = customer;
        this.items = items;
        this.status="Placed Order";
    }

    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer){
        this.customer=customer;
    }

    public List<MenuItem> getItems() {
        return items;
    }
    public void setItems(List<MenuItem> items) {
        this.items = items;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
