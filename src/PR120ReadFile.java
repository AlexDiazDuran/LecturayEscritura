import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PR120ReadFile {
    public static void main(String[] args) {
        try {
            // Abrir el archivo actual
            File archivo = new File("/home/super/Documents/M06/LecturayEscritura/src/PR120ReadFile.java");
            Scanner scanner = new Scanner(archivo);
            
            int numeroLinea = 1;
            
            // Leer y mostrar cada línea con su número de línea correspondiente
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                System.out.println(numeroLinea + ": " + linea);
                numeroLinea++;
            }
            
            // Cerrar el scanner
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado");
        }
    }
    //HOLAAA EL ARCHIVO SE ESTÁ LEYENDO CORRECTAMENTE
}
