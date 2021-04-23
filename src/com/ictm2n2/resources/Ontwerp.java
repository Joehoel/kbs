package com.ictm2n2.resources;

import java.util.ArrayList;

public class Ontwerp {
    private ArrayList<Component> componenten = new ArrayList<Component>();
    private double beschikbaarheidOntwerp;
    private boolean actiefOntwerp;
    private String naam;

    public Ontwerp(String naam) {
        this.naam = naam;
    }

    public Ontwerp() {
        this("Geen naam");
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        for (Component component : componenten) {
            if (component.getNaam().equals(naam)) {
                return;
            }
            this.naam = naam;
        }
    }

    public void setActief(boolean actiefOntwerp) {
        this.actiefOntwerp = actiefOntwerp;
    }

    public double getBeschikbaarheidOntwerp() {
        return beschikbaarheidOntwerp;
    }

    public void setBeschikbaarheidOntwerp(double beschikbaarheidOntwerp) {
        this.beschikbaarheidOntwerp = beschikbaarheidOntwerp;
    }

    public boolean isActief() {
        return actiefOntwerp;
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

    public String printComponenten() {
        String str = "";

        for (Component component : componenten) {
            str += component.getNaam() + "\n";
        }

        return str;
    }

    public String printDBComponenten() {
        String str = "";

        for (Component component : componenten) {
            str += component.getType();
        }

        return str;
    }

    public String printWSComponenten() {
        String str = "";

        for (Component component : componenten) {
            str += component.getType();
        }

        return str;
    }

    public String printFWComponenten() {
        String str = "";

        for (Component component : componenten) {
            str += component.getType();
        }

        return str;
    }

    public String printAangeslotenComponenten() {
        String str = "";

        for (Component component : componenten) {
            if (component.isAangesloten()) {
                str += component.getType();
            }
        }

        return str;
    }
}
