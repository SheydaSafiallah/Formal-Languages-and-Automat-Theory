import java.util.ArrayList;

public class Producer {
    private ArrayList<Transition> transitions;
    private int numberOfTransition;
    private int numberOfState;
    String [][] construction;
    ArrayList<String> stName;
    public Producer(ArrayList<Transition> transitions , int numberOfTransition , int numberOfState ,ArrayList<String> stName){
        this.transitions = transitions;
        this.numberOfTransition = numberOfTransition;
        this.numberOfState = numberOfState;
        this.stName = stName;

    }

    public void producer(){
        construction = new String[numberOfTransition][5];
        for (int i = 0; i < numberOfTransition; i++) {
                construction[i][0] = transitions.get(i).getSource();
                construction[i][1] = transitions.get(i).getInput();
                construction[i][2] = transitions.get(i).getPop();
                construction[i][3] = transitions.get(i).getDestination();
                construction[i][4] = transitions.get(i).getPush();

        }
        Grammar grammar = new Grammar(construction , numberOfTransition ,numberOfState ,stName);
        grammar.grammarChooser();
    }


}
