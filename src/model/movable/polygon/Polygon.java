
package model.movable.polygon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.movable.Figure;
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
    abstract public class Polygon extends Figure{

    
    //          Constructeur
    //----------------------------

        protected Polygon(ArrayList<PointPlacheux> points) {
            super(points);
        }

        protected Polygon(Element xml){
            super(xml);
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

    public Element toXML(){
        Element el = super.toXML();
        return el;
    }

}
