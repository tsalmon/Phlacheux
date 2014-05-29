
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
public class Segment extends Line{


    //          Constructeur
    //---------------------------

        public Segment(Point point1, Point point2) {
            super(point1, point2);
        }

        public Segment(Point point1, Point point2, int strokeThickness) {
            super(point1, point2, strokeThickness);
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
        return null;
    }
}
