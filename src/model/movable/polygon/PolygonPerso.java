
package model.movable.polygon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
public class PolygonPerso extends Polygon{


    //          Attributs
    //---------------------------


    
    //          Constructeur
    //----------------------------

        public PolygonPerso(ArrayList<Point> points) {
                super(points);
            }

        public PolygonPerso(Element xml) {
            super(xml);
            Element points = xml.getChild("points");
            List<Element> pointsElement = xml.getChild("points").getChildren();

            Iterator it = pointsElement.iterator();

            while (it.hasNext()){
                Element pointElement = (Element) it.next();

                double pX = Double.parseDouble(pointElement.getAttributeValue("x"));
                double pY = Double.parseDouble(pointElement.getAttributeValue("y"));

                this.addPoint(new Point(pX, pY));
            }
    }



    //          Accesseurs
    //----------------------------
        
        
        @Override
        public void addPoint(Point p){
            super.addPoint(p);
        }

        @Override
        public void removePoint(Point p){
            super.removePoint(p);
        }

        public ArrayList<Point> getPoints(){
            return this.points;
        }


    @Override
    public Element toXML() {
        Element el = super.toXML();

        el.setAttribute("type", "polygonPerso");

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
