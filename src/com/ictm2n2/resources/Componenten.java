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
            Database db = new Database("nerdygadgets", "monitoring", "Iloveberrit3!$");

            Query q = new Query();
            q.select(null).from("component_type");
            ResultSet rs = db.select(q);
            while (rs.next()) {
                String naam = rs.getString("type_naam");
                String type = rs.getString("type_soort");

                double beschikbaarheid = rs.getDouble("type_beschikbaarheid");
                double prijs = rs.getDouble("type_prijs");
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

    public ArrayList<DatabaseServer> getDbServers() {
        return dbServers;
    }

    public void setDbServers(ArrayList<DatabaseServer> dbServers) {
        this.dbServers = dbServers;
    }

    public ArrayList<Webserver> getWebServers() {
        return webServers;
    }

    public void setWebServers(ArrayList<Webserver> webServers) {
        this.webServers = webServers;
    }

    public ArrayList<Firewall> getFirewalls() {
        return firewalls;
    }

    public void setFirewalls(ArrayList<Firewall> firewalls) {
        this.firewalls = firewalls;
    }

    public String[] get(Class<?> type) {
        ArrayList<String> data = new ArrayList<String>();
        if (type.getSimpleName().equals("Webserver")) {
            for (Webserver s : webServers) {
                String str = s.getNaam() + " - " + s.getBeschikbaarheid() + "%" + " - €" + s.getPrijs();
                data.add(str);
            }
        } else if (type.getSimpleName().equals("DatabaseServer")) {
            for (DatabaseServer s : dbServers) {
                String str = s.getNaam() + " - " + s.getBeschikbaarheid() + "%" + " - €" + s.getPrijs();
                data.add(str);
            }
        } else if (type.getSimpleName().equals("Firewall")) {
            for (Firewall s : firewalls) {
                String str = s.getNaam() + " - " + s.getBeschikbaarheid() + "%" + " - €" + s.getPrijs();
                data.add(str);
            }
        }
        return data.toArray(new String[] {});
    }

}
