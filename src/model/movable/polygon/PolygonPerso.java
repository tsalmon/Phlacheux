
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
public class PolygonPerso extends Polygon{


    //          Attributs
    //---------------------------


    
    //          Constructeur
    //----------------------------
    
        public PolygonPerso(ArrayList<Point> points) {
            super(points);
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


}
