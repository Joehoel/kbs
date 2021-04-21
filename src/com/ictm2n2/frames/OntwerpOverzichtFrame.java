package com.ictm2n2.frames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.ictm2n2.resources.OntwerpOverzicht;

public class OntwerpOverzichtFrame extends JFrame {
    private JLabel jlOntwerpenOverzicht;
    private JComboBox<String> jcbAanGemaakteOntwerpen;
    private JButton jbBewerkButton;
    private JButton jbVerwijderButton;
    private JButton jbNieuwOntwerpButton;
    private JList<String> jkOntwerpList;
    private JPanel jpContainer;
    private JPanel jpOverzichtPanel;
    private JButton jbTerugButton;
    private JPanel jpHeaderPanel;

    public OntwerpOverzichtFrame(OntwerpOverzicht ontwerpOverzicht) {
        setSize(1600, 900);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Ontwerpen Overzicht");

        jcbAanGemaakteOntwerpen = new JComboBox<String>(new String[] { "Test", "test" });
        jlOntwerpenOverzicht = new JLabel("<html><h1>Ontwerp Overzicht</h1></html>", JLabel.CENTER);
        jbBewerkButton = new JButton("Bewerken");
        jbVerwijderButton = new JButton("Verwijderen");
        jbNieuwOntwerpButton = new JButton("Nieuw ontwerp");
        jpContainer = new JPanel();
        jpOverzichtPanel = new JPanel();
        jpHeaderPanel = new JPanel();
        jbTerugButton = new JButton("◀ Terug");
        jbTerugButton.setSize(50, 30);
        jbTerugButton.setAlignmentX(LEFT_ALIGNMENT);

        // ontwerpList = new JList<String>(new String[] { "Test", "test", "Test" }); //
        // ontwerpList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        // ontwerpList.setLayoutOrientation(JList.VERTICAL);
        // ontwerpList.setVisibleRowCount(-1);
        // JScrollPane listScroller = new JScrollPane(ontwerpList);
        // listScroller.setPreferredSize(new Dimension(250, 80));

        jcbAanGemaakteOntwerpen.setMaximumSize(new Dimension(100, 30));

        jpHeaderPanel.setLayout(new FlowLayout());

        jpContainer.setLayout(new BorderLayout());
        jpContainer.setPreferredSize(new Dimension(900, 600));
        jpContainer.setBorder(new EmptyBorder(20, 20, 20, 20));

        jpOverzichtPanel.setLayout(new BoxLayout(jpOverzichtPanel, BoxLayout.X_AXIS));

        jpHeaderPanel.add(jbTerugButton);
        jpHeaderPanel.add(jlOntwerpenOverzicht);

        jpContainer.add(jpHeaderPanel, BorderLayout.NORTH);
        jpContainer.add(jpOverzichtPanel, BorderLayout.CENTER);

        jpOverzichtPanel.add(jcbAanGemaakteOntwerpen);
        jpOverzichtPanel.add(jbBewerkButton);
        jpOverzichtPanel.add(jbVerwijderButton);
        jpOverzichtPanel.add(jbNieuwOntwerpButton);
        // overzichtPanel.add(ontwerpList);

        add(jpContainer);

        setVisible(true);
    }
}
