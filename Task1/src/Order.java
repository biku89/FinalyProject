import java.util.List;

public class Order {
    private String clientName;
    private List<Product> orderItems;
    private double priceSummary;

    public Order(String clientName, List<Product> orderItems) {
        this.clientName = clientName;
        this.orderItems = orderItems;
        this.priceSummary = sumOrder();
    }

    private double sumOrder() {
        return orderItems.stream()
                .mapToDouble(Product::getPrice).sum();
    }

    public void printOrderSummary() {
        System.out.println("Zamówienie dla " + clientName);
        System.out.println("Produkty");
        for (Product product : orderItems) {
            System.out.println(" - " + product.getName() + ", cena: " + product.getPrice());
        }
        System.out.println("Suma zamówienia " + priceSummary);
    }

    public double getPriceSummary() {
        return priceSummary;
    }
}
