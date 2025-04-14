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


    public double sumOrder() {
        return clientCart.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }

    public List<CartItem> getClientCart() {
        return new ArrayList<>(clientCart);
    }
}
