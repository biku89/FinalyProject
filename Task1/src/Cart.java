import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cart {
    private final List<Product> clientCart = new ArrayList<>();

    public void addToCart(Product product) {
        clientCart.add(product);
        System.out.println("Produkt dodany do koszyka ");
    }

    public void showClientCart() {
        if (clientCart.isEmpty()) {
            System.out.println("Koszyk jest pusty");
            return;
        }
        System.out.println("Zawartość koszyka: ");
        clientCart.stream().forEach(Product::printInfo);

    }

    public void makeOrder() {
        if (clientCart != null) {
            System.out.println("Udało się złożyć zamówienie");
            clientCart.stream().forEach(Product::printInfo);
        }
    }

}
