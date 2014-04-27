
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
            builder.append("Square [");
        builder.append("strokeThickness=").append(strokeThickness);
        builder.append(", gravity_center=").append(gravity_center);
        builder.append(", gravity_center_personalised=").append(gravity_center_personalised);
            builder.append(",\nside_length=").append(length);
            builder.append(",\npoint_haut_gauche=").append(pointhg);
            builder.append(", point_haut_droit=").append(pointhd);
            builder.append(",\npoint_bas_gauche=").append(pointbg);
            builder.append(", point_bas_droite=").append(pointbd);
            builder.append("]");
            return builder.toString();
        }


}
