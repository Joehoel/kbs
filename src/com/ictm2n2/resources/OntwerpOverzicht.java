package com.ictm2n2.resources;

import java.util.ArrayList;

public class OntwerpOverzicht {
    private ArrayList<Ontwerp> ontwerpen = new ArrayList<Ontwerp>();

    public Ontwerp vindOntwerp(String naam) {
        for (Ontwerp ontwerp : ontwerpen) {
            if (ontwerp.getNaam().equals(naam))
                return ontwerp;
        }
        return null;
    }

    public Ontwerp vindActiefOntwerp() {
        for (Ontwerp ontwerp : ontwerpen) {
            if (ontwerp.isActief())
                return ontwerp;
        }
        return null;
    }

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
