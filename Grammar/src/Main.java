import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        final String FILENAME = "/home/sheyda/Desktop/input.xml";
        DomParser domParser = new DomParser();
        domParser.parse(FILENAME);


    }
}