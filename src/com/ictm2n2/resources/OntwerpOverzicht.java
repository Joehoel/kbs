package com.ictm2n2.resources;

import java.util.ArrayList;

public class OntwerpOverzicht {
    private ArrayList<Ontwerp> ontwerpen = new ArrayList<Ontwerp>();

    public void verwijderOntwerp(int index) {
        this.ontwerpen.remove(index);
    }

    public void voegToeOntwerp(Ontwerp ontwerp) {
        this.ontwerpen.add(ontwerp);
    }

    public void vervangOntwerp(int index, Ontwerp ontwerp) {
        this.ontwerpen.set(index, ontwerp);
    }

    public String printOntwerpen() {
        String str = "";

        for (Ontwerp ontwerp : ontwerpen) {
            str += ontwerp.getNaam();
        }

        return str;
    }

    public ArrayList<Ontwerp> getOntwerpen() {
        return ontwerpen;
    }

    public void setOntwerpen(ArrayList<Ontwerp> ontwerpen) {
        this.ontwerpen = ontwerpen;
    }

}
