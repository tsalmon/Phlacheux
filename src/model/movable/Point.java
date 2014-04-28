
package model.movable;
import java.lang.Math.*;

/**
 *
 * @author Josian Chevalier, Vladislav Fitc, Thomas Salmon
 *
 * Modèle
 *
 * Projet Interface Graphique, Paris 7, Master 1, 2013-2014
 *
 */


public class Point {

    //          Attributs
    //---------------------------
       
        protected int x;
        protected int y;
    
    //         Constructeur
    //----------------------------
        
        public Point(int abs, int ord){
            x=abs;
            y=ord;
        }
        
    //          Accesseurs
    //----------------------------
    
        public int getX(){
            return this.x;
        }    

        public int getY(){
            return this.y;
        }    

        public void setX(int abs){
            this.x=abs;
        }    

        public void setY(int ord){
            this.y=ord;
        }    

        public void moveTo(int abs, int ord){
            this.setX(abs);
            this.setY(ord);
        }
        
    //          Methode
    //----------------------------
        
        // angle en degres
        public void rotateAround(int angle, Point p){
            double angle_rad=(Math.PI*(angle))/180;
            this.rotateAround(angle_rad, p);
        }
        
        // angle en radiants
        public void rotateAround(double angle, Point p){
                int dep_x=this.getX()-p.getX();
                int dep_y=this.getY()-p.getY();    
                double new_x=( dep_x * Math.cos(angle) ) - (dep_y * Math.sin(angle))+p.getX();
                double new_y=( dep_x * Math.sin(angle) ) + (dep_y * Math.cos(angle))+p.getY();
                this.setX((int)Math.round(new_x));
                this.setY((int)Math.round(new_y));
        }

        public void translation(Point from, Point to) {
            this.setX(this.getX()+to.getX()-from.getX());
            this.setY(this.getY()+to.getY()-from.getY());
        }

        public void translation(int x_from, int y_from, int x_to, int y_to){
            this.setX(this.getX()+x_to-x_from);
            this.setY(this.getY()+y_to-y_from);
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("Point [");
            builder.append("x=").append(x);
            builder.append(", y=").append(y);
            builder.append("]");
            return builder.toString();
        }
        
        
    
}
