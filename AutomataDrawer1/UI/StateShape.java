package UI;

import javax.swing.*;
import java.awt.*;

public class StateShape implements Shape {

    private int x, y, radius;
    private boolean initialState, finalState;
    private String labelName;

    public StateShape(int x, int y, int radius, boolean initialState, boolean finalState, String labelName) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.initialState = initialState;
        this.finalState = finalState;
        this.labelName = labelName;
    }

    public void paint(Graphics g) {
        Graphics2D State = (Graphics2D) g;
        State.drawString(labelName, x, y);
        if ((initialState) && (finalState)) {
            State.drawOval(x, y, radius, radius);
            State.drawOval(x + (radius / 4), y + (radius / 4), radius - (radius / 2), radius - (radius / 2));
            State.drawString("i", x+4, y+radius);
        }
        else if (initialState) {
            State.drawOval(x, y, radius, radius);
            State.drawString("i", x+4, y+radius);


        }
        else if (finalState) {
            State.drawOval(x, y, radius, radius);
            State.drawOval(x + (radius / 4), y + (radius / 4), radius - (radius / 2), radius - (radius / 2));
        }

        else  if((!initialState) && (!finalState)) {
            State.drawOval(x, y, radius, radius);
        }

    }
}
