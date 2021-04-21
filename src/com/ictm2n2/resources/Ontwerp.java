package com.ictm2n2.resources;

import java.util.ArrayList;

public class Ontwerp {
    private ArrayList<Component> componenten;
    private boolean actiefOntwerp;

    public void setActief(boolean actiefOntwerp) {
        this.actiefOntwerp = actiefOntwerp;
    }

    public boolean isActief() {
        return actiefOntwerp;
    }

    public void verwijderComponent(int index) {
        this.componenten.remove(index);
    }

    public void voegComponentToe(Component component) {
        this.componenten.add(component);
    }

    public void vervangComponent(int index, Component component) {
        this.componenten.set(index, component);
    }
}
