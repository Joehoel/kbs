package com.ictm2n2.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ictm2n2.resources.database.Database;
import com.ictm2n2.resources.database.Query;

import jdk.internal.dynalink.support.ClassLoaderGetterContextProvider;

public class BeheerDialoog extends JDialog implements ActionListener {

    JLabel jlConfiguraties;
    JComboBox<Object> jcbConfiguraties;
    JButton jbVerwijder;
    JButton jbOpenen;
    ArrayList<String> namen = new ArrayList<String>();
    ArrayList<Integer> ids = new ArrayList<Integer>();
    String geselecteerdeConfiguratie;
    int geselecteerdeConfiguratieId;

    public BeheerDialoog() {
        setTitle("Beheer");
        setSize(300, 155);
        setLayout(null);
        try {
            jcbConfiguraties = new JComboBox<Object>(namen.toArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        jlConfiguraties = new JLabel("Configuraties:");
        jbVerwijder = new JButton("Verwijder");
        jbOpenen = new JButton("Openen");

        jlConfiguraties.setBounds(10, 5, 260, 30);
        jcbConfiguraties.setBounds(10, 35, 260, 30);
        jbOpenen.setBounds(10, 75, 125, 30);
        jbVerwijder.setBounds(145, 75, 125, 30);

        jbOpenen.addActionListener(this);
        jbOpenen.addActionListener(this);
        jcbConfiguraties.addActionListener(this);

        add(jcbConfiguraties);
        add(jlConfiguraties);
        add(jbOpenen);
        add(jbVerwijder);

        geselecteerdeConfiguratie = (String) jcbConfiguraties.getSelectedItem();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        geselecteerdeConfiguratie = (String) jcbConfiguraties.getSelectedItem();
        geselecteerdeConfiguratieId = (int) ids.get(jcbConfiguraties.getSelectedIndex());
        if (e.getSource() == jbOpenen) {
            dispose();
        }
        if (e.getSource() == jbVerwijder) {

        }

    }

    // private Object[] getConfiguraties() throws SQLException {
    // Database db = new Database("nerdygadgets", "monitoring", "Iloveberrit3!$");
    // Query q = new Query();
    // q.select(null).from("configuratie");
    // ResultSet rs = db.select(q);

    // while (rs.next()) {
    // namen.add(rs.getString("naam"));
    // }
    // return namen.toArray();
    // }

    public void updateDropdown() throws SQLException {
        Database db = new Database("nerdygadgets", "monitoring", "Iloveberrit3!$");
        Query q = new Query();
        q.select(null).from("configuratie");
        ResultSet rs = db.select(q);

        while (rs.next()) {
            namen.add(rs.getString("naam"));
            ids.add(rs.getInt("configuratie_id"));
        }
        jcbConfiguraties.removeAll();
        // jcbConfiguraties.removeAllItems();
        for (String string : namen) {
            jcbConfiguraties.addItem(string);
        }
    }
}
