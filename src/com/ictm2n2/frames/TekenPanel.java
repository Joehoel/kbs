package com.ictm2n2.frames;

import com.ictm2n2.resources.Component;
import com.ictm2n2.resources.dragdrop.DragDropComponent;
import com.ictm2n2.resources.dragdrop.VerbindingComponent;

import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class TekenPanel extends JPanel {
    private ArrayList<DragDropComponent> componenten = new ArrayList<DragDropComponent>();

    public static DragDropComponent vanComponent = null;
    private Point previousPoint = null;

    public TekenPanel() {
        setLayout(null);
    }

    public void voegToeComponent(String type, Component component) {
        ImageIcon plaatje = new ImageIcon("src/com/ictm2n2/assets/" + type + ".png");
        this.componenten.add(new DragDropComponent(component, plaatje, getSize()));
        
        setLayout(new BorderLayout());
        setVisible(true);
    }

    public void verwijderComponent(int index) {
        this.componenten.remove(index);
    }

    public ArrayList<DragDropComponent> getComponenten() {
        return this.componenten;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        removeAll();
        for(int i = 0; i < this.componenten.size(); i++) {
            DragDropComponent cc = this.componenten.get(i);
            cc.setBounds(
                    (int)cc.getImageCorner().getX(),
                    (int)cc.getImageCorner().getY(),
                    100, 100);
            add(cc, BorderLayout.NORTH);

            for(int it = 0; it < cc.getVerbindingenLengte(); it++) {
                VerbindingComponent vc = cc.getVerbindingen().get(it);

                g.drawLine(
                        (int)vc.getVanComponent().getImageCorner().getX() + 25,
                        (int)vc.getVanComponent().getImageCorner().getY() + 25,
                        (int)vc.getNaarComponent().getImageCorner().getX() + 25,
                        (int)vc.getNaarComponent().getImageCorner().getY() + 25);
            }
        }

        setBackground(Color.WHITE);
        setVisible(true);
        repaint();
    }
}