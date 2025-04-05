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
        System.out.println("Produkt " + name);
        System.out.println("Id: " + id);
        System.out.println("Cena " + price);
        System.out.println("Dostępna ilość " + quantityAvaliable);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantityAvaliable() {
        return quantityAvaliable;
    }

    public void setQuantityAvaliable(int quantityAvaliable) {
        this.quantityAvaliable = quantityAvaliable;
    }
}
