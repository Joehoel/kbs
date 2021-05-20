package com.ictm2n2.frames;

import java.awt.Color;
import java.awt.FlowLayout;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import javax.swing.text.html.ImageView;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import com.ictm2n2.resources.database.Database;
import com.ictm2n2.resources.database.Query;

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
    private Timestamp DbTijdstip;

    private ArrayList<String> WbHostnames = new ArrayList<String>();
    private ArrayList<String> WbCpu = new ArrayList<String>();
    private ArrayList<String> WbOpslag = new ArrayList<String>();
    private ArrayList<String> WbAangesloten = new ArrayList<String>();
    private Timestamp WbTijdstip;

    private ArrayList<String> LbHostnames = new ArrayList<String>();
    private ArrayList<String> LbCpu = new ArrayList<String>();
    private ArrayList<String> LbOpslag = new ArrayList<String>();
    private ArrayList<String> LbAangesloten = new ArrayList<String>();
    private Timestamp LbTijdstip;

    private ArrayList<String> PfSHostnames = new ArrayList<String>();
    private ArrayList<String> PfSCpu = new ArrayList<String>();
    private ArrayList<String> PfSOpslag = new ArrayList<String>();
    private ArrayList<String> PfSAangesloten = new ArrayList<String>();
    private Timestamp PfSTijdstip;

    Timestamp localTime = new Timestamp(System.currentTimeMillis());

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

        DefaultListModel<String> dlLbModel = new DefaultListModel<>();
        JList LbList = new JList<>(dlLbModel);
        LbList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        LbList.setLayoutOrientation(JList.VERTICAL_WRAP);

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

                // loadbalancer ophalen en in array stoppen
                try {
                    Database db = new Database("nerdygadgets", "monitoring", "Iloveberrit3!$");
                    Query q = new Query();
                    q = q.LbMonitorPanelQuery();
                    ResultSet rs = db.preparedQuery(q);

                    try {
                        // eerst alle arraylists leegmaken indien deze al eens gevuld zijn door een
                        // query
                        LbHostnames.clear();
                        LbCpu.clear();
                        LbOpslag.clear();
                        LbAangesloten.clear();

                        while (rs.next()) {
                            LbHostnames.add(rs.getString("c.hostname"));
                            LbCpu.add(String.valueOf(rs.getDouble("c.cpu")));
                            LbOpslag.add(String.valueOf(rs.getDouble("c.opslag")));
                            LbTijdstip = (rs.getTimestamp("s.tijdstip"));
                            System.out.println(localTime.getTime() - LbTijdstip.getTime());

                            if ((localTime.getTime() - LbTijdstip.getTime()) > 5000000) {
                                LbAangesloten.add("niet aangesloten");
                            } else {
                                LbAangesloten.add("aangesloten");
                            }
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                } catch (Exception a) {
                    a.printStackTrace();
                }
                System.out.println(LbHostnames.size() + " " + LbCpu.size() + " " + LbOpslag.size());

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

                // eerst zorgen dat de defaultlistmodel leeg is
                dlLbModel.clear();

                // loadbalancer toevoegen aan jlist
                i = 0;
                while (i < LbHostnames.size()) {
                    String element = "<html><strong>Hostname: " + String.format(LbHostnames.get(i)) + " </strong><br>"
                            + LbCpu.get(i) + " GHz <br>" + LbOpslag.get(i) + " GB<br>";

                    if (LbAangesloten.get(i).equals("aangesloten")) {
                        element += "<i> <p style =\"color:green\">" + LbAangesloten.get(i) + "</p></i></html>";
                    } else {
                        element += "<i> <p style =\"color:red\">" + LbAangesloten.get(i) + "</p></i></html>";
                    }
                    dlLbModel.addElement(element);
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
//                               try {
//                                   isBereikbaarDb1 = sprDb1.sendPingRequest();
//                                   isBereikbaarDb2 = sprDb2.sendPingRequest();
//                                   isBereikbaarWb1 = sprWb1.sendPingRequest();
//                                   isBereikbaarWb2 = sprWb2.sendPingRequest();
//                                   isBereikbaarPfS = sprPfS.sendPingRequest();
//                               }  catch (UnknownHostException e) {
//                                   e.printStackTrace();
//                               } catch (IOException e) {
//                                   e.printStackTrace();
//                               }
                           }
                       }, 0, 5000);

        jlDb = new JLabel("Databases");
        jlWb = new JLabel("Webservers");
        jlLb = new JLabel("Load-Balancer");
        jlPfS = new JLabel("PfSense");
        jlDetailOverzicht = new JLabel("Gedetailleerd Overzicht");
        jlDetailOverzichtWaarden = new JLabel(detailOverzichtWaarden);
        jpDetailOverzichtWaarden = new JPanel();

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

        add(jlLb);
        jlLb.setBounds(585, 20, 270, 20);
        LbList.setBounds(585, 50, 270, 80);
        LbList.setFixedCellWidth(270);
        LbList.setFixedCellHeight(80);
        add(LbList);

        add(jlPfS);
        jlPfS.setBounds(585, 150, 270, 20);
        PfSList.setBounds(585, 180, 270, 80);
        PfSList.setFixedCellWidth(270);
        PfSList.setFixedCellHeight(80);
        add(PfSList);

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

        // selectionlistener voor loadbalancer
        LbList.getSelectionModel().addListSelectionListener(e -> {
            if (LbList.getValueIsAdjusting()) {
                // geselecteerde component wordt in geheugen opgeslagen en vervormt tot juiste
                // formaat
                Object selectedComponentLb = LbList.getSelectedValue();
                String selectedComponentMinHTMLLb = String.valueOf(selectedComponentLb).replaceAll("\\<.*?\\>", "");
                System.out.println(selectedComponentMinHTMLLb);
                String[] selectedComponentSplitLb = selectedComponentMinHTMLLb.split(" ");
                String hostnameLb = selectedComponentSplitLb[1];
                System.out.println(hostnameLb);

                // gedetaileerde informatie over component wordt opgevraagd uit database
                try {
                    Database db = new Database("nerdygadgets", "monitoring", "Iloveberrit3!$");
                    Query q = new Query();
                    q = q.DetailOverzichtMonitorPanelQuery(hostnameLb);
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

                            detailOverzichtWaarden = "<html><strong>Hostname: " + hostnameLb + "</strong><br><br>"
                                    + processor + " GHz kloksnelheid<br>" + processorBelasting
                                    + " GHz op het moment<br><br>" + opslag + " GB capaciteit waarvan<br>"
                                    + opslagVerbruik + " GB gebruikt<br><br>" + (beschikbaarLengte / 60)
                                    + " minuten in bedrijf<br>afgelezen op " + tijdstip + "</html>";
                            jlDetailOverzichtWaarden.setText(detailOverzichtWaarden);
                            LbList.setSelectedValue(null, true);
                            LbList.getSelectionModel().clearSelection();
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
                            // PfSList.setSelectionInterval(-1, -1);
                            // PfSList.setEnabled(false);
                            PfSList.setSelectedValue(null, true);
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
}
