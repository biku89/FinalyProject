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



//        productManager.addProduct(laptop);
//        productManager.showInfoAboutAllProducts();


    }
}