
package model.movable.polygon;

import java.util.ArrayList;
import java.util.Iterator;

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

    public Element toXML(){
        Element el = super.toXML();

        el.setAttribute("type", "polygon");

        Element pointsElement = new Element("points");
        Iterator it = points.iterator();
        while (it.hasNext()){
            Point p = (Point) it.next();
            Element pointElement = new Element("point");
            pointElement.setAttribute("x", Double.toString(p.getX()));
            pointElement.setAttribute("y", Double.toString(p.getY()));
            pointsElement.addContent(pointElement);
        }

        el.addContent(pointsElement);

        return el;
    }

}
