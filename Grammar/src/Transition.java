public class Transition {
    private String name;
    private String source;
    private String destination;
    private String input;
    private String pop;
    private String push;
    public Transition(String name, String source, String destination, String input, String pop, String push){
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.input = input;
        this.pop = pop;
        this.push = push;
    }

    public String getName() {
        return name;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getInput() {
        return input;
    }

    public String getPop() {
        return pop;
    }

    public String getPush() {
        return push;
    }

    @Override
    public String toString() {
        return "Transition{" +
                "name='" + name + '\'' +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", input='" + input + '\'' +
                ", pop='" + pop + '\'' +
                ", push='" + push + '\'' +
                '}';
    }
}
