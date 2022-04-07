import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class XMLParser {

    /**
     * TODO: Parse the input XML file and return a dictionary as described in the assignment insturctions
     *
     * @param filename the input XML file
     * @return a dictionary as described in the assignment insturctions
     */
    public static Map<String, Malware> parse(String filename) {
        // TODO: YOUR CODE HERE
        //parse the xml file and return a map of malware objects
        //the map should be keyed by the malware hashes
        //the map should be in the format:
        //  <hash, Malware>
        //Malware should contain the following fields:
        //  title
        //  level
        //  hash

        //initialize the map
        Map<String, Malware> map = new HashMap<String, Malware>();

        try {
            //get the document builder factory
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            //parse the xml file
            Document doc = db.parse(new File(filename));
            //get the root element
            Element root = doc.getDocumentElement();
            //get the list of malware elements
            NodeList malwareList = root.getElementsByTagName("row");
            //loop through the list of malware elements
            for (int i = 0; i < malwareList.getLength(); i++) {
                //get the malware element
                Element malware = (Element) malwareList.item(i);
                //get the title element
                Element title = (Element) malware.getElementsByTagName("title").item(0);
                //get the level element
                Element level = (Element) malware.getElementsByTagName("level").item(0);
                //get the hash element
                Element hash = (Element) malware.getElementsByTagName("hash").item(0);
                //create a new Malware object
                Malware m = new Malware(title.getTextContent(), Integer.parseInt(level.getTextContent()), hash.getTextContent());
                //add the malware to the map
                map.put(m.getHash(), m);
            }
        } catch (Exception e) {
            System.out.println("Error parsing XML file: " + e.getMessage());
        }
        return map;
    }



}
