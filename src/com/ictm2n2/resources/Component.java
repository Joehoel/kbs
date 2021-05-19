package com.ictm2n2.resources;

public abstract class Component {
    private int id;
    private String naam;
    private double beschikbaarheidsDuur;
    private double beschikbaarheidsPercentage;
    private double processorBelasting;
    private double diskRuimte;
    private double prijs;
    private String type;
    private boolean aangesloten;

    public Component(int id, String naam, String type, double prijs, double beschikbaarheidsPercentage) {
        this.id = id;
        this.type = type;
        this.naam = naam;
        this.prijs = prijs;
        this.beschikbaarheidsPercentage = beschikbaarheidsPercentage;
    }

    public String getType() {
        return this.type;
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public double getBeschikbaarheidsDuur() {
        return beschikbaarheidsDuur;
    }

    public void setBeschikbaarheidsDuur(double beschikbaarheidsDuur) {
        this.beschikbaarheidsDuur = beschikbaarheidsDuur;
    }

    public double getBeschikbaarheidsPercentage() {
        return beschikbaarheidsPercentage;
    }

    public void setBeschikbaarheidsPercentage(double beschikbaarheidsPercentage) {
        this.beschikbaarheidsPercentage = beschikbaarheidsPercentage;
    }

    public double getProcessorBelasting() {
        return processorBelasting;
    }

    public void setProcessorBelasting(double processorBelasting) {
        this.processorBelasting = processorBelasting;
    }

    public double getDiskRuimte() {
        return diskRuimte;
    }

    public void setDiskRuimte(double diskRuimte) {
        this.diskRuimte = diskRuimte;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public boolean isAangesloten() {
        return aangesloten;
    }

    public void setAangesloten(boolean aangesloten) {
        this.aangesloten = aangesloten;
    }

    public double getPrijs() {
        return this.prijs;
    }

    public double getBeschikbaarheid() {
        return beschikbaarheidsPercentage;
    }

    // public String toString() {
    // String string = "Component #%s: %s, %s, €%s";
    // return String.format(string, this.id, this.naam,
    // this.beschikbaarheidsPercentage, this.prijs);
    // }

}
