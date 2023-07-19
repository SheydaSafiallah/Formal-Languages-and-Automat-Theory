package SourceCodes;

public class Transition {
    private String name;
    private String source;
    private String destination;
    private String label;
    public Transition(String name, String source, String destination, String label){
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "Transition{" +
                "name='" + name + '\'' +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", label='" + label + '\'' +
                '}';
    }
}
