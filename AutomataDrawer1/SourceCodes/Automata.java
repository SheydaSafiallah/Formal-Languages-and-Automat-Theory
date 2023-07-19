package SourceCodes;

import java.util.ArrayList;

public class Automata {
    private String initialState;
    private ArrayList<String> finalStates;
    private ArrayList<State> states;
    private ArrayList<Transition> transitions;
    private ArrayList<String> alphabets;

    public Automata(String initialState, ArrayList<String> finalStates, ArrayList<State> states, ArrayList<Transition> transitions, ArrayList<String> alphabets) {
        this.initialState = initialState;
        this.finalStates = finalStates;
        this.states = states;
        this.transitions = transitions;
        this.alphabets = alphabets;
    }

    public void addState(String name, int positionX, int positionY, boolean isFinal, boolean isInitial) {
        states.add(new State(name, positionX, positionY));
        if (isFinal){
            finalStates.add(name);
        }
        if (isInitial){
            initialState = name;
        }
    }

    public void addTransition(String name, String source, String destination, String label) {
        // make sure that the name exists in `alphabets`
        if (!alphabets.contains(label)) {
            alphabets.add(label);
        }
        transitions.add(new Transition(name, source, destination, label));
    }

    public String getInitialState() {
        return initialState;
    }

    public void setInitialState(String initialState) {
        this.initialState = initialState;
    }

    public ArrayList<String> getFinalStates() {
        return finalStates;
    }

    public void setFinalStates(ArrayList<String> finalStates) {
        this.finalStates = finalStates;
    }

    public ArrayList<State> getStates() {
        return states;
    }

    public void setStates(ArrayList<State> states) {
        this.states = states;
    }

    public ArrayList<Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(ArrayList<Transition> transitions) {
        this.transitions = transitions;
    }

    public ArrayList<String> getAlphabets() {
        return alphabets;
    }

    public void setAlphabets(ArrayList<String> alphabets) {
        this.alphabets = alphabets;
    }

    @Override
    public String toString() {
        return "Automata{" +
                "initialState='" + initialState + '\'' +
                ", finalStates=" + finalStates +
                ", states=" + states +
                ", transitions=" + transitions +
                ", alphabets=" + alphabets +
                '}';
    }
}
