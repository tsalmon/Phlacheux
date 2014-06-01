package MovableSettings.ShapeSettings;


import model.movable.polygon.Rectangle;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class RectangleSettings  extends JPanel implements ChangeListener {
    private Rectangle rect;
    JLabel xLabel = new JLabel("X");
    JSpinner xPointSpinner;

    JLabel yLabel = new JLabel("Y");
    JSpinner yPointSpinner;


    JLabel widthLabel = new JLabel("Width");
    JSpinner widthSpinner;

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
    public int getRectWidth(){ return  getSpinnerValue(widthSpinner);}
    public int getRectLength(){ return  getSpinnerValue(lengthSpinner);}

    @Override
    public void stateChanged(ChangeEvent changeEvent) {
        rect.setPointhg(getPointX(), getPointY());
        rect.setWidth(getRectWidth());
        rect.setLength(getRectLength());
    }

    public RectangleSettings(Rectangle r){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        rect = r;

        xPointSpinner = new JSpinner(new SpinnerNumberModel(r.getPointhg().getX(), 0, 1000, 1));
        xPointSpinner.addChangeListener(this);

        yPointSpinner = new JSpinner(new SpinnerNumberModel(r.getPointhg().getY(), 0, 1000, 1));
        yPointSpinner.addChangeListener(this);

        widthSpinner = new JSpinner(new SpinnerNumberModel(r.getWidth(), 0, 1000,1));
        widthSpinner.addChangeListener(this);

        lengthSpinner = new JSpinner(new SpinnerNumberModel(r.getLength(), 0, 1000, 1));
        lengthSpinner.addChangeListener(this);

        add(xLabel);
        add(xPointSpinner);
        add(yLabel);
        add(yPointSpinner);
        add(widthLabel);
        add(widthSpinner);
        add(lengthLabel);
        add(lengthSpinner);
    }
}
