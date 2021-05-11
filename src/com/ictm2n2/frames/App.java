package com.ictm2n2.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class App extends JFrame implements ActionListener {
    private JTabbedPane jtpTabbedPane = new JTabbedPane();
    private ConfigureerPanel configureerPanel = new ConfigureerPanel();
    private MonitorPanel monitorPanel = new MonitorPanel();

    public App() {
        try {
            setSize(900, 600);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setTitle("NerdyGadgets | ICTm2n2");
            setResizable(false);
            setLayout(null);

            jtpTabbedPane.setBounds(0, 0, 900, 600);
            jtpTabbedPane.add("Configureer", configureerPanel);
            jtpTabbedPane.add("Monitor", monitorPanel);

            add(jtpTabbedPane);

            setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
