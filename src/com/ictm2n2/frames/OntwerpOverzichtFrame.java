package com.ictm2n2.frames;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.ictm2n2.resources.OntwerpOverzicht;

public class OntwerpOverzichtFrame extends JFrame {
    private JLabel ontwerpenOverzichtTitle;
    private JComboBox<String> aanGemaakteOntwerpenDropdown;
    private JButton bewerkButton;
    private JButton verwijderButton;
    private JButton nieuwOntwerpButton;
    private JList<String> ontwerpList;
    private JPanel container;
    private JPanel overzichtPanel;

    public OntwerpOverzichtFrame(OntwerpOverzicht ontwerpOverzicht) {
        setSize(1600, 900);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Ontwerpen Overzicht");

        aanGemaakteOntwerpenDropdown = new JComboBox<String>(new String[] { "Test", "test" });
        ontwerpenOverzichtTitle = new JLabel("<html><h1>Ontwerp Overzicht</h1></html>", JLabel.CENTER);
        bewerkButton = new JButton("Bewerken");
        verwijderButton = new JButton("Verwijderen");
        nieuwOntwerpButton = new JButton("Nieuw ontwerp");
        container = new JPanel();
        overzichtPanel = new JPanel();

        // ontwerpList = new JList<String>(new String[] { "Test", "test", "Test" }); //
        // ontwerpList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        // ontwerpList.setLayoutOrientation(JList.VERTICAL);
        // ontwerpList.setVisibleRowCount(-1);
        // JScrollPane listScroller = new JScrollPane(ontwerpList);
        // listScroller.setPreferredSize(new Dimension(250, 80));

        aanGemaakteOntwerpenDropdown.setMaximumSize(new Dimension(100, 30));

        container.setLayout(new BorderLayout());
        container.setPreferredSize(new Dimension(900, 600));
        container.setBorder(new EmptyBorder(20, 20, 20, 20));

        overzichtPanel.setLayout(new BoxLayout(overzichtPanel, BoxLayout.X_AXIS));

        container.add(ontwerpenOverzichtTitle, BorderLayout.NORTH);
        container.add(overzichtPanel, BorderLayout.CENTER);

        overzichtPanel.add(aanGemaakteOntwerpenDropdown);
        overzichtPanel.add(bewerkButton);
        overzichtPanel.add(verwijderButton);
        overzichtPanel.add(nieuwOntwerpButton);
        // overzichtPanel.add(ontwerpList);

        add(container);

        setVisible(true);
    }
}
