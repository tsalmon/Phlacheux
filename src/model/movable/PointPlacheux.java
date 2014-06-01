
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


public class PointPlacheux {

    //          Attributs
    //---------------------------
       
        protected double x;
        protected double y;
    
    //         Constructeur
    //----------------------------
        
        public PointPlacheux(double abs, double ord){
            x=abs;
            y=ord;
        }
        
    //          Accesseurs
    //----------------------------
    
        public double getX(){
            return this.x;
        }    

        public double getY(){
            return this.y;
        }    

        public void setX(double abs){
            this.x=abs;
        }    

        public void setY(double ord){
            this.y=ord;
        }    

        public void moveTo(double abs, double ord){
            this.setX(abs);
            this.setY(ord);
        }
        
    //          Methode
    //----------------------------
        
        // angle en degres
        public void rotateAroundDegres(double angle, PointPlacheux p){
            double angle_rad=(Math.PI*(angle))/180;
            this.rotateAroundRadian(angle_rad, p);
        }
        
        // angle en radiants
        public void rotateAroundRadian(double angle, PointPlacheux p){
                double dep_x=this.getX()-p.getX();
                double dep_y=this.getY()-p.getY();    
                double new_x=( dep_x * Math.cos(angle) ) - (dep_y * Math.sin(angle))+p.getX();
                double new_y=( dep_x * Math.sin(angle) ) + (dep_y * Math.cos(angle))+p.getY();
                this.setX(new_x);
                this.setY(new_y);
        }

        public void translation(PointPlacheux from, PointPlacheux to) {
            this.setX(this.getX()+to.getX()-from.getX());
            this.setY(this.getY()+to.getY()-from.getY());
        }

        public void translation(double x_from, double y_from, double x_to, double y_to){
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
