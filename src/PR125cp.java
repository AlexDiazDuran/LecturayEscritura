import java.io.*;
import java.util.Scanner;

public class PR125cp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Uso: java PR125cp <ruta_del_archivo_original> <ruta_de_destino>");
        System.out.print("Ruta del archivo original: ");
        String origen = scanner.nextLine();
        System.out.print("Ruta de destino: ");
        String destino = scanner.nextLine();

        try (FileInputStream fis = new FileInputStream(origen);
             FileOutputStream fos = new FileOutputStream(destino)) {
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }

            System.out.println("Archivo copiado con éxito.");
        } catch (IOException e) {
            System.err.println("Error al copiar el archivo: " + e.getMessage());
        }

        scanner.close();
    }
}
