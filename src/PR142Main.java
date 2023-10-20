import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.File;
import java.util.Scanner;

public class PR142Main {
    public static void main(String[] args) {
        try {
            File file = new File("xml/cursos.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            // Crear un objeto XPath
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xpath = xPathFactory.newXPath();

            // Crear un objeto Scanner para la entrada del usuario
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Elige una operación:");
                System.out.println("1. Listar IDs de cursos y tutores");
                System.out.println("2. Mostrar IDs y títulos de módulos a partir de un ID de curso");
                System.out.println("3. Listar alumnos de un curso");
                System.out.println("4. Agregar un alumno a un curso");
                System.out.println("5. Eliminar un alumno de un curso");
                System.out.println("6. Guardar cambios y salir");
                System.out.print("Opción: ");

                int opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el salto de línea

                switch (opcion) {
                    case 1:
                        listCourseIdsTutorsAndAlumn(doc, xpath);
                        break;
                    case 2:
                        System.out.print("Ingresa el ID del curso: ");
                        String courseId = scanner.nextLine();
                        showModuleIdsAndTitles(doc, xpath, courseId);
                        break;
                    case 3:
                        System.out.print("Ingresa el ID del curso: ");
                        String courseIdStudents = scanner.nextLine();
                        listStudentsInCourse(doc, xpath, courseIdStudents);
                        break;
                    case 4:
                        System.out.print("Ingresa el ID del curso: ");
                        String courseIdToAdd = scanner.nextLine();
                        System.out.print("Ingresa el nombre del alumno  agregar: ");
                        String studentNameToAdd = scanner.nextLine();
                        addStudentToCourse(doc, xpath, courseIdToAdd, studentNameToAdd);
                        break;
                    case 5:
                        System.out.print("Ingresa el ID del curso: ");
                        String courseIdToRemove = scanner.nextLine();
                        System.out.print("Ingresa el nombre del alumno a eliminar: ");
                        String studentNameToRemove = scanner.nextLine();
                        removeStudentFromCourse(doc, xpath, courseIdToRemove, studentNameToRemove);
                        break;
                    case 6:
                        saveDocumentToFile(doc, "xml/cursos.xml");
                        System.out.println("Cambios guardados. Saliendo.");
                        return;
                    default:
                        System.out.println("Opción no válida.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void listCourseIdsTutorsAndAlumn(Document doc, XPath xpath) throws XPathExpressionException {
        // Listar IDs de cursos
        XPathExpression idExpression = xpath.compile("/cursos/curs/@id");
        NodeList idNodes = (NodeList) idExpression.evaluate(doc, XPathConstants.NODESET);

        for (int i = 0; i < idNodes.getLength(); i++) {
            String courseId = idNodes.item(i).getNodeValue();
            System.out.println("ID del curso: " + courseId);

            // Listar tutores
            String tutorExpression = "/cursos/curs[@id='" + courseId + "']/tutor";
            String tutor = xpath.compile(tutorExpression).evaluate(doc);
            System.out.println("Tutor del curso: " + tutor);

            // Total Alumnos
            String studentsExpression = "/cursos/curs[@id='" + courseId + "']/alumnes/alumne";
        NodeList students = (NodeList) xpath.compile(studentsExpression).evaluate(doc, XPathConstants.NODESET);

            System.out.println("Total Alumnos " + students.getLength());
        }
            
        }

        private static void showModuleIdsAndTitles(Document doc, XPath xpath, String courseId) throws XPathExpressionException {
            // Mostrar IDs y títulos de módulos a partir de un ID de curso
            String moduleExpression = "/cursos/curs[@id='" + courseId + "']/moduls/modul";
            NodeList modules = (NodeList) xpath.compile(moduleExpression).evaluate(doc, XPathConstants.NODESET);
    
            for (int i = 0; i < modules.getLength(); i++) {
                Element module = (Element) modules.item(i);
                String moduleId = module.getAttribute("id");
                String moduleTitle = module.getElementsByTagName("titol").item(0).getTextContent();
                System.out.println("ID del módulo: " + moduleId + ", Título del módulo: " + moduleTitle);
            }
        }

        private static void listStudentsInCourse(Document doc, XPath xpath, String courseId) throws XPathExpressionException {
            String studentsExpression = "/cursos/curs[@id='" + courseId + "']/alumnes/alumne";
            NodeList students = (NodeList) xpath.compile(studentsExpression).evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < students.getLength(); i++){
                String studentName = students.item(i).getTextContent();
                System.out.println("Alumno del curso: " + studentName);
            }

        }

        private static void addStudentToCourse(Document doc, XPath xpath, String courseId, String studentName) throws XPathExpressionException {
            // Agregar un alumno a un curso
            String studentExpression = "/cursos/curs[@id='" + courseId + "']/alumnes";
            Element student = doc.createElement("alumne");
            student.appendChild(doc.createTextNode(studentName));
            Node courseNode = (Node) xpath.compile(studentExpression).evaluate(doc, XPathConstants.NODE);
            courseNode.appendChild(student);
        }

        private static void removeStudentFromCourse(Document doc, XPath xpath, String courseId, String studentName) throws XPathExpressionException {
            // Eliminar un alumno de un curso
            String studentExpression = "/cursos/curs[@id='" + courseId + "']/alumnes/alumne[text()='" + studentName + "']";
            Node studentNode = (Node) xpath.compile(studentExpression).evaluate(doc, XPathConstants.NODE);
            if (studentNode != null) {
                studentNode.getParentNode().removeChild(studentNode);
            }
        }

        private static void saveDocumentToFile(Document doc, String filePath) throws TransformerException {
            try {
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File(filePath)); // Utiliza la misma ruta del archivo original
                transformer.transform(source, result);
                System.out.println("Cambios guardados en el archivo " + filePath);
            } catch (TransformerException e) {
                e.printStackTrace();
            }
        }
        
    }


