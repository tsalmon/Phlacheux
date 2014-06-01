
package model.movable.polygon;

import java.util.ArrayList;
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
public class Triangle extends Polygon{


    //          Attributs
    //---------------------------
    protected PointPlacheux sommet1;
    protected PointPlacheux sommet2;
    protected PointPlacheux sommet3;

    //          Constructeur
    //----------------------------
    
    public Triangle(PointPlacheux p1, PointPlacheux p2, PointPlacheux p3) {
        super(new ArrayList<PointPlacheux>());
        this.sommet1 = p1;
        this.sommet2 = p2;
        this.sommet3 = p3;
        this.addPoint(this.sommet1);
        this.addPoint(this.sommet2);
        this.addPoint(this.sommet3);
    }
    
    public Triangle(Element xml){
        super(xml);

        this.sommet1 = new PointPlacheux(Double.parseDouble(xml.getAttributeValue("vertex1x")), Double.parseDouble(xml.getAttributeValue("vertex1y")));
        this.sommet2 = new PointPlacheux(Double.parseDouble(xml.getAttributeValue("vertex2x")), Double.parseDouble(xml.getAttributeValue("vertex2y")));
        this.sommet3 = new PointPlacheux(Double.parseDouble(xml.getAttributeValue("vertex3x")), Double.parseDouble(xml.getAttributeValue("vertex3y")));

        this.addPoint(this.sommet1);
        this.addPoint(this.sommet2);
        this.addPoint(this.sommet3);

    }
    
    //          Accesseurs
    //----------------------------



    public PointPlacheux getSommet1() {
        return this.sommet1;
    }

    public void setSommet1(PointPlacheux p) {
        this.removePoint(this.sommet1);
        this.sommet1 = p;
        this.addPoint(this.sommet1);
    }
    
    public void setSommet1(double x, double y) {
        this.setSommet1(new PointPlacheux(x,y));
    }

    public PointPlacheux getSommet2() {
        return this.sommet2;
    }

    public void setSommet2(PointPlacheux p) {
        this.removePoint(this.sommet2);
        this.sommet2 = p;
        this.addPoint(this.sommet2);
    }
    
    public void setSommet2(double x, double y) {
        this.setSommet2(new PointPlacheux(x,y));
    }

    public PointPlacheux getSommet3() {
        return this.sommet3;
    }

    public void setSommet3(PointPlacheux p) {
        this.removePoint(this.sommet3);
        this.sommet3 = p;
        this.addPoint(this.sommet3);
    }
    
    public void setSommet3(double x, double y) {
        this.setSommet3(new PointPlacheux(x,y));
    }
    
    public void setSommets(PointPlacheux p1, PointPlacheux p2,PointPlacheux p3) {
        this.setSommet1(p1);
        this.setSommet2(p2);
        this.setSommet3(p3);
    }

    public void setSommets(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.setSommet1(x1,y1);
        this.setSommet2(x2,y2);
        this.setSommet3(x3,y3);
    }


    //          Methodes
    //----------------------------

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(super.toString("Triangle"));
            builder.append("]");
            return builder.toString();
        }

    @Override
        protected String toString(String name) {
            StringBuilder builder = new StringBuilder();
            builder.append(super.toString(name));
            return builder.toString();
        }

        @Override
        public Element toXML() {
            Element el = super.toXML();

            el.setAttribute("type", "triangle");

            el.setAttribute("vertex1x", Double.toString(sommet1.getX()));
            el.setAttribute("vertex1y", Double.toString(sommet1.getY()));

            el.setAttribute("vertex2x", Double.toString(sommet2.getX()));
            el.setAttribute("vertex2y", Double.toString(sommet2.getY()));

            el.setAttribute("vertex3x", Double.toString(sommet3.getX()));
            el.setAttribute("vertex3y", Double.toString(sommet3.getY()));

            return el;
        }
}
