public class Main {
    public static void main(String[] args) {
        ProductManager productManager = new ProductManager();

        Computer computer = new Computer(1, "Intel", 4000, 10);
        computer.hardwareConfiguration("i7 2000", 32);

        Smartfon smartfon = new Smartfon(2,"Xiaomi", 430, 4);
        smartfon.hardwareConfiguration("Czarny",3200);

        productManager.addProduct(computer);
        productManager.addProduct(smartfon);

        productManager.addProduct(new Product(3, "Steelseries", 3000, 1));
        productManager.showInfoAboutAllProducts();
        productManager.removeProduct(2);
        productManager.productUpdate(1, "Amd", 300, 3);
        System.out.println("Po aktuzalizacji: ");
        productManager.showInfoAboutAllProducts();

    }
}