package com.ictm2n2.frames;

import com.ictm2n2.resources.Component;
import com.ictm2n2.resources.dragdrop.DragDropComponent;

import java.awt.*;

import javax.swing.*;

import java.util.ArrayList;

public class TekenPanel extends JPanel {
    private ArrayList<DragDropComponent> componenten = new ArrayList<DragDropComponent>();
    public static Component huidigComponent;

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

    public static void voegToeHuidigComponent(Component component) {
        huidigComponent = component;
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
        }
        setBackground(Color.WHITE);
        setVisible(true);
    }
}