import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PR123sobreescriu {
    public static void main(String[] args) {
        String[] frases = {
            "Yo sólo puedo mostrarte la puerta",
            "Tú eres quien la tiene que atravesar"
        };

        try (PrintWriter writer = new PrintWriter(new FileWriter("frasesMatrix.txt"))) {
            for (String frase : frases) {
                writer.println(frase);
            }
            System.out.println("Las frases se han sobrescrito en el archivo 'frasesMatrix.txt'.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
}
