package com.ictm2n2.frames;

import java.sql.ResultSet;

import javax.swing.JPanel;

import com.ictm2n2.resources.database.Database;
import com.ictm2n2.resources.database.Query;

public class MonitorPanel extends JPanel {

    public MonitorPanel() {
        setLayout(null);

        try {
            Database db = new Database("nerdygadgets", "monitoring", "Iloveberrit3!$");

            Query q = new Query();
            q.select(null).from("status");
            ResultSet rs = db.select(q);

            while (rs.next()) {
                String processorBelasting = rs.getString("processor_belasting");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
