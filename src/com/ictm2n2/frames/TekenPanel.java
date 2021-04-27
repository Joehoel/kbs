package com.ictm2n2.frames;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class TekenPanel extends JPanel {
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);
    }

    TekenPanel() {
        setLayout(null);
    }
}
