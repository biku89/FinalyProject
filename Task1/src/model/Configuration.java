package model;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Reprezentuje konfigurację produktu. Specyfikacja jest przechowywana w mapie.
 */
public class Configuration {
    private Map<ConfigurationType, String> specification = new HashMap<>();

    /**
     * Dodaje nowy paramater kongiguracji.
     *
     * @param type  - nazwa parametru (np.Kolor)
     * @param value - wartość parametru(np.Czarny)
     */
    public void add(ConfigurationType type, String value) {
        specification.put(type, value);
    }

    public String get(String key) {
        return specification.getOrDefault(key, " Brak ");
    }

    public Map<ConfigurationType, String> getAll() {
        return specification;
    }

    /**
     * Wyświetla konfigyrację produktów
     */
    public void printConfiguration() {
        if (specification.isEmpty()) {
            System.out.println("Brak dodatkowych konfiguracji");
        } else {
            specification.forEach((k, v) -> System.out.println("- " + k + ": " + v));
        }
    }

    public Map<ConfigurationType, String> getSpecification() {
        return specification;
    }

    @Override
    public String toString() {
        return specification.entrySet().stream()
                .map(entry -> entry.getKey() +": " + entry.getValue())
                .collect(Collectors.joining(", "));
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

