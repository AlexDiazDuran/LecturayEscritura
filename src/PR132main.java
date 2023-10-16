import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class PR132main {
    public static void main(String[] args) {
        HashMap<String, PR132persona> personas = new HashMap<>();

        personas.put("Persona1", new PR132persona("Anna", "Garcia", 25));
        personas.put("Persona2", new PR132persona("Carlos", "Pérez", 30));
        personas.put("Persona3", new PR132persona("Eva", "Martínez", 22));
        
        File file = new File("PR132people.dat");

        FileOutputStream fileOutputStream = null;
        DataOutputStream dataOutputStream = null;

        FileInputStream fileInputStream = null;
        DataInputStream dataInputStream = null;

        // Escribir en PR132people.dat
        try {

            fileOutputStream = new FileOutputStream(file);
            dataOutputStream = new DataOutputStream(fileOutputStream);
            dataOutputStream.writeInt(personas.size());

            for (Map.Entry<String, PR132persona> entry : personas.entrySet()) {
                String key = entry.getKey();
                PR132persona persona = entry.getValue();

                // Escribir datos
                dataOutputStream.writeUTF(key);
                dataOutputStream.writeUTF(persona.getNom());
                dataOutputStream.writeUTF(persona.getCognom());
                dataOutputStream.writeInt(persona.getEdat());
            }

            System.out.println("Objetos escritos en PR132people.dat");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Leer archivo y mostrarlo por pantalla
        try {

            fileInputStream = new FileInputStream(file);
            dataInputStream = new DataInputStream(fileInputStream); 
            int numPersonas = dataInputStream.readInt();

            System.out.println("Información leída del archivo:");
            for (int i = 0; i < numPersonas; i++) {
                String key = dataInputStream.readUTF();
                String nom = dataInputStream.readUTF();
                String cognom = dataInputStream.readUTF();
                int edat = dataInputStream.readInt();

                PR132persona persona = new PR132persona(nom, cognom, edat);
                personas.put(key, persona);

                System.out.println(persona);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
