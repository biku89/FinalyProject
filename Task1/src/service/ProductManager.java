package service;

import model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Klasa zarządzająca listę produktów.
 * Zawiera dodawanie, usuwaniem, aktualizację oraz przeglądanie produktów
 */
public class ProductManager {
    private final List<Product> products = new ArrayList<>();

    /**
     * Dodaje nowy produkt do listy.
     *
     * @param product - dodawany produkt
     */
    public void addProduct(Product product) {
        products.add(product);
        System.out.println("Produkt dodany");

    }

    public Optional<Product> findById(int id) {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findFirst();
    }

    /**
     * Usuwa produkt po danym id
     *
     * @param id - id do usunięcia
     */
    public void removeProduct(int id) {
        Optional<Product> productToRemove = findById(id);

        if (productToRemove.isPresent()) {
            products.remove(productToRemove.get());
            System.out.println("Produkt usunięty");
        } else {
            System.out.println("Produktu nie udało się znaleźć");
        }
    }

    /**
     * Wyświetla inofrmację o wszystkich dostępnych produktach
     */
    public void showInfoAboutAllProducts() {
        System.out.println("model.Product list: ");
        products.stream()
                .forEach(Product::printInfo);
    }

    /**
     * Umożliwia aktualizację danego produktu.
     *
     * @param id                - id produktu
     * @param name              - nazwa
     * @param price             - cena
     * @param quantityAvaliable - ilość
     */
    public void productUpdate(int id, String name, BigDecimal price, int quantityAvaliable) {
        Optional<Product> productToUpdate = findById(id);

        if (productToUpdate.isPresent()) {
            Product product = productToUpdate.get();
            product.setName(name);
            product.setPrice(price);
            product.setQuantityAvaliable(quantityAvaliable);
            System.out.println("Udało się zaktualizować");
        } else {
            System.out.println("Nie znaleziono takiego id");
        }

    }
}

