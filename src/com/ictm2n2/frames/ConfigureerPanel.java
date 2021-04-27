package com.ictm2n2.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ictm2n2.resources.Componenten;
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

    private JButton jbDbVoegToe;
    private JButton jbWsVoegToe;
    private JButton jbFwVoegToe;

    private TekenPanel tp = new TekenPanel();

    public ConfigureerPanel() {
        setLayout(null);

        Componenten c = new Componenten();

        jcbDbServers = new JComboBox<Object>(c.get(DatabaseServer.class));
        jcbWebServers = new JComboBox<Object>(c.get(Webserver.class));
        jcbFirewalls = new JComboBox<Object>(c.get(Firewall.class));

        jbDbVoegToe = new JButton("+");
        jbWsVoegToe = new JButton("+");
        jbFwVoegToe = new JButton("+");

        jlPercentage = new JLabel("Gewenst Percentage:");
        jtPercentage = new JTextField(4);
        jbOptimaliseer = new JButton("▶");

        jbDbVoegToe.addActionListener(this);
        jbWsVoegToe.addActionListener(this);
        jbFwVoegToe.addActionListener(this);

        jcbWebServers.setBounds(10, 10, 100, 30);
        jcbDbServers.setBounds(10, 45, 100, 30);
        jcbFirewalls.setBounds(10, 80, 100, 30);

        jbDbVoegToe.setBounds(120, 10, 45, 30);
        jbWsVoegToe.setBounds(120, 45, 45, 30);
        jbFwVoegToe.setBounds(120, 80, 45, 30);

        jlPercentage.setBounds(10, 110, 130, 30);
        jtPercentage.setBounds(10, 140, 45, 25);
        jbOptimaliseer.setBounds(65, 140, 100, 25);

        int width = 695;
        int height = 515;

        tp.setSize(width, height);
        tp.setBounds(175, 10, width, height);

        add(jcbWebServers);
        add(jcbDbServers);
        add(jcbFirewalls);
        add(jbDbVoegToe);
        add(jbWsVoegToe);
        add(jbFwVoegToe);
        add(jlPercentage);
        add(jtPercentage);
        add(jbOptimaliseer);
        add(tp);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
