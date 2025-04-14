public class Main {
    public static void main(String[] args) {
        ProductManager productManager = new ProductManager();

        Product laptop = new Product(1, "Laptop Dell", 4000,4, ProductType.COMPUTER);
        Configuration config1 = new Configuration();
        config1.add("Procesor", "Intel i7");
        config1.add("Ram","16Gb" );

        Configuration config2 = new Configuration();
        config2.add("Procesor", "Intel i5");
        config2.add("Ram","32Gb" );

        laptop.addConfiguration(config1);
        laptop.addConfiguration(config2);

        laptop.printInfo();

        Product smartphone = new Product(2, "Xioami", 30, 100, ProductType.SMARTFON);
        Configuration configSmartphone = new Configuration();
        configSmartphone.add("Kolor", "Czarny");
        configSmartphone.add("Bateria", "3200");
        configSmartphone.add("Akcesoria", "Słuchawki");
        smartphone.addConfiguration(configSmartphone);

        smartphone.printInfo();

        Product tv = new Product(3, "Telewizor", 5000, 2, ProductType.ELECTRONICTS);
        tv.printInfo();

        System.out.println("Dodaj rzeczy do productmenager");

        Product laptop4 = new Product(4, "Macbook", 400, 3, ProductType.COMPUTER);

        productManager.addProduct(laptop4);
        productManager.showInfoAboutAllProducts();

        Cart cart = new Cart();
        System.out.println("Testuje koszyk");

        cart.addToCart(laptop, 1);
        cart.addToCart(tv, 2);

        cart.showClientCart();
        cart.sumOrder();



    }
}