package MovableSettings;


import javax.swing.*;
import java.awt.*;

public class ScalingSettingsPanel extends JPanel{
    JLabel destinationScaleLabel = new JLabel("Echelle ciblé");
    JSpinner destinationScaleSpinner = new JSpinner(new SpinnerNumberModel(1,1,1000,1));

    public int getDestionationScale(){
        Integer v = (Integer)destinationScaleSpinner.getValue();
        if (v!= null){
            return v.intValue();
        } else {
            return 0;
        }
    }

    public ScalingSettingsPanel(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(destinationScaleLabel);
        add(destinationScaleSpinner);
    }
}
