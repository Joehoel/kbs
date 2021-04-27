package com.ictm2n2.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ictm2n2.resources.Componenten;
import com.ictm2n2.resources.Configuratie;
import com.ictm2n2.resources.DatabaseServer;
import com.ictm2n2.resources.Firewall;
import com.ictm2n2.resources.Webserver;

public class ConfigureerPanel extends JPanel implements ActionListener {

    private JLabel jlPercentage;
    private JTextField jtPercentage;
    private JButton jbOptimaliseer;

    private JComboBox<Object> jcbDbServers;
    private JComboBox<Object> jcbWebServers;
    private JComboBox<Object> jcbFirewalls;

    private JLabel jlToegevoegd;
    private JComboBox<Object> jcbToegevoegd;
    private JButton jbVerwijder;

    private JButton jbDbVoegToe;
    private JButton jbWsVoegToe;
    private JButton jbFwVoegToe;

    private JLabel jlTotaleBeschikbaarheid;
    private JLabel jlTotalePrijs;

    private JButton jbOpslaan;

    private TekenPanel tp = new TekenPanel();

    private Configuratie configuratie;
    private Componenten componenten;

    public ConfigureerPanel() {
        setLayout(null);

        Componenten componenten = new Componenten();
        Configuratie configuratie = new Configuratie();

        this.componenten = componenten;
        this.configuratie = configuratie;

        jcbDbServers = new JComboBox<Object>(componenten.get(DatabaseServer.class));
        jcbWebServers = new JComboBox<Object>(componenten.get(Webserver.class));
        jcbFirewalls = new JComboBox<Object>(componenten.get(Firewall.class));

        jlToegevoegd = new JLabel("Toegevoegd:");
        jcbToegevoegd = new JComboBox<Object>(configuratie.getComponentenNamen());
        jbVerwijder = new JButton("x");

        jbDbVoegToe = new JButton("+");
        jbWsVoegToe = new JButton("+");
        jbFwVoegToe = new JButton("+");

        jlPercentage = new JLabel("Gewenst Percentage:");
        jtPercentage = new JTextField(4);
        jbOptimaliseer = new JButton("▶");

        jlTotaleBeschikbaarheid = new JLabel("Totale Beschikbaarheid");
        jlTotalePrijs = new JLabel("Totale Prijs");

        jbOpslaan = new JButton("Opslaan");

        jcbWebServers.setBounds(10, 10, 200, 30);
        jcbDbServers.setBounds(10, 45, 200, 30);
        jcbFirewalls.setBounds(10, 80, 200, 30);

        jlToegevoegd.setBounds(10, 115, 100, 30);
        jcbToegevoegd.setBounds(10, 145, 100, 30);
        jbVerwijder.setBounds(120, 145, 45, 30);

        jbWsVoegToe.setBounds(220, 10, 45, 30);
        jbDbVoegToe.setBounds(220, 45, 45, 30);
        jbFwVoegToe.setBounds(220, 80, 45, 30);

        // jlPercentage.setBounds(10, 140, 130, 30);
        // jtPercentage.setBounds(10, 170, 45, 25);
        // jbOptimaliseer.setBounds(65, 170, 100, 25);

        jlTotaleBeschikbaarheid.setBounds(10, 180, 200, 30);
        jlTotalePrijs.setBounds(10, 200, 200, 30);

        jbOpslaan.setBounds(10, 505, 100, 30);

        int width = 605;
        int height = 525;

        tp.setSize(width, height);
        tp.setBounds(275, 10, width, height);

        add(jcbWebServers);
        add(jcbDbServers);
        add(jcbFirewalls);
        add(jbDbVoegToe);
        add(jbWsVoegToe);
        add(jbFwVoegToe);
        add(jlPercentage);
        add(jtPercentage);
        add(jbOptimaliseer);
        add(jcbToegevoegd);
        add(jlToegevoegd);
        add(jbVerwijder);
        add(jlTotaleBeschikbaarheid);
        add(jlTotalePrijs);
        add(jbOpslaan);
        add(tp);

        jbDbVoegToe.addActionListener(this);
        jbWsVoegToe.addActionListener(this);
        jbFwVoegToe.addActionListener(this);
        jbOptimaliseer.addActionListener(this);
        jbVerwijder.addActionListener(this);
        jbOpslaan.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbDbVoegToe) {
            Object selectedIndex = jcbDbServers.getSelectedIndex();
            DatabaseServer c = componenten.getDbServers().get((int) selectedIndex);
            configuratie.voegToeComponent(c);
            jcbToegevoegd.addItem(c.getNaam());
        }
        if (e.getSource() == jbWsVoegToe) {
            Object selectedIndex = jcbWebServers.getSelectedIndex();
            Webserver c = componenten.getWebServers().get((int) selectedIndex);
            configuratie.voegToeComponent(c);
            jcbToegevoegd.addItem(c.getNaam());
        }
        if (e.getSource() == jbFwVoegToe) {
            Object selectedIndex = jcbFirewalls.getSelectedIndex();
            Firewall c = componenten.getFirewalls().get((int) selectedIndex);
            configuratie.voegToeComponent(c);
            jcbToegevoegd.addItem(c.getNaam());
        }
        if (e.getSource() == jbVerwijder) {
            try {
                int index = jcbToegevoegd.getSelectedIndex();
                Object name = jcbToegevoegd.getSelectedItem();
                configuratie.verwijderComponent(index);
                jcbToegevoegd.removeItem(name);

            } catch (ArrayIndexOutOfBoundsException error) {
                JOptionPane.showMessageDialog(this, "Kan component niet verwijderen", "Error",
                        JOptionPane.ERROR_MESSAGE);
                error.printStackTrace();
            }
        }
    }
}
