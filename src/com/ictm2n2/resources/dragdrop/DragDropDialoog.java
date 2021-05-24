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
    private JLabel jlIPv4DNSServer = new JLabel("DNS server");

    private JLabel jlIPv6Configuratie = new JLabel("IPv6 configuratie");
    private JLabel jlIPv6Adres = new JLabel("IPv6 adres");
    private JLabel jlIPv6LinkLocal = new JLabel("Link local adres");
    private JLabel jlIPv6Gateway = new JLabel("Standaardgateway");
    private JLabel jlIPv6DNSServer = new JLabel("DNS server");

    private JLabel jlError = new JLabel();

    private JTextField jtIPv4Adres = new JTextField();
    private JTextField jtIPv4Subnet = new JTextField();
    private JTextField jtIpv4Gateway = new JTextField();
    private JTextField jtIPv4DNSServer = new JTextField();

    private JTextField jtIPv6Adres = new JTextField();
    private JTextField jtIPv6LinkLocal = new JTextField();
    private JTextField jtIPv6Gateway = new JTextField();
    private JTextField jtIPv6DNSServer = new JTextField();

    private JButton jbAnnuleer = new JButton("Annuleer");
    private JButton jbOpslaan = new JButton("Opslaan");

    public DragDropDialoog(TekenPanel tekenPanel, DragDropComponent dragDropComponent) {
        super(tekenPanel.getFrame(), true);
        this.dragDropComponent = dragDropComponent;

        setLayout(null);
        setSize(450, 460);
        setLocationRelativeTo(tekenPanel.getFrame());
        setTitle("Configureer IP");

        jlIPv4Configuratie.setBounds(10, 10, 100, 25);
        jlIPv4Adres.setBounds(20, 40, 100, 25);
        jlIPv4Subnet.setBounds(20, 70, 100, 25);
        jlIPv4Gateway.setBounds(20, 100, 100, 25);
        jlIPv4DNSServer.setBounds(20, 130, 100, 25);

        jlIPv6Configuratie.setBounds(10, 180, 100, 25);
        jlIPv6Adres.setBounds(20, 210, 100, 25);
        jlIPv6LinkLocal.setBounds(20, 240, 100, 25);
        jlIPv6Gateway.setBounds(20, 270, 100, 25);
        jlIPv6DNSServer.setBounds(20, 300, 100, 25);

        jlError.setBounds(10, 355, 275, 25);

        jtIPv4Adres.setBounds(150, 40, 270, 25);
        jtIPv4Subnet.setBounds(150, 70, 270, 25);
        jtIpv4Gateway.setBounds(150, 100, 270, 25);
        jtIPv4DNSServer.setBounds(150, 130, 270, 25);

        jtIPv6Adres.setBounds(150, 210, 270, 25);
        jtIPv6LinkLocal.setBounds(150, 240, 270, 25);
        jtIPv6Gateway.setBounds(150, 270, 270, 25);
        jtIPv6DNSServer.setBounds(150, 300, 270, 25);

        jbAnnuleer.setBounds(160, 385, 125, 25);
        jbOpslaan.setBounds(300, 385, 125, 25);

        jbAnnuleer.addActionListener(this);
        jbOpslaan.addActionListener(this);

        jlError.setForeground(Color.RED);
        jlError.setVisible(false);

        add(jlIPv4Configuratie);
        add(jlIPv4Adres);
        add(jlIPv4Subnet);
        add(jlIPv4Gateway);
        add(jlIPv4DNSServer);

        add(jlIPv6Configuratie);
        add(jlIPv6Adres);
        add(jlIPv6LinkLocal);
        add(jlIPv6Gateway);
        add(jlIPv6DNSServer);

        add(jlError);

        add(jtIPv4Adres);
        add(jtIPv4Subnet);
        add(jtIpv4Gateway);
        add(jtIPv4DNSServer);

        add(jtIPv6Adres);
        add(jtIPv6LinkLocal);
        add(jtIPv6Gateway);
        add(jtIPv6DNSServer);

        add(jbAnnuleer);
        add(jbOpslaan);

        setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbAnnuleer) {
            setVisible(false);
            this.jlError.setVisible(false);
        }

        if (e.getSource() == jbOpslaan) {
            String regexIPv4 = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";
            if (!jtIPv4Adres.getText().matches(regexIPv4) && !jtIPv4Adres.getText().equals("")) {
                this.jlError.setText("Geen geldig IPv4 adres opgegeven!");
                this.jlError.setVisible(true);
            }
            else if (!jtIPv4Subnet.getText().matches(regexIPv4) && !jtIPv4Subnet.getText().equals("")) {
                this.jlError.setText("Geen geldig IPv4 subnet opgegeven!");
                this.jlError.setVisible(true);
            }
            else if (!jtIpv4Gateway.getText().matches(regexIPv4) && !jtIpv4Gateway.getText().equals("")) {
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

    public void setIPv4Adres(String ipv4Adres) {
        this.jtIPv4Adres.setText(ipv4Adres);
    }

    public void setIPv4Subnet(String ipv4Subnet) {
        this.jtIPv4Subnet.setText(ipv4Subnet);
    }

    public void setIPv4Gateway(String ipv4Gateway) {
        this.jtIpv4Gateway.setText(ipv4Gateway);
    }

    public void setIPv4DNSServer(String ipv4DNSServer) {
        this.jtIPv4DNSServer.setText(ipv4DNSServer);
    }

    public void setIPv6Adres(String ipv6Adres) {
        this.jtIPv6Adres.setText(ipv6Adres);
    }

    public void setIPv6LinkLocal(String ipv6LinkLocal) {
        this.jtIPv6LinkLocal.setText(ipv6LinkLocal);
    }

    public void setIPv6Gateway(String ipv6Gateway) {
        this.jtIPv6Gateway.setText(ipv6Gateway);
    }

    public void setIPv6DNSServer(String ipv6DNSServer) {
        this.jtIPv6DNSServer.setText(ipv6DNSServer);
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

    public String getIPv4DNSServer() {
        return jtIPv4DNSServer.getText();
    }

    public String getIPv6Adres() {
        return this.jtIPv6Adres.getText();
    }

    public String getIPv6LinkLocal() {
        return this.jtIPv6LinkLocal.getText();
    }

    public String getIPv6Gateway() {
        return this.jtIPv6Gateway.getText();
    }

    public String getIPv6DNSServer() {
        return this.jtIPv6DNSServer.getText();
    }

    public boolean checkContent() {
        if (!jtIPv4Adres.getText().isEmpty() || !jtIPv4Subnet.getText().isEmpty() || !jtIpv4Gateway.getText().isEmpty()
                || !jtIPv4DNSServer.getText().isEmpty() || !jtIPv6Adres.getText().isEmpty()
                || !jtIPv6LinkLocal.getText().isEmpty() || !jtIPv6Gateway.getText().isEmpty()
                || !jtIPv6DNSServer.getText().isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }
}
