package MovableSettings.ShapeSettings;

import javax.swing.*;

public class PointSetPanel extends JPanel{
    JLabel pointLabel = new JLabel("NOT SPECIFIED");
    JLabel xLabel = new JLabel("X");
    JLabel yLabel = new JLabel("Y");

    JSpinner xPointSpinner;
    JSpinner yPointSpinner;

    private int getSpinnerValue(JSpinner spinner){
        Integer v = (Integer)spinner.getValue();
        if (v!= null){
            return v.intValue();
        } else {
            return 0;
        }
    }

    public int getPointX(){
        return getSpinnerValue(xPointSpinner);
    }
    public int getPointY(){
        return getSpinnerValue(yPointSpinner);
    }


    public PointSetPanel(String lbl, int initX,  int maxX, int initY,  int maxY){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        pointLabel.setText(lbl);
        xPointSpinner = new JSpinner(new SpinnerNumberModel(initX, 0, maxX, 1));
        yPointSpinner = new JSpinner(new SpinnerNumberModel(initY, 0, maxY, 1));

        add(pointLabel);
        add(xLabel);
        add(xPointSpinner);
        add(yLabel);
        add(yPointSpinner);
    }
}
