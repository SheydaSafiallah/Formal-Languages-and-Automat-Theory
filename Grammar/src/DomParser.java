import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class DomParser {

    public Producer parse(String fileName) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(fileName);

        //Alphabets
        ArrayList<String> alphabets = new ArrayList<>();
        NodeList alphabetsList = doc.getElementsByTagName("alphabet");
        for (int i = 0; i < alphabetsList.getLength(); i++) {
            alphabets.add(alphabetsList.item(i).getAttributes().getNamedItem("letter").getNodeValue());
        }
        //InitialState
        NodeList initialStateList = doc.getElementsByTagName("initialState");
        String initial = initialStateList.item(0).getAttributes().getNamedItem("name").getNodeValue();


        //FinalState
        ArrayList<String> finalStates = new ArrayList<>();
        NodeList finalStateList = doc.getElementsByTagName("finalState");
        for (int j = 0; j < finalStateList.getLength(); j++) {
            finalStates.add(finalStateList.item(j).getAttributes().getNamedItem("name").getNodeValue());
        }

        //States
        ArrayList<State> states = new ArrayList<>();
        ArrayList<String> stName = new ArrayList<>();
        NodeList stateList = doc.getElementsByTagName("state");
        for (int j = 0; j < stateList.getLength(); j++) {
            NamedNodeMap stateAttributes = stateList.item(j).getAttributes();
            String stateName = stateAttributes.getNamedItem("name").getNodeValue();
            State currentState = new State(stateName);
            states.add(currentState);
            stName.add(stateName);

        }

        //Transition
        ArrayList<Transition> transitions = new ArrayList<>();
        NodeList transitionList = doc.getElementsByTagName("transition");
        for (int i = 0; i < transitionList.getLength(); i++) {
            NamedNodeMap transitionAttributes = transitionList.item(i).getAttributes();
            String name = transitionAttributes.getNamedItem("name").getNodeValue();
            String source = transitionAttributes.getNamedItem("source").getNodeValue();
            String destination = transitionAttributes.getNamedItem("destination").getNodeValue();
            String input = transitionAttributes.getNamedItem("input").getNodeValue();
            String pop = transitionAttributes.getNamedItem("pop").getNodeValue();
            String push = transitionAttributes.getNamedItem("push").getNodeValue();

           Transition transition=new Transition(name,source,destination,input,pop,push);
            transitions.add(transition);
        }
        //return
//        for (int i = 0; i < transitionList.getLength(); i++) {
//            System.out.println(transitions.get(i).toString());
//        }
//        for (int i = 0; i < states.size(); i++) {
//            System.out.println(states.get(i).toString());
//        }
        Producer currentProducer = new Producer(transitions , transitionList.getLength(),stateList.getLength() , stName);
        Producer producer = new Producer(transitions , transitionList.getLength() , stateList.getLength() , stName);
        producer.producer();


        return currentProducer;
    }


}
