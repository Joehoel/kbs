package com.ictm2n2.resources.dragdrop;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;

import com.ictm2n2.frames.TekenPanel;
import com.ictm2n2.resources.Component;

public class DragDropComponent extends JLabel implements ActionListener {
    private Component component;
    private Point imageCorner;
    private Dimension panelGrootte;
    private ImageIcon plaatje;
    private TekenPanel tekenPanel;

    private Point previousPoint;

    private JPopupMenu popUpMenu = new JPopupMenu();
    private JMenuItem verwijder = new JMenuItem("Verwijder");
    private JMenuItem verbinden = new JMenuItem("Verbinden");
    private JMenuItem configureer = new JMenuItem("Configureer");
    private DragDropDialoog dialoog;

    public DragDropComponent(Component component, Point imageCorner, ImageIcon plaatje, Dimension panelGrootte,
            TekenPanel tekenPanel) {
        this(component, imageCorner, plaatje, panelGrootte, tekenPanel, "", "", "");
    }

    public DragDropComponent(Component component, Point imageCorner, ImageIcon plaatje, Dimension panelGrootte,
            TekenPanel tekenPanel, String ipv4Adres, String ipv4Subnet, String ipv4Gateway) {
        this.component = component;
        this.imageCorner = imageCorner;
        this.plaatje = plaatje;
        this.panelGrootte = panelGrootte;
        this.tekenPanel = tekenPanel;

        this.dialoog = new DragDropDialoog(tekenPanel, this);
        this.dialoog.setIPv4Adres(ipv4Adres);
        this.dialoog.setIPv4Subnet(ipv4Subnet);
        this.dialoog.setIPv4Gateway(ipv4Gateway);

        setIcon(plaatje);
        setText("<html>" + component.getNaam() + "<br>" + dialoog.getIPv4Adres() + "</html>");

        setVerticalAlignment(SwingConstants.CENTER);
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalTextPosition(JLabel.BOTTOM);
        setHorizontalTextPosition(JLabel.CENTER);

        addMouseListener(new ClickListener());
        addMouseMotionListener(new DragListener());

        verwijder.setActionCommand("verwijder");
        verbinden.setActionCommand("verbinden");
        configureer.setActionCommand("configureer");

        verwijder.addActionListener(this);
        verbinden.addActionListener(this);
        configureer.addActionListener(this);

        popUpMenu.add(verwijder);
        popUpMenu.add(verbinden);
        popUpMenu.add(configureer);

        add(popUpMenu);

        setVisible(true);
    }

    public Point getImageCorner() {
        return this.imageCorner;
    }

    public Component getComponent() {
        return this.component;
    }

    public void setLabel() {
        this.setText("<html>" + component.getNaam() + "<br>" + dialoog.getIPv4Adres() + "</html>");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("verwijder")) {
            tekenPanel.verwijderComponent(this);
        }

        if (e.getActionCommand().equals("verbinden")) {
            if (tekenPanel.getVanComponent() == null) {
                tekenPanel.setVanComponent(this);
            } else {
                tekenPanel.voegToeVerbinding(this);
            }
        }
        if (e.getActionCommand().equals("configureer")) {
            dialoog.setVisible(true);
        }
    }

    private class DragListener extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent e) {
            Point currentPoint = e.getPoint();

            imageCorner.translate((int) (currentPoint.getX() - previousPoint.getX()),
                    (int) (currentPoint.getY() - previousPoint.getY()));

            repaint();
        }
    }

    private class ClickListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                if (tekenPanel.getVanComponent() != null) {
                    tekenPanel.voegToeVerbinding(DragDropComponent.this);
                } else {
                    previousPoint = e.getPoint();
                }
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                popUpMenu.show(DragDropComponent.this, e.getX(), e.getY());
            }
        }
    }
}
