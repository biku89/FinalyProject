import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    }

