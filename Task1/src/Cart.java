import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cart {
    private final List<Product> clientCart = new ArrayList<>();

    public void addToCart(Product product, int quantity) {

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
        if (clientCart.isEmpty()) {
            System.out.println("Koszyk jest pusty");
        } else {
            System.out.println("Udało się złożyć zamówienie");
            clientCart.stream().forEach(Product::printInfo);
        }

    }


    public double sumOrder() {
        return clientCart.stream().mapToDouble(Product::getPrice).sum();
    }

    public List<Product> getClientCart() {
        return clientCart;
    }
}
