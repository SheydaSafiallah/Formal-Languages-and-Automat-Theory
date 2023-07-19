package SourceCodes;

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
    public static Automata parse(String fileName) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//      Defines a factory API that enables applications to obtain a parser that produces DOM object trees from XML documents.
        DocumentBuilder builder = factory.newDocumentBuilder();
//      Defines the API to obtain DOM Document instances from an XML document. Using this class, an application programmer can obtain a Document from XML.
        Document doc = builder.parse(fileName);
//      Conceptually, it is the root of the document tree, and provides the primary access to the document's data.

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
        NodeList stateList = doc.getElementsByTagName("state");
        for (int j = 0; j < stateList.getLength(); j++) {
            NamedNodeMap stateAttributes = stateList.item(j).getAttributes();
            String stateName = stateAttributes.getNamedItem("name").getNodeValue();
            int positionX = Integer.parseInt(stateAttributes.getNamedItem("positionX").getNodeValue());
            int positionY = Integer.parseInt(stateAttributes.getNamedItem("positionY").getNodeValue());
            State currentState = new State(stateName, positionX, positionY);
            states.add(currentState);
        }


        //Transitions
        ArrayList<Transition> transitions = new ArrayList<>();
        NodeList transitionList = doc.getElementsByTagName("transition");
        for (int i = 0; i < transitionList.getLength(); i++) {
            NamedNodeMap transitionAttributes = transitionList.item(i).getAttributes();
            String name = transitionAttributes.getNamedItem("name").getNodeValue();
            String source = transitionAttributes.getNamedItem("source").getNodeValue();
            String destination = transitionAttributes.getNamedItem("destination").getNodeValue();
            String label = transitionAttributes.getNamedItem("label").getNodeValue();
            Transition transition = new Transition(name, source, destination, label);
            transitions.add(transition);

        }

        Automata automata = new Automata(initial, finalStates, states, transitions,alphabets);
        return automata;
    }
}
