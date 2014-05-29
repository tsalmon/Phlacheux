
package model.movable.line;

import model.movable.Figure;
import model.movable.Point;
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

        protected Point point_depart;
        protected Point point_arrivee;

        
    //          Constructeur
    //----------------------------
        
        public Line(Point depart, Point arrivee) {
            super();
            this.point_depart = depart;
            this.point_arrivee = arrivee;
            this.addPoint(depart);
            this.addPoint(arrivee);
        }
        
        public Line(Point depart, Point arrivee, int strokeThickness) {
            super();
            this.point_depart = depart;
            this.point_arrivee = arrivee;
            this.addPoint(depart);
            this.addPoint(arrivee);
            this.changeStrokeThickness(strokeThickness);
        }
        
        
    //          Accesseurs
    //----------------------------


        public Point getPointDepart() {
            return point_depart;
        }

        public Point getPointArrivee() {
            return point_arrivee;
        }

        public void setPointDepart(Point p) {
            this.removePoint(this.getPointDepart());
            this.addPoint(p);
            this.point_depart = p;
        }

        public void setPointDepart(int x, int y) {
            this.setPointDepart(new Point(x,y));
        }

        public void setPointArrivee(Point p) {
            this.removePoint(this.getPointArrivee());
            this.addPoint(p);
            this.point_arrivee = p;
        }

        public void setPointArrivee(int x, int y) {
            this.setPointArrivee(new Point(x,y));
        }

        public Point[] getPointsTerminaison() {
            Point[] res={this.getPointDepart(), this.getPointArrivee()};
            return res;
        }

        public void setPointsTerminaison(Point depart, Point arrivee) {
            this.setPointDepart(depart);
            this.setPointArrivee(arrivee);
        }
        
        public void setPointsTerminaison(int x_point_depart, int y_point_depart, int x_point_arrivee, int y_point_arrivee) {
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

            el.setAttribute("type", "line");
            el.setAttribute("srcX", Integer.toString(point_depart.getX()));
            el.setAttribute("srcY", Integer.toString(point_depart.getY()));
            el.setAttribute("dstX", Integer.toString(point_arrivee.getX()));
            el.setAttribute("dstY", Integer.toString(point_arrivee.getY()));

            return el;
        }

}
