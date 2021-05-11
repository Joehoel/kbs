package com.ictm2n2.frames;

import com.ictm2n2.resources.database.Database;
import com.ictm2n2.resources.database.Query;
import javax.swing.*;

import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MonitorPanel extends JPanel {
    private JLabel jlDb;
    private JLabel jlWb;
    private JLabel jlLb;
    private JLabel jlPfS;
    private JLabel jlDetailOverzicht;

    private JLabel jlDetailOverzichtWaarden;
    private JPanel jpDetailOverzichtWaarden;

    private ArrayList<String> DbHostnames = new ArrayList<String>();
    private ArrayList<String> DbCpu = new ArrayList<String>();
    private ArrayList<String> DbOpslag = new ArrayList<String>();
    private ArrayList<String> DbAangesloten = new ArrayList<String>();
    private ArrayList<String> WbHostnames = new ArrayList<String>();
    private ArrayList<String> WbCpu = new ArrayList<String>();
    private ArrayList<String> WbOpslag = new ArrayList<String>();
    private ArrayList<String> WbAangesloten = new ArrayList<String>();
    private ArrayList<String> LbHostnames = new ArrayList<String>();
    private ArrayList<String> LbCpu = new ArrayList<String>();
    private ArrayList<String> LbOpslag = new ArrayList<String>();
    private ArrayList<String> LbAangesloten = new ArrayList<String>();
    private ArrayList<String> PfSHostnames = new ArrayList<String>();
    private ArrayList<String> PfSCpu = new ArrayList<String>();
    private ArrayList<String> PfSOpslag = new ArrayList<String>();
    private ArrayList<String> PfSAangesloten = new ArrayList<String>();

    private String detailOverzichtWaarden = "";

    public MonitorPanel() {
        setLayout(null);

        //lijsten maken voor component types
        DefaultListModel<String> dlDbModel = new DefaultListModel<>();
        JList DbList = new JList<>(dlDbModel);
        DbList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DbList.setLayoutOrientation(JList.VERTICAL_WRAP);

        DefaultListModel<String> dlWbModel = new DefaultListModel<>();
        JList WbList = new JList<>(dlWbModel);
        WbList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        WbList.setLayoutOrientation(JList.VERTICAL_WRAP);

        DefaultListModel<String> dlLbModel = new DefaultListModel<>();
        JList LbList = new JList<>(dlLbModel);
        LbList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        LbList.setLayoutOrientation(JList.VERTICAL_WRAP);

        DefaultListModel<String> dlPfSModel = new DefaultListModel<>();
        JList PfSList = new JList<>(dlPfSModel);
        PfSList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        PfSList.setLayoutOrientation(JList.VERTICAL_WRAP);

        //databaseservers ophalen en in array stoppen
                try {
            Database db = new Database("nerdygadgets", "monitoring", "Iloveberrit3!$");
            Query q = new Query();
            q = q.DbMonitorPanelQuery();
            ResultSet rs = db.preparedQuery(q);

            try {
                while (rs.next()) {
                    DbHostnames.add(rs.getString("c.hostname"));
                    DbCpu.add(String.valueOf(rs.getDouble("c.cpu")));
                    DbOpslag.add(String.valueOf(rs.getDouble("c.opslag")));
                    if (rs.getString("beschikbaar").equals("1")) {
                        DbAangesloten.add("aangesloten");
                    } else {
                        DbAangesloten.add("niet aangesloten");
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (Exception a) {
            a.printStackTrace();
        }
        System.out.println(DbHostnames.size()+" "+DbCpu.size()+" "+DbOpslag.size());

        //webservers ophalen en in array stoppen
        try {
            Database db = new Database("nerdygadgets", "monitoring", "Iloveberrit3!$");
            Query q = new Query();
            q = q.WbMonitorPanelQuery();
            ResultSet rs = db.preparedQuery(q);

            try {
                while (rs.next()) {
                    WbHostnames.add(rs.getString("c.hostname"));
                    WbCpu.add(String.valueOf(rs.getDouble("c.cpu")));
                    WbOpslag.add(String.valueOf(rs.getDouble("c.opslag")));
                    if (rs.getString("beschikbaar").equals("1")) {
                        WbAangesloten.add("aangesloten");
                    } else {
                        WbAangesloten.add("niet aangesloten");
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (Exception a) {
            a.printStackTrace();
        }
        System.out.println(WbHostnames.size()+" "+WbCpu.size()+" "+WbOpslag.size());

        //loadbalancer ophalen en in array stoppen
        try {
            Database db = new Database("nerdygadgets", "monitoring", "Iloveberrit3!$");
            Query q = new Query();
            q = q.LbMonitorPanelQuery();
            ResultSet rs = db.preparedQuery(q);

            try {
                while (rs.next()) {
                    LbHostnames.add(rs.getString("c.hostname"));
                    LbCpu.add(String.valueOf(rs.getDouble("c.cpu")));
                    LbOpslag.add(String.valueOf(rs.getDouble("c.opslag")));
                    if (rs.getString("beschikbaar").equals("1")) {
                        LbAangesloten.add("aangesloten");
                    } else {
                        LbAangesloten.add("niet aangesloten");
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (Exception a) {
            a.printStackTrace();
        }
        System.out.println(LbHostnames.size()+" "+LbCpu.size()+" "+LbOpslag.size());

        //pfsense ophalen en in array stoppen
        try {
            Database db = new Database("nerdygadgets", "monitoring", "Iloveberrit3!$");
            Query q = new Query();
            q = q.PfSMonitorPanelQuery();
            ResultSet rs = db.preparedQuery(q);

            try {
                while (rs.next()) {
                    PfSHostnames.add(rs.getString("c.hostname"));
                    PfSCpu.add(String.valueOf(rs.getDouble("c.cpu")));
                    PfSOpslag.add(String.valueOf(rs.getDouble("c.opslag")));
                    if (rs.getString("beschikbaar").equals("1")) {
                        PfSAangesloten.add("aangesloten");
                    } else {
                        PfSAangesloten.add("niet aangesloten");
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (Exception a) {
            a.printStackTrace();
        }
        System.out.println(PfSHostnames.size()+" "+PfSCpu.size()+" "+PfSOpslag.size());


        //database servers toevoegen aan jlist
        int i = 0;
        while (i < DbHostnames.size()) {
            String element = "<html><strong>Hostname: "+String.format(DbHostnames.get(i))+" </strong><br>"+
                    DbCpu.get(i)+" GHz <br>"+
                    DbOpslag.get(i)+" GB <br>";

            if (DbAangesloten.get(i).equals("aangesloten")) {
                element += "<i> <p style =\"color:green\">"+DbAangesloten.get(i)+" </p></i></html>";
            } else {
                element += "<i> <p style =\"color:red\">"+DbAangesloten.get(i)+" </p></i></html>";
            }
            dlDbModel.addElement(element);
            i++;
        }

        //webservers toevoegen aan jlist
        i = 0;
        while (i < WbHostnames.size()) {
            String element = "<html><strong>Hostname: "+String.format(WbHostnames.get(i))+"</strong><br>"+
                    WbCpu.get(i)+" GHz<br>"+
                    WbOpslag.get(i)+" GB<br>";

            if (WbAangesloten.get(i).equals("aangesloten")) {
                element += "<i> <p style =\"color:green\">"+WbAangesloten.get(i)+"</p></i></html>";
            } else {
                element += "<i> <p style =\"color:red\">"+WbAangesloten.get(i)+"</p></i></html>";
            }
            dlWbModel.addElement(element);
            i++;
        }

        //loadbalancer toevoegen aan jlist
        i = 0;
        while (i < LbHostnames.size()) {
            String element = "<html><strong>Hostname: "+String.format(LbHostnames.get(i))+"</strong><br>"+
                    LbCpu.get(i)+" GHz<br>"+
                    LbOpslag.get(i)+" GB<br>";

            if (LbAangesloten.get(i).equals("aangesloten")) {
                element += "<i> <p style =\"color:green\">"+LbAangesloten.get(i)+"</p></i></html>";
            } else {
                element += "<i> <p style =\"color:red\">"+LbAangesloten.get(i)+"</p></i></html>";
            }
            dlLbModel.addElement(element);
            i++;
        }

        //pfsense toevoegen aan jlist
        i = 0;
        while (i < PfSHostnames.size()) {
            String element = "<html><strong>Hostname: "+String.format(PfSHostnames.get(i))+"</strong><br>"+
                    PfSCpu.get(i)+" GHz<br>"+
                    PfSOpslag.get(i)+" GB<br>";

            if (PfSAangesloten.get(i).equals("aangesloten")) {
                element += "<i> <p style =\"color:green\">"+PfSAangesloten.get(i)+"</p></i></html>";
            } else {
                element += "<i> <p style =\"color:red\">"+PfSAangesloten.get(i)+"</p></i></html>";
            }
            dlPfSModel.addElement(element);
            i++;
        }

        jlDb = new JLabel("Databases");
        jlWb = new JLabel("Webservers");
        jlLb = new JLabel("Load-Balancer");
        jlPfS= new JLabel("PfSense");
        jlDetailOverzicht = new JLabel("Gedetailleerd Overzicht");
        jlDetailOverzichtWaarden = new JLabel(detailOverzichtWaarden);
        jpDetailOverzichtWaarden = new JPanel();

        add(jlDb);
        jlDb.setBounds(25,20,270,20);
        DbList.setBounds(25, 50, 270, 465);
        DbList.setFixedCellWidth(270);
        DbList.setFixedCellHeight(80);
        add(DbList);

        add(jlWb);
        jlWb.setBounds(305,20,270,20);
        WbList.setBounds(305,50,270,465);
        WbList.setFixedCellWidth(270);
        WbList.setFixedCellHeight(80);
        add(WbList);

        add(jlLb);
        jlLb.setBounds(585,20,270,20);
        LbList.setBounds(585,50,270,80);
        LbList.setFixedCellWidth(270);
        LbList.setFixedCellHeight(80);
        add(LbList);

        add(jlPfS);
        jlPfS.setBounds(585,150,270,20);
        PfSList.setBounds(585,180,270,80);
        PfSList.setFixedCellWidth(270);
        PfSList.setFixedCellHeight(80);
        add(PfSList);

        add(jlDetailOverzicht);
        jlDetailOverzicht.setBounds(585,280,270,20);
        jpDetailOverzichtWaarden = new JPanel();
        jpDetailOverzichtWaarden.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpDetailOverzichtWaarden.add(jlDetailOverzichtWaarden);
        jlDetailOverzichtWaarden.setBounds(585,310,270,205);
        jpDetailOverzichtWaarden.setBackground(Color.white);
        jpDetailOverzichtWaarden.setBounds(585,310,270,205);
        add(jpDetailOverzichtWaarden);

        //selectionlistener voor databasecomponenten
        DbList.getSelectionModel().addListSelectionListener(e -> {
            if (DbList.getValueIsAdjusting()) {
                //geselecteerde component wordt in geheugen opgeslagen en vervormt tot juiste formaat
                Object selectedComponent = DbList.getSelectedValue();
                String selectedComponentMinHTML = String.valueOf(selectedComponent).replaceAll("\\<.*?\\>", "");
                String[] selectedComponentSplit = selectedComponentMinHTML.split(" ");
                String hostname = selectedComponentSplit[1];
                System.out.println(hostname);

                //gedetaileerde informatie over component wordt opgevraagd uit database
                try {
                    Database db = new Database("nerdygadgets", "monitoring", "Iloveberrit3!$");
                    Query q = new Query();
                    q = q.DetailOverzichtMonitorPanelQuery(hostname);
                    ResultSet rs = db.preparedQuery(q);

                    // en natuurlijk opgeslagen om het vervolgens te kunnen weergeven in de GUI :)
                    try {
                        while (rs.next()) {
                            double processor = rs.getDouble("c.cpu");
                            double processorBelasting = rs.getDouble("s.processor_belasting");
                            double opslag = rs.getDouble("c.opslag");
                            double opslagVerbruik = rs.getDouble("s.opslag_verbruik");
                            int beschikbaarLengte = (int) rs.getDouble("beschikbaar_lengte");
                            String tijdstip = rs.getString("s.tijdstip");
                            //System.out.println(opslag+"\n"+opslagVerbruik+"\n"+beschikbaarLengte+"\n"+tijdstip);

                            detailOverzichtWaarden = "<html><strong>Hostname: " + hostname + "</strong><br>" +
                                    processor + " GHz<br>" +
                                    processorBelasting + " GHz<br>" +
                                    opslag + " GB<br>" +
                                    opslagVerbruik + " GB<br>" +
                                    (beschikbaarLengte / 60) + " minuten<br>sinds " +
                                    tijdstip+"</html>";
                            jlDetailOverzichtWaarden.setText(detailOverzichtWaarden);
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                } catch (Exception a) {
                    a.printStackTrace();
                }
            }
        });

        //selectionlistener voor webservercomponenten
        WbList.getSelectionModel().addListSelectionListener(e -> {
            if (WbList.getValueIsAdjusting()) {
                //geselecteerde component wordt in geheugen opgeslagen en vervormt tot juiste formaat
                Object selectedComponent = WbList.getSelectedValue();
                String selectedComponentMinHTML = String.valueOf(selectedComponent).replaceAll("\\<.*?\\>", "");
                String[] selectedComponentSplit = selectedComponentMinHTML.split(" ");
                String hostname = selectedComponentSplit[1];
                System.out.println(hostname);

                //gedetaileerde informatie over component wordt opgevraagd uit database
                try {
                    Database db = new Database("nerdygadgets", "monitoring", "Iloveberrit3!$");
                    Query q = new Query();
                    q = q.DetailOverzichtMonitorPanelQuery(hostname);
                    ResultSet rs = db.preparedQuery(q);

                    // en natuurlijk opgeslagen om het vervolgens te kunnen weergeven in de GUI :)
                    try {
                        while (rs.next()) {
                            double processor = rs.getDouble("c.cpu");
                            double processorBelasting = rs.getDouble("s.processor_belasting");
                            double opslag = rs.getDouble("c.opslag");
                            double opslagVerbruik = rs.getDouble("s.opslag_verbruik");
                            int beschikbaarLengte = (int) rs.getDouble("beschikbaar_lengte");
                            String tijdstip = rs.getString("s.tijdstip");
                            //System.out.println(opslag+"\n"+opslagVerbruik+"\n"+beschikbaarLengte+"\n"+tijdstip);

                            detailOverzichtWaarden = "<html><strong>Hostname: " + hostname + "</strong><br>" +
                                    processor + " GHz<br>" +
                                    processorBelasting + " GHz<br>" +
                                    opslag + " GB<br>" +
                                    opslagVerbruik + " GB<br>" +
                                    (beschikbaarLengte / 60) + " minuten<br>sinds " +
                                    tijdstip+"</html>";
                            jlDetailOverzichtWaarden.setText(detailOverzichtWaarden);
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                } catch (Exception a) {
                    a.printStackTrace();
                }
            }
        });

        //selectionlistener voor loadbalancer
        LbList.getSelectionModel().addListSelectionListener(e -> {
            if (LbList.getValueIsAdjusting()) {
                //geselecteerde component wordt in geheugen opgeslagen en vervormt tot juiste formaat
                Object selectedComponent = LbList.getSelectedValue();
                String selectedComponentMinHTML = String.valueOf(selectedComponent).replaceAll("\\<.*?\\>", "");
                String[] selectedComponentSplit = selectedComponentMinHTML.split(" ");
                String hostname = selectedComponentSplit[1];
                System.out.println(hostname);

                //gedetaileerde informatie over component wordt opgevraagd uit database
                try {
                    Database db = new Database("nerdygadgets", "monitoring", "Iloveberrit3!$");
                    Query q = new Query();
                    q = q.DetailOverzichtMonitorPanelQuery(hostname);
                    ResultSet rs = db.preparedQuery(q);

                    // en natuurlijk opgeslagen om het vervolgens te kunnen weergeven in de GUI :)
                    try {
                        while (rs.next()) {
                            double processor = rs.getDouble("c.cpu");
                            double processorBelasting = rs.getDouble("s.processor_belasting");
                            double opslag = rs.getDouble("c.opslag");
                            double opslagVerbruik = rs.getDouble("s.opslag_verbruik");
                            int beschikbaarLengte = (int) rs.getDouble("beschikbaar_lengte");
                            String tijdstip = rs.getString("s.tijdstip");
                            //System.out.println(opslag+"\n"+opslagVerbruik+"\n"+beschikbaarLengte+"\n"+tijdstip);

                            detailOverzichtWaarden = "<html><strong>Hostname: " + hostname + "</strong><br>" +
                                    processor + " GHz<br>" +
                                    processorBelasting + " GHz<br>" +
                                    opslag + " GB<br>" +
                                    opslagVerbruik + " GB<br>" +
                                    (beschikbaarLengte / 60) + " minuten<br>sinds " +
                                    tijdstip+"</html>";
                            jlDetailOverzichtWaarden.setText(detailOverzichtWaarden);
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                } catch (Exception a) {
                    a.printStackTrace();
                }
            }
        });

        //selectionlistener voor firewall
        PfSList.getSelectionModel().addListSelectionListener(e -> {
            if (PfSList.getValueIsAdjusting()) {
                //geselecteerde component wordt in geheugen opgeslagen en vervormt tot juiste formaat
                Object selectedComponent = PfSList.getSelectedValue();
                String selectedComponentMinHTML = String.valueOf(selectedComponent).replaceAll("\\<.*?\\>", "");
                String[] selectedComponentSplit = selectedComponentMinHTML.split(" ");
                String hostname = selectedComponentSplit[1];
                System.out.println(hostname);

                //gedetaileerde informatie over component wordt opgevraagd uit database
                try {
                    Database db = new Database("nerdygadgets", "monitoring", "Iloveberrit3!$");
                    Query q = new Query();
                    q = q.DetailOverzichtMonitorPanelQuery(hostname);
                    ResultSet rs = db.preparedQuery(q);

                    // en natuurlijk opgeslagen om het vervolgens te kunnen weergeven in de GUI :)
                    try {
                        while (rs.next()) {
                            double processor = rs.getDouble("c.cpu");
                            double processorBelasting = rs.getDouble("s.processor_belasting");
                            double opslag = rs.getDouble("c.opslag");
                            double opslagVerbruik = rs.getDouble("s.opslag_verbruik");
                            int beschikbaarLengte = (int) rs.getDouble("beschikbaar_lengte");
                            String tijdstip = rs.getString("s.tijdstip");
                            //System.out.println(opslag+"\n"+opslagVerbruik+"\n"+beschikbaarLengte+"\n"+tijdstip);

                            detailOverzichtWaarden = "<html><strong>Hostname: " + hostname + "</strong><br>" +
                                    processor + " GHz<br>momenteel " +
                                    processorBelasting + " GHz in gebruik<br><br>" +
                                    opslag + " GB opslag<br>" +
                                    opslagVerbruik + " GB verbruikt<br><br>" +
                                    (beschikbaarLengte / 60) + " minuten in bedrijf<br>sinds " +
                                    tijdstip+"</html>";
                            jlDetailOverzichtWaarden.setText(detailOverzichtWaarden);
                            //PfSList.setSelectionInterval(-1, -1);
                            //PfSList.setEnabled(false);
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                } catch (Exception a) {
                    a.printStackTrace();
                }
            }
        });
    }
}
