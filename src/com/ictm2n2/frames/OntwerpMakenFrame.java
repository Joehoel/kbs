package com.ictm2n2.frames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.ictm2n2.frames.elements.Header;
import com.ictm2n2.resources.Component;
import com.ictm2n2.resources.Ontwerp;

public class OntwerpMakenFrame extends JFrame implements ActionListener {

    private Ontwerp ontwerp;

    private Header header;
    private JComboBox<String> jcbDatabaseNaam;
    private JComboBox<String> jcbWebserverNaam;
    private JComboBox<String> jcbToegevoegdeComponenten;

    private JPanel jpContainer;
    // private JButton jbTerugButton;
    // private JPanel jpHeaderPanel;
    // private JLabel jlOntwerpMaken;
    private JPanel jpMaken;
    private JLabel jlVoerBeschikbaarheidIn;

    public OntwerpMakenFrame(Ontwerp ontwerp) {
        this.ontwerp = ontwerp;

        System.out.println(ontwerp.getNaam());

        jlVoerBeschikbaarheidIn = new JLabel("Voer gewenste beschikbaarheid in:");

        setTitle("Ontwerp Maken | " + this.ontwerp.getNaam());
        setSize(900, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // jbTerugButton = new JButton("◀ Terug");
        // jpHeaderPanel = new JPanel();
        jpContainer = new JPanel();
        // jlOntwerpMaken = new JLabel("<html><h1>Ontwerp Maken</h1></html>");
        header = new Header(this, "Ontwerp Maken");
        jpMaken = new JPanel();
        jcbDatabaseNaam = new JComboBox<String>();

        jcbDatabaseNaam.setMaximumSize(new Dimension(200, 30));

        jpContainer.setLayout(new BorderLayout());
        jpContainer.setPreferredSize(new Dimension(900, 600));
        jpContainer.setBorder(new EmptyBorder(20, 20, 20, 20));

        // jpHeaderPanel.setLayout(new FlowLayout());

        jpMaken.setLayout(new BoxLayout(jpMaken, BoxLayout.Y_AXIS));
        jlVoerBeschikbaarheidIn.setAlignmentX(LEFT_ALIGNMENT);

        // jpHeaderPanel.add(jbTerugButton);
        // jpHeaderPanel.add(jlOntwerpMaken);

        jpMaken.add(jcbDatabaseNaam);
        jpMaken.add(jlVoerBeschikbaarheidIn);

        // jpContainer.add(jpHeaderPanel);
        jpContainer.add(header, BorderLayout.NORTH);
        jpContainer.add(jpMaken);

        add(jpContainer);

        setVisible(true);
    }

    // @Override
    // public void actionPerformed(ActionEvent e) {
    // if (e.getSource() == header.jbTerugButton) {
    // dispose();
    // }
    // }
}
