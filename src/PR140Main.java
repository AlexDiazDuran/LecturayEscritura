import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PR140Main {
    public static void main(String[] args) {
        String file = "xml/persones.xml";
        String name = "";
        String cognom = "";
        String edat = "";
        String ciutat = "";
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

            // Crea un constructor de documents
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            // Analitza el fitxer XML
            Document doc = dBuilder.parse(file);

            // Normalitza l'element arrel del document
            doc.getDocumentElement().normalize();

            // Obté una llista de tots els elements "student" del document
            NodeList listStudents = doc.getElementsByTagName("persona");

            for (int cnt = 0; cnt < listStudents.getLength(); cnt++) {
                // Obté l'estudiant actual
                Node nodeEstudiant = listStudents.item(cnt);
                // Comprova si l'estudiant actual és un element
                if (nodeEstudiant.getNodeType() == Node.ELEMENT_NODE) {
                    // Converteix l'estudiant actual a un element
                    Element elm = (Element) nodeEstudiant;
                    // **Obté el nom de l'estudiant**
                    NodeList nodeList = elm.getElementsByTagName("nom");
                    NodeList nodeList1 = elm.getElementsByTagName("cognom");
                    NodeList nodeList2 = elm.getElementsByTagName("edat");
                    NodeList nodeList3 = elm.getElementsByTagName("ciutat");
                    if (nodeList.getLength() > 0) {
                        name = nodeList.item(0).getTextContent();
                    }
                    if (nodeList1.getLength() > 0) {
                        cognom = nodeList1.item(0).getTextContent();
                    }
                    if (nodeList2.getLength() > 0) {
                        edat = nodeList2.item(0).getTextContent();
                    }
                    if (nodeList3.getLength() > 0) {
                        ciutat = nodeList3.item(0).getTextContent();
                    }

                    // Crea una cadena amb les dades de l'estudiant actual
                    System.out.printf("%-20s%-20s%-10s%-20s%n", name, cognom, edat, ciutat);
                    // Imprimeix les dades de l'estudiant actual
                }
            }

        } catch (Exception e) {
            // Imprimeix la pila d'errors en cas d'excepció
            e.printStackTrace();
        }
    }

}