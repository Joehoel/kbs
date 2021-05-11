package com.ictm2n2.resources;

import java.util.ArrayList;

public class Configuratie {

    private ArrayList<Component> componenten;

    public Configuratie() {
        componenten = new ArrayList<Component>();
    }

    public void verwijderComponent(int index) {
        this.componenten.remove(index);
    }

    public void verwijderComponent(Component c) {
        this.componenten.remove(c);
    }

    public void voegToeComponent(Component component) {
        this.componenten.add(component);
    }

    public void vervangComponent(int index, Component component) {
        this.componenten.set(index, component);
    }

    public ArrayList<Component> getComponenten() {
        return componenten;
    }

    public Object[] getComponentenNamen() {
        ArrayList<String> namen = new ArrayList<String>();
        for (Component component : componenten) {
            namen.add(component.getNaam());
        }
        return namen.toArray();
    }

    public String berekenTotalePrijs() {
        int totalePrijs = 0;
        try {
            for (Component component : getComponenten()) {
                totalePrijs += component.getPrijs();
            }
        } catch (NullPointerException npe) {
            System.out.println(npe);
        }
        return "€" + totalePrijs;
    }

    public double berekenTotalePrijsDouble() {
        int totalePrijs = 0;
        try {
            for (Component component : getComponenten()) {
                totalePrijs += component.getPrijs();
            }
        } catch (NullPointerException npe) {
            System.out.println(npe);
        }
        return totalePrijs;
    }

    public double berekenBeschikbaarheid() {
        return (berekenComponent(Firewall.class) / 100) * (berekenComponent(Webserver.class) / 100)
                * berekenComponent(DatabaseServer.class);
    }

    private double berekenComponent(Class<?> type) {
        double beschikbaarheid = 1;
        // voor elke component wordt gekeken of het een webserver is.
        // hierna wordt de formule uitgevoerd voor de beschikbaarheid.
        for (Component component : getComponenten()) {
            if (type.isAssignableFrom(component.getClass())) {
                beschikbaarheid *= (1 - (component.getBeschikbaarheid() / 100));
            }
        }
        return (1 - beschikbaarheid) * 100;
    }

    public void optimaliseer(double percentage) {
        Backtracking bt = new Backtracking();
        setComponenten(bt.maakConfiguratie(percentage).getComponenten());
    }

    public void setComponenten(ArrayList<Component> componenten) {
        this.componenten = componenten;
    }

}
