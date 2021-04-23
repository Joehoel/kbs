package com.ictm2n2.frames.elements;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTable;

public class Table extends JPanel {

    private JTable jpTable;

    public Table(Object[][] data, String[] headerRows) {
        jpTable = new JTable(data, headerRows) {
            public boolean isCellEditable(int row, int column) {
                return false;
            };

        };
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(jpTable.getTableHeader());
        add(jpTable);
    }

}
