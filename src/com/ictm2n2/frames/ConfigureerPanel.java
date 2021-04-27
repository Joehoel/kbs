package com.ictm2n2.frames;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.ictm2n2.resources.Componenten;
import com.ictm2n2.resources.DatabaseServer;
import com.ictm2n2.resources.Firewall;
import com.ictm2n2.resources.Webserver;

public class ConfigureerPanel extends JPanel {

    JComboBox<Object> jcbDbServers;
    JComboBox<Object> jcbWebServers;
    JComboBox<Object> jcbFirewalls;

    public ConfigureerPanel() {
        setLayout(null);

        Componenten c = new Componenten();

        jcbDbServers = new JComboBox<Object>(c.get(DatabaseServer.class));
        jcbWebServers = new JComboBox<Object>(c.get(Webserver.class));
        jcbFirewalls = new JComboBox<Object>(c.get(Firewall.class));

        jcbWebServers.setBounds(10, 10, 100, 25);
        jcbDbServers.setBounds(10, 45, 100, 25);
        jcbFirewalls.setBounds(10, 80, 100, 25);

        add(jcbWebServers);
        add(jcbDbServers);
        add(jcbFirewalls);

    }

}
