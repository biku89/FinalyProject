import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductManager {
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
        System.out.println("Produkt dodany");

    }

    public void removeProduct(int id){
        Optional<Product> productToRemove = products.stream()
                .filter(p -> p.getId() == id)
                .findFirst();

        if (productToRemove.isPresent()){
            products.remove(productToRemove.get());
            System.out.println("Produkt usunięty");
        } else {
            System.out.println("Produktu nie udało się znaleźć");
        }
    }

    public void showInfoAboutAllProducts(){
        System.out.println("Product list: ");
        products.stream()
                .forEach(Product::printInfo);
    }

    public void productUpdate(int id, String name, double price, int quantityAvaliable){
        Optional<Product> productToUpdate = products
                .stream()
                .filter(p -> p.getId() == id).findFirst();

        if (productToUpdate.isPresent()){
            Product product = productToUpdate.get();
            product.setName(name);
            product.setPrice(price);
            product.setQuantityAvaliable(quantityAvaliable);
            System.out.println("Udało się zaktualizować");
        } else {
            System.out.println("Nie znaleziono takiego id");
        }

    }
}

