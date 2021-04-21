package com.ictm2n2.frames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.ictm2n2.resources.Component;
import com.ictm2n2.resources.Ontwerp;

public class OntwerpMakenFrame extends JFrame {

    private Ontwerp ontwerp;

    private JComboBox<String> jcbDatabaseNaam;
    private JComboBox<String> jcbWebserverNaam;
    private JComboBox<String> jcbToegevoegdeComponenten;

    private JPanel jpContainer;
    private JButton jbTerugButton;
    private JPanel jpHeaderPanel;
    private JLabel jlOntwerpMaken;
    private JPanel jpMaken;
    private JLabel jlVoerBeschikbaarheidIn;

    public OntwerpMakenFrame(Ontwerp ontwerp) {
        this.ontwerp = ontwerp;

        System.out.println(ontwerp.getNaam());

        jlVoerBeschikbaarheidIn = new JLabel("Voer gewenste beschikbaarheid in:");

        setTitle("Ontwerp Maken | " + this.ontwerp.getNaam());
        setSize(900, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        jbTerugButton = new JButton("◀ Terug");
        jpHeaderPanel = new JPanel();
        jpContainer = new JPanel();
        jlOntwerpMaken = new JLabel("<html><h1>Ontwerp Maken</h1></html>");
        jpMaken = new JPanel();
        jcbDatabaseNaam = new JComboBox<String>();

        jbTerugButton.setSize(50, 30);
        jbTerugButton.setAlignmentX(LEFT_ALIGNMENT);

        jpContainer.setLayout(new BorderLayout());
        jpContainer.setPreferredSize(new Dimension(900, 600));
        jpContainer.setBorder(new EmptyBorder(20, 20, 20, 20));

        jpHeaderPanel.setLayout(new FlowLayout());

        jpMaken.setLayout(new BoxLayout(jpMaken, BoxLayout.X_AXIS));

        jpHeaderPanel.add(jbTerugButton);
        jpHeaderPanel.add(jlOntwerpMaken);

        jpMaken.add(jcbDatabaseNaam);

        jpContainer.add(jpHeaderPanel);
        jpContainer.add(jpMaken);

        add(jpContainer);

        setVisible(true);
    }

}
