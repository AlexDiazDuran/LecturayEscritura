import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class PR131mainLlegeix {
    public static void main(String[] args) {

        File file = new File("myFiles/PR131HashMapData.ser");

        FileInputStream fileInputStream = null;
        DataInputStream dataInputStream = null;

        try {

            fileInputStream = new FileInputStream(file);
            dataInputStream = new DataInputStream(fileInputStream);
            int numEntries = dataInputStream.readInt();
            HashMap<String, Integer> hashMap = new HashMap<>();

            for (int i = 0; i < numEntries; i++) {
                String key = dataInputStream.readUTF();
                int value = dataInputStream.readInt();
                hashMap.put(key, value);
            }

            System.out.println("Contenido del HashMap:");
            for (String key : hashMap.keySet()) {
                int value = hashMap.get(key);
                System.out.println("Nombre: " + key + ", Edad: " + value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
