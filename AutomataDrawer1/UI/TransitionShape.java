package UI;

import java.awt.*;
import java.awt.geom.QuadCurve2D;
class TransitionShape implements Shape {////// 2 func loop , normal
    private int startX, startY, endX, endY;
    private String label;

    public TransitionShape(int startX, int startY, int endX, int endY, String label) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.label = label;
    }


    public void paint(Graphics g) {
        Graphics2D transition = (Graphics2D) g;
        transition.setColor(Color.BLACK);
        QuadCurve2D q = new QuadCurve2D.Float();
        final int RADIUS = 10;
        if ((startX == endX) && (startY == endY)) {
            q.setCurve(startX, startY, (RADIUS / 2) + startX, 10, startX + RADIUS, endY);
            transition.draw(q);
            transition.drawString(label, (RADIUS / 2) + startX, startY / 2);
            transition.drawLine(endX, endY, endX - 5, endY - 10);
            transition.drawLine(endX, endY, endX + 5, endY - 10);
        } else if (startX < endX) {
            q.setCurve(startX, startY, ((endX - startX) / 2) + startX, 10, endX, endY);
            transition.draw(q);
            transition.drawLine(endX, endY, endX - 10, endY - 5);
            transition.drawLine(endX, endY, endX + 5, endY - 10);
            if (startY > endY) {
                transition.drawString(label, ((endX - startX) / 2) + startX, (startY - endY) / 2 + endY / 2);
            } else {
                transition.drawString(label, ((endX - startX) / 2) + startX, (endY - startY) / 2 + startY / 2);
            }

        } else if (startX > endX) {
            q.setCurve(startX, startY, ((endX - startX) / 2) + startX, startY + 50, endX, endY);
            transition.draw(q);
            transition.drawLine(endX, endY, endX + 10, endY + 10);
            transition.drawLine(endX, endY, endX + 10, endY - 10);
            if (startY > endY) {
                transition.drawString(label, ((endX - startX) / 2) + startX, (startY - endY) / 2 + startY / 2);
            } else {
                transition.drawString(label, ((endX - startX) / 2) + startX, (endY - startY) / 2 + endY / 2);
            }

        } else if (startX == endX) {
            if (startX == 0) {
                transition.drawLine(startX, startY, endX, endY);
                transition.drawLine(endX, endY, endX + 10, endY + 10);
                transition.drawLine(endX, endY, endX - 10, endY + 10);
                if (startY > endY) {
                    transition.drawString(label, 0, (startY - endY) / 2 + endY);
                } else {
                    transition.drawString(label, 0, (endY - startY) / 2 + startY);
                }

            } else if (startY > endY) {

                q.setCurve(startX, startY, startX / 2, ((startY - endY) / 2) + endY, endX, endY);
                transition.draw(q);
                transition.drawLine(endX, endY, endX - 10, endY + 20);
                transition.drawLine(endX, endY, endX - 30, endY + 10);
                transition.drawString(label, startX / 2, (startY - endY) / 2 + endY);
            } else if (startY < endY) {

                q.setCurve(startX, startY, startX * 2, ((endY - startY) / 2) + startY, endX, endY);
                transition.draw(q);
                transition.drawLine(endX, endY, endX + 10, endY + 5);
                transition.drawLine(endX, endY, endX + 10, endY - 10);
                transition.drawString(label, startX * 2, (startY - endY) / 2 + endY);
            }
        }
    }
}