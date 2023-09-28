import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PR122cat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduce la ruta del archivo: ");
        String filePath = scanner.nextLine();  // Lee la ruta del archivo desde el teclado

        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("El archivo no existe.");
            scanner.close();
            System.exit(1); // Termina la ejecución con código de error
        }

        if (file.isFile()) {
            try (Scanner fileScanner = new Scanner(file)) {
                while (fileScanner.hasNextLine()) {
                    System.out.println(fileScanner.nextLine());
                }
            } catch (FileNotFoundException e) {
                System.err.println("Error al leer el archivo: " + e.getMessage());
            }
        } else {
            System.out.println("El path no corresponde a un archivo, sino a una carpeta.");
        }

        scanner.close();
        System.exit(0); // Termina la ejecución con éxito
    }
}
