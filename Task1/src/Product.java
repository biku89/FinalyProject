public class Product {
    private int id;
    private String name;
    private double price;
    private int quantityAvaliable;

    public Product(int id, String name, double price, int quantityAvaliable) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantityAvaliable = quantityAvaliable;
    }

    public void printInfo(){
        System.out.println("Product: " + name);
        System.out.println("Id: " + id);
        System.out.println("Price: " + price);
        System.out.println("Quantity available: " + quantityAvaliable);
    }
}
