
package model.movable.polygon;

import java.util.ArrayList;
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
public class EquilateralTriangle extends Triangle {


    //          Constructeur
    //----------------------------
    
    public EquilateralTriangle(Point p1, Point p2) {
        super(p1,p2,p2);
        super.setSommet3(this.getLast(this.getSommet1(),this.getSommet2(),true));
    }


    
    //          Accesseurs
    //----------------------------


    public void setSommet1(Point p) {
        super.setSommet1(p);
        super.setSommet3(this.getLast(this.getSommet1(),this.getSommet2(),true));
    }
    
    public void setSommet2(Point p) {
        super.setSommet2(p);
        super.setSommet3(this.getLast(this.getSommet1(),this.getSommet2(),true));
    }
    
    public void setSommet3(Point p) {
        super.setSommet3(p);
        super.setSommet2(this.getLast(this.getSommet1(),this.getSommet3(),false));
    }

    //          Methodes
    //----------------------------
    
        private Point getLast(Point p1, Point p2,boolean up){
            
            double dx=p2.getX()-p1.getX();
            double dy=p2.getY()-p1.getY();
            
            double d=Math.sqrt(Math.pow(dx,2)
                              +Math.pow(dy,2));
            
            double scal = dx*d + 0;
            double angle = Math.acos(scal/(d*d));
            
            double o = (up) ? 1 : 1;
            
             Point res=new Point(
                    Math.cos((o*Math.PI/3)+angle)*d+p1.getX(), 
                    Math.sin((o*Math.PI/3)+angle)*d+p1.getY());
             
             return res;
        }
        
        
        
        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(super.toString("EquilateralTriangle"));
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

            el.setAttribute("type", "equilateralTriangle");

            return el;
        }

}
