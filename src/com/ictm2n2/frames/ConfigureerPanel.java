package com.ictm2n2.frames;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sound.sampled.SourceDataLine;
import javax.swing.*;

import com.ictm2n2.resources.Component;
import com.ictm2n2.resources.Componenten;
import com.ictm2n2.resources.Configuratie;
import com.ictm2n2.resources.DatabaseServer;
import com.ictm2n2.resources.Firewall;
import com.ictm2n2.resources.Webserver;
import com.ictm2n2.resources.database.Database;
import com.ictm2n2.resources.database.Query;
import com.ictm2n2.resources.dragdrop.DragDropComponent;
import com.ictm2n2.resources.dragdrop.DragDropDialoog;
import com.ictm2n2.resources.dragdrop.VerbindingComponent;

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

    private JButton jbMaakLeeg;

    private Configuratie configuratie;
    private Componenten componenten;

    private TekenPanel tp;
    private BeheerDialoog bd;

    public ConfigureerPanel(JFrame frame) {
        setLayout(null);

        bd = new BeheerDialoog();

        Componenten componenten = new Componenten();
        Configuratie configuratie = new Configuratie();

        this.componenten = componenten;
        this.configuratie = configuratie;

        tp = new TekenPanel(frame, this, this.configuratie);

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

        jbMaakLeeg = new JButton("Leeg maken");

        jcbWebServers.setBounds(10, 10, 200, 30);
        jcbDbServers.setBounds(10, 45, 200, 30);
        jcbFirewalls.setBounds(10, 80, 200, 30);

        jbWsVoegToe.setBounds(220, 10, 45, 30);
        jbDbVoegToe.setBounds(220, 45, 45, 30);
        jbFwVoegToe.setBounds(220, 80, 45, 30);

        jlTotaleBeschikbaarheid.setBounds(10, 180 + 180, 200, 30);
        jlTotalePrijs.setBounds(10, 200 + 180, 200, 30);

        jlPercentage.setBounds(10, 240 + 180, 130, 30);
        jtPercentage.setBounds(10, 270 + 180, 45, 25);
        jbOptimaliseer.setBounds(65, 270 + 180, 200, 25);

        jbOpslaan.setBounds(10, 495, 122, 30);
        jbBeheer.setBounds(142, 495, 122, 30);

        jbMaakLeeg.setBounds(165, 115, 100, 30);

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

        add(jbMaakLeeg);

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

        jbMaakLeeg.addActionListener(this);
    }

    public void bewerkGegevens() {
        jlTotaleBeschikbaarheid.setText("Totale Beschikbaarheid: " + configuratie.berekenBeschikbaarheid() + "%");
        jlTotalePrijs.setText("Totale Prijs: " + configuratie.berekenTotalePrijs());

        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tp.annuleerVerbinding();
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

                String naamConfiguratie = JOptionPane.showInputDialog(this, "Geef deze configuratie een naam", null);
                ArrayList<DragDropComponent> componenten = tp.getComponenten();

                Query insertQuery = new Query();
                insertQuery.insert("configuratie")
                        .columns(new Object[] { "beschikbaarheids_percentage", "naam", "prijs" })
                        .values(new Object[] { String.valueOf(configuratie.berekenBeschikbaarheid()), naamConfiguratie,
                                String.valueOf(configuratie.berekenTotalePrijsDouble()) });

                PreparedStatement ps = db.getConnection().prepareStatement(insertQuery.getQuery());
                ps.setDouble(1, configuratie.berekenBeschikbaarheid());
                ps.setString(2, naamConfiguratie);
                ps.setDouble(3, configuratie.berekenTotalePrijsDouble());
                ps.executeUpdate();

                Query q = new Query().LastInsertedIdConfiguratie();
                ResultSet rs = db.select(q);

                int id = 0;
                while (rs.next()) {
                    id = rs.getInt("configuratie_id");
                }

                for (DragDropComponent dragDropComponent : componenten) {
                    int componentId = dragDropComponent.getComponent().getId();
                    double x = dragDropComponent.getImageCorner().getX();
                    double y = dragDropComponent.getImageCorner().getY();

                    Query q1 = new Query();
                    q1.insert("configuratie_onderdeel")
                            .columns(new Object[] { "configuratie_id", "type_id", "positie_x", "positie_y" })
                            .values(new Object[] { id, componentId, x, y });
                    PreparedStatement ps1 = db.getConnection().prepareStatement(q1.getQuery());
                    ps1.setInt(1, id);
                    ps1.setInt(2, componentId);
                    ps1.setInt(3, (int) x);
                    ps1.setInt(4, (int) y);
                    ps1.executeUpdate();

                    Query idQuery = new Query().LastInsertedIdOnderdeel();
                    ResultSet rs1 = db.select(idQuery);
                    int onderdeel_id = 0;
                    while (rs1.next()) {
                        onderdeel_id = rs1.getInt("onderdeel_id");
                    }

                    if (dragDropComponent.getDialoog().checkContent()) {
                        DragDropDialoog d = dragDropComponent.getDialoog();
                        Query q2 = new Query();
                        Object[] columns = new Object[] { "onderdeel_id", "IPv4_adres", "IPv4_subnet", "IPv4_gateway",
                                "IPv4_dns", "IPv6_adres", "IPv6_linklocal", "IPv6_gateway", "IPv6_dns", };
                        q2.insert("configuratie_eigenschap").columns(columns).values(columns);
                        PreparedStatement ps2 = db.getConnection().prepareStatement(q2.getQuery());
                        ps2.setInt(1, onderdeel_id);
                        ps2.setString(2, d.getIPv4Adres());
                        ps2.setString(3, d.getIPv4Subnet());
                        ps2.setString(4, d.getIPv4Gateway());
                        ps2.setString(5, d.getIPv4DNSServer());
                        ps2.setString(6, d.getIPv6Adres());
                        ps2.setString(7, d.getIPv6LinkLocal());
                        ps2.setString(8, d.getIPv6Gateway());
                        ps2.setString(9, d.getIPv6DNSServer());
                        ps2.executeUpdate();
                    }

                }

                for (VerbindingComponent verbindingComponent : tp.getVerbindingen()) {
                    Query verbindingQuery = new Query();
                    Object[] verbindingColumns = new Object[] { "configuratie_id", "begin_positie_x", "begin_positie_y",
                            "eind_positie_x", "eind_positie_y", };
                    verbindingQuery.insert("configuratie_positie").columns(verbindingColumns).values(verbindingColumns);
                    System.out.println(verbindingQuery.getQuery());
                    PreparedStatement ps3 = db.getConnection().prepareStatement(verbindingQuery.getQuery());
                    ps3.setInt(1, id);
                    ps3.setInt(2, (int) verbindingComponent.getBeginPositie().getX());
                    ps3.setInt(3, (int) verbindingComponent.getBeginPositie().getY());
                    ps3.setInt(4, (int) verbindingComponent.getEindPositie().getX());
                    ps3.setInt(5, (int) verbindingComponent.getEindPositie().getY());
                    ps3.executeUpdate();

                }

            } catch (Exception a) {
                System.err.println("Fout met opslaan van configuratie");
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

                Query yeet = new Query().SelectOnderdeel(bd.geselecteerdeConfiguratieId);
                ResultSet rs2 = db.select(yeet);
                while (rs2.next()) {
                    String ipv4Adres = rs2.getString("IPv4_adres");
                    String ipv4Subnet = rs2.getString("IPv4_subnet");
                    String ipv4Gateway = rs2.getString("IPv4_gateway");
                    String ipv4Dns = rs2.getString("IPv4_dns");
                    String ipv6Adres = rs2.getString("IPv6_adres");
                    String ipv6LinkLocal = rs2.getString("IPv6_linklocal");
                    String ipv6Gateway = rs2.getString("IPv6_gateway");
                    String ipv6Dns = rs2.getString("IPv6_dns");

                    int x = rs2.getInt("positie_x");
                    int y = rs2.getInt("positie_y");
                    int typeId = rs2.getInt("type_id");

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
                            Point location = new Point(x, y);
                            configuratie.voegToeComponent(c);
                            // tp.voegToeComponent(location, soort, c);
                            ImageIcon plaatje = new ImageIcon("src/com/ictm2n2/assets/" + soort + ".png");

                            DragDropComponent ddc = new DragDropComponent(c, location, plaatje, tp.getSize(), tp,
                                    ipv4Adres, ipv4Subnet, ipv4Gateway, ipv4Dns, ipv6Adres, ipv6LinkLocal, ipv6Gateway,
                                    ipv6Dns);
                            tp.voegToeComponent(ddc);
                        }
                    }
                }
                Query positiesQuery = new Query();
                positiesQuery.select(null).from("configuratie_positie")
                        .where("configuratie_id = " + bd.geselecteerdeConfiguratieId);
                ResultSet rs3 = db.select(positiesQuery);
                while (rs3.next()) {
                    DragDropComponent beginComponent = null;
                    DragDropComponent eindComponent = null;
                    for (DragDropComponent dragDropComponent : tp.getComponenten()) {
                        if ((int) dragDropComponent.getImageCorner().getX() == rs3.getInt("begin_positie_x") - 50
                                && (int) dragDropComponent.getImageCorner().getY() == rs3.getInt("begin_positie_y")
                                        - 50) {
                            beginComponent = dragDropComponent;
                        }
                        if ((int) dragDropComponent.getImageCorner().getX() == rs3.getInt("eind_positie_x") - 50
                                && (int) dragDropComponent.getImageCorner().getY() == rs3.getInt("eind_positie_y")
                                        - 50) {
                            eindComponent = dragDropComponent;
                        }
                    }
                    tp.setVanComponent(beginComponent);
                    tp.voegToeVerbinding(eindComponent);
                }
            } catch (Exception error) {
                error.printStackTrace();
            }
        }
        if (e.getSource() == bd.jbVerwijder) {

        }

        if (e.getSource() == jbMaakLeeg) {
            // for (DragDropComponent dragDropComponent : tp.getComponenten()) {
            // tp.verwijderComponent(dragDropComponent);

            // }
            for (int i = (tp.getComponenten().size() - 1); i >= 0; i--) {
                tp.verwijderComponent(tp.getComponenten().get(i));
                // tp.getComponenten().remove(tp.getComponenten().get(i));
            }
        }
        bewerkGegevens();
    }

}
