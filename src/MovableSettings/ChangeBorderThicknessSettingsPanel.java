package MovableSettings;

import javax.swing.*;
import java.awt.*;

public class ChangeBorderThicknessSettingsPanel extends JPanel{
    JLabel destinationThicknessLabel = new JLabel("Epasseur ciblé");
    JSpinner destinationThicknessSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));

    public int getDestinationThickness(){
        Integer v = (Integer)destinationThicknessSpinner.getValue();
        if (v!= null){
            return v.intValue();
        } else {
            return 0;
        }
    }

    public ChangeBorderThicknessSettingsPanel(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(destinationThicknessLabel);
        add(destinationThicknessSpinner);
    }
}
