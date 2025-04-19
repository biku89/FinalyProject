import java.math.BigDecimal;

public class CartItem {
    private Product product;
    private Configuration configuration;
    private int quantity;

    public CartItem(Product product,Configuration configuration, int quantity) {
        this.product = product;
        this.configuration = configuration;
        this.quantity = quantity;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice(){
        return product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    public void printItem(){
        product.printInfo();
        if (configuration != null){
            System.out.println("Wybrana konfiguracja: ");
            configuration.printConfiguration();
        }
        System.out.println("Ilość: " + quantity);

    }
}
