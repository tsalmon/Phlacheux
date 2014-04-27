
package model.movable.line;

import model.movable.Point;

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

        public void setPointControle(int x, int y) {
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
}
