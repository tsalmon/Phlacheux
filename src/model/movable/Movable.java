package model.movable;

import XML.XMLSerializable;
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

abstract public class Movable implements XMLSerializable{

    //          Attributs
    //---------------------------

        protected Point gravity_center;  
        protected boolean gravity_center_personalised;  
    
    
    
    //          Accesseurs
    //----------------------------
    
        public Point getGravityCenter(){
            return this.gravity_center;
        }
        public boolean getGravityCenterPerso(){
            return this.gravity_center_personalised;
        }
        
        public void setGravityCenterPerso(boolean b){
            this.gravity_center_personalised=b;
        }
        //set avec un Point (/!\ PASSAGE PAR REFERENCE, 
        //uniquement si on veut qu'il se deplace avec ce point)
        public void setGravityCenter(Point p){
            this.gravity_center=p;
            this.setGravityCenterPerso(true);
        }
        
        //set avec les coordonnées
        public void setGravityCenter(double x, double y){
            this.gravity_center.moveTo(x,y);
            this.setGravityCenterPerso(true);
        }
        
        protected void gcResfresh(){
            if(!this.getGravityCenterPerso()){
                this.autoGravityCenter();
            }
        }
            
        
    //     Transformations
    //----------------------------
        
        abstract public void rotation(double angle);

        abstract public void rotation(double angle, Point p);

        abstract public void translation(double x_from, double y_from, double x_to, double y_to);

        abstract public void translation(Point from, Point to);

        abstract public void changeStrokeThickness(int thickness);

        abstract public void scaling(double scale);

        abstract protected void autoGravityCenter();

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("gravity_center=").append(gravity_center);
            builder.append(", gravity_center_personalised=").append(gravity_center_personalised);
            return builder.toString();
        }
        
}
