package MovableSettings.ShapeSettings;

import model.movable.PointPlacheux;
import model.movable.polygon.EquilateralTriangle;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class EquilateralTriangleSettings extends JPanel implements ChangeListener {

    private EquilateralTriangle triangle;

    protected JLabel x1Label = new JLabel("X1");
    protected JSpinner x1PointSpinner;

    protected JLabel y1Label = new JLabel("Y1");
    protected JSpinner y1PointSpinner;

    protected JLabel x2Label = new JLabel("X2");
    protected JSpinner x2PointSpinner;

    protected JLabel y2Label = new JLabel("Y2");
    protected JSpinner y2PointSpinner;


    private int getSpinnerValue(JSpinner spinner) {
        Double v = (Double) spinner.getValue();
        if (v != null) {
            return v.intValue();
        } else {
            return 0;
        }
    }

    public int getX1() {
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

    @Override
    public void stateChanged(ChangeEvent changeEvent) {
        triangle.setSommet1(new PointPlacheux(getX1(), getY1()));
        triangle.setSommet2(new PointPlacheux(getX2(), getY2()));
    }

    public EquilateralTriangleSettings(EquilateralTriangle t) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.triangle = t;

        x1PointSpinner = new JSpinner(new SpinnerNumberModel(t.getSommet1().getX(), 0, 1000, 1));
        x1PointSpinner.addChangeListener(this);

        y1PointSpinner = new JSpinner(new SpinnerNumberModel(t.getSommet1().getY(), 0, 1000, 1));
        y1PointSpinner.addChangeListener(this);

        x2PointSpinner = new JSpinner(new SpinnerNumberModel(t.getSommet2().getX(), 0, 1000, 1));
        x2PointSpinner.addChangeListener(this);

        y2PointSpinner = new JSpinner(new SpinnerNumberModel(t.getSommet2().getY(), 0, 1000, 1));
        y2PointSpinner.addChangeListener(this);

        add(x1Label);
        add(x1PointSpinner);
        add(y1Label);
        add(y1PointSpinner);
        add(x2Label);
        add(x2PointSpinner);
        add(y2Label);
        add(y2PointSpinner);
    }
}
