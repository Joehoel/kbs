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
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.ictm2n2.resources.Ontwerp;
import com.ictm2n2.resources.OntwerpOverzicht;

public class OntwerpOverzichtFrame extends JFrame implements ActionListener {

    private JLabel jlOntwerpenOverzicht;
    private JComboBox<Object> jcbAanGemaakteOntwerpen;
    private JButton jbBewerkButton;
    private JButton jbVerwijderButton;
    private JButton jbNieuwOntwerpButton;
    private JList<Object> jkOntwerpList;
    private JPanel jpContainer;
    private JPanel jpOverzichtPanel;
    private JButton jbTerugButton;
    private JPanel jpHeaderPanel;

    private double gewensteBeschikbaarheid;
    private OntwerpOverzicht ontwerpOverzicht;

    public OntwerpOverzichtFrame(OntwerpOverzicht ontwerpOverzicht) {
        this.ontwerpOverzicht = ontwerpOverzicht;

        Ontwerp o = new Ontwerp("WS1");
        Ontwerp o1 = new Ontwerp("WS2");
        Ontwerp o2 = new Ontwerp("WS3");
        Ontwerp o3 = new Ontwerp("WS4");
        Ontwerp o4 = new Ontwerp("WS5");
        Ontwerp o5 = new Ontwerp("WS6");
        this.ontwerpOverzicht.voegToeOntwerp(o);
        this.ontwerpOverzicht.voegToeOntwerp(o1);
        this.ontwerpOverzicht.voegToeOntwerp(o2);
        this.ontwerpOverzicht.voegToeOntwerp(o3);
        this.ontwerpOverzicht.voegToeOntwerp(o4);
        this.ontwerpOverzicht.voegToeOntwerp(o5);

        // Dropdown data list
        ArrayList<String> data = new ArrayList<String>();
        for (Ontwerp ontwerp : ontwerpOverzicht.getOntwerpen()) {
            data.add(ontwerp.getNaam());
        }

        setSize(1600, 900);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Ontwerpen Overzicht");

        jcbAanGemaakteOntwerpen = new JComboBox<Object>(data.toArray());
        jlOntwerpenOverzicht = new JLabel("<html><h1>Ontwerp Overzicht</h1></html>", JLabel.CENTER);
        jbBewerkButton = new JButton("Bewerken");
        jbVerwijderButton = new JButton("Verwijderen");
        jbNieuwOntwerpButton = new JButton("Nieuw ontwerp");
        jpContainer = new JPanel();
        jpOverzichtPanel = new JPanel();
        jbTerugButton = new JButton("◀ Terug");
        jpHeaderPanel = new JPanel();
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

        jbBewerkButton.addActionListener(this);
        jbVerwijderButton.addActionListener(this);
        jbNieuwOntwerpButton.addActionListener(this);

        add(jpContainer);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Ontwerp o = ontwerpOverzicht.vindOntwerp(jcbAanGemaakteOntwerpen.getSelectedItem().toString());
        if (e.getSource() == jbBewerkButton) {
            new OntwerpMakenFrame(o);
        }
        if (e.getSource() == jbVerwijderButton) {
            int index = ontwerpOverzicht.getOntwerpen().indexOf(o);
            ontwerpOverzicht.verwijderOntwerp(index);
            jcbAanGemaakteOntwerpen.removeItemAt(index);
        }
        if (e.getSource() == jbNieuwOntwerpButton) {
            new OntwerpMakenFrame(new Ontwerp());
        }

    }
}
