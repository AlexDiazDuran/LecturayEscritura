import java.io.File;
import java.io.IOException;

public class PR121Files {
    public static void main(String args[]) {

        // ---------------CREAR CARPETA---------------

        String basePath = System.getProperty("user.dir") + "/myFiles/";

        File dir = new File(basePath);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                System.out.println("Error en la creació de la carpeta 'myFiles'");
            }

        }

        // ---------------CREAR ARCHIVOS---------------

        File file1 = new File(basePath + "file1.txt");
        File file2 = new File(basePath + "file2.txt");

        try {
            if (file1.createNewFile()) {
                System.out.println("Archivo 'file1.txt' creado con éxito.");
            } else {
                System.out.println("El archivo 'file1.txt' ya existe.");
            }

            if (file2.createNewFile()) {
                System.out.println("Archivo 'file2.txt' creado con éxito.");
            } else {
                System.out.println("El archivo 'file2.txt' ya existe.");
            }
        } catch (IOException e) {
            System.err.println("Error al crear archivos: " + e.getMessage());
        }

        // ---------------RENOMBRAR ARCHIVO---------------

        if (file2.exists()) {

            File renamedFile = new File(basePath + "renamedFile.txt");

            // Renombrar el archivo "file2.txt" a "renamedFile.txt"
            if (file2.renameTo(renamedFile)) {
                System.out.println("El archivo 'file2.txt' ha sido renombrado a 'renamedFile.txt'");
            } else {
                System.out.println("No se pudo renombrar el archivo.");
            }
        } else {
            System.out.println("El archivo 'file2.txt' no existe.");
        }

        // ---------------MOSTRAR LISTA ARCHIVOS---------------

        File folder = new File("myFiles");
        if (folder.listFiles() != null) {
            System.out.println("Els arxius d'aquesta carpeta son:");

            for (File file : folder.listFiles()) {
                System.out.println(file.getName());
            }
        }

        // ---------------BORRAR ARCHIVO FILE1---------------

        try {
            if (file1.delete()) {
                System.out.println("Archivo 'file1.txt' borrado con éxito.");
            } else {
                System.out.println("El archivo 'file1.txt' no    existe.");
            }

        } catch (Exception e) {
            System.out.println("Error al eliminar el archivo");
        }
        
        
        System.out.println("Els arxius d'aquesta carpeta DESPRÈS DE ESBORRAR son:");

        for (File file : folder.listFiles()) {
                System.out.println(file.getName());
            }
        }
    }

