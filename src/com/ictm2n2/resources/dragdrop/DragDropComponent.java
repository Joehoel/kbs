package com.ictm2n2.resources.dragdrop;

import com.ictm2n2.frames.TekenPanel;
import com.ictm2n2.resources.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class DragDropComponent extends JLabel {
    private Component component;
    private ArrayList<VerbindingComponent> verbindingen = new ArrayList<VerbindingComponent>();
    private Point imageCorner = new Point(0, 0);
    private Dimension panelGrootte;
    private ImageIcon plaatje;

    private Point previousPoint;

    public DragDropComponent(Component component, ImageIcon plaatje, Dimension panelGrootte) {
        this.component = component;
        this.plaatje = plaatje;
        this.panelGrootte = panelGrootte;

        setIcon(plaatje);
        setText(component.getNaam());
        setVerticalTextPosition(JLabel.BOTTOM);
        setHorizontalTextPosition(JLabel.CENTER);
        addMouseListener(new ClickListener());
        addMouseMotionListener(new DragListener());
        setVisible(true);
    }

    public Point getImageCorner() {
        return this.imageCorner;
    }

    public void verwijderVerbindingComponent(int index) {
        verbindingen.remove(index);
    }

    public ArrayList<VerbindingComponent> getVerbindingen() {
        return this.verbindingen;
    }

    public int getVerbindingenLengte() {
        return this.verbindingen.size();
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
            if (e.getButton() == MouseEvent.BUTTON1) {
                previousPoint = e.getPoint();
            }
            else if (e.getButton() == MouseEvent.BUTTON3) {
                if (TekenPanel.vanComponent == null) {
                    TekenPanel.vanComponent = DragDropComponent.this;
                }
                else {
                    if (TekenPanel.vanComponent != DragDropComponent.this) {

                        verbindingen.add(new VerbindingComponent(TekenPanel.vanComponent, DragDropComponent.this));
                        repaint();
                    }
                    TekenPanel.vanComponent = null;
                }
            }
        }
    }
}
