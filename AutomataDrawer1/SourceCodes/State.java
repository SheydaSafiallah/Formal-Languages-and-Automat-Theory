package SourceCodes;

public class State {
    private String name;
    private int positionX;
    private int positionY;

    public State(String name, int positionx, int positiony) {
        this.name = name;
        positionX = positionx;
        positionY = positiony;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    @Override
    public String toString() {
        return "State{" +
                "name='" + name + '\'' +
                ", positionX=" + positionX +
                ", positionY=" + positionY +
                '}';
    }
}
