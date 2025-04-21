import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class StoreInterface {
    private final ProductManager productManager = new ProductManager();
    private final Cart cart = new Cart();
    private final OrderProcessor orderProcessor = new OrderProcessor();
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        productsInMagazine();

        boolean running = true;

        while (running) {
            showmenu();
            int option = scanner.nextInt();

            switch (option) {
                case 1 -> productManager.showInfoAboutAllProducts();
                case 2 -> addProductToCart();
                case 3 -> cart.showClientCart();
                case 4 -> removeProductFromCart();
                case 5 -> placeOrder();
                case 0 -> {
                    System.out.println("Zamykamy aplikację");
                    running = false;
                }

            }
        }
    }

    private void placeOrder() {
        try {
            if (cart.getClientCart().isEmpty()) {
                throw new OrderProcessException("Koszyk jest pusty.");
            }
            String clientName = ("Jan Kowalski");
            Order order = orderProcessor.createOrder(clientName, cart);
            orderProcessor.generateInvoice(order);
            orderProcessor.processOrder(order);

        } catch (OrderProcessException e) {
            System.err.println("Błąd " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Wystąpił błąd przy skłądaniu zamówienia " + e.getMessage());
        }
    }


private void removeProductFromCart() {
    try {
        if (cart.getClientCart().isEmpty()) {
            throw new EmptyCartException("Koszyk jest pusty");
        }
        cart.showClientCart();
        System.out.println("Podaj id produktu do usunięcia: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        List<CartItem> cartItems = cart.getClientCart();
        List<Configuration> clientConfigs = cartItems.stream()
                .filter(item -> item.getProduct().getId() == id)
                .map(CartItem::getConfiguration).toList();

        if (clientConfigs.isEmpty()) {
            throw new ProductNotFoundException("Nie znaleziono danego produktu o id: " + id);
        }


        System.out.println("Podaj numer kofniguracji do usunięcia");
        int numberCfg = scanner.nextInt();
        scanner.nextLine();

        if (numberCfg < 1 || numberCfg > clientConfigs.size()) {
            throw new InvalidConfigurationException("Błędny numer konfiguracji");
        }

        Configuration cfgToRemove = clientConfigs.get(numberCfg - 1);
        cart.removeProductFromCart(id, cfgToRemove);
    } catch (EmptyCartException | ProductNotFoundException | InvalidConfigurationException e) {
        System.err.println("Błąd; " + e.getMessage());
    } catch (Exception e) {
        System.err.println("Wystąpił błąd przy usuwaniu produktu " + e.getMessage());
    }

}

private void addProductToCart() {
    try {
        System.out.println("Podaj id produktu");
        int id = scanner.nextInt();
        scanner.nextLine();

        Optional<Product> OptionalProduct = productManager.findById(id);
        if (OptionalProduct.isEmpty()) {
            throw new ProductNotFoundException("Nie znaleziono produktu: " + id);
        }

        Product product = OptionalProduct.get();

        List<Configuration> configs = product.getConfigurations();
        Configuration selectConfig = null;

        if (!configs.isEmpty()) {
            System.out.println("Podaj numer konfiguracji ");
            int chooseCfgNumber = scanner.nextInt();
            scanner.nextLine();
            if (chooseCfgNumber < 1 || chooseCfgNumber > configs.size()) {
                throw new IllegalArgumentException("NIeprawidłowy numer konfiguracji ");
            }
            selectConfig = configs.get(chooseCfgNumber - 1);

            System.out.println("Podaj ilość: ");
            int quantity = scanner.nextInt();

            if (quantity > product.getQuantityAvaliable()) {
                throw new InsufficientStockMagazineException("Brak wystarczającej ilości sztuk na magazynie");
            }
            cart.addToCart(product, selectConfig, quantity);
            System.out.println("Produtk: " + product.getName() + " W ilości: " + quantity + " Został dodany do koszyka ");
        }
    } catch (ProductNotFoundException | InsufficientStockMagazineException | IllegalArgumentException e) {
        System.err.println("Błąd: " + e.getMessage());
    } catch (Exception e) {
        System.err.println("Wystąpił błąd: " + e.getMessage());
    }
}

private void showmenu() {
    System.out.println("\n=== MENU ===");
    System.out.println("1 - Dostępne produktu");
    System.out.println("2 - Dodaj produkt do koszyka");
    System.out.println("3 - Pokaż koszyk");
    System.out.println("4 - Usuń produkt z koszyka");
    System.out.println("5 - Złóż zamówienie");
    System.out.println("0 - Wyjście");
    System.out.println("Wybierz opcję");
}

private void productsInMagazine() {
    Product computer = new Product(1, "AMD", 3500, 8, ProductType.COMPUTER);

    Configuration computerConfig1 = new Configuration();
    computerConfig1.add("Procesor", "Ryzen 5700xd");
    computerConfig1.add("Ram", "32 GB");

    Configuration computerConfig2 = new Configuration();
    computerConfig2.add("Procesor", "Ryzen 9500");
    computerConfig2.add("Ram", "16 GB");

    computer.addConfiguration(computerConfig1);
    computer.addConfiguration(computerConfig2);

    Product smartphone = new Product(2, "Xioami", 500, 10, ProductType.SMARTFON);
    Configuration phoneConfig1 = new Configuration();
    phoneConfig1.add("Kolor", "Czarny");
    phoneConfig1.add("Bateria", "6500 mah");
    phoneConfig1.add("Akcesoria", "Słuchawki");
    smartphone.addConfiguration(phoneConfig1);

    Product tv = new Product(3, "Telewizor", 7000, 3, ProductType.ELECTRONICTS);
    tv.addConfiguration(new Configuration());

    productManager.addProduct(computer);
    productManager.addProduct(smartphone);
    productManager.addProduct(tv);


}
}
