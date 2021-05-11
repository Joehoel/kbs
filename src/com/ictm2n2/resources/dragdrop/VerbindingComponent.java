package com.ictm2n2.resources.dragdrop;

import com.ictm2n2.resources.Component;

import javax.sound.sampled.Line;

public class VerbindingComponent {
    DragDropComponent vanComponent;
    DragDropComponent naarComponent;

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
