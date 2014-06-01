package tests;

import model.animation.*;
import model.easing.Bounce;
import model.easing.Circ;
import model.easing.Quad;
import model.easing.Sine;
import model.movable.*;
import model.movable.PointPlacheux;
import model.movable.circle.Circle;
import model.movable.line.CubicCurve;
import model.movable.line.QuadraticCurve;
import model.movable.line.Segment;
import model.movable.polygon.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class CreationXML {

    public static void main(String args[]){
        Film film = new Film();

        Circle c = new Circle(50, 40, 100);
        c.setColor(Color.cyan);
        film.addShape(c);

        Segment s = new Segment(new model.movable.PointPlacheux(10,20), new model.movable.PointPlacheux(30,40));
        s.setColor(Color.cyan);
        film.addShape(s);

        QuadraticCurve q = new QuadraticCurve(new model.movable.PointPlacheux(10,20), new model.movable.PointPlacheux(30,40), new model.movable.PointPlacheux(50,60));
        q.setColor(Color.cyan);
        film.addShape(q);

        CubicCurve cubcurve = new CubicCurve(new model.movable.PointPlacheux(10,20), new model.movable.PointPlacheux(30,40), new model.movable.PointPlacheux(50,60), new model.movable.PointPlacheux(70,80));
        cubcurve.setColor(Color.cyan);
        film.addShape(cubcurve);

        EquilateralTriangle eqtri = new EquilateralTriangle(new model.movable.PointPlacheux(15,15), new model.movable.PointPlacheux(30,30));
        eqtri.setColor(Color.cyan);
        film.addShape(eqtri);

        ArrayList<model.movable.PointPlacheux> points = new ArrayList<model.movable.PointPlacheux>();
        points.add(new model.movable.PointPlacheux(10,10));
        points.add(new model.movable.PointPlacheux(20,20));
        points.add(new model.movable.PointPlacheux(30,30));
        points.add(new model.movable.PointPlacheux(40,40));

        PolygonPerso poly = new PolygonPerso(points);
        poly.setColor(Color.cyan);
        film.addShape(poly);

        model.movable.polygon.Rectangle rect = new model.movable.polygon.Rectangle(100,100, new model.movable.PointPlacheux(5,5));
        rect.setColor(Color.cyan);
        film.addShape(rect);

        Square square = new Square(50, new model.movable.PointPlacheux(10,10));
        square.setColor(Color.cyan);
        film.addShape(square);

        Triangle tri = new Triangle(new model.movable.PointPlacheux(10,10), new model.movable.PointPlacheux(20,20), new model.movable.PointPlacheux(30,30));
        tri.setColor(Color.cyan);
        film.addShape(tri);

        MovableGroup group1 = new MovableGroup();
        group1.addMovable(c);
        group1.addMovable(s);
        group1.addMovable(q);
        group1.addMovable(cubcurve);
        group1.addMovable(eqtri);
        group1.addMovable(poly);
        group1.addMovable(rect);
        group1.addMovable(square);
        group1.addMovable(tri);

        film.addGroup(group1);

        MovableGroup group2 = new MovableGroup();
        group2.addMovable(eqtri);
        group2.addMovable(group1);
        film.addGroup(group2);

        Translation tr = new Translation("anim1", c, 0,50, new Quad(), EasingType.EASE_IN, new PointPlacheux(100,100), new PointPlacheux(200,200));
        film.addAnimation(tr);

        Rotation rot = new Rotation("anim2", square, 0,50,new Sine(), EasingType.EASE_IN, 45);
        film.addAnimation(rot);

        Scaling scal = new Scaling("anim3", tri, 0, 50, 1.5);
        film.addAnimation(scal);

        ChangeStrokeThickness cst = new ChangeStrokeThickness("anim4", rect, 0, 50, 20);
        film.addAnimation(cst);

        film.saveToFile("film1.xml");

        Film f2 = Film.fromFile("film1.xml");
        f2.saveToFile("film2.xml");


    }
}
