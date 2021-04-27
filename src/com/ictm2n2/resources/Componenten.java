package com.ictm2n2.resources;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.ictm2n2.resources.database.Database;
import com.ictm2n2.resources.database.Query;

public class Componenten {
    ArrayList<DatabaseServer> dbServers = new ArrayList<DatabaseServer>();
    ArrayList<Webserver> webServers = new ArrayList<Webserver>();
    ArrayList<Firewall> firewalls = new ArrayList<Firewall>();

    public Componenten() {
        try {
            Database db = new Database("nerdygadgets_1", "root", "");

            Query q = new Query();
            q.select(null).from("componenten");
            ResultSet rs = db.select(q);
            while (rs.next()) {
                String type = rs.getString("type");

                String naam = rs.getString("hostname");
                int prijs = rs.getInt("prijs");
                int beschikbaarheid = rs.getInt("beschikbaarheid");
                if (type.equals("DBserver")) {
                    dbServers.add(new DatabaseServer(naam, prijs, beschikbaarheid));
                } else if (type.equals("Webserver")) {
                    webServers.add(new Webserver(naam, prijs, beschikbaarheid));
                } else if (type.equals("firewall")) {
                    firewalls.add(new Firewall(naam, prijs, beschikbaarheid));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String[] get(Class<?> type) {
        ArrayList<String> data = new ArrayList<String>();
        if (type.getSimpleName().equals("Webserver")) {
            for (Webserver s : webServers) {
                data.add(s.getNaam());
            }
        } else if (type.getSimpleName().equals("DatabaseServer")) {
            for (DatabaseServer s : dbServers) {
                data.add(s.getNaam());
            }
        } else if (type.getSimpleName().equals("Firewall")) {
            for (Firewall s : firewalls) {
                data.add(s.getNaam());
            }
        }
        return data.toArray(new String[] {});
    }

}
