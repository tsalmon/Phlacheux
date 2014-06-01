
package model.movable.circle;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import model.movable.Figure;
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
public class Circle  extends Figure{


    //          Attributs
    //---------------------------
        protected Point center;
        protected double radius;

    
    //          Constructeur
    //----------------------------


        public Circle(Point c, double radius) {
            this.center = c;
            this.addPoint(c);
            this.radius = radius;
        }
        
        public Circle(double x_center, double y_center, double radius) {
            this.center = new Point(x_center,y_center);
            this.addPoint(this.center);
            this.radius = radius;
        }

        public Circle(Element xml) {
            super(xml);

            double centerX = Double.parseDouble(xml.getAttributeValue("centerX"));
            double centerY = Double.parseDouble(xml.getAttributeValue("centerY"));
            double radius  = Double.parseDouble(xml.getAttributeValue("radius"));
            this.center = new Point(centerX, centerY);
            this.addPoint(this.center);
            this.radius = radius;
        }

    
    //          Accesseurs
    //----------------------------


        public Point getCenter() {
            return center;
        }

        public void setCenter(Point center) {
            this.removePoint(this.center);
            this.center = center;
            this.addPoint(this.center);
        }
        
        public void setCenter(double x_center, double y_center) {
            this.setCenter(new Point(x_center,y_center));
        }

        public double getRadius() {
            return radius;
        }

        public void setRadius(double radius) {
            this.radius = radius;
        }

    //          Methodes
    //----------------------------

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
            builder.append("Circle");
            builder.append(super.toString());
        builder.append(", center=").append(center);
        builder.append(", radius=").append(radius);
        builder.append("]");
        return builder.toString();
    }

    @Override
    public Shape getShape(){
		Ellipse2D p = new Ellipse2D.Double(center.getX() - radius, center.getY() - radius, radius, radius);
		return p;
    }

    @Override
    public Element toXML() {
        Element el = super.toXML();

        el.setAttribute("type", "circle");

        el.setAttribute("centerX", Double.toString(center.getX()));
        el.setAttribute("centerY", Double.toString(center.getY()));
        el.setAttribute("radius", Double.toString(radius));

        return el;
    }
}
