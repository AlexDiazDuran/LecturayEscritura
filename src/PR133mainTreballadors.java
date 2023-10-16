import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PR133mainTreballadors {
    public static void main(String[] args) {
        String basePath = System.getProperty("user.dir");
        String fileName = "/myFiles/PR133treballadors.csv";
        String filePath = basePath + fileName;

        // Crear la carpeta 'data' si no existe
        File dir = new File(basePath);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                System.out.println("Error en la creación de la carpeta 'data'");
            }
        }

        List<String> csv = UtilsCSV.read(filePath);

        String[] columnas = UtilsCSV.getKeys(csv);
        System.out.println("Columnas disponibles: " + Arrays.toString(columnas));

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el identificador del trabajador: ");
        String identificador = scanner.nextLine();

        // Encuentra la posición de la columna de identificación
        int columnaId = UtilsCSV.csvGetColumnPosition(csv, "Id");

        // Busca el trabajador por identificador
        int filaTrabajador = -1;
        for (int i = 1; i < csv.size(); i++) {
            String[] fila = UtilsCSV.getLineArray(csv.get(i));
            if (fila[columnaId].equals(identificador)) {
                filaTrabajador = i;
                break;
            }
        }

        if (filaTrabajador != -1) {
            System.out.print("Ingrese el nombre de la columna a modificar: ");
            String columnaModificar = scanner.nextLine();

            int columnaModificarPos = UtilsCSV.csvGetColumnPosition(csv, columnaModificar);

            if (columnaModificarPos != -1) {
                System.out.print("Ingrese el nuevo valor: ");
                String nuevoValor = scanner.nextLine();

                // Actualiza el valor en la fila correspondiente
                String filaActual = csv.get(filaTrabajador);
                String[] partes = filaActual.split(",");
                partes[columnaModificarPos] = nuevoValor;
                String filaModificada = String.join(",", partes);
                csv.set(filaTrabajador, filaModificada);

                // Guarda los cambios en el archivo CSV
                UtilsCSV.write(filePath, csv);
                System.out.println("Los cambios se han guardado en el archivo.");
            } else {
                System.out.println("La columna especificada no existe.");
            }
        } else {
            System.out.println("No se encontró un trabajador con el identificador especificado.");
        }
        scanner.close();
    }
}
