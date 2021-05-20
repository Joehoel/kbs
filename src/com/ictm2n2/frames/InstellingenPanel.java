package com.ictm2n2.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLaf;

public class InstellingenPanel extends JPanel implements ActionListener {
    private Object theme;
    private Map<String, String> themes;

    private JLabel jlThema = new JLabel("Thema:");
    private JComboBox<Object> jcbThemes;

    public InstellingenPanel() {
        setLayout(null);

        try {
            themes = new HashMap<String, String>();

            themes.put("Dark", "com.formdev.flatlaf.FlatDarkLaf");
            themes.put("Light", "com.formdev.flatlaf.FlatLightLaf");
            themes.put("Atom Dark", "com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatAtomOneDarkIJTheme");
            themes.put("Moonlight", "com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMoonlightIJTheme");
            themes.put("Dracula", "com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatDraculaIJTheme");
            themes.put("Github", "com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubIJTheme");
            themes.put("Solarized Dark",
                    "com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatSolarizedDarkIJTheme");

            Object[] themeData = new Object[themes.size()];

            int i = 0;
            for (Map.Entry<String, String> theme : themes.entrySet()) {
                themeData[i] = theme.getKey();
                i++;
            }
            jcbThemes = new JComboBox<Object>(themeData);

            jlThema.setBounds(10, 10, 100, 30);

            jcbThemes.setBounds(10, 35, 100, 30);
            jcbThemes.setSelectedItem("Light");
            jcbThemes.addActionListener(this);

            add(jlThema);
            add(jcbThemes);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jcbThemes) {
            try {
                theme = jcbThemes.getSelectedItem();
                String themeName = themes.get(theme);
                UIManager.setLookAndFeel(themeName);
                FlatLaf.updateUI();
            } catch (Exception error) {
                System.err.println(error);
            }
        }
    }
}
