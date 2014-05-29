
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
public class CubicCurve extends Line {


    //          Attributs
    //---------------------------

        Point point_controle1;
        Point point_controle2;

    //          Constructeur
    //---------------------------

        public CubicCurve(Point depart, Point arrivee, Point controle1, Point controle2) {
            super(depart, arrivee);
            this.point_controle1 = controle1;
            this.addPoint(controle1);
            this.point_controle2 = controle2;
            this.addPoint(controle2);
        }

        public CubicCurve(Point depart, Point arrivee, Point controle1, Point controle2, int strokeThickness) {
            super(depart, arrivee, strokeThickness);
            this.point_controle1 = controle1;
            this.addPoint(controle1);
            this.point_controle2 = controle2;
            this.addPoint(controle2);
        }
    
    //          Accesseurs
    //----------------------------


        public Point getPointControle1() {
            return point_controle1;
        }
        
        public void setPointControle1(Point p) {
            this.removePoint(this.getPointControle1());
            this.addPoint(p);
            this.point_controle1 = p;
        }

        public void setPointControle1(double x, double y) {
            this.setPointControle1(new Point(x,y));
        }

        public Point getPointControle2() {
            return point_controle2;
        }
        
        public void setPointControle2(Point p) {
            this.removePoint(this.getPointControle2());
            this.addPoint(p);
            this.point_controle2 = p;
        }

        public void setPointControle2(double x, double y) {
            this.setPointControle2(new Point(x,y));
        }

        public Point[] getPointsControle() {
            Point[] res={this.getPointControle1(), this.getPointControle2()};
            return res;
        }

        public void setPointsControle(Point depart, Point arrivee) {
            this.setPointControle1(depart);
            this.setPointControle2(arrivee);
        }
        
        public void setPointsControle(double x_point_controle1, double y_point_controle1, double x_point_controle2, double y_point_controle2) {
            this.setPointControle1(x_point_controle1,y_point_controle1);
            this.setPointControle2(x_point_controle2,y_point_controle2);
        }

    //          Methodes
    //----------------------------


        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("CubicCurve");
            builder.append(super.toString());
            builder.append(", point_controle1=").append(this.getPointControle1());
            builder.append(", point_controle2=").append(this.getPointControle2());
            builder.append("]");
            return builder.toString();
        }

    @Override
    public Element toXML() {
        return null;
    }
}
