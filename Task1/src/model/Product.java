package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Klasa reprezentująca produkt dostępny w sklepie. Zawiera takie informację jak: cena,ilość, typ produktu i jego możliwa konfiguracja
 */
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

    /**
     * Dodaje nową konfigurację produktu
     *
     * @param config konfiguracja
     */
    public void addConfiguration(Configuration config) {
        configurations.add(config);
    }

    private Map<ConfigurationType, List<String>> availableOptions = new HashMap<>();

    public void addAvailableOption(ConfigurationType type, List<String> options) {
        availableOptions.put(type, options);
    }

    public List<String> getOptions(ConfigurationType type) {
        return availableOptions.getOrDefault(type, new ArrayList<>());
    }

    public Map<ConfigurationType, List<String>> getAllOptions() {
        return availableOptions;
    }

    /**
     * Wyświetla informację o danym produkcie.
     */
    public void printInfo() {
        System.out.println("Produkt #" + id + ": " + name + ", cena: " + price + " zł, Ilość: " + quantityAvaliable + ", typ: " + type);
        if (!availableOptions.isEmpty()) {
            System.out.println("Dostępne opcje konfiguracyjne:");
            for (Map.Entry<ConfigurationType, List<String>> entry : availableOptions.entrySet()) {
                ConfigurationType type = entry.getKey();
                List<String> options = entry.getValue();
                System.out.println("- " + type + ": " + String.join(", ", options));
            }
        } else {
            System.out.println("Brak dodatkowych konfiguracji.");
        }
    }

    public synchronized void reduceQuantity(int quantity) {
        if (quantity > quantityAvaliable) {
            throw new IllegalArgumentException("Za mało produktów w magazynie");
        }
        this.quantityAvaliable -= quantity;
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