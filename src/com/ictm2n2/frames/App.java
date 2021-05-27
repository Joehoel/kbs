package com.ictm2n2.frames;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.print.attribute.standard.Media;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class App extends JFrame implements ActionListener {
    private JTabbedPane jtpTabbedPane = new JTabbedPane();
    private ConfigureerPanel configureerPanel = new ConfigureerPanel(this);
    private MonitorPanel monitorPanel = new MonitorPanel();
    private InstellingenPanel instellingenPanel = new InstellingenPanel();

    public App() {
        try {
            setSize(900, 600);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setTitle("NerdyGadgets | ICTm2n2");
            setResizable(false);
            setLayout(null);
            setAutoRequestFocus(true);

            jtpTabbedPane.setBounds(0, 0, 900, 600);
            jtpTabbedPane.add("Configureer", configureerPanel);
            jtpTabbedPane.add("Monitor", monitorPanel);
            jtpTabbedPane.add("Instellingen", instellingenPanel);

            add(jtpTabbedPane);

            setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }

        add(jtpTabbedPane);

        setVisible(true);
        centerWindow(this);
    }

    public static void centerWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
