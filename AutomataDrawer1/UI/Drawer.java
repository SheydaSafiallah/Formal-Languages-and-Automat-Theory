package UI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Drawer extends JPanel {
    private ArrayList<Shape> shapes;

    public Drawer() {
        shapes = new ArrayList<>();
    }

    public void addShape(Shape shape) {
        this.shapes.add(shape);
        this.repaint();
    }

    public void removeAllShapes(){
        this.shapes.clear();
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        shapes.forEach(shape -> {
            shape.paint(g);
        });
    }
}
