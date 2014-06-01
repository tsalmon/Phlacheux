package MovableSettings.ShapeSettings;


import model.movable.line.QuadraticCurve;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class QuadraticCurveSettings extends JPanel implements ChangeListener {
    private QuadraticCurve curve;

    protected JLabel x1Label = new JLabel("X1");
    protected JSpinner x1PointSpinner;

    protected JLabel y1Label = new JLabel("Y1");
    protected JSpinner y1PointSpinner;

    protected JLabel x2Label = new JLabel("X2");
    protected JSpinner x2PointSpinner;

    protected JLabel y2Label = new JLabel("Y2");
    protected JSpinner y2PointSpinner;

    protected JLabel xCtrlLabel = new JLabel("ctrlX");
    protected JSpinner xCtrlPointSpinner;

    protected JLabel yCtrlLabel = new JLabel("ctrlY");
    protected JSpinner yCtrlPointSpinner;

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

    public int getCtrlX() {
        return getSpinnerValue(xCtrlPointSpinner);
    }

    public int getCtrlY() {
        return getSpinnerValue(yCtrlPointSpinner);
    }

    @Override
    public void stateChanged(ChangeEvent changeEvent) {
        curve.setPointDepart(getX1(), getY1());
        curve.setPointArrivee(getX2(), getY2());
        curve.setPointControle(getCtrlX(), getCtrlY());
    }

    public QuadraticCurveSettings(QuadraticCurve q){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.curve = q;

        x1PointSpinner = new JSpinner(new SpinnerNumberModel(q.getPointDepart().getX(), 0, 1000, 1));
        x1PointSpinner.addChangeListener(this);

        y1PointSpinner = new JSpinner(new SpinnerNumberModel(q.getPointDepart().getY(), 0, 1000, 1));
        y1PointSpinner.addChangeListener(this);

        x2PointSpinner = new JSpinner(new SpinnerNumberModel(q.getPointArrivee().getX(), 0, 1000, 1));
        x2PointSpinner.addChangeListener(this);

        y2PointSpinner = new JSpinner(new SpinnerNumberModel(q.getPointArrivee().getY(), 0, 1000, 1));
        y2PointSpinner.addChangeListener(this);

        xCtrlPointSpinner = new JSpinner(new SpinnerNumberModel(q.getPointControle().getX(), 0, 1000, 1));
        xCtrlPointSpinner.addChangeListener(this);

        yCtrlPointSpinner = new JSpinner(new SpinnerNumberModel(q.getPointControle().getY(), 0, 1000, 1));
        yCtrlPointSpinner.addChangeListener(this);


        add(x1Label);
        add(x1PointSpinner);
        add(y1Label);
        add(y1PointSpinner);
        add(x2Label);
        add(x2PointSpinner);
        add(y2Label);
        add(y2PointSpinner);
        add(xCtrlLabel);
        add(xCtrlPointSpinner);
        add(yCtrlLabel);
        add(yCtrlPointSpinner);
    }
}
