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
                case 1 -> {
                    productManager.showInfoAboutAllProducts();
                }
                case 2 -> {
                    System.out.println("Podaj id produktu");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    Optional<Product> OptionalProduct = productManager.findById(id);
                    if (OptionalProduct.isEmpty()) {
                        System.out.println("Nie znaleziono produktu");
                        return;
                    }
                    Product product = OptionalProduct.get();

                    System.out.println("Podaj ilość: ");
                    int quantity = scanner.nextInt();

                    if (quantity > product.getQuantityAvaliable()) {
                        System.out.println("Brak takiej ilości sztuk na magazynie");
                    } else {
                        cart.addToCart(product, quantity);
                        System.out.println("Produtk: " + product.getName() + " W ilości: " + quantity + " Został dodany do koszyka ");
                    }
                }
                case 3 -> {
                    cart.showClientCart();
                }
                case 4 -> {
                    if (cart.getClientCart().isEmpty()) {
                        System.out.println("Koszyk jest pusty");
                        return;
                    }
                    cart.showClientCart();
                    System.out.println("Podaj id produktu do usunięcia: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    cart.removeProductFromCart(id);

                }
                case 5 -> {
                    if (cart.getClientCart().isEmpty()) {
                        System.out.println("Koszyk jest pusty");
                        return;
                    }
                    String clientName = ("Jan Kowalski");
                    Order order = orderProcessor.createOrder(clientName, cart);
                    orderProcessor.generateInvoice(order);
                }

            }
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
