
package model.animation;

import model.easing.*;
import model.movable.Movable;
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
public class Rotation extends Animation{


    //          Attributs
    //---------------------------

        protected Point center;
        protected double angle;
        
        
    //        Constructeur
    //---------------------------

    public Rotation(Movable movable, double debut, double fin, double current_time, Easing easing, String easing_type,  double angle, Point centre) {
        super(movable, debut, fin, current_time, easing, easing_type);
        this.setCenter(centre);
        this.setAngle(angle);
    }

    public Rotation(Movable movable, double debut, double fin, double current_time, Easing easing, String easing_type,  double angle) {
        super(movable, debut, fin, current_time, easing, easing_type);
        this.setCenter(movable.getGravityCenter());
        this.setAngle(angle);
    }

    public Rotation(Movable movable, double debut, double fin, double current_time,  double angle) {
        super(movable, debut, fin, current_time, new Linear(),  "easeNone");
        this.setCenter(movable.getGravityCenter());
        this.setAngle(angle);
    }

    public Rotation(Movable movable, double debut, double fin, double current_time,  double angle, Point centre) {
        super(movable, debut, fin, current_time, new Linear(),  "easeNone");
        this.setCenter(centre);
        this.setAngle(angle);
    }
    


    //          Accesseurs
    //----------------------------
    
    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }


    //          Methodes
    //----------------------------
        
        public void goToTime(double t){
            double angle_to_apply=this.getAngleAt(t)-this.getAngleAt(this.getCurrent());
            this.getMovable().rotation(angle_to_apply, this.getCenter());
        }
        
        protected double getAngleAt(double t){
            switch(this.getEasing_Type()){
                case "easeIn":
                    return this.getEasing().easeIn(0, t, this.getAngle(), this.getFin()-this.getDebut());
                case "easeOut":
                    return this.getEasing().easeOut(0, t, this.getAngle(), this.getFin()-this.getDebut());
                case "easeInOut":
                    return this.getEasing().easeInOut(0, t, this.getAngle(), this.getFin()-this.getDebut());
                case "easeNone":
                    return this.getEasing().easeNone(0, t, this.getAngle(), this.getFin()-this.getDebut());
                default:
                    return this.getEasing().easeNone(0, t, this.getAngle(), this.getFin()-this.getDebut());
            }
        }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Rotation [");
        builder.append("angle=").append(angle);
        builder.append(", center=").append(center);
        builder.append(", current=").append(current);
        builder.append(", debut=").append(debut);
        builder.append(", easing=").append(easing);
        builder.append(", easing_Type=").append(easing_Type);
        builder.append(", fin=").append(fin);
        builder.append(", movable=").append(movable);
        builder.append("]");
        return builder.toString();
    }

        
}
