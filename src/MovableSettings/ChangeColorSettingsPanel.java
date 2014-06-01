package MovableSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ChangeColorSettingsPanel extends JPanel {
    JLabel colorLabel = new JLabel("NOT SPECIFIED!!!");
    JButton colorButton = new JButton("");
    private static JColorChooser сolorChooser = new JColorChooser();

    private static void chooseColor(){
        final JDialog dialog = JColorChooser.createDialog(null,"Choose color...",true, сolorChooser,null,null);
        dialog.setVisible(true);
    }

    private Action backgroundColorChooserListener = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            chooseColor();
            colorButton.setBackground(сolorChooser.getColor());
        }
    };

    public Color getColor(){
        return colorButton.getBackground();
    }

    public ChangeColorSettingsPanel(String s){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        colorLabel.setText(s);

        colorButton.setOpaque(true);
        colorButton.setBorderPainted(false);
        colorButton.setBackground(Color.black);
        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                chooseColor();
            }
        });

        add(colorLabel);
        add(colorButton);
    }
}
