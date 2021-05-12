package com.ictm2n2;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;
import com.ictm2n2.frames.App;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            new App();
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}
