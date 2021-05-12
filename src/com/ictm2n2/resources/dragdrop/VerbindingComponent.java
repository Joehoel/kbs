package com.ictm2n2.resources.dragdrop;

import com.ictm2n2.resources.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class VerbindingComponent extends JComponent {
    private DragDropComponent vanComponent;
    private DragDropComponent naarComponent;

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
}
