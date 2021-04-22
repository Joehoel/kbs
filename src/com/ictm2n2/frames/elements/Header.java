package com.ictm2n2.frames.elements;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Header extends JPanel implements ActionListener {

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

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
