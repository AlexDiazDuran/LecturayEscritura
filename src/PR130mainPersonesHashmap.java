import java.util.HashMap;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class PR130mainPersonesHashmap {
    public static void main(String[] args) {
        HashMap<String, Integer> Personas = new HashMap<String, Integer>();

        Personas.put("Roberto", 15);
        Personas.put("Alex", 20);
        Personas.put("Paco", 67);
        Personas.put("Pablo", 32);
        Personas.put("Antonio", 99);
        System.out.println(Personas);

        File file = new File("myFiles/PR130persones.dat");

        FileOutputStream fileOutputStream = null;
        DataOutputStream dataOutputStream = null;

        FileInputStream fileInputStream = null;
        DataInputStream dataInputStream = null;

        try {
            fileOutputStream = new FileOutputStream(file);
            dataOutputStream = new DataOutputStream(fileOutputStream);

            // Escribir datos en el archivo
            for (Map.Entry<String, Integer> entry : Personas.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                dataOutputStream.writeUTF(key);
                dataOutputStream.writeInt(value);
            }

            fileOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            fileInputStream = new FileInputStream(file);
            dataInputStream = new DataInputStream(fileInputStream);

            // Leer datos del archivo
            while (true) {
                try {
                    String key = dataInputStream.readUTF();
                    int value = dataInputStream.readInt();
                    System.out.println("Nombre: " + key + ", Edad: " + value);
                } catch (IOException e) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (dataInputStream != null) {
                    dataInputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
