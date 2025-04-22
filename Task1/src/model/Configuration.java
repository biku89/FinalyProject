package model;

import java.util.*;

/**
 * Reprezentuje konfigurację produktu. Specyfikacja jest przechowywana w mapie.
 */
public class Configuration {
    private Map<String, String> specification = new HashMap<>();

    /**
     * Dodaje nowy paramater kongiguracji.
     * @param key - nazwa parametru (np.Kolor)
     * @param value - wartość parametru(np.Czarny)
     */
    public void add(String key, String value){
        specification.put(key, value);
    }

    public String get(String key){
        return specification.getOrDefault(key, " Brak ");
    }

    public Map<String, String> getAll(){
        return specification;
    }

    /**
     * Wyświetla konfigyrację produktów
     */
    public void printConfiguration(){
        System.out.println("Konfiguracja produktów: ");
        specification.forEach((k, v) -> System.out.println("- " + k + ": " + v));
    }

    public Map<String, String> getSpecification() {
        return specification;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Configuration that = (Configuration) o;
        return Objects.equals(specification, that.specification);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(specification);
    }
}

