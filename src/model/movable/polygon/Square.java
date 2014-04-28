
package model.movable.polygon;

import java.util.ArrayList;
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
public class Square extends Rectangle{

    
    //       Constructeurs
    //----------------------------
    
        public Square(int length, Point point_haut_gauche) {
            super(length, length, point_haut_gauche);
        }
        
    //          Accesseurs
    //----------------------------
    

        public int getSideLength() {
            return this.getLength();
        }

        public void setSideLength(int length) {
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


}
