package com.ictm2n2.frames;

import com.ictm2n2.resources.Component;
import com.ictm2n2.resources.dragdrop.DragDropComponent;
import com.ictm2n2.resources.dragdrop.VerbindingComponent;

import java.awt.*;

import javax.swing.*;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.stream.Stream;

public class TekenPanel extends JPanel {
    private ArrayList<DragDropComponent> componenten = new ArrayList<DragDropComponent>();

    public static DragDropComponent vanComponent = null;

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

//            if (cc.getVerbindingenLengte() > 0) {
//                Graphics2D g2 = (Graphics2D) g;
//                g2.setColor(Color.BLACK);
//                for(int it = 0; it < cc.getVerbindingenLengte(); it++) {
//                    VerbindingComponent vc = cc.getVerbindingen().get(it);
//
//                    g2.draw(new Line2D.Double(
//                            (int)vc.getVanComponent().getImageCorner().getX(),
//                            (int)vc.getVanComponent().getImageCorner().getY(),
//                            (int)vc.getNaarComponent().getImageCorner().getX(),
//                            (int)vc.getNaarComponent().getImageCorner().getY()
//                    ));
//                }
//            }
        }
        Stream.of(VerbindingComponent)
        setBackground(Color.WHITE);
        setVisible(true);
    }
}