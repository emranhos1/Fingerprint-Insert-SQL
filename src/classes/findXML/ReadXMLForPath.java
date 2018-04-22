/**
 *
 * @author Md. Emran Hossain
 * @email: emranhos1@gmail.com
 */
package classes.findXML;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadXMLForPath {

    private static DocumentBuilderFactory documentBuilderFactory;
    private static DocumentBuilder documentBuilder;
    private static Document document;
    private static String newFilePath;
    private static String newLogFilePath;
    private static String afterSQLPath;

    public static AllPath ReadXMLForPath() throws IOException {
        File xmlFile = new File("XML/ForPath.xml");

        documentBuilderFactory = DocumentBuilderFactory.newInstance();

        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(xmlFile);
        } catch (ParserConfigurationException | SAXException ex) {
            Logger.getLogger(ReadXMLForPath.class.getName()).log(Level.SEVERE, null, ex);
        } 

        NodeList nodeList = document.getElementsByTagName("path");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node theNode = nodeList.item(i);
            Element aelement = (Element) theNode;

            Node nFPath = aelement.getElementsByTagName("newFilePath").item(0);
            Node nLFPath = aelement.getElementsByTagName("newLogFilePath").item(0);
            Node aSQLP = aelement.getElementsByTagName("afterSQLPath").item(0);

            Element nFilePath = (Element) nFPath;
            Element nLFilePath = (Element) nLFPath;
            Element aSQLPath = (Element) aSQLP;

            newFilePath = nFilePath.getTextContent();
            newLogFilePath = nLFilePath.getTextContent();
            afterSQLPath = aSQLPath.getTextContent();
        }
        
        AllPath allPath = new AllPath(newFilePath, newLogFilePath, afterSQLPath);
        return allPath;
    }
}
