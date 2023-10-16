import java.io.Serializable;
import java.util.HashMap;

public class PR131hashmap implements Serializable {
    private HashMap<String, Integer> hashMap;

    public PR131hashmap() {
        hashMap = new HashMap<>();
    }

    public HashMap<String, Integer> getHashMap() {
        return hashMap;
    }
}
