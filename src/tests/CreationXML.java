package tests;

import model.animation.*;
import model.easing.Bounce;
import model.easing.Circ;
import model.easing.Quad;
import model.easing.Sine;
import model.movable.*;
import model.movable.Point;
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

        Segment s = new Segment(new model.movable.Point(10,20), new model.movable.Point(30,40));
        s.setColor(Color.black);
        film.addShape(s);

        QuadraticCurve q = new QuadraticCurve(new model.movable.Point(10,20), new model.movable.Point(30,40), new model.movable.Point(50,60));
        q.setColor(Color.yellow);
        film.addShape(q);

        CubicCurve cubcurve = new CubicCurve(new model.movable.Point(10,20), new model.movable.Point(30,40), new model.movable.Point(50,60), new model.movable.Point(70,80));
        cubcurve.setColor(Color.blue);
        film.addShape(cubcurve);

        EquilateralTriangle eqtri = new EquilateralTriangle(new model.movable.Point(15,15), new model.movable.Point(30,30));
        eqtri.setColor(Color.green);
        film.addShape(eqtri);

        ArrayList<model.movable.Point> points = new ArrayList<model.movable.Point>();
        points.add(new model.movable.Point(10,10));
        points.add(new model.movable.Point(20,20));
        points.add(new model.movable.Point(30,30));
        points.add(new model.movable.Point(40,40));

        PolygonPerso poly = new PolygonPerso(points);
        poly.setColor(Color.gray);
        film.addShape(poly);

        model.movable.polygon.Rectangle rect = new model.movable.polygon.Rectangle(100,100, new model.movable.Point(5,5));
        rect.setColor(Color.magenta);
        film.addShape(rect);

        Square square = new Square(50, new model.movable.Point(10,10));
        square.setColor(Color.green);
        film.addShape(square);

        Triangle tri = new Triangle(new model.movable.Point(10,10), new model.movable.Point(20,20), new model.movable.Point(30,30));
        tri.setColor(Color.orange);
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

        Translation tr = new Translation("anim1", c, 0,50, new Quad(), EasingType.EASE_IN, new Point(100,100), new Point(200,200));
        film.addAnimation(tr);

        Rotation rot = new Rotation("anim2", square, 0,50,new Sine(), EasingType.EASE_IN, 45);
        film.addAnimation(rot);

        Scaling scal = new Scaling("anim3", tri, 0, 50, 1.5);
        film.addAnimation(scal);

        ChangeStrokeThickness cst = new ChangeStrokeThickness("anim4", rect, 0, 50, 20);
        film.addAnimation(cst);

        ChangeColor chcol = new ChangeColor("anim5", eqtri, 0, 50, new Circ(), EasingType.EASE_IN, 45, new Point(100,100));
        film.addAnimation(chcol);

        film.saveToFile("film1.xml");

        Film f2 = Film.fromFile("film1.xml");
        f2.saveToFile("film2.xml");


    }
}
