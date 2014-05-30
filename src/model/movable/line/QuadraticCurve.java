
package model.movable.line;

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
public class QuadraticCurve extends Line {


    //          Attributs
    //---------------------------

        Point point_controle;

    //          Constructeur
    //---------------------------

        public QuadraticCurve(Point depart, Point arrivee, Point controle) {
            super(depart, arrivee);
            this.point_controle = controle;
            this.addPoint(controle);
        }

        public QuadraticCurve(Point depart, Point arrivee, Point controle, int strokeThickness) {
            super(depart, arrivee, strokeThickness);
            this.point_controle = controle;
            this.addPoint(controle);
        }
    
    //          Accesseurs
    //----------------------------


        public Point getPointControle() {
            return point_controle;
        }
        public void setPointControle(Point p) {
            this.removePoint(this.getPointControle());
            this.addPoint(p);
            this.point_controle = p;
        }

        public void setPointControle(double x, double y) {
            this.setPointControle(new Point(x,y));
        }

    //          Methodes
    //----------------------------


        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("QuadraticCurve");
            builder.append(super.toString());
            builder.append(", point_controle=").append(this.getPointControle());
            builder.append("]");
            return builder.toString();
        }

    @Override
    public Element toXML() {
        Element el = super.toXML();

        el.setAttribute("type", "quadraticLine");
        el.setAttribute("ctrlX", Integer.toString(point_controle.getX()));
        el.setAttribute("ctrlY", Integer.toString(point_controle.getY()));

        return el;
    }
}
