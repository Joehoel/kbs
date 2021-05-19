package com.ictm2n2.frames;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ictm2n2.resources.Component;
import com.ictm2n2.resources.Componenten;
import com.ictm2n2.resources.Configuratie;
import com.ictm2n2.resources.DatabaseServer;
import com.ictm2n2.resources.Firewall;
import com.ictm2n2.resources.Webserver;
import com.ictm2n2.resources.database.Database;
import com.ictm2n2.resources.database.Query;
import com.ictm2n2.resources.dragdrop.DragDropComponent;

public class ConfigureerPanel extends JPanel implements ActionListener {

    private JLabel jlPercentage;
    private JTextField jtPercentage;
    private JButton jbOptimaliseer;

    private JComboBox<Object> jcbDbServers;
    private JComboBox<Object> jcbWebServers;
    private JComboBox<Object> jcbFirewalls;

    private JButton jbDbVoegToe;
    private JButton jbWsVoegToe;
    private JButton jbFwVoegToe;

    private JLabel jlTotaleBeschikbaarheid;
    private JLabel jlTotalePrijs;

    private JButton jbOpslaan;
    private JButton jbBeheer;

    private Configuratie configuratie;
    private Componenten componenten;
    private static int primaryKey = 0;

    private TekenPanel tp;
    private BeheerDialoog bd;

    public ConfigureerPanel() {
        setLayout(null);

        bd = new BeheerDialoog();

        Componenten componenten = new Componenten();
        Configuratie configuratie = new Configuratie();

        this.componenten = componenten;
        this.configuratie = configuratie;

        tp = new TekenPanel(this, this.configuratie);

        jcbDbServers = new JComboBox<Object>(componenten.get(DatabaseServer.class));
        jcbWebServers = new JComboBox<Object>(componenten.get(Webserver.class));
        jcbFirewalls = new JComboBox<Object>(componenten.get(Firewall.class));

        jbDbVoegToe = new JButton("➕");
        jbWsVoegToe = new JButton("➕");
        jbFwVoegToe = new JButton("➕");

        jlPercentage = new JLabel("Gewenst Percentage:");
        jtPercentage = new JTextField(4);
        jbOptimaliseer = new JButton("Optimaliseer");

        jlTotaleBeschikbaarheid = new JLabel("Totale Beschikbaarheid: " + configuratie.berekenBeschikbaarheid() + "%");
        jlTotalePrijs = new JLabel("Totale Prijs: " + configuratie.berekenTotalePrijs());

        jbOpslaan = new JButton("Opslaan");
        jbBeheer = new JButton("Beheer");

        jcbWebServers.setBounds(10, 10, 200, 30);
        jcbDbServers.setBounds(10, 45, 200, 30);
        jcbFirewalls.setBounds(10, 80, 200, 30);

        jbWsVoegToe.setBounds(220, 10, 45, 30);
        jbDbVoegToe.setBounds(220, 45, 45, 30);
        jbFwVoegToe.setBounds(220, 80, 45, 30);

        jlTotaleBeschikbaarheid.setBounds(10, 180, 200, 30);
        jlTotalePrijs.setBounds(10, 200, 200, 30);

        jlPercentage.setBounds(10, 240, 130, 30);
        jtPercentage.setBounds(10, 270, 45, 25);
        jbOptimaliseer.setBounds(65, 270, 200, 25);

        jbOpslaan.setBounds(10, 495, 122, 30);
        jbBeheer.setBounds(142, 495, 122, 30);

        int width = 605;
        int height = 515;

        tp.setSize(width, height);
        tp.setBounds(275, 10, width, height);

        add(jcbWebServers);
        add(jcbDbServers);
        add(jcbFirewalls);

        add(jbDbVoegToe);
        add(jbWsVoegToe);
        add(jbFwVoegToe);

        add(jlPercentage);
        add(jtPercentage);
        add(jbOptimaliseer);

        // Backtracking grafisch toevoeging
        add(jlTotaleBeschikbaarheid);
        add(jlTotalePrijs);

        add(jbOpslaan);
        add(jbBeheer);
        add(tp);

        jbDbVoegToe.addActionListener(this);
        jbWsVoegToe.addActionListener(this);
        jbFwVoegToe.addActionListener(this);

        // Backtracking optimaliseer
        jbOptimaliseer.addActionListener(this);
        jbOpslaan.addActionListener(this);
        jbBeheer.addActionListener(this);

        bd.jbOpenen.addActionListener(this);
        bd.jbVerwijder.addActionListener(this);
    }

    public void bewerkGegevens() {
        jlTotaleBeschikbaarheid.setText("Totale Beschikbaarheid: " + configuratie.berekenBeschikbaarheid() + "%");
        jlTotalePrijs.setText("Totale Prijs: " + configuratie.berekenTotalePrijs());

        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbDbVoegToe) {
            Object selectedIndex = jcbDbServers.getSelectedIndex();
            DatabaseServer c = componenten.getDbServers().get((int) selectedIndex);
            configuratie.voegToeComponent(c);
            tp.voegToeComponent(new Point(0, 0), "dbserver", c);
        }
        if (e.getSource() == jbWsVoegToe) {
            Object selectedIndex = jcbWebServers.getSelectedIndex();
            Webserver c = componenten.getWebServers().get((int) selectedIndex);
            configuratie.voegToeComponent(c);
            tp.voegToeComponent(new Point(0, 0), "webserver", c);
        }
        if (e.getSource() == jbFwVoegToe) {
            Object selectedIndex = jcbFirewalls.getSelectedIndex();
            Firewall c = componenten.getFirewalls().get((int) selectedIndex);
            configuratie.voegToeComponent(c);
            tp.voegToeComponent(new Point(0, 0), "firewall", c);
        }
        // Check if optimaliseer button has been pressed for backtracking
        if (e.getSource() == jbOptimaliseer) {
            try {
                double gewenstPercentage;
                // If string contains comma, convert to dot
                if (jtPercentage.getText().contains(",")) {
                    gewenstPercentage = Double.parseDouble(jtPercentage.getText().replace(",", "."));
                } else {
                    gewenstPercentage = Double.parseDouble(jtPercentage.getText());
                }

                if (gewenstPercentage > 99.99 || gewenstPercentage < 0) {
                    JOptionPane.showMessageDialog(this,
                            "Fout met optimaliseren. Voer geldig getal tussen 0 tot en met 99.99 in", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    configuratie.optimaliseer(gewenstPercentage);
                    for (Component component : configuratie.getComponenten()) {
                        tp.voegToeComponent(new Point(0, 0), component.getType(), component);
                    }
                }
                // this.componenten = configuratie.getComponenten();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Fout met optimaliseren. Voer geldig getal tussen 0 tot en met 99.99 in", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == jbOpslaan) {
            try {
                Database db = new Database("nerdygadgets", "monitoring", "Iloveberrit3!$");

                java.util.Date date = new java.util.Date();

                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                System.out.println(sqlDate);
                String naamConfiguratie = JOptionPane.showInputDialog(this, "Geef deze configuratie een naam", null);
                ArrayList<DragDropComponent> componenten = tp.getComponenten();

                Query insertQuery = new Query();
                insertQuery.insert("configuratie")
                        .columns(new Object[] { "beschikbaarheids_percentage", "naam", "prijs" })
                        .values(new Object[] { String.valueOf(configuratie.berekenBeschikbaarheid()), naamConfiguratie,
                                String.valueOf(configuratie.berekenTotalePrijsDouble()) });

                System.out.println(insertQuery.getQuery());
                PreparedStatement ps = db.getConnection().prepareStatement(insertQuery.getQuery());
                ps.setDouble(1, configuratie.berekenBeschikbaarheid());
                ps.setString(2, naamConfiguratie);
                ps.setDouble(3, configuratie.berekenTotalePrijsDouble());
                ps.executeUpdate();

                Query q = new Query().LastInsertedId();
                ResultSet rs = db.select(q);

                int id = 0;
                while (rs.next()) {
                    id = rs.getInt("configuratie_id");
                }
                System.out.println("Last id " + id);

                for (DragDropComponent dragDropComponent : componenten) {
                    int componentId = dragDropComponent.getComponent().getId();
                    double x = dragDropComponent.getImageCorner().getX();
                    double y = dragDropComponent.getImageCorner().getY();

                    Query q1 = new Query();
                    q1.insert("configuratie_onderdeel")
                            .columns(new Object[] { "configuratie_id", "type_id", "positie_x", "positie_y" })
                            .values(new Object[] { id, componentId, x, y });
                    // db.update(q1);
                    System.out.println(q1.getQuery());
                    PreparedStatement ps1 = db.getConnection().prepareStatement(q1.getQuery());
                    ps1.setInt(1, id);
                    ps1.setInt(2, componentId);
                    ps1.setInt(3, (int) x);
                    ps1.setInt(4, (int) y);
                    ps1.executeUpdate();

                }

            } catch (Exception a) {
                a.printStackTrace();
            }

        }
        if (e.getSource() == jbBeheer) {
            try {
                bd.setVisible(true);
                bd.setLocationRelativeTo(this);
                bd.updateDropdown();

            } catch (Exception err) {
                err.printStackTrace();
            }

        }
        if (e.getSource() == bd.jbOpenen) {
            try {
                Database db = new Database("nerdygadgets", "monitoring", "Iloveberrit3!$");

                Query query = new Query();
                query.select(null).from("configuratie_onderdeel")
                        .where("configuratie_id = " + bd.geselecteerdeConfiguratieId);
                ResultSet rs = db.select(query);
                while (rs.next()) {
                    int x = rs.getInt("positie_x");
                    int y = rs.getInt("positie_y");
                    int typeId = rs.getInt("type_id");

                    Query typeQuery = new Query();
                    typeQuery.select(null).from("component_type").where("type_id = " + typeId);
                    ResultSet rs1 = db.select(typeQuery);
                    while (rs1.next()) {
                        String naam = rs1.getString("type_naam");
                        double beschikbaarheid = rs1.getDouble("type_beschikbaarheid");
                        double prijs = rs1.getDouble("type_prijs");
                        String soort = rs1.getString("type_soort").toLowerCase();

                        Component c = null;

                        if (soort.equals("dbserver")) {
                            c = new DatabaseServer(typeId, naam, soort, prijs, beschikbaarheid);
                        } else if (soort.equals("webserver")) {
                            c = new Webserver(typeId, naam, soort, prijs, beschikbaarheid);
                        } else if (soort.equals("firewall")) {
                            c = new Firewall(typeId, naam, soort, prijs, beschikbaarheid);
                        }
                        if (c != null) {
                            configuratie.voegToeComponent(c);
                            tp.voegToeComponent(new Point(x, y), soort, c);
                        }

                    }
                }
            } catch (Exception error) {
                error.printStackTrace();
            }
        }
        if (e.getSource() == bd.jbVerwijder) {
            try {
                Database db = new Database("nerdygadgets", "monitoring", "Iloveberrit3!$");

                try {

                    int id = bd.geselecteerdeConfiguratieId;
                    System.out.println(id);
                    db.getConnection().setAutoCommit(false);
                    Query deleteQuery1 = new Query();
                    deleteQuery1.delete("configuratie_onderdeel").where("configuratie_id = " + id);
                    PreparedStatement ps1 = db.getConnection().prepareStatement(deleteQuery1.getQuery());
                    ps1.executeUpdate();
                    db.getConnection().commit();

                    Query deleteQuery = new Query();
                    deleteQuery.delete("configuratie").where("configuratie_id = " + id);
                    PreparedStatement ps = db.getConnection().prepareStatement(deleteQuery.getQuery());
                    ps.executeUpdate();
                    db.getConnection().commit();

                    db.getConnection().setAutoCommit(true);

                    // bd.jcbConfiguraties.remove(bd.jcbConfiguraties.getSelectedIndex());
                    bd.updateDropdown();
                    // db.delete("configuratie", "WHERE configuratie_id = " + id, new Object[] { id
                    // });
                    // db.delete("configuratie_onderdeel", "WHERE configuratie_id = " + id, new
                    // Object[] { id });
                } catch (SQLException ex) {
                    db.getConnection().rollback();

                }

            } catch (Exception err) {

                err.printStackTrace();
            }

        }

        bewerkGegevens();
    }

}
