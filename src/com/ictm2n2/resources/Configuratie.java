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
}
