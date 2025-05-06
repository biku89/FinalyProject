package model;

import exception.ProductNotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Klasa reprezentujaca koszyk klienta w której jest przechowywana lista
public class Cart {
    private final List<CartItem> cartItems = new ArrayList<>();

    /**
     * Metoda odpowiedzialna za dodanie produktu z wybraną konfiguracją i okrśloną ilością.
     *
     * @param product       - produkt do dodania
     * @param configuration - konfiguracja produktu
     * @param quantity      - ilość danego produktu
     */
    public void addToCart(Product product, Configuration configuration, int quantity) {
        Optional<CartItem> items = findByProductId(product.getId(), configuration);

        if (items.isPresent()) {
            CartItem item = items.get();
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            cartItems.add(new CartItem(product, configuration, quantity));
        }
        product.reduceQuantity(quantity);
        System.out.println("Dodano do koszyka produkt " + product.getName() + " Ilość " + quantity);

    }

    public List<Configuration> getConfigurationsForProduct(int productId) {
        List<Configuration> configs = cartItems.stream()
                .filter(item -> item.getProduct().getId() == productId)
                .map(CartItem::getConfiguration)
                .toList();

        if (configs.isEmpty()) {
            throw new ProductNotFoundException("Nie znaleziono danego produktu o id: " + productId);
        }
        return configs;
    }

    private Optional<CartItem> findByProductId(int productid, Configuration configuration) {
        return cartItems.stream()
                .filter(i -> i.getProduct().getId() == productid && i.getConfiguration().equals(configuration))
                .findFirst();
    }

    /**
     * Usuwanie produktu z koszyka po id i konfiguracji
     *
     * @param productid     - id produktu
     * @param configuration - konfiguracja produktu
     */
    public void removeProductFromCart(int productid, Configuration configuration) {
        Optional<CartItem> itemToRemove = findByProductId(productid, configuration);

        if (itemToRemove.isPresent()) {
            cartItems.remove(itemToRemove.get());
            System.out.println("Usunięto produkt z koszyka");
        } else {
            System.out.println("Nie znaleziono produktu");
        }
    }

    /**
     * Wyświetla koszyk klienta
     */
    public void showClientCart() {
        if (cartItems.isEmpty()) {
            System.out.println("Koszyk jest pusty");
            return;
        }
        System.out.println("Zawartość koszyka: ");
        for (CartItem item : cartItems){
            System.out.println("Produkt: " + item.getProduct().getName());
            System.out.println("Wybrana konfiguracja: " );
            item.getConfiguration().printConfiguration();
            System.out.println("Ilość: " + item.getQuantity());
        }
        System.out.println("Podsumowanie: " + sumOrder());

    }

    public void makeOrder() {
        if (cartItems.isEmpty()) {
            System.out.println("Koszyk jest pusty");
        } else {
            System.out.println("Udało się złożyć zamówienie");
            cartItems.stream()
                    .forEach(CartItem::printItem);
        }

    }

    public BigDecimal sumOrder() {
        return cartItems.stream().map(CartItem::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<CartItem> getCartItems() {
        return new ArrayList<>(cartItems);
    }
}
