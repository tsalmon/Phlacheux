package MovableSettings.ShapeSettings;

import javafx.scene.Parent;
import model.movable.Figure;
import model.movable.circle.Circle;
import model.movable.line.CubicCurve;
import model.movable.line.QuadraticCurve;
import model.movable.line.Segment;
import model.movable.polygon.*;
import model.movable.polygon.Rectangle;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ShapeAdjustementPane extends JDialog implements ChangeListener {

    private static final int maxX = 1000;
    private static final int maxY = 1000;
    private static final int maxWidth = 500;
    private static final int maxHeight = 500;
    private static final int maxThickness = 100;

    private Figure figure;
    private JPanel parent;
    private static JColorChooser сolorChooser = new JColorChooser();


    JLabel nameLabel = new JLabel("Nom");
    JTextField nameField = new JTextField("figure1");

    JLabel colorLabel = new JLabel("Color");
    JButton colorButton = new JButton();

    JLabel borderLabel = new JLabel("Bordure");
    JSpinner thicknessSpinner = new JSpinner(new SpinnerNumberModel(10, 0, maxThickness, 1));
    JButton borderColorButton = new JButton();

    JPanel customShapePanel;

    private static void chooseColor(){
        final JDialog dialog = JColorChooser.createDialog(null,"Choose color...",true, сolorChooser,null,null);
        dialog.setVisible(true);
    }

    private Action backgroundColorChooserListener = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            chooseColor();
            colorButton.setBackground(сolorChooser.getColor());
            figure.setColor(сolorChooser.getColor());
            parent.repaint();
        }
    };

    private Action borderColorChooserListener = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            chooseColor();
            borderColorButton.setBackground(сolorChooser.getColor());
            figure.setBorderColor(сolorChooser.getColor());
            parent.repaint();
        }
    };

    private Action nameChangeListener = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            figure.setName(nameField.getText());
            parent.repaint();
        }
    };

    @Override
    public void stateChanged(ChangeEvent changeEvent) {
        figure.setStrokeThickness((double)getShapeBorderThickness());
        parent.repaint();
    }

    public String getShapeName(){
        return nameField.getText();
    }

    private int spinnerValue(Object spinnerValue){
        Integer v = (Integer)spinnerValue;
        if (v!= null){
            return v.intValue();
        } else {
            return 0;
        }
    }

    public int getShapeBorderThickness(){
        return spinnerValue(thicknessSpinner.getValue());
    }

    public Color getColor(){
        return colorButton.getBackground();
    }

    public Color getBorderColor(){
        return borderColorButton.getBackground();
    }


    public ShapeAdjustementPane(final Figure fig, JPanel parent){
        this.figure = fig;
        this.parent = parent;
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        nameField.setText(figure.getName());
        nameField.addActionListener(nameChangeListener);

        colorButton.setOpaque(true);
        colorButton.setBorderPainted(false);
        colorButton.setBackground(figure.getColor());
        colorButton.addActionListener(backgroundColorChooserListener);

        thicknessSpinner.setValue(Double.valueOf(figure.getStrokeThickness()));
        thicknessSpinner.addChangeListener(this);

        borderColorButton.setOpaque(true);
        borderColorButton.setBorderPainted(false);
        borderColorButton.setBackground(figure.getBorderColor());
        borderColorButton.addActionListener(borderColorChooserListener);

        add(nameLabel);
        add(nameField);
        add(colorLabel);
        add(colorButton);
        add(borderLabel);
        add(thicknessSpinner);
        add(borderColorButton);

        JButton testButton = new JButton("Test");
        testButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Nom:"+figure.getName());
                System.out.println("Color:"+figure.getColor());
                System.out.println("Border thickness:" + figure.getStrokeThickness());
                System.out.println("Border color:"+figure.getBorderColor());

                if (figure instanceof Circle){
                    Circle c = (Circle)figure;
                    System.out.println("Center:"+c.getCenter());
                    System.out.println("Radius:"+c.getRadius());
                }
                else
                if (figure instanceof Segment){
                    Segment s = (Segment)figure;
                    System.out.println("Depart:"+s.getPointDepart());
                    System.out.println("Arrive" + s.getPointArrivee());
                }
                else
                if (figure instanceof QuadraticCurve){
                    QuadraticCurve q = (QuadraticCurve)figure;
                    System.out.println("Depart:" + q.getPointDepart());
                    System.out.println("Arrive" + q.getPointArrivee());
                    System.out.println("Ctrl"+q.getPointControle());
                }
                else
                if (figure instanceof CubicCurve){
                    CubicCurve c = (CubicCurve)figure;
                    System.out.println("Depart:" + c.getPointDepart());
                    System.out.println("Arrive" + c.getPointArrivee());
                    System.out.println("Ctrl1"+c.getPointControle1());
                    System.out.println("Ctrl2"+c.getPointControle2());
                }
                else
                if (figure instanceof Square){
                    Square s = (Square)figure;
                    System.out.println("Point:" + s.getPointhg());
                    System.out.println("Side: " + s.getSideLength());
                }
                else
                if (figure instanceof Rectangle){
                    Rectangle r = (Rectangle)figure;
                    System.out.println("Point: " + r.getPointhg());
                    System.out.println("Width: " + r.getWidth());
                    System.out.println("Length: "+ r.getLength());
                }
                else
                if (figure instanceof EquilateralTriangle){
                    EquilateralTriangle tri = (EquilateralTriangle)figure;
                    System.out.println("Point1:" + tri.getSommet1());
                    System.out.println("Point1:" + tri.getSommet2());
                    System.out.println("Point1:" + tri.getSommet3());
                }
                else
                if (figure instanceof Triangle){
                    Triangle tri = (Triangle)figure;
                    System.out.println("Point1:" + tri.getSommet1());
                    System.out.println("Point1:" + tri.getSommet2());
                    System.out.println("Point1:" + tri.getSommet3());
                }

            }
        });

        if (figure instanceof Circle){
            customShapePanel = new CircleSettings((Circle)figure, parent);
        } else
        if (figure instanceof Segment){
            customShapePanel = new SegmentSettings((Segment)figure, parent);
        } else
        if (figure instanceof QuadraticCurve){
            customShapePanel = new QuadraticCurveSettings((QuadraticCurve)figure, parent);
        } else
        if (figure instanceof CubicCurve){
            customShapePanel = new CubicCurveSettings((CubicCurve)figure, parent);
        }else
        if (figure instanceof Square){
            customShapePanel = new SquareSettings((Square)figure, parent);
        }else
        if (figure instanceof model.movable.polygon.Rectangle){
            customShapePanel = new RectangleSettings((Rectangle)figure, parent);
        }else
        if (figure instanceof EquilateralTriangle){
            customShapePanel = new EquilateralTriangleSettings((EquilateralTriangle)figure, parent);
        }else
        if (figure instanceof Triangle){
            customShapePanel = new TriangleSettings((Triangle)figure, parent);
        }
        else{
            customShapePanel = new JPanel();
        }
        add(customShapePanel);
        //add(testButton);

        pack();
        setVisible(true);
    }
}
