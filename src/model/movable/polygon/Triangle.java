
package model.movable.polygon;

import java.util.ArrayList;
import model.movable.Point;
import org.jdom2.Element;

/**
 *
 * @author Josian Chevalier, Vladislav Fitc, Thomas Salmon
 *
 * Modèle
 *
 * Projet Interface Graphique, Paris 7, Master 1, 2013-2014
 *
 */
public class Triangle extends Polygon{


    //          Attributs
    //---------------------------
    protected Point sommet1;
    protected Point sommet2;
    protected Point sommet3;

    //          Constructeur
    //----------------------------
    
    public Triangle(Point p1, Point p2, Point p3) {
        super(new ArrayList<Point>());
        this.sommet1 = p1;
        this.sommet2 = p2;
        this.sommet3 = p3;
        this.addPoint(this.sommet1);
        this.addPoint(this.sommet2);
        this.addPoint(this.sommet3);
    }
    
    
    
    //          Accesseurs
    //----------------------------



    public Point getSommet1() {
        return this.sommet1;
    }

    public void setSommet1(Point p) {
        this.removePoint(this.sommet1);
        this.sommet1 = p;
        this.addPoint(this.sommet1);
    }
    
    public void setSommet1(double x, double y) {
        this.setSommet1(new Point(x,y));
    }

    public Point getSommet2() {
        return this.sommet2;
    }

    public void setSommet2(Point p) {
        this.removePoint(this.sommet2);
        this.sommet2 = p;
        this.addPoint(this.sommet2);
    }
    
    public void setSommet2(double x, double y) {
        this.setSommet2(new Point(x,y));
    }

    public Point getSommet3() {
        return this.sommet3;
    }

    public void setSommet3(Point p) {
        this.removePoint(this.sommet3);
        this.sommet3 = p;
        this.addPoint(this.sommet3);
    }
    
    public void setSommet3(double x, double y) {
        this.setSommet3(new Point(x,y));
    }
    
    public Point[] getPoints() {
        return this.points.toArray(new Point[3]);
    }

    public void setSommets(Point p1, Point p2,Point p3) {
        this.setSommet1(p1);
        this.setSommet2(p2);
        this.setSommet3(p3);
    }

    public void setSommets(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.setSommet1(x1,y1);
        this.setSommet2(x2,y2);
        this.setSommet3(x3,y3);
    }

    
    //          Methodes
    //----------------------------

    

    //          Methodes
    //----------------------------

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(super.toString("Triangle"));
            builder.append("]");
            return builder.toString();
        }

        protected String toString(String name) {
            StringBuilder builder = new StringBuilder();
            builder.append(super.toString(name));
            return builder.toString();
        }

    @Override
    public Element toXML() {
        return null;
    }
}
