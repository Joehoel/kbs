package com.ictm2n2.frames;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.ictm2n2.resources.OntwerpOverzicht;
import com.ictm2n2.resources.database.Database;
import com.ictm2n2.resources.database.Query;

public class App extends JFrame implements ActionListener {
    private JButton ontwerpOverzichtButton;

    public App() {
        try {
            setSize(900, 600);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setTitle("NerdyGadgets | ICTm2n2");
            setLayout(new FlowLayout());

            ontwerpOverzichtButton = new JButton("Ontwerpen Overzicht");
            add(ontwerpOverzichtButton);
            ontwerpOverzichtButton.addActionListener(this);

            // Database db = new Database("nerdygadgets", "root", "");

            setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ontwerpOverzichtButton) {
            new OntwerpOverzichtFrame(new OntwerpOverzicht());
        }
    }
}
