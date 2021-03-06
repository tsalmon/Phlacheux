
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
public class Square extends Rectangle{

    
    //       Constructeurs
    //----------------------------
    
        public Square(double length, PointPlacheux point_haut_gauche) {
            super(length, length, point_haut_gauche);
        }

        public Square(Element xml){
            super(xml);
        }
        
    //          Accesseurs
    //----------------------------
    

        public double getSideLength() {
            return this.getLength();
        }

        public void setSideLength(double length) {
            this.setLength(length);
            this.setWidth(length);
        }        
    

    //          Methodes
    //----------------------------

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(super.toString("Square"));
            builder.append("]");
            return builder.toString();
        }

        protected String toString(String name) {
            StringBuilder builder = new StringBuilder();
            builder.append(super.toString(name));
            return builder.toString();
        }

        public Element toXML(){
            Element el = super.toXML();

            el.setAttribute("type", "square");

            return el;
        }
}
