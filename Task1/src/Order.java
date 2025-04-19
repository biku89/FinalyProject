import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private String clientName;
    private List<CartItem> orderItems;
    private BigDecimal priceSummary;
    private LocalDateTime orderDate;

    public Order(String clientName, List<CartItem> orderItems) {
        this.clientName = clientName;
        this.orderItems = orderItems;
        this.priceSummary = sumOrder();
        this.orderDate = LocalDateTime.now();
    }

    private BigDecimal sumOrder() {
        return orderItems.stream()
                .map(CartItem::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void printOrderSummary() {
        System.out.println("Zamówienie dla: " + clientName);
        System.out.println("Data: " + orderDate);
        System.out.println("Produkty: ");
        for (CartItem item : orderItems) {
            System.out.println(" - " + item.getProduct().getName()
                    +", Ilość : " + item.getQuantity()
                    + ", cena: " + item.getTotalPrice() + " zł ");
        }
        System.out.println("Suma zamówienia: " + priceSummary);
    }

    public BigDecimal getPriceSummary() {
        return priceSummary;
    }
}
