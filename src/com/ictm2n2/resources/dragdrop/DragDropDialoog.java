package com.ictm2n2.resources.dragdrop;

import com.ictm2n2.frames.TekenPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DragDropDialoog extends JDialog implements ActionListener {
    private DragDropComponent dragDropComponent;

    private JLabel jlIPv4Configuratie = new JLabel("IPv4 configuratie");
    private JLabel jlIPv4Adres = new JLabel("IPv4 adres");
    private JLabel jlIPv4Subnet = new JLabel("Subnetmasker");
    private JLabel jlIPv4Gateway = new JLabel("Standaardgateway");

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

        jtIPv4Adres.setBounds(150, 40, 120, 25);
        jtIPv4Subnet.setBounds(150, 70, 120, 25);
        jtIpv4Gateway.setBounds(150, 100, 120, 25);

        jbAnnuleer.setBounds(10, 325, 125, 25);
        jbOpslaan.setBounds(150, 325, 125, 25);

        jbAnnuleer.addActionListener(this);
        jbOpslaan.addActionListener(this);

        add(jlIPv4Configuratie);
        add(jlIPv4Adres);
        add(jlIPv4Subnet);
        add(jlIPv4Gateway);

        add(jtIPv4Adres);
        add(jtIPv4Subnet);
        add(jtIpv4Gateway);

        add(jbAnnuleer);
        add(jbOpslaan);

        setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dragDropComponent.setLabel();
        setVisible(false);
    }

    public String getjtIPv4Adres() {
        return this.jtIPv4Adres.getText();
    }
}
