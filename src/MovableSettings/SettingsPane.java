package MovableSettings;

import javax.swing.*;
import java.awt.*;

public class SettingsPane extends JFrame{
    public SettingsPane() {
        setTitle("Réglages");
        JTabbedPane settingsPane = new JTabbedPane();
        getContentPane().add(settingsPane);

        ShapeAdjustementPane shapeAdjusment = new ShapeAdjustementPane();
        AnimationPane animationsAdjustement = new AnimationPane();

        settingsPane.setPreferredSize(new Dimension(300, 800));
        settingsPane.addTab("Réglages", shapeAdjusment);
        settingsPane.addTab("Animations", animationsAdjustement);

    }

    public static void main(String[] args) {
        SettingsPane tp = new SettingsPane();
        tp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tp.pack();
        tp.setVisible(true);

    }
}

