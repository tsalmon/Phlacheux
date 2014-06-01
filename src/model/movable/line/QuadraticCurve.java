
package model.movable.line;

import model.movable.PointPlacheux;
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

        PointPlacheux point_controle;

    //          Constructeur
    //---------------------------

        public QuadraticCurve(PointPlacheux depart, PointPlacheux arrivee, PointPlacheux controle) {
            super(depart, arrivee);
            this.point_controle = controle;
            this.addPoint(controle);
        }

        public QuadraticCurve(PointPlacheux depart, PointPlacheux arrivee, PointPlacheux controle, double strokeThickness) {
            super(depart, arrivee, strokeThickness);
            this.point_controle = controle;
            this.addPoint(controle);
        }

        public QuadraticCurve(Element xml){
            super(xml);

            double ctrlX = Double.parseDouble(xml.getAttributeValue("ctrlX"));
            double ctrlY = Double.parseDouble(xml.getAttributeValue("ctrlY"));

            this.point_controle = new PointPlacheux(ctrlX, ctrlY);
            this.addPoint(this.point_controle);
        }
    
    //          Accesseurs
    //----------------------------


        public PointPlacheux getPointControle() {
            return point_controle;
        }
        public void setPointControle(PointPlacheux p) {
            this.removePoint(this.getPointControle());
            this.addPoint(p);
            this.point_controle = p;
        }

        public void setPointControle(double x, double y) {
            this.setPointControle(new PointPlacheux(x,y));
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

        el.setAttribute("type", "quadraticCurve");
        el.setAttribute("ctrlX", Double.toString(point_controle.getX()));
        el.setAttribute("ctrlY", Double.toString(point_controle.getY()));

        return el;
    }
}
