import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.w3c.dom.Attr;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class PR141Main {
    public static void main(String[] args) throws TransformerException {
        try {
            //factory documentos
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            //constructor de documentos
            DocumentBuilder db = dbf.newDocumentBuilder();
            //nuevo documento XML
            Document doc = db.newDocument();
            //elemento raíz "biblioteca"
            Element biblioteca = doc.createElement("biblioteca");
            doc.appendChild(biblioteca);
            //elemento "llibre" con el atributo "id"
            Element llibre = doc.createElement("llibre");
            Attr attrId = doc.createAttribute("id");
            attrId.setValue("001");
            llibre.setAttributeNode(attrId);
            biblioteca.appendChild(llibre);
            

            agregarElemento(doc, llibre, "titol", "El viatge dels venturons");
            agregarElemento(doc, llibre, "autor", "Joan Pla");
            agregarElemento(doc, llibre, "anyPublicacio", "1998");
            agregarElemento(doc, llibre, "editorial", "Edicions Mar");
            agregarElemento(doc, llibre, "genere", "Aventura");
            agregarElemento(doc, llibre, "pagines", "320");
            agregarElemento(doc, llibre, "disponible", "true");

            //objeto Transformer para guardar el documento en un archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult("xml/biblioteca.xml");

            // Guarda en "biblioteca.xml"
            transformer.transform(source, result);

            System.out.println("Archivo 'biblioteca.xml' creado exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    private static void agregarElemento(Document document, Element elementoPadre, String nombre, String valor) {
        Element elemento = document.createElement(nombre);
                Text texto = document.createTextNode(valor);
                elemento.appendChild(texto);
                elementoPadre.appendChild(elemento);
    }
}
