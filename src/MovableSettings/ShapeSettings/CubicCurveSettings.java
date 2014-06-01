package MovableSettings.ShapeSettings;


import model.movable.line.CubicCurve;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class CubicCurveSettings extends JPanel implements ChangeListener {
    private CubicCurve curve;
    private JPanel parent;

    protected JLabel x1Label = new JLabel("X1");
    protected JSpinner x1PointSpinner;

    protected JLabel y1Label = new JLabel("Y1");
    protected JSpinner y1PointSpinner;

    protected JLabel x2Label = new JLabel("X2");
    protected JSpinner x2PointSpinner;

    protected JLabel y2Label = new JLabel("Y2");
    protected JSpinner y2PointSpinner;

    protected JLabel xCtrl1Label = new JLabel("ctrl1X");
    protected JSpinner xCtrl1PointSpinner;

    protected JLabel yCtrl1Label = new JLabel("ctrl1Y");
    protected JSpinner yCtrl1PointSpinner;

    protected JLabel xCtrl2Label = new JLabel("ctrl2X");
    protected JSpinner xCtrl2PointSpinner;

    protected JLabel yCtrl2Label = new JLabel("ctrl2Y");
    protected JSpinner yCtrl2PointSpinner;

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

    public int getCtrl1X() {
        return getSpinnerValue(xCtrl1PointSpinner);
    }

    public int getCtrl1Y() {
        return getSpinnerValue(yCtrl1PointSpinner);
    }

    public int getCtrl2X() {
        return getSpinnerValue(xCtrl2PointSpinner);
    }

    public int getCtrl2Y() {
        return getSpinnerValue(yCtrl2PointSpinner);
    }

    @Override
    public void stateChanged(ChangeEvent changeEvent) {
        curve.setPointDepart(getX1(), getY1());
        curve.setPointArrivee(getX2(), getY2());
        curve.setPointControle1(getCtrl1X(), getCtrl1Y());
        curve.setPointControle2(getCtrl2X(), getCtrl2Y());
        parent.repaint();
    }

    public CubicCurveSettings(CubicCurve c, JPanel parent){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.curve = c;
        this.parent = parent;

        x1PointSpinner = new JSpinner(new SpinnerNumberModel(c.getPointDepart().getX(), 0, 1000, 1));
        x1PointSpinner.addChangeListener(this);

        y1PointSpinner = new JSpinner(new SpinnerNumberModel(c.getPointDepart().getY(), 0, 1000, 1));
        y1PointSpinner.addChangeListener(this);

        x2PointSpinner = new JSpinner(new SpinnerNumberModel(c.getPointArrivee().getX(), 0, 1000, 1));
        x2PointSpinner.addChangeListener(this);

        y2PointSpinner = new JSpinner(new SpinnerNumberModel(c.getPointArrivee().getY(), 0, 1000, 1));
        y2PointSpinner.addChangeListener(this);

        xCtrl1PointSpinner = new JSpinner(new SpinnerNumberModel(c.getPointControle1().getX(), 0, 1000, 1));
        xCtrl1PointSpinner.addChangeListener(this);

        yCtrl1PointSpinner = new JSpinner(new SpinnerNumberModel(c.getPointControle1().getY(), 0, 1000, 1));
        xCtrl1PointSpinner.addChangeListener(this);

        xCtrl2PointSpinner = new JSpinner(new SpinnerNumberModel(c.getPointControle2().getX(), 0, 1000, 1));
        xCtrl2PointSpinner.addChangeListener(this);

        yCtrl2PointSpinner = new JSpinner(new SpinnerNumberModel(c.getPointControle2().getY(), 0, 1000, 1));
        yCtrl2PointSpinner.addChangeListener(this);

        add(x1Label);
        add(x1PointSpinner);
        add(y1Label);
        add(y1PointSpinner);
        add(x2Label);
        add(x2PointSpinner);
        add(y2Label);
        add(y2PointSpinner);
        add(xCtrl1Label);
        add(xCtrl1PointSpinner);
        add(yCtrl1Label);
        add(yCtrl1PointSpinner);
        add(xCtrl2Label);
        add(xCtrl2PointSpinner);
        add(yCtrl2Label);
        add(yCtrl2PointSpinner);
    }
}
