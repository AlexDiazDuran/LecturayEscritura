import java.io.FileWriter;
import java.io.IOException;

public class PR124linies {
    public static void main(String[] args) {
        int numLineas = 10;

        try (FileWriter writer = new FileWriter("numeros.txt")) {
            for (int i = 0; i < numLineas; i++) {
                int numeroAleatorio = (int) (Math.random() * 100); // Genera un número aleatorio entre 0 y 99
                writer.write(numeroAleatorio + "\n");
            }

            System.out.println("Se han generado y escrito los números en 'numeros.txt'.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
}
