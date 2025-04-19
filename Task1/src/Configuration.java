import java.util.*;

public class Configuration {
    private Map<String, String> specification = new HashMap<>();

    public void add(String key, String value){
        specification.put(key, value);
    }

    public String get(String key){
        return specification.getOrDefault(key, " Brak ");
    }

    public Map<String, String> getAll(){
        return specification;
    }

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

