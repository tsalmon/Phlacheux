package MovableSettings.ShapeSettings;


import model.movable.circle.Circle;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CircleSettings extends JPanel implements ChangeListener{

    private Circle circle;
    private JPanel parent;
    JLabel xLabel = new JLabel("X");
    JSpinner xPointSpinner;

    JLabel yLabel = new JLabel("Y");
    JSpinner yPointSpinner;


    JLabel radiusLabel = new JLabel("Radius");
    JSpinner radiusSpinner;

    private int getSpinnerValue(JSpinner spinner){
        Double v = (Double)spinner.getValue();
        if (v!= null){
            return v.intValue();
        } else {
            return 0;
        }
    }

    public int getCenterX(){
        return getSpinnerValue(xPointSpinner);
    }
    public int getCenterY(){
        return getSpinnerValue(yPointSpinner);
    }
    public int getRadius(){return  getSpinnerValue(radiusSpinner);}

    @Override
    public void stateChanged(ChangeEvent changeEvent) {
        circle.setCenter((double)getCenterX(), (double)getCenterY());
        circle.setRadius((double)getRadius());
        parent.repaint();
    }

    public CircleSettings(Circle c, JPanel parent){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        circle = c;
        this.parent = parent;
        xPointSpinner = new JSpinner(new SpinnerNumberModel(c.getCenter().getX(), 0, 1000, 1));
        xPointSpinner.addChangeListener(this);
        yPointSpinner = new JSpinner(new SpinnerNumberModel(c.getCenter().getY(), 0, 1000, 1));
        yPointSpinner.addChangeListener(this);
        radiusSpinner = new JSpinner(new SpinnerNumberModel(c.getRadius(), 1, 1000, 1));
        radiusSpinner.addChangeListener(this);

        add(xLabel);
        add(xPointSpinner);
        add(yLabel);
        add(yPointSpinner);
        add(radiusLabel);
        add(radiusSpinner);
    }
}
