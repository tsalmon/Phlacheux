
package model.movable.circle;

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
public class Circle  extends Figure{


    //          Attributs
    //---------------------------
        protected Point center;
        protected int radius;

    
    //          Constructeur
    //----------------------------


        public Circle(Point c, int radius) {
            this.center = c;
            this.addPoint(c);
            this.radius = radius;
        }
        
        public Circle(int x_center, int y_center, int radius) {
            this.center = new Point(x_center,y_center);
            this.addPoint(this.center);
            this.radius = radius;
        }

    
    //          Accesseurs
    //----------------------------


        public Point getCenter() {
            return center;
        }

        public void setCenter(Point center) {
            this.removePoint(this.center);
            this.center = center;
            this.addPoint(this.center);
        }
        
        public void setCenter(int x_center, int y_center) {
            this.setCenter(new Point(x_center,y_center));
        }

        public int getRadius() {
            return radius;
        }

        public void setRadius(int radius) {
            this.radius = radius;
        }

    //          Methodes
    //----------------------------

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
            builder.append("Circle");
            builder.append(super.toString());
        builder.append(", center=").append(center);
        builder.append(", radius=").append(radius);
        builder.append("]");
        return builder.toString();
    }


    @Override
    public Element toXML() {
        return null;
    }
}
