package com.ictm2n2.resources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class DragDropComponent extends JLabel {
    private Component component;
    private Point imageCorner = new Point(0, 0);
    private Dimension panelSize;
    private ImageIcon image;

    private Point previousPoint;

    public DragDropComponent(Component component, ImageIcon image, Dimension panelSize) {
        this.component = component;
        this.image = image;
        this.panelSize = panelSize;

        setIcon(image);
        setText(component.getNaam());
        setVerticalTextPosition(JLabel.BOTTOM);
        setHorizontalTextPosition(JLabel.CENTER);
        setBounds((int)imageCorner.getX(), (int)imageCorner.getY(), 100, 100);
        addMouseListener(new ClickListener());
        addMouseMotionListener(new DragListener());
    }

    public Point getImageCorner() {
        return this.imageCorner;
    }

    private class DragListener extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent e) {
            Point currentPoint = e.getPoint();

            imageCorner.translate(
                    (int)(currentPoint.getX() - previousPoint.getX()),
                    (int)(currentPoint.getY() - previousPoint.getY()));

            repaint();
        }
    }

    private class ClickListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            previousPoint = e.getPoint();
        }
    }
}
