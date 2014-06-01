package MovableSettings.ShapeSettings;


import model.movable.polygon.Square;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SquareSettings  extends JPanel implements ChangeListener {
    private Square square;

    JLabel xLabel = new JLabel("X");
    JSpinner xPointSpinner;

    JLabel yLabel = new JLabel("Y");
    JSpinner yPointSpinner;

    JLabel lengthLabel = new JLabel("Length");
    JSpinner lengthSpinner;

    private int getSpinnerValue(JSpinner spinner){
        Double v = (Double)spinner.getValue();
        if (v!= null){
            return v.intValue();
        } else {
            return 0;
        }
    }

    public int getPointX(){ return getSpinnerValue(xPointSpinner);}
    public int getPointY(){ return getSpinnerValue(yPointSpinner);}
    public int getSideLength(){ return  getSpinnerValue(lengthSpinner);}

    @Override
    public void stateChanged(ChangeEvent changeEvent) {
        square.setPointhg(getPointX(), getPointY());
        square.setSideLength(getSideLength());
    }

    public SquareSettings(final Square s){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        square = s;

        xPointSpinner = new JSpinner(new SpinnerNumberModel(s.getPointhg().getX(), 0, 1000, 1));
        xPointSpinner.addChangeListener(this);

        yPointSpinner = new JSpinner(new SpinnerNumberModel(s.getPointhg().getY(), 0, 1000, 1));
        yPointSpinner.addChangeListener(this);

        lengthSpinner = new JSpinner(new SpinnerNumberModel(s.getLength(), 0, 1000, 1));
        lengthSpinner.addChangeListener(this);

        add(xLabel);
        add(xPointSpinner);
        add(yLabel);
        add(yPointSpinner);
        add(lengthLabel);
        add(lengthSpinner);
    }
}
