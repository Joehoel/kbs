package com.ictm2n2.frames;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class OntwerpOverzichtFrame extends JFrame {
    private JLabel ontwerpenOverzichtTitle;
    private JComboBox<String> aanGemaakteOntwerpenDropdown;
    private JButton bewerkButton;
    private JButton verwijderButton;
    private JButton nieuwOntwerpButton;
    private JPanel container;

    public OntwerpOverzichtFrame() {
        setSize(1600, 900);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Ontwerpen Overzicht");
        setLayout(new GridLayout(2, 1));

        aanGemaakteOntwerpenDropdown = new JComboBox<String>(new String[] { "Test", "test" });
        ontwerpenOverzichtTitle = new JLabel("<html><h1>Ontwerp Overzicht</h1></html>", JLabel.CENTER);
        bewerkButton = new JButton("Bewerken");
        verwijderButton = new JButton("Verwijderen");
        nieuwOntwerpButton = new JButton("Nieuw ontwerp");

        container = new JPanel(new FlowLayout());
        container.setPreferredSize(new Dimension(900, 600));
        container.setBorder(new EmptyBorder(20, 20, 20, 20));

        container.add(aanGemaakteOntwerpenDropdown);
        container.add(bewerkButton);
        container.add(verwijderButton);
        container.add(nieuwOntwerpButton);

        add(ontwerpenOverzichtTitle);
        add(container);

        setVisible(true);
    }
}
