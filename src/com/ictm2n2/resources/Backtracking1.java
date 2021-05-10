package com.ictm2n2.resources;

import java.util.ArrayList;

public class Backtracking1 {

    private Configuratie configuratie;
    private ArrayList<Configuratie> oplossingen;
    private Componenten componenten;
    private double percentage;

    // public static void main(String[] args) {
    // new Backtracking1(new Configuratie(), 99.99);
    // }

    public Backtracking1(Configuratie configuratie, double percentage) {
        this.configuratie = configuratie;
        this.percentage = percentage;
    }

//    public boolean backtrack() {
//
//        // if (configuratie.getComponenten().isEmpty()) {
//        // configuratie.voegToeComponent(componenten.firewalls.get(0));
//        // configuratie.voegToeComponent(componenten.loadbalancers.get(0));
//        // configuratie.voegToeComponent(componenten.webServers.get(0));
//        // configuratie.voegToeComponent(componenten.dbServers.get(0));
//        // }
//
//    }

    public double berekenComponent(Class<?> type, Configuratie configuratie) {
        double beschikbaarheid = 1;

        /*
         * Voor elk component wordt gekeken wat voor component het is. Daarna wordt de
         * beschikbaarheid berekend.
         */
        for (Component c : configuratie.getComponenten()) {
            if (type.isAssignableFrom(c.getClass())) {
                beschikbaarheid *= (1 - (c.getBeschikbaarheid() / 100));
            }
        }
        return (1 - beschikbaarheid) * 100;
    }

    public double berekenTotaleBeschikbaarheid(Configuratie configuratie) {
        /* Loop over de componenten en bereken totale beschikbaarheid */
        return (berekenComponent(Firewall.class, configuratie) / 100)
                * (berekenComponent(Webserver.class, configuratie) / 100)
                * (berekenComponent(Loadbalancer.class, configuratie) / 100)
                * berekenComponent(DatabaseServer.class, configuratie);
    }
}
