package MovableSettings.ShapeSettings;



import model.movable.polygon.Triangle;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TriangleSettings extends JPanel implements ChangeListener {
    private Triangle triangle;
    private JPanel parent;

    protected JLabel x1Label = new JLabel("X1");
    protected JSpinner x1PointSpinner;

    protected JLabel y1Label = new JLabel("Y1");
    protected JSpinner y1PointSpinner;

    protected JLabel x2Label = new JLabel("X2");
    protected JSpinner x2PointSpinner;

    protected JLabel y2Label = new JLabel("Y2");
    protected JSpinner y2PointSpinner;

    protected JLabel x3Label = new JLabel("X3");
    protected JSpinner x3PointSpinner;

    protected JLabel y3Label = new JLabel("Y3");
    protected JSpinner y3PointSpinner;


    private int getSpinnerValue(JSpinner spinner){
        Double v = (Double)spinner.getValue();
        if (v!= null){
            return v.intValue();
        } else {
            return 0;
        }
    }

    public int getX1(){
        return getSpinnerValue(x1PointSpinner);
    }

    public int getY1() {
        return getSpinnerValue(y1PointSpinner);
    }

    public int getX2() {
        return getSpinnerValue(x2PointSpinner);
    }

    public int getY2() {
        return getSpinnerValue(y2PointSpinner);
    }

    public int getX3() {
        return getSpinnerValue(x3PointSpinner);
    }

    public int getY3() {
        return getSpinnerValue(y3PointSpinner);
    }

    @Override
    public void stateChanged(ChangeEvent changeEvent) {
        triangle.setSommet1(getX1(), getY1());
        triangle.setSommet2(getX2(), getY2());
        triangle.setSommet3(getX3(), getY3());
        parent.repaint();
    }

    public TriangleSettings(Triangle t, JPanel parent) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.triangle = t;
        this.parent = parent;

        x1PointSpinner = new JSpinner(new SpinnerNumberModel(t.getSommet1().getX(), 0, 1000, 1));
        x1PointSpinner.addChangeListener(this);

        y1PointSpinner = new JSpinner(new SpinnerNumberModel(t.getSommet1().getY(), 0, 1000, 1));
        y1PointSpinner.addChangeListener(this);

        x2PointSpinner = new JSpinner(new SpinnerNumberModel(t.getSommet2().getX(), 0, 1000, 1));
        x2PointSpinner.addChangeListener(this);

        y2PointSpinner = new JSpinner(new SpinnerNumberModel(t.getSommet2().getY(), 0, 1000, 1));
        y2PointSpinner.addChangeListener(this);

        x3PointSpinner = new JSpinner(new SpinnerNumberModel(t.getSommet3().getX(), 0, 1000, 1));
        x3PointSpinner.addChangeListener(this);

        y3PointSpinner = new JSpinner(new SpinnerNumberModel(t.getSommet3().getY(), 0, 1000, 1));
        y3PointSpinner.addChangeListener(this);

        add(x1Label);
        add(x1PointSpinner);
        add(y1Label);
        add(y1PointSpinner);
        add(x2Label);
        add(x2PointSpinner);
        add(y2Label);
        add(y2PointSpinner);
        add(x3Label);
        add(x3PointSpinner);
        add(y3Label);
        add(y3PointSpinner);
    }
}
