package com.ictm2n2.frames;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class OntwerpOverzichtFrame extends JFrame {
    private JLabel ontwerpenOverzichtTitle;

    public OntwerpOverzichtFrame() {
        setSize(1600, 900);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Ontwerpen Overzicht");
        setLayout(new GridLayout(5, 1));

        ontwerpenOverzichtTitle = new JLabel("<html><h1>Ontwerp Overzicht</h1></html>", JLabel.CENTER);
        add(ontwerpenOverzichtTitle);

        setVisible(true);
    }
}
