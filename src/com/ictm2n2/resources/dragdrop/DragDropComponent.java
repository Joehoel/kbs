package com.ictm2n2.resources.dragdrop;

import com.ictm2n2.frames.TekenPanel;
import com.ictm2n2.resources.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DragDropComponent extends JLabel implements ActionListener {
    private Component component;
    private Point imageCorner = new Point(0, 0);
    private Dimension panelGrootte;
    private ImageIcon plaatje;
    private TekenPanel tekenPanel;

    private Point previousPoint;

    private JPopupMenu popUpMenu = new JPopupMenu();
    private JMenuItem verwijder = new JMenuItem("Verwijder");
    private JMenuItem verbinden = new JMenuItem("Verbinden");

    public DragDropComponent(Component component, ImageIcon plaatje, Dimension panelGrootte, TekenPanel tekenPanel) {
        this.component = component;
        this.plaatje = plaatje;
        this.panelGrootte = panelGrootte;
        this.tekenPanel = tekenPanel;

        setIcon(plaatje);
        setText(component.getNaam());

        setVerticalAlignment(SwingConstants.CENTER);
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalTextPosition(JLabel.BOTTOM);
        setHorizontalTextPosition(JLabel.CENTER);

        addMouseListener(new ClickListener());
        addMouseMotionListener(new DragListener());

        verwijder.setActionCommand("verwijder");
        verbinden.setActionCommand("verbinden");

        verwijder.addActionListener(this);
        verbinden.addActionListener(this);

        popUpMenu.add(verwijder);
        popUpMenu.add(verbinden);

        add(popUpMenu);

        setVisible(true);
    }

    public Point getImageCorner() {
        return this.imageCorner;
    }

    public Component getComponent() {
        return this.component;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("verwijder")) {
            tekenPanel.verwijderComponent(this);
        }

        if (e.getActionCommand().equals("verbinden")) {
            if (tekenPanel.getVanComponent() == null) {
                tekenPanel.setVanComponent(this);
            }
            else {
                tekenPanel.voegToeVerbinding(this);
            }
        }
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
                if (tekenPanel.getVanComponent() != null) {
                    tekenPanel.voegToeVerbinding(DragDropComponent.this);
                }
                else {
                    previousPoint = e.getPoint();
                }
            }
            else if (e.getButton() == MouseEvent.BUTTON3) {
                popUpMenu.show(DragDropComponent.this, e.getX(), e.getY());
            }
        }
    }
}
