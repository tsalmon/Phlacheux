package tests;

import model.animation.*;
import model.easing.Bounce;
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
        //TODO::problème de gravity center quand on ajoute un polygon personalisé!
        //group1.addMovable(poly);
        group1.addMovable(rect);
        group1.addMovable(square);
        group1.addMovable(tri);

        film.addGroup(group1);

        MovableGroup group2 = new MovableGroup();
        group2.addMovable(eqtri);
        group2.addMovable(group1);
        film.addGroup(group2);

//        Translation t = new Translation("anim1", tri, 1, 200, new Quad(), EasingType.EASE_IN_OUT, new model.movable.Point(0,0), new model.movable.Point(10,10));
//        film.addAnimation(t);

//        Rotation r = new Rotation("anim2", square, 1, 100, 1, new Bounce(), EasingType.EASE_IN, 45);
//        film.addAnimation(r);

//        Scaling sc = new Scaling("anim3", rect, 1, 50, 1, new Sine(), EasingType.EASE_IN, 1.5);
//        film.addAnimation(sc);
//
//        ChangeStrokeThickness cst = new ChangeStrokeThickness("anim4", rect, 10, 50, 1, 20);
//        film.addAnimation(cst);

//        ChangeColor cc = new ChangeColor("anim5", rect, 10, 100, 1, 15);
//        film.addAnimation(cc);

        film.saveToFile("film1.xml");

        Film f2 = Film.fromFile("film1.xml");
        f2.saveToFile("film2.xml");


    }
}
