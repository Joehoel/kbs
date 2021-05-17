package com.ictm2n2.resources.dragdrop;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class VerbindingComponent extends JComponent {
    private DragDropComponent vanComponent;
    private DragDropComponent naarComponent;

    private Point2D beginPositie;
    private Point2D eindPositie;

    public VerbindingComponent(DragDropComponent vanComponent, DragDropComponent naarComponent) {
        this.vanComponent = vanComponent;
        this.naarComponent = naarComponent;
    }

    public DragDropComponent getVanComponent() {
        return this.vanComponent;
    }

    public DragDropComponent getNaarComponent() {
        return this.naarComponent;
    }

    public void setLijnPosities(Point2D startPositie, Point2D eindPositie) {
        this.beginPositie = startPositie;
        this.eindPositie = eindPositie;
    }

    public Point2D getBeginPositie() {
        return this.beginPositie;
    }

    public Point2D getEindPositie() {
        return this.eindPositie;
    }
}
