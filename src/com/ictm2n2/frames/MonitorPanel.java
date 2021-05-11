package com.ictm2n2.frames;

import com.ictm2n2.resources.*;
import com.ictm2n2.resources.database.Database;
import com.ictm2n2.resources.database.Query;

import javax.imageio.ImageIO;
import javax.swing.*;
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
    private JLabel jlFw;
    private JLabel jlPfS;

    private ArrayList<String> DbHostnames = new ArrayList<String>();
    private ArrayList<String> DbCpu = new ArrayList<String>();
    private ArrayList<String> DbOpslag = new ArrayList<String>();
    private ArrayList<String> DbAangesloten = new ArrayList<String>();
    private ArrayList<String> WbHostnames = new ArrayList<String>();
    private ArrayList<String> WbCpu = new ArrayList<String>();
    private ArrayList<String> WbOpslag = new ArrayList<String>();
    private ArrayList<String> WbAangesloten = new ArrayList<String>();

    public MonitorPanel() {
        setLayout(null);

        //lijsten
        DefaultListModel<String> dlDbModel = new DefaultListModel<>();
        JList DbList = new JList<>(dlDbModel);
        DbList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DbList.setLayoutOrientation(JList.VERTICAL_WRAP);

        DefaultListModel<String> dlWbModel = new DefaultListModel<>();
        JList WbList = new JList<>(dlWbModel);
        WbList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        WbList.setLayoutOrientation(JList.VERTICAL_WRAP);

        DefaultListModel<String> dlFwModel = new DefaultListModel<>();
        JList FwList = new JList<>(dlFwModel);
        FwList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        FwList.setLayoutOrientation(JList.VERTICAL_WRAP);

        DefaultListModel<String> dlPfSModel = new DefaultListModel<>();
        JList PfSList = new JList<>(dlPfSModel);
        PfSList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        PfSList.setLayoutOrientation(JList.VERTICAL_WRAP);

        //databaseservers
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

        //webservers
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

        //png toevoegen
//        ImageIcon icon = new ImageIcon("com/ictm2n2/assets/dbserver.png");
//        JLabel label = new JLabel(icon);
//        add(label);
//        label.setBounds(585,310,100,100);
//        System.out.println(icon);

        try {
//            Path source = Paths.get("c:\\test\\mkyong.png");
//            BufferedImage img = ImageIO.read(sourc.toFile("src/com/ictm2n2/assets/dbserver.png"));
            Path source = Paths.get("src/com/ictm2n2/assets/dbserver.png");
            BufferedImage img = ImageIO.read(source.toFile());
            System.out.println(img);
//            dlDbModel.add(img);
        } catch (IOException e) {
            System.out.println("image not found");
        }
//        JLabel label = new JLabel((Icon) img);
//        add(label);
//        label.setBounds(585,310,100,100);
//        System.out.println(img);

        int i = 0;
        while (i < DbHostnames.size()) {
            String element = "<html><img src=\"src/com/ictm2n2/assets/dbserver.png\" alt=\"test img\" width=\"20\" height=\"20\"><strong>Hostname: "+String.format("%-50s",DbHostnames.get(i))+"</strong><br>"+
                    DbCpu.get(i)+" GHz<br>"+
                    DbOpslag.get(i)+" GB<br>";
            if (DbAangesloten.get(i).equals("aangesloten")) {
                element += "<i> <p style =\"color:green\">"+DbAangesloten.get(i)+"</p></i></html>";
            } else {
                element += "<i> <p style =\"color:red\">"+DbAangesloten.get(i)+"</p></i></html>";
            }
            dlDbModel.addElement(element);
            i++;
        }

        i = 0;
        while (i < WbHostnames.size()) {
            dlWbModel.addElement("<html>"+WbHostnames.get(i)+"<br>"+
                    WbCpu.get(i)+" GHz<br>"+
                    WbOpslag.get(i)+" GB<br>"+
                    WbAangesloten.get(i)+"</html>");
            i++;
        }

        jlDb = new JLabel("Databases");
        jlWb = new JLabel("Webservers");
        jlFw = new JLabel("Load-Balancer");
        jlPfS= new JLabel("PfSense");

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

        add(jlFw);
        jlFw.setBounds(585,20,270,20);
        FwList.setBounds(585,50,270,205);
        FwList.setFixedCellWidth(270);
        FwList.setFixedCellHeight(80);
        add(FwList);

        add(jlPfS);
        jlPfS.setBounds(585,280,270,20);
        PfSList.setBounds(585,310,270,205);
        PfSList.setFixedCellWidth(270);
        PfSList.setFixedCellHeight(80);
        add(PfSList);

    }
}
