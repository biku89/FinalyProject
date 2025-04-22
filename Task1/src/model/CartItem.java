package model;

import java.math.BigDecimal;

/**
 * Klasa reprezentuje pojdeynczy element w koszyku z jego konfiguracja oraz ilością.
 */
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

    /**
     * Obliczna łączną cene dla danego produktu i ilości.
     * @return łączna cena
     */
    public BigDecimal getTotalPrice(){
        return product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    /**
     * Wyświetla informację o produkcie, konfiguracji i jego ilości.
     */
    public void printItem(){
        product.printInfo();
        if (configuration != null){
            System.out.println("Wybrana konfiguracja: ");
            configuration.printConfiguration();
        }
        System.out.println("Ilość: " + quantity);

    }
}
