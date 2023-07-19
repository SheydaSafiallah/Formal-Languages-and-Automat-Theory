import java.util.ArrayList;

public class Grammar {
    private String [][] construction;
    private int numberOfTransition;
    private int numberOfState;
    private ArrayList<String> stName;

    public Grammar(String[][] construction , int numberOfTransition , int numberOfState , ArrayList<String> stName){
        this.construction = construction;
        this.numberOfTransition = numberOfTransition;
        this.numberOfState = numberOfState;
        this.stName = stName;
    }


    public void grammarChooser(){

        for (int i = 0; i < numberOfTransition; i++) {
            if (construction[i][4].length() == 1){
                if (construction[i][4].contentEquals("0")){ //if push = landa
                    String [] information = construction[i];
                    lambda(information);
                }
                else { /// if push = one words
                    oneWord();
                }
            }
            else if (construction[i][4].length() == 2){
                String [] information = construction[i];
                twoWords(information);
            }
            else System.out.println("you can't push this:    "+construction[i][4]);

        }
    }


    public void lambda(String[] information){
        System.out.println("("+information[0]+information[2]+information[3]+") ---> " + information[1]);
    }


    public void oneWord(){

    }
    public void twoWords(String[] information){
        String [] words = information[4].split("");
        for (int i = 0; i < numberOfState; i++) {
            System.out.println("("+ information[0]+words[1]+stName.get(i)+")"+"--->");
            for (int j = 0; j < numberOfState; j++) {
                System.out.print("a("+information[0]+words[0]+stName.get(j)+")"+"("+stName.get(j)+words[1]+stName.get(i)+")"+"|");
            }
            System.out.println();
        }
    }

}
