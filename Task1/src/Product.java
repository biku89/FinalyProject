import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Product {
    private int id;
    private String name;
    private BigDecimal price;
    private int quantityAvaliable;
    private ProductType type;
    private List<Configuration> configurations = new ArrayList<>();

    public Product(int id, String name, double price, int quantityAvaliable, ProductType type) {
        this.id = id;
        this.name = name;
        this.price = BigDecimal.valueOf(price);
        this.quantityAvaliable = quantityAvaliable;
        this.type = type;
    }

    public void addConfiguration(Configuration config) {
        configurations.add(config);
    }


    public void printInfo() {
        System.out.println("Produkt #" + id + ": " + name + ", cena: " + price + " zł, Ilość: " + quantityAvaliable + ", typ: " + type);
        if (!configurations.isEmpty()) {
            System.out.println("Dostępne konfiguracje:");
            for (int i = 0; i < configurations.size(); i++) {
                System.out.println("Konfiguracja #" + (i + 1));
                configurations.get(i).printConfiguration();
            }
        } else {
            System.out.println("Brak dodatkowych konfiguracji.");
        }
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantityAvaliable() {
        return quantityAvaliable;
    }

    public void setQuantityAvaliable(int quantityAvaliable) {
        this.quantityAvaliable = quantityAvaliable;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public List<Configuration> getConfigurations() {
        return configurations;
    }
}