package com.ictm2n2.resources;

public class Backtracking {

    // private Configuratie c = new Configuratie();
    private Componenten componenten = new Componenten();


    private double kosten = 0;
    private double prijsBesteOplossing = 0;
    private Configuratie besteConfiguratie = new Configuratie();

    public Configuratie berekenBesteConfiguratie(double percentage) {
        berekenBesteConfiguratie(percentage, new Configuratie());
        return besteConfiguratie;
    }

    public void berekenBesteConfiguratie(double percentage, Configuratie configuratie) {
        if (configuratie.getComponenten().isEmpty()) {
            configuratie.voegToeComponent(componenten.firewalls.get(0));
            configuratie.voegToeComponent(componenten.loadbalancers.get(0));
            configuratie.voegToeComponent(componenten.webServers.get(0));
            configuratie.voegToeComponent(componenten.dbServers.get(0));
        }

        for (Component component : componenten.getComponenten()) {

            configuratie.voegToeComponent(component);

            /*
             * Is de beschikbaarheid van de huidige configuratie groter of gelijk aan het
             * doel percentage EN is de prijs goedkoper dan die van de vorige oplossing
             */

            if (berekenTotaleBeschikbaarheid(configuratie) >= percentage
                    && (berekenTotalePrijs(configuratie) < prijsBesteOplossing || prijsBesteOplossing == 0)) {
                /*
                 * Beste configuratie leegmaken. Hier staan nog items van de vorige oplossing in
                 */
                besteConfiguratie = new Configuratie();
                /* Componenten van de nieuwe oplossing aan de beste configuratie toevoegen */
                for (Component component1 : configuratie.getComponenten()) {
                    besteConfiguratie.voegToeComponent(component1);
                }
                /* Prijs berekenen */
                prijsBesteOplossing = berekenTotalePrijsDouble(configuratie);

            }
            /*
             * Is de beschikbaarheid van de oplossing kleiner dan het minimale percentage
             * doel
             */
            else if (berekenTotaleBeschikbaarheid(configuratie) < percentage) {
                // Kiezen of we als volgende een webserver of databaseserver moeten toevoegen
                if (berekenComponent(Webserver.class, configuratie) < berekenComponent(DatabaseServer.class,
                        configuratie)) {
                    voegVolgendeToe(Webserver.class);
                } else {
                    voegVolgendeToe(DatabaseServer.class);
                }
            }
            /* Is de prijs hoger dan de prijs van de tot nu toe beste oplossing */
            else if (berekenTotalePrijsDouble(configuratie) > prijsBesteOplossing && prijsBesteOplossing != 0) {
                /* Niets doen, oplossing is te duur */
            }

            /* Het component verwijderen */
            configuratie.verwijderComponent(configuratie.getComponenten().size() - 1);
        }
        configuratie.print();

    }

    public double berekenTotaleBeschikbaarheid(Configuratie configuratie) {
        /* Loop over de componenten en bereken totale beschikbaarheid */
        return (berekenComponent(Firewall.class, configuratie) / 100)
                * (berekenComponent(Webserver.class, configuratie) / 100)
                * (berekenComponent(Loadbalancer.class, configuratie) / 100)
                * berekenComponent(DatabaseServer.class, configuratie);
    }

    // public Configuratie maakConfiguratie(double percentage) {
    // /*
    // * Kijken of er al componenten in de huidige configuratie zitten. Als er niks
    // in
    // * zit worden er 1 van elke soort in gezet.
    // */
    // if (c.getComponenten().isEmpty()) {
    // c.voegToeComponent(componenten.firewalls.get(0));
    // c.voegToeComponent(componenten.loadbalancers.get(0));
    // c.voegToeComponent(componenten.webServers.get(0));
    // c.voegToeComponent(componenten.dbServers.get(0));
    // } else {
    // if (berekenComponent(Webserver.class, c) <
    // berekenComponent(DatabaseServer.class, c)) {
    // voegVolgendeToe(Webserver.class);
    // } else {
    // voegVolgendeToe(DatabaseServer.class);
    // }
    // }
    // if (isVoldaan(percentage, c)) {
    // kosten = berekenTotalePrijs(c);
    // c.setComponenten(maakGoedkoper(percentage, c).getComponenten());
    // // System.out.println("Voldaan" + c.berekenTotalePrijsDouble());
    // } else if (!isVoldaan(percentage, c)) {
    // return maakConfiguratie(percentage);
    // }

    // System.out.println("Returned: " + c.berekenTotalePrijsDouble() + " - " +
    // kosten);
    // return c;

    // }

    private Configuratie maakGoedkoper(double percentage, Configuratie configuratie) {

        boolean wsDoorlopen = false;
        boolean dbDoorlopen = false;

        while (!wsDoorlopen && !dbDoorlopen) {
            for (int i = 0; i < componenten.webServers.size() - 1; i++) {
                Webserver goedkoopsteWs = componenten.webServers.get(0);
                Webserver duurdereWs = componenten.webServers.get(componenten.webServers.indexOf(goedkoopsteWs) + 1);

                for (int j = 0; j < hoeveelVan(goedkoopsteWs, configuratie); j++) {
                    configuratie.vervangComponent(configuratie.getComponenten().lastIndexOf(goedkoopsteWs), duurdereWs);
                }

                // kijken of het goedkoper is en of het nog wel voldaan is aan het percentage
                // als dit niet zo is dan gaan we eerst weer een goedkope webserver toevoegen
                // als dat nog niet werkt gaan we helemaal terug naar de vorige stap en eindigt
                // de loop

                for (int j = 0; j < hoeveelVan(duurdereWs, configuratie); j++) {
                    if (kosten < berekenTotalePrijsDouble(configuratie)) {
                        configuratie.verwijderComponent(duurdereWs);
                    } else if (isVoldaan(percentage, configuratie)) {
                        kosten = berekenTotalePrijsDouble(configuratie);
                    }
                    if (!isVoldaan(percentage, configuratie)) {
                        configuratie.voegToeComponent(goedkoopsteWs);
                    } else {
                        break;
                    }
                }
            }
            wsDoorlopen = true;
            for (int i = 0; i < componenten.dbServers.size() - 1; i++) {
                DatabaseServer goedkoopsteDb = componenten.dbServers.get(0);
                DatabaseServer duurdereDb = componenten.dbServers.get(componenten.dbServers.indexOf(goedkoopsteDb) + 1);

                for (int j = 0; j < hoeveelVan(goedkoopsteDb, configuratie); j++) {
                    configuratie.vervangComponent(configuratie.getComponenten().lastIndexOf(goedkoopsteDb), duurdereDb);
                }

                for (int j = 0; j < hoeveelVan(duurdereDb, configuratie); j++) {
                    if (kosten < berekenTotalePrijsDouble(configuratie)) {
                        configuratie.verwijderComponent(duurdereDb);
                    } else {
                        kosten = berekenTotalePrijsDouble(configuratie);
                    }
                    if (!isVoldaan(percentage, configuratie)) {
                        configuratie.voegToeComponent(goedkoopsteDb);
                    } else {
                        break;
                    }
                }
            }
        }
        dbDoorlopen = true;
        return configuratie;
    }

    private boolean isVoldaan(double percentage, Configuratie configuratie) {
        double b = (berekenComponent(Firewall.class, configuratie) / 100)
                * (berekenComponent(Loadbalancer.class, configuratie) / 100)
                * (berekenComponent(Webserver.class, configuratie) / 100)
                * berekenComponent(DatabaseServer.class, configuratie);

        System.out.println(String.format("%s >= %s ---- %s", b, percentage, b >= percentage));
        // Om te kijken of de percentage al behaald is in de configuratie.
        return (berekenComponent(Firewall.class, configuratie) / 100)
                * (berekenComponent(Webserver.class, configuratie) / 100)
                * berekenComponent(DatabaseServer.class, configuratie) >= percentage;
    }

    private void voegVolgendeToe(Class<?> type) {
        // Switchen tussen Webserver en Database server.
        if (type.isAssignableFrom(Webserver.class)) {
            besteConfiguratie.voegToeComponent(componenten.webServers.get(0));
        } else if (type.isAssignableFrom(DatabaseServer.class)) {
            besteConfiguratie.voegToeComponent(componenten.dbServers.get(0));
        }
    }

    private int hoeveelVan(Component component, Configuratie configuratie) {
        // Bereken hoeveel er van een component al in de configuratie zitten.

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

    public double berekenTotalePrijsDouble(Configuratie configuratie) {
        double totalePrijs = 0;
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
