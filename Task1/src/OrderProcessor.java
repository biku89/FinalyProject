import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderProcessor {
    private final List<Order> orders = new ArrayList<>();

    public Order createOrder(String clientName, Cart cart) {
        Order newOrder = new Order(clientName, cart.getClientCart());

        Discount discount = new Discount();
        BigDecimal discounted = discount.applyDiscountTenPercent(newOrder.getPriceSummary());
        newOrder.setPriceAfterDiscount(discounted);

        orders.add(newOrder);
        System.out.println("Zamówienie zostało złożone.");
        return newOrder;
    }

    public void processOrder(Order order){
        OrderThread orderThread = new OrderThread(order);
        Thread thread = new Thread(orderThread);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.err.println("Błąd przy przetwarzaniu zamówienia");
        }
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
