package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Klasa reprezentujaca koszyk klienta w której jest przechowywana lista
public class Cart {
    private final List<CartItem> clientCart = new ArrayList<>();

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
            clientCart.add(new CartItem(product, configuration, quantity));
        }
        product.reduceQuantity(quantity);
        System.out.println("Dodano do koszyka produkt " + product.getName() + " Ilość " + quantity);

    }

    private Optional<CartItem> findByProductId(int productid, Configuration configuration) {
        return clientCart.stream()
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
            clientCart.remove(itemToRemove.get());
            System.out.println("Usunięto produkt z koszyka");
        } else {
            System.out.println("Nie znaleziono produktu");
        }
    }

    /**
     * Wyświetla koszyk klienta
     */
    public void showClientCart() {
        if (clientCart.isEmpty()) {
            System.out.println("Koszyk jest pusty");
            return;
        }
        System.out.println("Zawartość koszyka: ");
        for (CartItem item : clientCart){
            System.out.println("Produkt: " + item.getProduct().getName());
            System.out.println("Wybrana konfiguracja: " );
            item.getConfiguration().printConfiguration();
            System.out.println("Ilość: " + item.getQuantity());
        }
        System.out.println("Podsumowanie: " + sumOrder());

    }

    public void makeOrder() {
        if (clientCart.isEmpty()) {
            System.out.println("Koszyk jest pusty");
        } else {
            System.out.println("Udało się złożyć zamówienie");
            clientCart.stream()
                    .forEach(CartItem::printItem);
        }

    }

    public BigDecimal sumOrder() {
        return clientCart.stream().map(CartItem::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<CartItem> getClientCart() {
        return new ArrayList<>(clientCart);
    }
}
