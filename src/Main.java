import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Menú Principal");
            System.out.println("1. Mostrar contenido de archivo (PR120ReadFile)");
            System.out.println("2. Crear carpeta y archivos (PR121Files)");
            System.out.println("3. Menú cat");
            System.out.println("4. Agregar frases al archivo frasesMatrix.txt");
            System.out.println("5. Sobreescribir frases a 'frasesMatrix.txt' (PR123append)");
            System.out.println("6. Agregar 10 líneas de números random a 'numeros.txt'");
            System.out.println("7. Copiar archivo");
            System.out.println("8. Escribir y leer archivo PR130persones.dat");
            System.out.println("9. Escribir hashmap en PR131HashMapData.ser");
            System.out.println("10. Leer PR131HashMapData.ser");
            System.out.println("11. Guardar datos en PR132people.dat y leerlos");
            System.out.println("12. Modificar datos de PR133treballadors");
            System.out.println("13. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    PR120ReadFile.main(args);
                    break;
                case 2:
                    PR121Files.main(args);
                    break;
                case 3:
                    PR122cat.main(args);
                    break;
                case 4:
                    PR123append.main(args);
                    break;
                case 5:
                    PR123sobreescriu.main(args);
                    break;
                case 6:
                    PR124linies.main(args);
                    break;
                case 7:
                    PR125cp.main(args);
                    break;
                case 8:
                    PR130mainPersonesHashmap.main(args);
                    break;
                case 9:
                    PR131mainEscriu.main(args);
                    break;
                case 10:
                    PR131mainLlegeix.main(args);
                    break;
                case 11:
                    PR132main.main(args);
                    break;
                case 12:
                    PR133mainTreballadors.main(args);
                    break;
                case 13:
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    running = false;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }
        }

        scanner.close();
    }
}
