package com.ictm2n2.frames;

import com.ictm2n2.resources.*;
import com.ictm2n2.resources.database.Database;
import com.ictm2n2.resources.database.Query;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MonitorPanel extends JPanel {
    JList jlistOntwerpen;
    DefaultListModel<String> dlmOntwerpen;
    ArrayList<Object[]> ontwerpComponenten = new ArrayList<Object[]>();

    public MonitorPanel() {
        setLayout(null);

        DefaultListModel<String> dlModel = new DefaultListModel<>();
        JList list = new JList<>(dlModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL_WRAP);

        try {
            Database db = new Database("nerdygadgets", "monitoring", "Iloveberrit3!$");
            Query q = new Query();
            String[] columns = { "hostname", "cpu", "opslag" };
            q.select(columns).from("component");
            ResultSet rs = db.select(q);

            try {
                while (rs.next()) {
                    // String[] hostname = rs.getString("hostname");
                    // double[] cpu = rs.getDouble("cpu");
                    // double[] opslag = rs.getDouble("opslag");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            System.out.println(rs);
        } catch (Exception a) {
            a.printStackTrace();
        }

        // for (int i=0; i<this.actiefOntwerp.getComponenten().size();i++) {
        // String naam = this.actiefOntwerp.getComponenten().get(i).getNaam();
        // String prijs = ""+this.actiefOntwerp.getComponenten().get(i).getPrijs()+"";
        // dlModel.addElement("<html>"+naam+"<br>"+prijs+"</html>");
        // }

        add(list);

    }
}
