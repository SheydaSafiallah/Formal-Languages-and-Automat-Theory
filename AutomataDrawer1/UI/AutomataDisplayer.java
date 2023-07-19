package UI;

import SourceCodes.*;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AutomataDisplayer {
    JFrame frame = new JFrame("Painter");
    private JComboBox comboBox = new JComboBox();
    private boolean initialState;
    private boolean finalState;
    private Drawer drawer = new Drawer();
    private Automata currentAutomata;
    private Button button = new Button("Save");

    public AutomataDisplayer() {
        createUI();
        frame.getContentPane().add(drawer, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setMaximumSize(frame.getSize());
        frame.setMinimumSize(frame.getSize());
        frame.setLocationRelativeTo(null);
    }

    private void createUI() {
        button.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showSaveDialog(frame);
            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    DomExporter.export(file.getAbsolutePath(), currentAutomata);
                } catch (ParserConfigurationException | TransformerException parserConfigurationException) {
                    parserConfigurationException.printStackTrace();
                }
            }
        });
        comboBox.addItem(null);
        comboBox.addItem("State");
        comboBox.addItem("Transition");
        comboBox.addActionListener(actionEvent -> {
            if (comboBox.getSelectedIndex() == 1) {
                takeState();
            }
            if (comboBox.getSelectedIndex() == 2) {
                takeTransition();
            }
        });
        drawer.add(comboBox);
        drawer.add(button);
    }

    public void display() {
        frame.setVisible(true);
    }

    public void show(Automata automata) {
        this.drawer.removeAllShapes();
        /////State handler
        ArrayList<State> states = automata.getStates();
        ArrayList<String> finalState = automata.getFinalStates();
        String initialState = automata.getInitialState();
        for (int i = 0; i < states.size(); i++) { //// itrate States
            State currentState = states.get(i);
            boolean isInitial = currentState.getName().equals(initialState);
            boolean isFinal = finalState.contains(currentState.getName());
            stateDrawer(currentState, isInitial, isFinal);
        }
        //////Transition handler
        ArrayList<Transition> transitions = automata.getTransitions();
        for (int i = 0; i < transitions.size(); i++) {
            Transition currentTransition = transitions.get(i);
            String name = currentTransition.getName();
            String label = currentTransition.getLabel();
            String source = currentTransition.getSource();
            String destination = currentTransition.getDestination();
            int startX = 0;
            int startY = 0;
            int endX = 0;
            int endY = 0;
            for (int j = 0; j < states.size(); j++) {
                State currentState = states.get(j);
                if (source.equals(currentState.getName())) {
                    startX = currentState.getPositionX();
                    startY = currentState.getPositionY();
                }
                if (destination.equals(currentState.getName())) {
                    endX = currentState.getPositionX();
                    endY = currentState.getPositionY();
                }
            }
            transitionDrawer(startX, startY, endX, endY, name, label);
        }
        this.currentAutomata = automata;
    }

    public void stateDrawer(State state, boolean isInitial, boolean isFinal) {
        int positionX = state.getPositionX();
        int positionY = state.getPositionY();
        String labelName = state.getName();
        drawer.addShape(new StateShape(positionX, positionY, 20, isInitial, isFinal, labelName));
    }

    public void transitionDrawer(int startX, int startY, int endX, int endY, String name, String label) {
        drawer.addShape(new TransitionShape(startX, startY, endX, endY, label));
    }

    public void takeState() {
        JFrame jFrame = new JFrame();
        jFrame.setSize(300, 300);
        jFrame.setMinimumSize(jFrame.getSize());
        jFrame.setMaximumSize(jFrame.getSize());
        jFrame.setVisible(true);
        ///
        JPanel jPanel = new JPanel();
        ///
        SpringLayout layoutState = new SpringLayout();
        //
        Label nameLabel = new Label("Name:");
        JTextField nameTextField = new JTextField("", 18);
        //
        Label positionXLabel = new Label("PositionX:");
        JTextField positionXField = new JTextField("", 16);
        //
        Label positionYLabel = new Label("PositionY:");
        JTextField positionYField = new JTextField("", 16);
        //
        Label initialStateLabel = new Label("initialState:");
        ButtonGroup init = new ButtonGroup();
        JRadioButton isInitial = new JRadioButton("YES");
        JRadioButton notInitial = new JRadioButton("NO");
        init.add(isInitial);
        init.add(notInitial);
        //
        Label FinalStateLabel = new Label("FinalState:");
        ButtonGroup finalS = new ButtonGroup();
        JRadioButton isFinal = new JRadioButton("YES");
        JRadioButton notFinal = new JRadioButton("NO");
        finalS.add(isFinal);
        finalS.add(notFinal);
        //
        JButton saveButton = new JButton("save");
        //
        jPanel.setLayout(layoutState);
        //
        jPanel.add(nameLabel);
        jPanel.add(nameTextField);
        jPanel.add(positionXLabel);
        jPanel.add(positionXField);
        jPanel.add(positionYLabel);
        jPanel.add(positionYField);
        jPanel.add(initialStateLabel);
        jPanel.add(isInitial);
        jPanel.add(notInitial);
        jPanel.add(FinalStateLabel);
        jPanel.add(isFinal);
        jPanel.add(notFinal);
        jPanel.add(saveButton);
        //
        layoutState.putConstraint(SpringLayout.WEST, nameLabel, 6, SpringLayout.WEST, jPanel);
        layoutState.putConstraint(SpringLayout.NORTH, nameLabel, 6, SpringLayout.NORTH, jPanel);
        layoutState.putConstraint(SpringLayout.WEST, nameTextField, 6, SpringLayout.EAST, nameLabel);
        layoutState.putConstraint(SpringLayout.NORTH, nameTextField, 6, SpringLayout.NORTH, jPanel);
        //
        layoutState.putConstraint(SpringLayout.NORTH, positionXLabel, 36, SpringLayout.NORTH, nameLabel);
        layoutState.putConstraint(SpringLayout.WEST, positionXLabel, 6, SpringLayout.WEST, jPanel);
        layoutState.putConstraint(SpringLayout.WEST, positionXField, 10, SpringLayout.EAST, positionXLabel);
        layoutState.putConstraint(SpringLayout.NORTH, positionXField, 36, SpringLayout.NORTH, nameTextField);
        //
        layoutState.putConstraint(SpringLayout.NORTH, positionYLabel, 36, SpringLayout.NORTH, positionXLabel);
        layoutState.putConstraint(SpringLayout.WEST, positionYLabel, 6, SpringLayout.WEST, jPanel);
        layoutState.putConstraint(SpringLayout.WEST, positionYField, 10, SpringLayout.EAST, positionYLabel);
        layoutState.putConstraint(SpringLayout.NORTH, positionYField, 36, SpringLayout.NORTH, positionXField);
        //
        layoutState.putConstraint(SpringLayout.NORTH, initialStateLabel, 36, SpringLayout.NORTH, positionYLabel);
        layoutState.putConstraint(SpringLayout.WEST, initialStateLabel, 6, SpringLayout.WEST, jPanel);
        layoutState.putConstraint(SpringLayout.WEST, isInitial, 10, SpringLayout.EAST, initialStateLabel);
        layoutState.putConstraint(SpringLayout.NORTH, isInitial, 36, SpringLayout.NORTH, positionYField);
        layoutState.putConstraint(SpringLayout.NORTH, notInitial, 36, SpringLayout.NORTH, positionYField);
        layoutState.putConstraint(SpringLayout.WEST, notInitial, 10, SpringLayout.EAST, isInitial);
        //
        layoutState.putConstraint(SpringLayout.NORTH, FinalStateLabel, 36, SpringLayout.NORTH, initialStateLabel);
        layoutState.putConstraint(SpringLayout.WEST, FinalStateLabel, 6, SpringLayout.WEST, jPanel);
        layoutState.putConstraint(SpringLayout.WEST, isFinal, 10, SpringLayout.EAST, FinalStateLabel);
        layoutState.putConstraint(SpringLayout.NORTH, isFinal, 36, SpringLayout.NORTH, isInitial);
        layoutState.putConstraint(SpringLayout.NORTH, notFinal, 36, SpringLayout.NORTH, isInitial);
        layoutState.putConstraint(SpringLayout.WEST, notFinal, 10, SpringLayout.EAST, isFinal);
        //
        layoutState.putConstraint(SpringLayout.NORTH, saveButton, 180, SpringLayout.NORTH, jPanel);
        layoutState.putConstraint(SpringLayout.WEST, saveButton, 130, SpringLayout.WEST, jPanel);
        //
        jFrame.add(jPanel);
        //
        saveButton.addActionListener(e -> {
            String stateName = nameTextField.getText();
            int positionX = Integer.parseInt(positionXField.getText());
            int positionY = Integer.parseInt(positionYField.getText());
            currentAutomata.addState(stateName, positionX, positionY, finalState, initialState);
            show(currentAutomata);
            jFrame.dispose();
        });
        isInitial.addActionListener(e -> initialState = true);
        notInitial.addActionListener(e -> initialState = false);
        isFinal.addActionListener(e -> finalState = true);
        notFinal.addActionListener(e -> finalState = false);

    }

    public void takeTransition() {
        JFrame frame = new JFrame();
        frame.setSize(300, 300);
        frame.setMaximumSize(frame.getSize());
        frame.setMinimumSize(frame.getSize());
        frame.setVisible(true);
        ///
        JPanel jPanel = new JPanel();
        JPanel panel = new JPanel();
        ///
        SpringLayout layoutTransition = new SpringLayout();
        Label nameLabel = new Label("Name: ");
        JTextField nameTextField = new JTextField("", 19);
        ///
        Label alphabetLabel = new Label("Alphabet: ");
        JTextField alphabetTextField = new JTextField("", 17);
        ///
        Label sourceLabel = new Label("Source: ");
        JTextField sourceTextField = new JTextField("", 18);
        ///
        Label destinationLabel = new Label("Destination: ");
        JTextField destinationTextField = new JTextField("", 16);
        ///
        JButton button = new JButton("save");
        ///
        jPanel.setLayout(layoutTransition);
        ///
        jPanel.add(nameLabel);
        jPanel.add(nameTextField);
        jPanel.add(alphabetLabel);
        jPanel.add(alphabetTextField);
        jPanel.add(sourceLabel);
        jPanel.add(sourceTextField);
        jPanel.add(destinationLabel);
        jPanel.add(destinationTextField);
        jPanel.add(button);
        ///
        layoutTransition.putConstraint(SpringLayout.WEST, nameLabel, 6, SpringLayout.WEST, jPanel);
        layoutTransition.putConstraint(SpringLayout.NORTH, nameLabel, 6, SpringLayout.NORTH, jPanel);
        layoutTransition.putConstraint(SpringLayout.WEST, nameTextField, 6, SpringLayout.EAST, nameLabel);
        layoutTransition.putConstraint(SpringLayout.NORTH, nameTextField, 6, SpringLayout.NORTH, jPanel);
        ///
        layoutTransition.putConstraint(SpringLayout.NORTH, alphabetLabel, 36, SpringLayout.NORTH, nameLabel);
        layoutTransition.putConstraint(SpringLayout.WEST, alphabetLabel, 6, SpringLayout.WEST, jPanel);
        layoutTransition.putConstraint(SpringLayout.WEST, alphabetTextField, 10, SpringLayout.EAST, alphabetLabel);
        layoutTransition.putConstraint(SpringLayout.NORTH, alphabetTextField, 36, SpringLayout.NORTH, nameTextField);
        ///
        layoutTransition.putConstraint(SpringLayout.NORTH, sourceLabel, 36, SpringLayout.NORTH, alphabetLabel);
        layoutTransition.putConstraint(SpringLayout.WEST, sourceLabel, 6, SpringLayout.WEST, jPanel);
        layoutTransition.putConstraint(SpringLayout.WEST, sourceTextField, 10, SpringLayout.EAST, sourceLabel);
        layoutTransition.putConstraint(SpringLayout.NORTH, sourceTextField, 36, SpringLayout.NORTH, alphabetTextField);
        ///
        layoutTransition.putConstraint(SpringLayout.NORTH, destinationLabel, 36, SpringLayout.NORTH, sourceLabel);
        layoutTransition.putConstraint(SpringLayout.WEST, destinationLabel, 6, SpringLayout.WEST, jPanel);
        layoutTransition.putConstraint(SpringLayout.WEST, destinationTextField, 10, SpringLayout.EAST, destinationLabel);
        layoutTransition.putConstraint(SpringLayout.NORTH, destinationTextField, 36, SpringLayout.NORTH, sourceTextField);
        ///
        layoutTransition.putConstraint(SpringLayout.NORTH, button, 150, SpringLayout.NORTH, jPanel);
        layoutTransition.putConstraint(SpringLayout.WEST, button, 130, SpringLayout.WEST, jPanel);
        ///
        frame.add(jPanel);
        button.addActionListener(e -> {
            String transitionName = nameTextField.getText();
            String alphabet = alphabetTextField.getText();

            String source = sourceTextField.getText();
            String destination = destinationTextField.getText();
            currentAutomata.addTransition(transitionName, source, destination, alphabet);
            show(currentAutomata);
            frame.dispose();
        });

    }
}
