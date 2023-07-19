package SourceCodes;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

public class DomExporter {
    static public void export(String fileName,Automata automata) throws ParserConfigurationException, TransformerException {

        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

        Document document = documentBuilder.newDocument();


        //root element -> Automata
        Element Automata = document.createElement("Automata");
        document.appendChild(Automata);
        // set an attribute to Automata element
        Attr automataAttr = document.createAttribute("type");
        automataAttr.setValue("FA");
        Automata.setAttributeNode(automataAttr);


        ///////////////////////////////////////Alphabets////////////////////////////////////////
        int numberOfAlphabets = automata.getAlphabets().size();
        ArrayList<String> alphabets = automata.getAlphabets();
        //Alphabets
        Element Alphabets = document.createElement("Alphabets");
        Automata.appendChild(Alphabets);
        // set an attribute to Alphabets element
        Attr alphabetsAttr = document.createAttribute("numberOfAlphabets");
        alphabetsAttr.setValue(String.valueOf(numberOfAlphabets));
        Alphabets.setAttributeNode(alphabetsAttr);
        for (int i = 0; i < numberOfAlphabets; i++) {
            Element alphabet = document.createElement("alphabet");
            Alphabets.appendChild(alphabet);
            Attr alphabetAtrr = document.createAttribute("letter");
            alphabetAtrr.setValue(alphabets.get(i));
            alphabet.setAttributeNode(alphabetAtrr);
        }

        /////->children of Alphabets Node


        ///////////////////////////////////////States////////////////////////////////////////
        int numberOfStates = automata.getStates().size();
        //States
        Element States = document.createElement("States");
        Automata.appendChild(States);
        // set an attribute to States element ->numberOfStates
        Attr statesAttr = document.createAttribute("numberOfStates");
        statesAttr.setValue(String.valueOf(numberOfStates));
        States.setAttributeNode(statesAttr);
        /////->children of States Node

        /////state
        ArrayList<State> statesList = automata.getStates();

        for (int i = 0; i < numberOfStates; i++) {
            Element stateElement = document.createElement("state");
            States.appendChild(stateElement);
            // set the attributes to state element ->name/positionX/positionY
            State stateNode = statesList.get(i);
            Attr stateName = document.createAttribute("name");
            Attr stateX = document.createAttribute("positionX");
            Attr stateY = document.createAttribute("positionY");
            stateName.setValue(stateNode.getName());
            stateX.setValue(String.valueOf(stateNode.getPositionX()));
            stateY.setValue(String.valueOf(stateNode.getPositionY()));
            stateElement.setAttributeNode(stateName);
            stateElement.setAttributeNode(stateX);
            stateElement.setAttributeNode(stateY);
        }

        /////initial state
        Element initialStateElement = document.createElement("initialState");
        States.appendChild(initialStateElement);
        Attr initialStateName = document.createAttribute("name");
        initialStateName.setValue(automata.getInitialState());
        initialStateElement.setAttributeNode(initialStateName);

        ////Final states
        Element finalStatesElement = document.createElement("FinalStates");
        States.appendChild(finalStatesElement);
        int numberOfFinalStates = automata.getFinalStates().size();
        Attr finalStatesAttr = document.createAttribute("numberOfFinalStates");
        finalStatesAttr.setValue(String.valueOf(numberOfFinalStates));
        finalStatesElement.setAttributeNode(finalStatesAttr);

        ArrayList<String> finalStatesList = automata.getFinalStates();
        for (int i = 0; i < numberOfFinalStates; i++) {
            Element finalStateElement = document.createElement("finalState");
            finalStatesElement.appendChild(finalStateElement);
            Attr finalStateAtrr = document.createAttribute("name");
            finalStateAtrr.setValue(finalStatesList.get(i));
            finalStateElement.setAttributeNode(finalStateAtrr);
        }


        ///////////////////////////////////////Transitions////////////////////////////////////////
        int numberOfTrans = automata.getTransitions().size();
        //first children3 elements -> Transitions
        Element transitions = document.createElement("Transitions");
        Automata.appendChild(transitions);
        // set an attribute to Transitions element
        Attr transitionsAttr = document.createAttribute("numberOfTrans");
        transitionsAttr.setValue(String.valueOf(numberOfTrans));
        transitions.setAttributeNode(transitionsAttr);

        /////->children of Transitions Node
        ArrayList<Transition> transitionList = automata.getTransitions();
        for (int i = 0; i < numberOfTrans; i++) {
            Element transitionElement = document.createElement("transition");
            transitions.appendChild(transitionElement);
            // set the attributes to transition element ->name/source/destination/label
            Transition transitionNode = transitionList.get(i);
            Attr transitionName = document.createAttribute("name");
            Attr transitionSource = document.createAttribute("source");
            Attr transitionDestination = document.createAttribute("destination");
            Attr transitionLable = document.createAttribute("label");
            transitionName.setValue(transitionNode.getName());
            transitionSource.setValue(String.valueOf(transitionNode.getSource()));
            transitionDestination.setValue(String.valueOf(transitionNode.getDestination()));
            transitionLable.setValue(String.valueOf(transitionNode.getLabel()));
            transitionElement.setAttributeNode(transitionName);
            transitionElement.setAttributeNode(transitionSource);
            transitionElement.setAttributeNode(transitionDestination);
            transitionElement.setAttributeNode(transitionLable);


        }


        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(fileName));
        transformer.transform(source, result);


    }

}
