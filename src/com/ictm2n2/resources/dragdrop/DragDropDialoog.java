package com.ictm2n2.resources.dragdrop;

import com.ictm2n2.frames.TekenPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.regex.Pattern;

public class DragDropDialoog extends JDialog implements ActionListener {
    private DragDropComponent dragDropComponent;

    private JLabel jlIPv4Configuratie = new JLabel("IPv4 configuratie");
    private JLabel jlIPv4Adres = new JLabel("IPv4 adres");
    private JLabel jlIPv4Subnet = new JLabel("Subnetmasker");
    private JLabel jlIPv4Gateway = new JLabel("Standaardgateway");
    private JLabel jlError = new JLabel();

    private JTextField jtIPv4Adres = new JTextField();
    private JTextField jtIPv4Subnet = new JTextField();
    private JTextField jtIpv4Gateway = new JTextField();

    private JButton jbAnnuleer = new JButton("Annuleer");
    private JButton jbOpslaan = new JButton("Opslaan");

    public DragDropDialoog(TekenPanel tekenPanel, DragDropComponent dragDropComponent) {
        super(tekenPanel.getFrame(), true);
        this.dragDropComponent = dragDropComponent;

        setLayout(null);
        setSize(300, 400);
        setLocationRelativeTo(tekenPanel.getFrame());
        setTitle("Configureer IP");

        jlIPv4Configuratie.setBounds(10, 10, 100, 25);
        jlIPv4Adres.setBounds(20, 40, 100, 25);
        jlIPv4Subnet.setBounds(20, 70, 100, 25);
        jlIPv4Gateway.setBounds(20, 100, 100, 25);
        jlError.setBounds(10, 285, 275, 25);

        jtIPv4Adres.setBounds(150, 40, 120, 25);
        jtIPv4Subnet.setBounds(150, 70, 120, 25);
        jtIpv4Gateway.setBounds(150, 100, 120, 25);

        jbAnnuleer.setBounds(10, 325, 125, 25);
        jbOpslaan.setBounds(150, 325, 125, 25);

        jbAnnuleer.addActionListener(this);
        jbOpslaan.addActionListener(this);

        jlError.setForeground(Color.RED);
        jlError.setVisible(false);

        add(jlIPv4Configuratie);
        add(jlIPv4Adres);
        add(jlIPv4Subnet);
        add(jlIPv4Gateway);
        add(jlError);

        add(jtIPv4Adres);
        add(jtIPv4Subnet);
        add(jtIpv4Gateway);

        add(jbAnnuleer);
        add(jbOpslaan);

        setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbAnnuleer) {
            setVisible(false);
        }

        if (e.getSource() == jbOpslaan) {
            if (jtIPv4Adres.getText().equals("")) {
                setVisible(false);
            }
            else {
                String regex = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";
                if (!jtIPv4Adres.getText().matches(regex)) {
                    this.jlError.setText("Geen geldig IPv4 adres opgegeven!");
                    this.jlError.setVisible(true);
                }
                else if (!jtIPv4Subnet.getText().matches(regex) && !jtIPv4Subnet.getText().equals("")) {
                    this.jlError.setText("Geen geldig IPv4 subnet opgegeven!");
                    this.jlError.setVisible(true);
                }
                else if (!jtIpv4Gateway.getText().matches(regex) && !jtIpv4Gateway.getText().equals("")) {
                    this.jlError.setText("Geen geldig IPv4 gateway opgegeven!");
                    this.jlError.setVisible(true);
                }
                else {
                    dragDropComponent.setLabel();
                    setVisible(false);
                    this.jlError.setVisible(false);
                }
            }
        }
    }

    public void setIPv4Adres(String ipv4Adres) {
        this.jtIPv4Adres.setText(ipv4Adres);
    }

    public void setIPv4Subnet(String ipv4Subnet) {
        this.jtIPv4Subnet.setText(ipv4Subnet);
    }

    public void setIPv4Gateway(String ipv4Gateway) {
        this.jtIpv4Gateway.setText(ipv4Gateway);
    }

    public String getIPv4Adres() {
        return this.jtIPv4Adres.getText();
    }

    public String getIPv4Subnet() {
        return jtIPv4Subnet.getText();
    }

    public String getIPv4Gateway() {
        return jtIpv4Gateway.getText();
    }
}
