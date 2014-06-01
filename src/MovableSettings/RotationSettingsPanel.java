package MovableSettings;

import javax.swing.*;
import java.awt.*;

/**
 * Created by coolermaster on 01/06/2014.
 */
public class RotationSettingsPanel extends JPanel{
    JLabel angleLabel = new JLabel("Angle");
    JSpinner angleSpinner = new JSpinner(new SpinnerNumberModel(1,1,1440,1));

    public int getAngle(){
        Integer v = (Integer)angleSpinner.getValue();
        if (v!= null){
            return v.intValue();
        } else {
            return 0;
        }
    }

    public RotationSettingsPanel(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(angleLabel);
        add(angleSpinner);
    }
}
