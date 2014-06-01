
package model.movable.line;

import model.movable.Figure;
import model.movable.PointPlacheux;
import org.jdom2.Element;

import java.util.Iterator;

/**
 *
 * @author Josian Chevalier, Vladislav Fitc, Thomas Salmon
 *
 * Modèle
 *
 * Projet Interface Graphique, Paris 7, Master 1, 2013-2014
 *
 */
abstract public class Line extends Figure {


    //          Attributs
    //---------------------------

        protected PointPlacheux point_depart;
        protected PointPlacheux point_arrivee;

        
    //          Constructeur
    //----------------------------
        
        public Line(PointPlacheux depart, PointPlacheux arrivee) {
            this.point_depart = depart;
            this.point_arrivee = arrivee;
            this.addPoint(depart);
            this.addPoint(arrivee);
        }
        
        public Line(PointPlacheux depart, PointPlacheux arrivee, double strokeThickness) {
            this.point_depart = depart;
            this.point_arrivee = arrivee;
            this.addPoint(depart);
            this.addPoint(arrivee);
            this.changeStrokeThickness(strokeThickness);
        }

        public Line(Element xml) {
            super(xml);
            double srcX = Double.parseDouble(xml.getAttributeValue("srcX"));
            double srcY = Double.parseDouble(xml.getAttributeValue("srcY"));
            double dstX = Double.parseDouble(xml.getAttributeValue("dstX"));
            double dstY = Double.parseDouble(xml.getAttributeValue("dstY"));

            this.point_depart = new PointPlacheux(srcX, srcY);
            this.point_arrivee = new PointPlacheux(dstX, dstY);
            this.addPoint(this.point_depart);
            this.addPoint(this.point_arrivee);
        }



    //          Accesseurs
    //----------------------------


        public PointPlacheux getPointDepart() {
            return point_depart;
        }

        public PointPlacheux getPointArrivee() {
            return point_arrivee;
        }

        public void setPointDepart(PointPlacheux p) {
            this.removePoint(this.getPointDepart());
            this.addPoint(p);
            this.point_depart = p;
        }

        public void setPointDepart(double x, double y) {
            this.setPointDepart(new PointPlacheux(x,y));
        }

        public void setPointArrivee(PointPlacheux p) {
            this.removePoint(this.getPointArrivee());
            this.addPoint(p);
            this.point_arrivee = p;
        }

        public void setPointArrivee(double x, double y) {
            this.setPointArrivee(new PointPlacheux(x,y));
        }

        public PointPlacheux[] getPointsTerminaison() {
            PointPlacheux[] res={this.getPointDepart(), this.getPointArrivee()};
            return res;
        }

        public void setPointsTerminaison(PointPlacheux depart, PointPlacheux arrivee) {
            this.setPointDepart(depart);
            this.setPointArrivee(arrivee);
        }
        
        public void setPointsTerminaison(double x_point_depart, double y_point_depart, double x_point_arrivee, double y_point_arrivee) {
            this.setPointDepart(x_point_depart,y_point_depart);
            this.setPointArrivee(x_point_arrivee,y_point_arrivee);
        }
        
        
    //          Methodes
    //----------------------------

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(super.toString());
            builder.append(",\npoint_depart=").append(point_depart);
            builder.append(", point_arrivee=").append(point_arrivee);
            return builder.toString();
        }

        public Element toXML(){
            Element el = super.toXML();

            el.setAttribute("srcX", Double.toString(point_depart.getX()));
            el.setAttribute("srcY", Double.toString(point_depart.getY()));
            el.setAttribute("dstX", Double.toString(point_arrivee.getX()));
            el.setAttribute("dstY", Double.toString(point_arrivee.getY()));

            return el;
        }

}
