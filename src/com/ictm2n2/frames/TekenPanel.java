package com.ictm2n2.frames;

import com.ictm2n2.resources.Component;
import com.ictm2n2.resources.Configuratie;
import com.ictm2n2.resources.dragdrop.DragDropComponent;
import com.ictm2n2.resources.dragdrop.VerbindingComponent;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class TekenPanel extends JPanel implements MouseMotionListener, MouseInputListener {
    private ArrayList<DragDropComponent> componenten = new ArrayList<DragDropComponent>();
    private ArrayList<VerbindingComponent> verbindingen = new ArrayList<VerbindingComponent>();

    private DragDropComponent vanComponent = null;
    private JFrame frame;
    private ConfigureerPanel configureerPanel;
    private Configuratie configuratie;

    private Point muisPositie = null;
    private Point2D beginPositie = null;
    private Point2D eindPositie = null;

    public TekenPanel(JFrame frame, ConfigureerPanel configureerPanel, Configuratie configuratie) {
        this.frame = frame;
        this.configureerPanel = configureerPanel;
        this.configuratie = configuratie;

        addMouseMotionListener(this);
        addMouseListener(this);
        setLayout(null);
    }

    public void voegToeComponent(Point imageCorner, String type, Component component) {
        ImageIcon plaatje = new ImageIcon("src/com/ictm2n2/assets/" + type + ".png");
        this.componenten.add(new DragDropComponent(component, imageCorner, plaatje, getSize(), this));

        setLayout(new BorderLayout());
        setVisible(true);
    }

    public void verwijderComponent(DragDropComponent component) {
        componenten.remove(component);
        configuratie.verwijderComponent(component.getComponent());

        this.vanComponent = null;

        try {
            for (int i = (verbindingen.size() - 1); i >= 0; i--) {
                VerbindingComponent verbinding = verbindingen.get(i);

                if (component == verbinding.getVanComponent()) {

                    this.verbindingen.remove(i);
                }

                if (component == verbinding.getNaarComponent()) {
                    this.verbindingen.remove(i);
                }
            }
        } catch (ArrayIndexOutOfBoundsException error) {
            JOptionPane.showMessageDialog(this, "Kan component niet verwijderen", "Error", JOptionPane.ERROR_MESSAGE);
            error.printStackTrace();
        }

        configureerPanel.bewerkGegevens();
    }

    public void voegToeVerbinding(DragDropComponent naarComponent) {
        if (vanComponent != naarComponent) {
            verbindingen.add(new VerbindingComponent(this.vanComponent, naarComponent));
            repaint();
        }
        this.vanComponent = null;
    }

    public void verwijderVerbinding() {
        int beginPositieX = (int) beginPositie.getX();
        int beginPositieY = (int) beginPositie.getY();
        int eindPositieX = (int) eindPositie.getX();
        int eindPositieY = (int) eindPositie.getY();

        for (int i = (verbindingen.size() - 1); i >= 0; i--) {
            VerbindingComponent verbinding = verbindingen.get(i);

            int loopBeginPositieX = (int) verbinding.getBeginPositie().getX();
            int loopBeginPositieY = (int) verbinding.getBeginPositie().getY();
            int loopEindPositieX = (int) verbinding.getEindPositie().getX();
            int loopEindPositieY = (int) verbinding.getEindPositie().getY();

            if (beginPositieX == loopBeginPositieX && beginPositieY == loopBeginPositieY
                    && eindPositieX == loopEindPositieX && eindPositieY == loopEindPositieY) {
                verbindingen.remove(i);
            }
            beginPositie = null;
            eindPositie = null;
        }
    }

    public JFrame getFrame() {
        return this.frame;
    }

    public DragDropComponent getVanComponent() {
        return this.vanComponent;
    }

    public void setVanComponent(DragDropComponent vanComponent) {
        this.vanComponent = vanComponent;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        removeAll();
        int count = 0;
        int xPosition = 0;
        int yPosition = 0;

        for (int i = 0; i < this.componenten.size(); i++) {
            DragDropComponent cc = this.componenten.get(i);

//             if (cc.getImageCorner().getY() == 0 && cc.getImageCorner().getX() == 0) {
//                if (count == 6) {
//                    count = 0;
//                    yPosition = yPosition + 100;
//                }
//                xPosition = 100 * count;
//                count = count + 1;
//             }
//             else {
//                xPosition = (int)cc.getImageCorner().getX();
//                yPosition = (int)cc.getImageCorner().getY();
//                System.out.println("hoi");
//             }

            cc.setBounds((int) cc.getImageCorner().getX(), (int) cc.getImageCorner().getY(), 100, 100);

            if (vanComponent != null) {
                if (cc != vanComponent) {
                    cc.setOpaque(true);
                    cc.setBackground(new Color(0, 0, 0, 10));
                }
            } else {
                cc.setOpaque(false);
            }
            add(cc, BorderLayout.NORTH);
        }

        boolean hover = false;

        for (int i = 0; i < verbindingen.size(); i++) {
            VerbindingComponent verbinding = verbindingen.get(i);
            int vanPositieX = (int) verbinding.getVanComponent().getImageCorner().getX() + 50;
            int vanPositieY = (int) verbinding.getVanComponent().getImageCorner().getY() + 50;
            int naarPositieX = (int) verbinding.getNaarComponent().getImageCorner().getX() + 50;
            int naarPositieY = (int) verbinding.getNaarComponent().getImageCorner().getY() + 50;

            Polygon achtergrondLijn = new Polygon();
            achtergrondLijn.addPoint(naarPositieX + 3, naarPositieY + 3);
            achtergrondLijn.addPoint(vanPositieX + 3, vanPositieY + 3);
            achtergrondLijn.addPoint(vanPositieX - 3, vanPositieY - 3);
            achtergrondLijn.addPoint(naarPositieX - 3, naarPositieY - 3);

            g2.setColor(new Color(0,0,0,0));
            g2.fillPolygon(achtergrondLijn);

            Line2D lijn = new Line2D.Double();
            lijn.setLine(vanPositieX, vanPositieY, naarPositieX, naarPositieY);

            if (achtergrondLijn.contains(muisPositie)) {
                g2.setColor(Color.RED);
                beginPositie = lijn.getP1();
                eindPositie = lijn.getP2();
                hover = true;
            } else {
                g2.setColor(Color.BLACK);
            }

            verbinding.setLijnPosities(lijn.getP1(), lijn.getP2());
            g2.draw(lijn);
        }

        if (!hover) {
            beginPositie = null;
            eindPositie = null;
        }

        setBackground(UIManager.getColor("ComboBox.buttonBackground"));
        setVisible(true);
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
        muisPositie = e.getPoint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(beginPositie);
        if (beginPositie != null && eindPositie != null) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                verwijderVerbinding();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
