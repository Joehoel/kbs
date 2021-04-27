package com.ictm2n2.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.ictm2n2.resources.Componenten;
import com.ictm2n2.resources.DatabaseServer;
import com.ictm2n2.resources.Firewall;
import com.ictm2n2.resources.Webserver;

public class ConfigureerPanel extends JPanel implements ActionListener {

    JComboBox<Object> jcbDbServers;
    JComboBox<Object> jcbWebServers;
    JComboBox<Object> jcbFirewalls;

    JButton jbDbVoegToe;
    JButton jbWsVoegToe;
    JButton jbFwVoegToe;

    public ConfigureerPanel() {
        setLayout(null);

        Componenten c = new Componenten();

        jcbDbServers = new JComboBox<Object>(c.get(DatabaseServer.class));
        jcbWebServers = new JComboBox<Object>(c.get(Webserver.class));
        jcbFirewalls = new JComboBox<Object>(c.get(Firewall.class));

        jbDbVoegToe = new JButton("+");
        jbWsVoegToe = new JButton("+");
        jbFwVoegToe = new JButton("+");

        jbDbVoegToe.addActionListener(this);
        jbWsVoegToe.addActionListener(this);
        jbFwVoegToe.addActionListener(this);

        jcbWebServers.setBounds(10, 10, 100, 30);
        jcbDbServers.setBounds(10, 45, 100, 30);
        jcbFirewalls.setBounds(10, 80, 100, 30);

        jbDbVoegToe.setBounds(120, 10, 45, 30);
        jbWsVoegToe.setBounds(120, 45, 45, 30);
        jbFwVoegToe.setBounds(120, 80, 45, 30);

        add(jcbWebServers);
        add(jcbDbServers);
        add(jcbFirewalls);
        add(jbDbVoegToe);
        add(jbWsVoegToe);
        add(jbFwVoegToe);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }
}
