import exception.*;
import model.*;
import service.DiscountService;
import service.OrderProcessor;
import service.ProductManager;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

/**
 * Klasa odpowiedzialna za interfejs użytkownika.
 * Pozwala na przeglądanie produktów, zarządzanie koszykiem oraz składanie zamówień.
 */
public class StoreInterface {
    private final ProductManager productManager = new ProductManager();
    private final Cart cart = new Cart();
    private final OrderProcessor orderProcessor = new OrderProcessor();
    private Scanner scanner;

    public void start() {
        initializeProducts();
        boolean running = true;

        try (Scanner scanner = new Scanner(System.in)){
            this.scanner = scanner;

            while (running) {
                showMenu();
                try {
                    int option = scanner.nextInt();
                    scanner.nextLine();

                    switch (option) {
                        case 1 -> productManager.showInfoAboutAllProducts();
                        case 2 -> addProductToCart();
                        case 3 -> cart.showClientCart();
                        case 4 -> removeProductFromCart();
                        case 5 -> placeOrder();
                        case 6 -> {
                            System.out.println("Twoja historia zamówień: ");
                            orderProcessor.printOrderHistory();
                        }
                        case 0 -> {
                            System.out.println("Zamykamy aplikację");
                            running = false;
                        }
                        default -> System.out.println("Nie prawidłowy wybór");
                    }
                } catch (Exception e) {
                    System.out.println("Podaj poprawną cyfrę");
                    scanner.nextLine();
                }

            }
        }
    }

    private void placeOrder() {
        try {
            if (cart.getCartItems().isEmpty()) {
                throw new OrderProcessException("Koszyk jest pusty.");
            }

            String promoCode = usePromoCode();

            String clientName = ("Jan Kowalski");
            Order order = orderProcessor.createOrder(clientName, cart, promoCode);
            CompletableFuture<String> proces1 = orderProcessor.generateInvoiceAsync(order);

            proces1.join();

        } catch (OrderProcessException e) {
            System.err.println("Błąd " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Wystąpił błąd przy składaniu zamówienia " + e.getMessage());
        }
    }

    private String usePromoCode() {
        while (true) {
            System.out.println("Czy posiadasz kod promocyjny ? (y/n)");
            String choice = scanner.nextLine();

            if (choice.equals("n")){
                return "";
            } else if (choice.equals("y")) {
                System.out.println("Podaj kod: ");
                String promoCode = scanner.nextLine();

                try {
                    isValidPromoCode(promoCode);
                    System.out.println("Użyto kodu: " + promoCode);
                    return promoCode;
                } catch (InvalidPromoCodeException e) {
                    System.err.println("Błąd: " + e.getMessage());
                    return "";
                }

            } else {
                System.out.println("Nie prawidłowa odpowiedź! wpisz 'y' lub 'n'");
            }
        }
    }

    private void isValidPromoCode(String promoCode) {
        if (!DiscountService.CODE_PROMO_20.equalsIgnoreCase(promoCode)){
            throw new InvalidPromoCodeException("Kod: " + promoCode + " Jest nieprawidłowy");
        }
    }


    private void removeProductFromCart() {
        try {
            cartIsNotEmpty();
            cart.showClientCart();

            int productId = getProductIdFromUser();
            List<Configuration> configurations = getConfigurationsForProductId(productId);

            int configNumber = getNumberConfigFromUser(configurations.size());
            Configuration configToRemove = configurations.get(configNumber);

            cart.removeProductFromCart(productId, configToRemove);
        } catch (EmptyCartException | ProductNotFoundException | InvalidConfigurationException e) {
            System.err.println("Błąd; " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Wystąpił błąd przy usuwaniu produktu " + e.getMessage());
        }

    }

    private int getNumberConfigFromUser(int size) {
        System.out.println("Podaj numer konfiguracji do usunięcia: ");
        int number = scanner.nextInt();
        scanner.nextLine();

        if (number < 1 || number > size) {
            throw new InvalidConfigurationException("Błędny numer konfiguracji");
        }

        return number - 1;
    }

    private List<Configuration> getConfigurationsForProductId(int productId) throws ProductNotFoundException {
       return cart.getConfigurationsForProduct(productId);
    }

    private int getProductIdFromUser() {
        System.out.println("Podaj id produktu do usunięcia: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }

    private void cartIsNotEmpty() {
        if (cart.getCartItems().isEmpty()) {
            throw new EmptyCartException("Koszyk jest pusty");
        }
    }

    private void addProductToCart() {
        try {
            Product product = chooseProduct();
            Configuration config = chooseConfiguration(product);
            int quantity = chooseQunatity(product);

            cart.addToCart(product, config, quantity);
            System.out.println("Produtk: " + product.getName() + " W ilości: " + quantity + " Został dodany do koszyka ");
        } catch (ProductNotFoundException | InsufficientStockMagazineException | IllegalArgumentException e) {
            System.err.println("Błąd: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Wystąpił nieznany błąd: " + e.getMessage());
        }
    }

    private int chooseQunatity(Product product) {
        System.out.println("Podaj ilość:");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        if (quantity > product.getQuantityAvaliable()) {
            throw new InsufficientStockMagazineException("Brak wystarczającej ilości sztuk na magazynie.");
        }
        return quantity;
    }

    private Configuration chooseConfiguration(Product product) {
        Configuration config = new Configuration();
        Map<ConfigurationType, List<String>> options = product.getAllOptions();

        if (options.isEmpty()) {
            System.out.println("Ten produkt nie posiada dodatkowych opcji konfiguracji.");
            return config;
        }

        System.out.println("Skonfiguruj produkt: " + product.getName());

        options.forEach((type, values) -> {
            printOptions(type, values);
            int userChoice = getUserChoice(type, values.size());
            config.add(type, values.get(userChoice - 1));
        });

        return config;
    }

    private void printOptions(ConfigurationType type, List<String> values) {
        System.out.println("Dostępne opcje dla " + type + ":");
        for (int i = 0; i < values.size(); i++) {
            System.out.println("  " + (i + 1) + " - " + values.get(i));
        }
    }

    private int getUserChoice(ConfigurationType type, int max) {
        int chooseNumber = -1;
        while (chooseNumber < 1 || chooseNumber > max) {
            System.out.print("Wybierz opcję (1-" + max + ") dla " + type + ": ");
            if (scanner.hasNextInt()) {
                chooseNumber = scanner.nextInt();
            } else {
                System.out.println("Spróbuj ponownie.");
            }
            scanner.nextLine();
        }
        return chooseNumber;
    }


    private Product chooseProduct() {
        System.out.println("Podaj id produktu");
        int id = scanner.nextInt();
        scanner.nextLine();

        Optional<Product> optionalProduct = productManager.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException("Nie znaleziono produktu: " + id);
        }
        return optionalProduct.get();
    }

    private void showMenu() {
        System.out.println("\n=== MENU ===");
        System.out.println("1 - Dostępne produkty");
        System.out.println("2 - Dodaj produkt do koszyka");
        System.out.println("3 - Pokaż koszyk");
        System.out.println("4 - Usuń produkt z koszyka");
        System.out.println("5 - Złóż zamówienie");
        System.out.println("6 - Pokaż historię zamówień");
        System.out.println("0 - Wyjście");
        System.out.println("Wybierz opcję");
    }

    private void initializeProducts() {
        Product computer = new Product(1, "AMD", 3500, 8, ProductType.COMPUTER);
        computer.addAvailableOption(ConfigurationType.PROCESOR, List.of("Ryzen 5700xd", "Ryzen 9500"));
        computer.addAvailableOption(ConfigurationType.RAM, List.of("16 GB", "32 GB"));

        Product smartphone = new Product(2, "Xioami", 500, 10, ProductType.SMARTFON);
        smartphone.addAvailableOption(ConfigurationType.COLOR, List.of("Czarny", "Biały", "Niebieski"));
        smartphone.addAvailableOption(ConfigurationType.BATTERY, List.of("4500 mah", "5000 mah", "6500 mah"));
        smartphone.addAvailableOption(ConfigurationType.ACCESSORIES, List.of("Etui", "Słuchawki", "Szkło Ochronne"));

        Product tv = new Product(3, "Telewizor", 7000, 3, ProductType.ELECTRONICTS);

        productManager.addProduct(computer);
        productManager.addProduct(smartphone);
        productManager.addProduct(tv);


    }
}
