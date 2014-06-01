package MovableSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ShapeAdjustementPane extends JPanel{

    private static final int maxX = 1000;
    private static final int maxY = 1000;
    private static final int maxWidth = 500;
    private static final int maxHeight = 500;
    private static final int maxThickness = 100;

    private static JColorChooser сolorChooser = new JColorChooser();


    JLabel nameLabel = new JLabel("Nom");
    JTextField nameField = new JTextField("figure1");

    JLabel xLabel = new JLabel("X");
    JSpinner xSpinner = new JSpinner(new SpinnerNumberModel(10,0,maxX,1));

    JLabel yLabel = new JLabel("Y");
    JSpinner ySpinner = new JSpinner(new SpinnerNumberModel(10,0,maxY,1));

    JLabel widthLabel = new JLabel("Width");
    JSpinner widthSpinner = new JSpinner(new SpinnerNumberModel(10,0,maxWidth,1));

    JLabel heightLabel = new JLabel("Height");
    JSpinner heightSpinner = new JSpinner(new SpinnerNumberModel(10,0,maxHeight,1));

    JLabel colorLabel = new JLabel("Color");
    JButton colorButton = new JButton();

    JLabel borderLabel = new JLabel("Bordure");
    JSpinner thicknessSpinner = new JSpinner(new SpinnerNumberModel(10, 0, maxThickness, 1));
    JButton borderColorButton = new JButton();


    private static void chooseColor(){
        final JDialog dialog = JColorChooser.createDialog(null,"Choose color...",true, сolorChooser,null,null);
        dialog.setVisible(true);
    }

    private Action backgroundColorChooserListener = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            chooseColor();
            colorButton.setBackground(сolorChooser.getColor());
        }
    };

    private Action borderColorChooserListener = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            chooseColor();
            borderColorButton.setBackground(сolorChooser.getColor());
        }
    };

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

    public int getShapeX(){
        return spinnerValue(xSpinner.getValue());
    }

    public int getShapeY(){
        return spinnerValue(ySpinner.getValue());
    }

    public int getShapeWidth(){
        return spinnerValue(widthSpinner.getValue());
    }

    public int getShapeHeight(){
        return spinnerValue(heightSpinner.getValue());
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


    public ShapeAdjustementPane(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        colorButton.setOpaque(true);
        colorButton.setBorderPainted(false);
        colorButton.setBackground(Color.black);
        colorButton.addActionListener(backgroundColorChooserListener);

        borderColorButton.setOpaque(true);
        borderColorButton.setBorderPainted(false);
        borderColorButton.setBackground(Color.black);
        borderColorButton.addActionListener(borderColorChooserListener);

        add(nameLabel);
        add(nameField);
        add(xLabel);
        add(xSpinner);
        add(yLabel);
        add(ySpinner);
        add(widthLabel);
        add(widthSpinner);
        add(heightLabel);
        add(heightSpinner);
        add(colorLabel);
        add(colorButton);
        add(borderLabel);
        add(thicknessSpinner);
        add(borderColorButton);

        JButton testButton = new JButton("Test");
        testButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Nom:"+getShapeName());
                System.out.println("X:"+getShapeX());
                System.out.println("Y:"+getShapeY());
                System.out.println("Width:"+getShapeWidth());
                System.out.println("Height:"+getShapeHeight());
                System.out.println("Color:"+getColor());
                System.out.println("Border thickness:"+getShapeBorderThickness());
                System.out.println("Border color:"+getBorderColor());


            }
        });
        //add(testButton);
    }
}
