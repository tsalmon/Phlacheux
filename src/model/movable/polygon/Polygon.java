
package model.movable.polygon;

import java.util.ArrayList;
import model.movable.Figure;
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
    abstract public class Polygon extends Figure{

    
    //          Constructeur
    //----------------------------
    
        protected Polygon(ArrayList<Point> points) {
            super(points);
        }
    

    //          Methodes
    //----------------------------

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append(",\npoints=").append(points);
        builder.append("]");
        return builder.toString();
    }
    
    protected String toString(String name) {
        StringBuilder builder = new StringBuilder();
        builder.append(super.toString(name));
        builder.append(",\npoints=").append(points);
        return builder.toString();
    }

        

}
