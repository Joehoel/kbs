package com.ictm2n2.frames.elements;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Header extends JPanel {
    private JLabel jlTitle;
    public JButton jbTerugButton;

    public Header(String title) {
        jlTitle = new JLabel(String.format("<html><h1>%s</h1></html>", title));
        jbTerugButton = new JButton("◀  Terug");
        jbTerugButton.setSize(50, 30);
        jbTerugButton.setAlignmentX(LEFT_ALIGNMENT);
        add(jbTerugButton);
        add(jlTitle);
        setLayout(new FlowLayout());
    }
}
