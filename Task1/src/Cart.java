import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cart {
    private final List<CartItem> clientCart = new ArrayList<>();

    public void addToCart(Product product, int quantity) {
        Optional<CartItem> items = findByProductId(product.getId());

        if (items.isPresent()){
            CartItem item = items.get();
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            clientCart.add(new CartItem(product, quantity));
        }
        System.out.println("Dodano do koszyka produkt " + product.getName() + " Ilość " + quantity);

    }

    private Optional<CartItem> findByProductId(int productid){
        return clientCart.stream()
                .filter(i -> i.getProduct().getId() == productid)
                .findFirst();
    }

    public void removeProductFromCart(int productid){
        Optional<CartItem> itemToRemove = findByProductId(productid);

        if (itemToRemove.isPresent()){
            clientCart.remove(itemToRemove.get());
            System.out.println("Usunięto produkt z koszyka");
        } else {
            System.out.println("Nie znaleziono produktu");
        }
    }



    public void showClientCart() {
        if (clientCart.isEmpty()) {
            System.out.println("Koszyk jest pusty");
            return;
        }
        System.out.println("Zawartość koszyka: ");
        clientCart.stream()
                .forEach(CartItem::printItem);

        System.out.println("Podsumowanie: " + sumOrder() );

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
