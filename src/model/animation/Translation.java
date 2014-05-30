
package model.animation;

import model.easing.*;
import model.movable.Movable;
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
public class Translation extends Animation{


    //          Attributs
    //---------------------------

        protected Point depart;
        protected Point arrivee;
        
        
    //        Constructeur
    //---------------------------

    public Translation(Movable movable, double debut, double fin, double current_time, Easing easing, String easing_type,  Point point_depart, Point point_arrivee) {
        super(movable, debut, fin, current_time, easing, easing_type);
        this.setPointDepart(point_depart);
        this.setPointArrivee(point_arrivee);
    }

    public Translation(Movable movable, double debut, double fin, double current_time, Easing easing, String easing_type,  Point point_arrivee) {
        super(movable, debut, fin, current_time, easing, easing_type);
        this.setPointDepart(movable.getGravityCenter());
        this.setPointArrivee(point_arrivee);
    }

    public Translation(Movable movable, double debut, double fin, double current_time, Point point_arrivee) {
        super(movable, debut, fin, current_time, new Linear(),  "easeNone");
        this.setPointDepart(movable.getGravityCenter());
        this.setPointArrivee(point_arrivee);
    }

    public Translation(Movable movable, double debut, double fin, double current_time, Point point_depart, Point point_arrivee) {
        super(movable, debut, fin, current_time, new Linear(),  "easeNone");
        this.setPointDepart(point_depart);
        this.setPointArrivee(point_arrivee);
    }
    


    //          Accesseurs
    //----------------------------
        
        public Point getPointDepart(){
         return depart;
        }

        public void setPointDepart(Point depart) {
            this.depart = depart;
        }

        public Point getPointArrivee() {
            return arrivee;
        }

        public void setPointArrivee(Point arrivee) {
            this.arrivee = arrivee;
        }
    
    
    
    //          Methodes
    //----------------------------
        
        public void goToTime(double t){
            double dx_to_apply=this.getDXAt(t)-this.getDXAt(this.getCurrent());
            double dy_to_apply=this.getDYAt(t)-this.getDYAt(this.getCurrent());
            Point depart_translation=this.getMovable().getGravityCenter();
            this.getMovable().translation(depart_translation,new Point(dx_to_apply, dy_to_apply));
        }
        
        protected double getDXAt(double t){
            switch(this.getEasing_Type()){
                case "easeIn":
                    return this.getEasing().easeIn(0, t, this.getPointArrivee().getX()-this.getPointDepart().getX(), this.getFin()-this.getDebut());
                case "easeOut":
                    return this.getEasing().easeOut(0, t, this.getPointArrivee().getX()-this.getPointDepart().getX(), this.getFin()-this.getDebut());
                case "easeInOut":
                    return this.getEasing().easeInOut(0, t, this.getPointArrivee().getX()-this.getPointDepart().getX(), this.getFin()-this.getDebut());
                case "easeNone":
                    return this.getEasing().easeNone(0, t, this.getPointArrivee().getX()-this.getPointDepart().getX(), this.getFin()-this.getDebut());
                default:
                    return this.getEasing().easeNone(0, t, this.getPointArrivee().getX()-this.getPointDepart().getX(), this.getFin()-this.getDebut());
            }
        }
        
        protected double getDYAt(double t){
            switch(this.getEasing_Type()){
                case "easeIn":
                    return this.getEasing().easeIn(0, t, this.getPointArrivee().getY()-this.getPointDepart().getY(), this.getFin()-this.getDebut());
                case "easeOut":
                    return this.getEasing().easeOut(0, t, this.getPointArrivee().getY()-this.getPointDepart().getY(), this.getFin()-this.getDebut());
                case "easeInOut":
                    return this.getEasing().easeInOut(0, t, this.getPointArrivee().getY()-this.getPointDepart().getY(), this.getFin()-this.getDebut());
                case "easeNone":
                    return this.getEasing().easeNone(0, t, this.getPointArrivee().getY()-this.getPointDepart().getY(), this.getFin()-this.getDebut());
                default:
                    return this.getEasing().easeNone(0, t, this.getPointArrivee().getY()-this.getPointDepart().getY(), this.getFin()-this.getDebut());
            }
        }


        @Override
        public Element toXML(){
            Element el = super.toXML();

            el.setAttribute("type", "translation");
            el.setAttribute("srcX", Double.toString(depart.getX()));
            el.setAttribute("srcY", Double.toString(depart.getY()));
            el.setAttribute("dstX", Double.toString(arrivee.getX()));
            el.setAttribute("dstY", Double.toString(arrivee.getY()));

            return el;
        }

        
}
