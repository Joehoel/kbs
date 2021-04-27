package com.ictm2n2.resources;

public class Backtracking {

    private Configuratie c = new Configuratie();
    private Componenten componenten = new Componenten();

    private int kosten = 0;

    public Configuratie maakConfiguratie(double percentage) {

        if (c.getComponenten().isEmpty()) {
            c.voegToeComponent(componenten.dbServers.get(0));
            c.voegToeComponent(componenten.webServers.get(0));
            c.voegToeComponent(componenten.firewalls.get(0));
        } else {
            if (berekenComponent(Webserver.class, c) < berekenComponent(DatabaseServer.class, c)) {
                voegVolgendeToe(Webserver.class);
            } else {
                voegVolgendeToe(DatabaseServer.class);
            }
        }

        if (isVoldaan(percentage, c)) {
            maakConfiguratie(percentage);
        } else {
            kosten = berekenTotalePrijs(c);
            c = maakGoedkoper(percentage, c);
        }

        return new Configuratie();
    }

    private Configuratie maakGoedkoper(double percentage, Configuratie configuratie) {

        boolean wsDoorlopen = false;
        boolean dbDoorlopen = false;

        while (!wsDoorlopen && !dbDoorlopen) {
            for (int i = 0; i < componenten.webServers.size() - 1; i++) {
                Component goedKoopsteWs = componenten.webServers.get(0);
                Component duurdereWs = componenten.webServers.get(componenten.webServers.indexOf(goedKoopsteWs) + 1);

                for (int j = 0; j < hoeveelVan(goedKoopsteWs, configuratie); j++) {
                    configuratie.vervangComponent(configuratie.getComponenten().lastIndexOf(goedKoopsteWs), duurdereWs);
                }

                for (int j = 0; j < hoeveelVan(duurdereWs, configuratie); j++) {
                    if (kosten < berekenTotalePrijs(configuratie)) {
                        configuratie.verwijderComponent(componenten.webServers.indexOf(duurdereWs));
                    } else if (isVoldaan(percentage, configuratie)) {
                        kosten = berekenTotalePrijs(configuratie);
                    }
                    if (!isVoldaan(percentage, configuratie)) {
                        configuratie.voegToeComponent(goedKoopsteWs);
                    } else {
                        break;
                    }
                }
            }
        }
        return configuratie;
    }

    private boolean isVoldaan(double percentage, Configuratie configuratie) {
        return (berekenComponent(Firewall.class, configuratie) / 100)
                * (berekenComponent(Webserver.class, configuratie) / 100)
                * berekenComponent(DatabaseServer.class, configuratie) >= percentage;
    }

    private void voegVolgendeToe(Class<?> type) {
        if (type.isAssignableFrom(Webserver.class)) { // kijken of de volgende class een webserver of DBServer is
            c.voegToeComponent(componenten.webServers.get(0));
        } else if (type.isAssignableFrom(DatabaseServer.class)) {
            c.voegToeComponent(componenten.dbServers.get(0));
        }
    }

    private int hoeveelVan(Component component, Configuratie configuratie) {
        int counter = 0;
        for (Component c : configuratie.getComponenten()) {
            if (c.equals(component)) {
                counter++;
            }
        }
        return counter;
    }

    public double berekenComponent(Class<?> type, Configuratie configuratie) {
        double beschikbaarheid = 1;

        // Voor elk component wordt gekeken wat voor component het is. Daarna wordt de
        // beschikbaarheid berekend.
        for (Component c : configuratie.getComponenten()) {
            if (type.isAssignableFrom(c.getClass())) {
                beschikbaarheid *= (1 - (c.getBeschikbaarheid() / 100));
            }
        }
        return (1 - beschikbaarheid) * 100;
    }

    private int berekenTotalePrijs(Configuratie configuratie) {
        int totalePrijs = 0;
        try {
            for (Component component : configuratie.getComponenten()) {
                totalePrijs += component.getPrijs();
            }
        } catch (NullPointerException npe) {
            System.out.println(npe);
        }
        return totalePrijs;
    }

}
