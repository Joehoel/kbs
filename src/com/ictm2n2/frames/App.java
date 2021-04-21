package com.ictm2n2.frames;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class App extends JFrame implements ActionListener {
    private JButton ontwerpOverzichtButton;

    public App() {
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("NerdyGadgets | ICTm2n2");
        setLayout(new FlowLayout());

        ontwerpOverzichtButton = new JButton("Ontwerpen Overzicht");
        add(ontwerpOverzichtButton);
        ontwerpOverzichtButton.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ontwerpOverzichtButton) {
            new OntwerpOverzichtFrame();
        }
    }
}
