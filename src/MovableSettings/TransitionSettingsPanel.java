package MovableSettings;

import javax.swing.*;
import java.awt.*;


public class TransitionSettingsPanel extends JPanel{

    JLabel transitionLabel = new JLabel("Transition");
    JLabel xLabel = new JLabel("X");
    JLabel yLabel = new JLabel("Y");

    JSpinner transitionSpinnerX = new JSpinner(new SpinnerNumberModel(1,1,1440,1));
    JSpinner transitionSpinnerY = new JSpinner(new SpinnerNumberModel(1,1,1440,1));

    private int getSpinnerValue(JSpinner spinner){
        Integer v = (Integer)spinner.getValue();
        if (v!= null){
            return v.intValue();
        } else {
            return 0;
        }
    }

    public int getTransitionX(){
        return getSpinnerValue(transitionSpinnerX);
    }

    public int getTransitionY(){
        return getSpinnerValue(transitionSpinnerY);
    }



    public TransitionSettingsPanel(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(transitionLabel);
        add(xLabel);
        add(transitionSpinnerX);
        add(yLabel);
        add(transitionSpinnerY);
    }
}
