package com.ictm2n2.resources;

import java.util.ArrayList;

public class Configuratie {

    private ArrayList<Component> componenten = new ArrayList<Component>();

    public Configuratie() {
    }

    public void verwijderComponent(int index) {
        this.componenten.remove(index);
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
        return "$" + totalePrijs;
    }

    public double berekenBeschikbaarheid() {
        Backtracking bt = new Backtracking();
        return (bt.berekenComponent(Firewall.class, this) / 100) * (bt.berekenComponent(Webserver.class, this) / 100)
                * bt.berekenComponent(DatabaseServer.class, this);
    }

    public void optimaliseer(double percentage) {
        Backtracking bt = new Backtracking();
        this.componenten = bt.maakConfiguratie(percentage).getComponenten();
    }

}
