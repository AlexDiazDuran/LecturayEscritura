import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PR131mainEscriu {
    public static void main(String[] args) {
        HashMap<String, Integer> myHashMap = new HashMap<>();
        myHashMap.put("Roberto", 15);
        myHashMap.put("Alex", 20);
        myHashMap.put("Paco", 67);
        myHashMap.put("Pablo", 32);
        myHashMap.put("Antonio", 99);

        File file = new File("myFiles/PR131HashMapData.ser");

        FileOutputStream fileOutputStream = null;
        DataOutputStream dataOutputStream = null;


        try {

            fileOutputStream = new FileOutputStream(file);
            dataOutputStream = new DataOutputStream(fileOutputStream);

            dataOutputStream.writeInt(myHashMap.size());

            for (Map.Entry<String, Integer> entry : myHashMap.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                dataOutputStream.writeUTF(key);
                dataOutputStream.writeInt(value);
            }

            System.out.println("HashMap escrito en PR131HashMapData.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
