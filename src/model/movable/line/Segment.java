
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
public class Segment extends Line{


    //          Constructeur
    //---------------------------

        public Segment(PointPlacheux point1, PointPlacheux point2) {
            super(point1, point2);
        }

        public Segment(PointPlacheux point1, PointPlacheux point2, double strokeThickness) {
            super(point1, point2, strokeThickness);
        }

        public Segment(Element xml) {
            super(xml);
        }

    //          Methodes
    //----------------------------

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("Segment");
            builder.append(super.toString());
            builder.append("]");
            return builder.toString();
        }


    @Override
    public Element toXML() {
        Element el = super.toXML();

        el.setAttribute("type", "segment");

        return el;
    }
}
