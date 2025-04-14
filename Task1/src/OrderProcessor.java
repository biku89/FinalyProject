import java.util.ArrayList;
import java.util.List;

public class OrderProcessor {
    private final List<Order> orders = new ArrayList<>();

    public Order createOrder(String clientName, Cart cart) {
        Order newOrder = new Order(clientName, cart.getClientCart());
        orders.add(newOrder);
        System.out.println("Udało się przetworzyć zamówienie.");
        return newOrder;
    }

    public void generateInvoice(Order order){
        System.out.println("Faktura : ");
        order.printOrderSummary();
    }

    public void showAllOrders(){
        if (orders.isEmpty()){
            System.out.println("Brak złożonych zamówień");
        } else {
            System.out.println("Złożone zamówienia: ");
            orders.forEach(Order::printOrderSummary);
        }
    }

    public List<Order> getOrders() {
        return orders;
    }
}
