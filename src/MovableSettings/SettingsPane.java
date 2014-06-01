package MovableSettings;

import MovableSettings.ShapeSettings.ShapeAdjustementPane;
import model.easing.Circ;
import model.movable.PointPlacheux;
import model.movable.circle.Circle;
import model.movable.line.CubicCurve;
import model.movable.line.QuadraticCurve;
import model.movable.line.Segment;
import model.movable.polygon.*;
import model.movable.polygon.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SettingsPane extends JFrame{
    public SettingsPane() {
        setTitle("Réglages");
        JTabbedPane settingsPane = new JTabbedPane();
        getContentPane().add(settingsPane);

        Circle circle = new Circle(50,50,20);
        Segment segment = new Segment(new PointPlacheux(10,10), new PointPlacheux(20,20));
        QuadraticCurve qcurve = new QuadraticCurve(new PointPlacheux(10,10), new PointPlacheux(20,20), new PointPlacheux(30,30));
        CubicCurve ccurve = new CubicCurve(new PointPlacheux(10,10), new PointPlacheux(20,20), new PointPlacheux(30,30), new PointPlacheux(40,40));
        model.movable.polygon.Rectangle rect = new Rectangle(40,40,new PointPlacheux(10,10));
        Square square = new Square(50, new PointPlacheux(70,70));
        Triangle tri = new Triangle(new PointPlacheux(10,10), new PointPlacheux(20,20), new PointPlacheux(30,30));
        EquilateralTriangle eqtri = new EquilateralTriangle(new PointPlacheux(10,10), new PointPlacheux(20,20));
        ArrayList<PointPlacheux> points = new ArrayList<PointPlacheux>();
        points.add(new PointPlacheux(10,10));
        points.add(new PointPlacheux(20,20));
        points.add(new PointPlacheux(30,30));

        PolygonPerso poly = new PolygonPerso(points);

        ShapeAdjustementPane shapeAdjusment = new ShapeAdjustementPane(poly);
        AnimationPane animationsAdjustement = new AnimationPane();

        settingsPane.setPreferredSize(new Dimension(300, 800));
        settingsPane.addTab("Réglages", shapeAdjusment);
        settingsPane.addTab("Animations", animationsAdjustement);

    }

    public static void main(String[] args) {
        SettingsPane tp = new SettingsPane();
        tp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tp.pack();
        tp.setVisible(true);

    }
}

