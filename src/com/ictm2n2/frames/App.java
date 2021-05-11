package com.ictm2n2.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class App extends JFrame implements ActionListener {
    private JTabbedPane jtpTabbedPane = new JTabbedPane();
    private ConfigureerPanel configureerPanel = new ConfigureerPanel();
    private MonitorPanel monitorPanel = new MonitorPanel();

    public App() {
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("NerdyGadgets | ICTm2n2");
        setResizable(false);
        setLayout(null);
        try {
            URL resource = getClass().getResource("../assets/KBS.png");
            BufferedImage image = ImageIO.read(resource);
            setIconImage(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
        jtpTabbedPane.setBounds(0, 0, 900, 600);
        jtpTabbedPane.add("configureer", configureerPanel);
        jtpTabbedPane.add("monitor", monitorPanel);

        add(jtpTabbedPane);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
