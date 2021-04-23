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
import javax.swing.border.EmptyBorder;

import com.ictm2n2.frames.elements.Header;
import com.ictm2n2.frames.elements.Table;
import com.ictm2n2.resources.Component;
import com.ictm2n2.resources.DatabaseServer;
import com.ictm2n2.resources.Ontwerp;
import com.ictm2n2.resources.OntwerpOverzicht;
import com.ictm2n2.resources.Webserver;

public class OntwerpOverzichtFrame extends JFrame implements ActionListener {

    private App parent;

    private Header header;
    private JLabel jlOntwerpenOverzicht;
    private JComboBox<Object> jcbAanGemaakteOntwerpen;
    private JButton jbBewerkButton;
    private JButton jbVerwijderButton;
    private JButton jbNieuwOntwerpButton;
    // private List<Object> jlOntwerpList;
    private Table jtOntwerpTable;
    private JPanel jpContainer;
    private JPanel jpOverzichtPanel;
    private JButton jbTerugButton;
    private JPanel jpHeaderPanel;

    // private double gewensteBeschikbaarheid;
    private OntwerpOverzicht ontwerpOverzicht;

    private Ontwerp actiefOntwerp;

    public OntwerpOverzichtFrame(App parent, OntwerpOverzicht ontwerpOverzicht) {
        this.parent = parent;
        this.ontwerpOverzicht = ontwerpOverzicht;

        Ontwerp o = new Ontwerp("Ontwerp 1");
        Ontwerp o1 = new Ontwerp("Ontwerp 2");
        Webserver ws1 = new Webserver("Webserver 1", true, 20.00, 99.99);
        DatabaseServer ws2 = new DatabaseServer("Webserver 1", true, 20.00, 99.99);
        o.voegToeComponent(ws1);
        o.voegToeComponent(ws2);
        this.ontwerpOverzicht.voegToeOntwerp(o);
        this.ontwerpOverzicht.voegToeOntwerp(o1);

        // Dropdown data list
        ArrayList<String> data = new ArrayList<String>();
        for (Ontwerp ontwerp : ontwerpOverzicht.getOntwerpen()) {
            data.add(ontwerp.getNaam());
        }

        setSize(900, 600);
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
        header = new Header("Ontwerp Overzicht");
        jbTerugButton.setSize(50, 30);
        jbTerugButton.setAlignmentX(LEFT_ALIGNMENT);

        this.actiefOntwerp = this.ontwerpOverzicht.vindOntwerp(jcbAanGemaakteOntwerpen.getSelectedItem().toString());

        // jlOntwerpList = new List<Object>(this.actiefOntwerp.getComponentenNamen());

        Object[][] ontwerpData = new Object[this.actiefOntwerp.getComponenten().size()][2];

        int i = 0;
        int j = 0;
        for (Component c : this.actiefOntwerp.getComponenten()) {
            ontwerpData[i][j] = c.getNaam();
            ontwerpData[i][j + 1] = c.getPrijs();
            System.out.println(c.getNaam());
            System.out.println(c.getPrijs());
            i++;
            j++;
        }

        jtOntwerpTable = new Table(ontwerpData, new String[] { "naam", "prijs" });

        jcbAanGemaakteOntwerpen.setMaximumSize(new Dimension(100, 30));

        jpHeaderPanel.setLayout(new FlowLayout());

        jpContainer.setLayout(new BorderLayout());
        jpContainer.setPreferredSize(new Dimension(900, 600));
        jpContainer.setBorder(new EmptyBorder(20, 20, 20, 20));

        jpOverzichtPanel.setLayout(new BoxLayout(jpOverzichtPanel, BoxLayout.X_AXIS));

        jpHeaderPanel.add(jbTerugButton);
        jpHeaderPanel.add(jlOntwerpenOverzicht);

        jpOverzichtPanel.add(jcbAanGemaakteOntwerpen);
        jpOverzichtPanel.add(jbBewerkButton);
        jpOverzichtPanel.add(jbVerwijderButton);
        jpOverzichtPanel.add(jbNieuwOntwerpButton);
        // jpOverzichtPanel.add(jlOntwerpList);
        // jpOverzichtPanel.add(jtOntwerpTable.getTableHeader());
        jpOverzichtPanel.add(jtOntwerpTable);

        jpContainer.add(header, BorderLayout.NORTH);
        jpContainer.add(jpOverzichtPanel, BorderLayout.CENTER);

        jbBewerkButton.addActionListener(this);
        jbVerwijderButton.addActionListener(this);
        jbNieuwOntwerpButton.addActionListener(this);
        jcbAanGemaakteOntwerpen.addActionListener(this);
        header.jbTerugButton.addActionListener(this);

        add(jpContainer);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            this.actiefOntwerp = ontwerpOverzicht.vindOntwerp(jcbAanGemaakteOntwerpen.getSelectedItem().toString());
            // DefaultListModel<Object> model = new DefaultListModel<Object>();
            // for (Object string : this.actiefOntwerp.getComponentenNamen()) {
            // model.addElement(string);
            // }
            // jlOntwerpList.setModel(model);
            if (e.getSource() == jbBewerkButton) {
                new OntwerpMakenFrame(this.actiefOntwerp);
            }
            if (e.getSource() == jbVerwijderButton) {
                // Ontwerp o =
                // ontwerpOverzicht.vindOntwerp(jcbAanGemaakteOntwerpen.getSelectedItem().toString());
                int index = ontwerpOverzicht.getOntwerpen().indexOf(this.actiefOntwerp);

                ontwerpOverzicht.verwijderOntwerp(index);
                jcbAanGemaakteOntwerpen.removeItemAt(index);

            }
            if (e.getSource() == jbNieuwOntwerpButton) {
                new OntwerpMakenFrame(new Ontwerp());
            }
            if (e.getSource() == header.jbTerugButton) {
                dispose();
                this.parent.setVisible(true);

            }
            repaint();
        } catch (NullPointerException exception) {
            System.err.println(exception.getMessage());
        }

    }
}
