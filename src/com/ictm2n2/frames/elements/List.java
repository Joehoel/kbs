package com.ictm2n2.frames.elements;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class List<E> extends JList<E> {

    public List(E[] data) {
        DefaultListModel<E> model = updateModel(data);

        setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        setLayoutOrientation(JList.VERTICAL);
        setVisibleRowCount(-1);
        setModel(model);
    }

    public DefaultListModel<E> updateModel(E[] data) {
        DefaultListModel<E> model = new DefaultListModel<E>();
        for (E object : data) {
            model.addElement(object);
        }
        return model;
    }

}
