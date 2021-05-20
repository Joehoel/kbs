package com.ictm2n2.frames;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

import com.ictm2n2.resources.database.Database;
import com.ictm2n2.resources.database.Query;
import com.ictm2n2.resources.database.SendPingRequest;

public class MonitorPanel extends JPanel implements ActionListener {
    private JLabel jlDb;
    private JLabel jlWb;
    private JLabel jlPfS;
    private JLabel jlStatus;
    private JLabel jlDetailOverzicht;

    private JButton jbStatus;

    private JLabel jlDetailOverzichtWaarden;
    private JPanel jpDetailOverzichtWaarden;

    private ArrayList<String> DbHostnames = new ArrayList<String>();
    private ArrayList<String> DbCpu = new ArrayList<String>();
    private ArrayList<String> DbOpslag = new ArrayList<String>();
    private ArrayList<String> DbAangesloten = new ArrayList<String>();
    private Timestamp DbTijdstip;

    private ArrayList<String> WbHostnames = new ArrayList<String>();
    private ArrayList<String> WbCpu = new ArrayList<String>();
    private ArrayList<String> WbOpslag = new ArrayList<String>();
    private ArrayList<String> WbAangesloten = new ArrayList<String>();
    private Timestamp WbTijdstip;

    private ArrayList<String> PfSHostnames = new ArrayList<String>();
    private ArrayList<String> PfSCpu = new ArrayList<String>();
    private ArrayList<String> PfSOpslag = new ArrayList<String>();
    private ArrayList<String> PfSAangesloten = new ArrayList<String>();
    private Timestamp PfSTijdstip;

    private Timestamp localTime = new Timestamp(System.currentTimeMillis());

    private byte[] ipDb1 = {(byte) 172, 16, 1, 2};
    private byte[] ipDb2 = {(byte) 172, 16, 1, 3};
    private byte[] ipWb1 = {(byte) 172, 16, 0, 2};
    private byte[] ipWb2 = {(byte) 172, 16, 0, 3};
    private byte[] ipPfS = {(byte) 172, 16, 0, 1};

    private SendPingRequest sprDb1 = new SendPingRequest("Database 1", ipDb1);
    private SendPingRequest sprDb2 = new SendPingRequest("Database 2", ipDb2);
    private SendPingRequest sprWb1 = new SendPingRequest("Webserver 1", ipWb1);
    private SendPingRequest sprWb2 = new SendPingRequest("Webserver 2", ipWb2);
    private SendPingRequest sprPfS = new SendPingRequest("PfSense", ipPfS);

    private boolean isBereikbaarDb1 = false;
    private boolean isBereikbaarDb2 = false;
    private boolean isBereikbaarWb1 = false;
    private boolean isBereikbaarWb2 = false;
    private boolean isBereikbaarPfS = false;

    private String detailOverzichtWaarden = "";

    public MonitorPanel() {
        setLayout(null);

        // lijsten maken voor component types
        DefaultListModel<String> dlDbModel = new DefaultListModel<>();
        JList DbList = new JList<>(dlDbModel);
        DbList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DbList.setLayoutOrientation(JList.VERTICAL_WRAP);

        DefaultListModel<String> dlWbModel = new DefaultListModel<>();
        JList WbList = new JList<>(dlWbModel);
        WbList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        WbList.setLayoutOrientation(JList.VERTICAL_WRAP);

        DefaultListModel<String> dlPfSModel = new DefaultListModel<>();
        JList PfSList = new JList<>(dlPfSModel);
        PfSList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        PfSList.setLayoutOrientation(JList.VERTICAL_WRAP);

        // timer toevoegen die moet zorgen dat de list altijd up-to-date blijft (iedere
        // 60 seconden)
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
                           @Override
                           public void run() {
 //                               databaseservers ophalen en in array stoppen
                               try {
                                   Database db = new Database("nerdygadgets", "monitoring", "Iloveberrit3!$");
                                   Query q = new Query();
                                   q = q.DbMonitorPanelQuery();
                                   ResultSet rs = db.preparedQuery(q);

                                   try {
                                       // eerst alle arraylists leegmaken indien deze al eens gevuld zijn door een query
                                       DbHostnames.clear();
                                       DbCpu.clear();
                                       DbOpslag.clear();
                                       DbAangesloten.clear();

                                       while (rs.next()) {
                                           DbHostnames.add(rs.getString("c.hostname"));
                                           DbCpu.add(String.valueOf(rs.getDouble("c.cpu")));
                                           DbOpslag.add(String.valueOf(rs.getDouble("c.opslag")));
                                           DbTijdstip = (rs.getTimestamp("s.tijdstip"));
                                           System.out.println(localTime.getTime()-DbTijdstip.getTime());

                                           if ((localTime.getTime()-DbTijdstip.getTime()) > 5000000) {
                                               DbAangesloten.add("niet aangesloten");
                                           } else {
                                               DbAangesloten.add("aangesloten");
                                           }
                                       }
                                   } catch (SQLException throwables) {
                                       throwables.printStackTrace();
                                   }
                               } catch (Exception a) {
                                   a.printStackTrace();
                               }
                               System.out.println(DbHostnames.size() + " " + DbCpu.size() + " " + DbOpslag.size());

                               // webservers ophalen en in array stoppen
                               try {
                                   Database db = new Database("nerdygadgets", "monitoring", "Iloveberrit3!$");
                                   Query q = new Query();
                                   q = q.WbMonitorPanelQuery();
                                   ResultSet rs = db.preparedQuery(q);

                                   try {
                                       // eerst alle arraylists leegmaken indien deze al eens gevuld zijn door een query
                                       WbHostnames.clear();
                                       WbCpu.clear();
                                       WbOpslag.clear();
                                       WbAangesloten.clear();

                                       while (rs.next()) {
                                           WbHostnames.add(rs.getString("c.hostname"));
                                           WbCpu.add(String.valueOf(rs.getDouble("c.cpu")));
                                           WbOpslag.add(String.valueOf(rs.getDouble("c.opslag")));
                                           WbTijdstip = (rs.getTimestamp("s.tijdstip"));
                                           System.out.println(localTime.getTime()-WbTijdstip.getTime());

                                           if ((localTime.getTime()-WbTijdstip.getTime()) > 5000000) {
                                               WbAangesloten.add("niet aangesloten");
                                           } else {
                                               WbAangesloten.add("aangesloten");
                                           }
                                       }
                                   } catch (SQLException throwables) {
                                       throwables.printStackTrace();
                                   }
                               } catch (Exception a) {
                                   a.printStackTrace();
                               }
                               System.out.println(WbHostnames.size() + " " + WbCpu.size() + " " + WbOpslag.size());

                               // pfsense ophalen en in array stoppen
                               try {
                                   Database db = new Database("nerdygadgets", "monitoring", "Iloveberrit3!$");
                                   Query q = new Query();
                                   q = q.PfSMonitorPanelQuery();
                                   ResultSet rs = db.preparedQuery(q);

                                   try {
                                       // eerst alle arraylists leegmaken indien deze al eens gevuld zijn door een query
                                       PfSHostnames.clear();
                                       PfSCpu.clear();
                                       PfSOpslag.clear();
                                       PfSAangesloten.clear();

                                       while (rs.next()) {
                                           PfSHostnames.add(rs.getString("c.hostname"));
                                           PfSCpu.add(String.valueOf(rs.getDouble("c.cpu")));
                                           PfSOpslag.add(String.valueOf(rs.getDouble("c.opslag")));
                                           PfSTijdstip = (rs.getTimestamp("s.tijdstip"));
                                           System.out.println(localTime.getTime()-PfSTijdstip.getTime());

                                           if ((localTime.getTime()-PfSTijdstip.getTime()) > 5000000) {
                                               PfSAangesloten.add("niet aangesloten");
                                           } else {
                                               PfSAangesloten.add("aangesloten");
                                           }
                                       }
                                   } catch (SQLException throwables) {
                                       throwables.printStackTrace();
                                   }
                               } catch (Exception a) {
                                   a.printStackTrace();
                               }
                               System.out.println(PfSHostnames.size() + " " + PfSCpu.size() + " " + PfSOpslag.size());

                               //eerst zorgen dat de defaultlistmodel leeg is
                                dlDbModel.clear();

                               // database servers toevoegen aan jlist
                               int i = 0;
                               while (i < DbHostnames.size()) {
                                   String element = "<html><strong>Hostname: " + String.format(DbHostnames.get(i)) + " </strong><br>"
                                           + DbCpu.get(i) + " GHz <br>" + DbOpslag.get(i) + " GB <br>";

                                   if (DbAangesloten.get(i).equals("aangesloten")) {
                                       element += "<i> <p style =\"color:green\">" + DbAangesloten.get(i) + " </p></i></html>";
                                   } else {
                                       element += "<i> <p style =\"color:red\">" + DbAangesloten.get(i) + " </p></i></html>";
                                   }
                                   dlDbModel.addElement(element);
                                   i++;
                               }

                               //eerst zorgen dat de defaultlistmodel leeg is
                                dlWbModel.clear();

                               // webservers toevoegen aan jlist
                               i = 0;
                               while (i < WbHostnames.size()) {
                                   String element = "<html><strong>Hostname: " + String.format(WbHostnames.get(i)) + " </strong><br>"
                                           + WbCpu.get(i) + " GHz <br>" + WbOpslag.get(i) + " GB<br>";

                                   if (WbAangesloten.get(i).equals("aangesloten")) {
                                       element += "<i> <p style =\"color:green\">" + WbAangesloten.get(i) + "</p></i></html>";
                                   } else {
                                       element += "<i> <p style =\"color:red\">" + WbAangesloten.get(i) + "</p></i></html>";
                                   }
                                   dlWbModel.addElement(element);
                                   i++;
                               }

                                //eerst zorgen dat de defaultlistmodel leeg is
                                dlPfSModel.clear();

                               // pfsense toevoegen aan jlist
                               i = 0;
                               while (i < PfSHostnames.size()) {
                                   String element = "<html><strong>Hostname: " + String.format(PfSHostnames.get(i)) + " </strong><br>"
                                           + PfSCpu.get(i) + " GHz <br>" + PfSOpslag.get(i) + " GB<br>";

                                   if (PfSAangesloten.get(i).equals("aangesloten")) {
                                       element += "<i> <p style =\"color:green\">" + PfSAangesloten.get(i) + "</p></i></html>";
                                   } else {
                                       element += "<i> <p style =\"color:red\">" + PfSAangesloten.get(i) + "</p></i></html>";
                                   }
                                   dlPfSModel.addElement(element);
                                   i++;
                               }

                               //zorgen dat alle componenten gepingt worden voor troubleshooting
                               try {
                                   isBereikbaarDb1 = sprDb1.sendPingRequest();
                                   isBereikbaarDb2 = sprDb2.sendPingRequest();
                                   isBereikbaarWb1 = sprWb1.sendPingRequest();
                                   isBereikbaarWb2 = sprWb2.sendPingRequest();
                                   isBereikbaarPfS = sprPfS.sendPingRequest();
                               }  catch (UnknownHostException e) {
                                   e.printStackTrace();
                               } catch (IOException e) {
                                   e.printStackTrace();
                               }

                               if (isBereikbaarDb1 && isBereikbaarDb2 && isBereikbaarWb1 && isBereikbaarWb2 && isBereikbaarPfS) {
                                   jlStatus.setText("<html><p style=\"color:green\">&#x2714; Status bereikbaarheid</p></html>");
                               } else {
                                   jlStatus.setText("<html><p style=\"color:red\">&#10060; Status bereikbaarheid</p></html>");
                               }
                           }
                       }, 0, 5000);

        jlDb = new JLabel("Databases");
        jlWb = new JLabel("Webservers");
        jlPfS = new JLabel("PfSense");
        jlStatus = new JLabel("<html><p style=\"color:red\">&#10060; Status bereikbaarheid</p></html>");
        jlDetailOverzicht = new JLabel("Gedetailleerd Overzicht");
        jlDetailOverzichtWaarden = new JLabel(detailOverzichtWaarden);
        jpDetailOverzichtWaarden = new JPanel();

        jbStatus = new JButton("Bekijk status per component");

        add(jlDb);
        jlDb.setBounds(25, 20, 270, 20);
        DbList.setBounds(25, 50, 270, 465);
        DbList.setFixedCellWidth(270);
        DbList.setFixedCellHeight(80);
        add(DbList);

        add(jlWb);
        jlWb.setBounds(305, 20, 270, 20);
        WbList.setBounds(305, 50, 270, 465);
        WbList.setFixedCellWidth(270);
        WbList.setFixedCellHeight(80);
        add(WbList);

        add(jlPfS);
        jlPfS.setBounds(585, 20, 270, 20);
        PfSList.setBounds(585, 50, 270, 80);
        PfSList.setFixedCellWidth(270);
        PfSList.setFixedCellHeight(80);
        add(PfSList);

        add(jlStatus);
        jlStatus.setBounds(585, 150, 270, 20);
        jbStatus.setBounds(585, 180, 270, 80);
        add(jbStatus);
        jbStatus.addActionListener(this);

        add(jlDetailOverzicht);
        jlDetailOverzicht.setBounds(585, 280, 270, 20);
        jpDetailOverzichtWaarden = new JPanel();
        jpDetailOverzichtWaarden.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpDetailOverzichtWaarden.add(jlDetailOverzichtWaarden);
        jlDetailOverzichtWaarden.setBounds(585, 310, 270, 205);
        jpDetailOverzichtWaarden.setBackground(Color.white);
        jpDetailOverzichtWaarden.setBounds(585, 310, 270, 205);
        add(jpDetailOverzichtWaarden);

        // selectionlistener voor databasecomponenten
        DbList.getSelectionModel().addListSelectionListener(e -> {
            if (DbList.getValueIsAdjusting()) {
                // geselecteerde component wordt in geheugen opgeslagen en vervormt tot juiste
                // formaat
                Object selectedComponentDb = DbList.getSelectedValue();
                String selectedComponentMinHTMLDb = String.valueOf(selectedComponentDb).replaceAll("\\<.*?\\>", "");
                System.out.println(selectedComponentMinHTMLDb);
                String[] selectedComponentSplitDb = selectedComponentMinHTMLDb.split(" ");
                String hostnameDb = selectedComponentSplitDb[1];
                System.out.println(hostnameDb);

                // gedetaileerde informatie over component wordt opgevraagd uit database
                try {
                    Database db = new Database("nerdygadgets", "monitoring", "Iloveberrit3!$");
                    Query q = new Query();
                    q = q.DetailOverzichtMonitorPanelQuery(hostnameDb);
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
                            // System.out.println(opslag+"\n"+opslagVerbruik+"\n"+beschikbaarLengte+"\n"+tijdstip);

                            detailOverzichtWaarden = "<html><strong>Hostname: " + hostnameDb + "</strong><br><br>"
                                    + processor + " GHz kloksnelheid<br>" + processorBelasting + " GHz op het moment<br><br>" + opslag + " GB capaciteit waarvan<br>"
                                    + opslagVerbruik + " GB gebruikt<br><br>" + (beschikbaarLengte / 60) + " minuten in bedrijf<br>afgelezen op "
                                    + tijdstip + "</html>";
                            jlDetailOverzichtWaarden.setText(detailOverzichtWaarden);
                            DbList.setSelectedValue(null,true);
                            DbList.getSelectionModel().clearSelection();
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                } catch (Exception a) {
                    a.printStackTrace();
                }
            }
        });

        // selectionlistener voor webservercomponenten
        WbList.getSelectionModel().addListSelectionListener(e -> {
            if (WbList.getValueIsAdjusting()) {
                // geselecteerde component wordt in geheugen opgeslagen en vervormt tot juiste
                // formaat
                Object selectedComponentWb = WbList.getSelectedValue();
                String selectedComponentMinHTMLWb = String.valueOf(selectedComponentWb).replaceAll("\\<.*?\\>", "");
                System.out.println(selectedComponentMinHTMLWb);
                String[] selectedComponentSplitWb = selectedComponentMinHTMLWb.split(" ");
                String hostnameWb = selectedComponentSplitWb[1];
                System.out.println(hostnameWb);

                // gedetaileerde informatie over component wordt opgevraagd uit database
                try {
                    Database db = new Database("nerdygadgets", "monitoring", "Iloveberrit3!$");
                    Query q = new Query();
                    q = q.DetailOverzichtMonitorPanelQuery(hostnameWb);
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
                            // System.out.println(opslag+"\n"+opslagVerbruik+"\n"+beschikbaarLengte+"\n"+tijdstip);

                            detailOverzichtWaarden = "<html><strong>Hostname: " + hostnameWb + "</strong><br><br>"
                                    + processor + " GHz kloksnelheid<br>" + processorBelasting + " GHz op het moment<br><br>" + opslag + " GB capaciteit waarvan<br>"
                                    + opslagVerbruik + " GB gebruikt<br><br>" + (beschikbaarLengte / 60) + " minuten in bedrijf<br>afgelezen op "
                                    + tijdstip + "</html>";
                            jlDetailOverzichtWaarden.setText(detailOverzichtWaarden);
                            WbList.setSelectedValue(null,true);
                            WbList.getSelectionModel().clearSelection();
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                } catch (Exception a) {
                    a.printStackTrace();
                }
            }
        });

        // selectionlistener voor firewall
        PfSList.getSelectionModel().addListSelectionListener(e -> {
            if (PfSList.getValueIsAdjusting()) {
                // geselecteerde component wordt in geheugen opgeslagen en vervormt tot juiste
                // formaat
                Object selectedComponentPfS = PfSList.getSelectedValue();
                String selectedComponentMinHTMLPfS = String.valueOf(selectedComponentPfS).replaceAll("\\<.*?\\>", "");
                System.out.println(selectedComponentMinHTMLPfS);
                String[] selectedComponentSplitPfS = selectedComponentMinHTMLPfS.split(" ");
                String hostnamePfS = selectedComponentSplitPfS[1];
                System.out.println(hostnamePfS);

                // gedetaileerde informatie over component wordt opgevraagd uit database
                try {
                    Database db = new Database("nerdygadgets", "monitoring", "Iloveberrit3!$");
                    Query q = new Query();
                    q = q.DetailOverzichtMonitorPanelQuery(hostnamePfS);
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
                            // System.out.println(opslag+"\n"+opslagVerbruik+"\n"+beschikbaarLengte+"\n"+tijdstip);

                            detailOverzichtWaarden = "<html><strong>Hostname: " + hostnamePfS + "</strong><br><br>"
                                    + processor + " GHz kloksnelheid<br>" + processorBelasting + " GHz op het moment<br><br>" + opslag + " GB capaciteit waarvan<br>"
                                    + opslagVerbruik + " GB gebruikt<br><br>" + (beschikbaarLengte / 60) + " minuten in bedrijf<br>afgelezen op "
                                    + tijdstip + "</html>";
                            jlDetailOverzichtWaarden.setText(detailOverzichtWaarden);
                            PfSList.setSelectedValue(null,true);
                            PfSList.getSelectionModel().clearSelection();
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

    //actionlistener aan jbstatus toevoegen
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbStatus) {
            String message = "";
            if (isBereikbaarDb1) {
                message = "<html><p style=\"color:green\">&#x2714; Database1</p>";
            } else {
                message = "<html><p style=\"color:red\">&#10060; Database1</p>";
            }
            if (isBereikbaarDb2) {
                message += "<p style=\"color:green\">&#x2714; Database2</p>";
            } else {
                message += "<p style=\"color:red\">&#10060; Database2</p>";
            }
            if (isBereikbaarWb1) {
                message += "<p style=\"color:green\">&#x2714; Webserver1</p>";
            } else {
                message += "<p style=\"color:red\">&#10060; Webserver1</p>";
            }
            if (isBereikbaarWb2) {
                message += "<p style=\"color:green\">&#x2714; Webserver2</p>";
            } else {
                message += "<p style=\"color:red\">&#10060; Webserver2</p>";
            }
            if (isBereikbaarPfS) {
                message += "<p style=\"color:green\">&#x2714; PfSense</p></html>";
            } else {
                message += "<p style=\"color:red\">&#10060; PfSense</p></html>";
            }
            JOptionPane.showMessageDialog(this, message,"Status componenten", 1);
        }
    }
}