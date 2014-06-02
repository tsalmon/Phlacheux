
package model.animation;

import model.easing.*;
import model.movable.Movable;
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
public class Rotation extends Animation{


    //          Attributs
    //---------------------------

        protected PointPlacheux center;
        protected double angle;
        
        
    //        Constructeur
    //---------------------------

    public Rotation(String name, Movable movable, double debut, double fin, Easing easing, EasingType easing_type,  double angle, PointPlacheux centre) {
        super(name, movable, debut, fin, easing, easing_type);
        this.setCenter(centre);
        this.setAngle(angle);
    }

    public Rotation(String name, Movable movable, double debut, double fin, Easing easing, EasingType easing_type,  double angle) {
        super(name, movable, debut, fin, easing, easing_type);
        this.setCenter(movable.getGravityCenter());
        this.setAngle(angle);
    }

    public Rotation(String name, Movable movable, double debut, double fin, double angle) {
        super(name, movable, debut, fin, new Linear(),  EasingType.EASE_NONE);
        this.setCenter(movable.getGravityCenter());
        this.setAngle(angle);
    }

    public Rotation(String name, Movable movable, double debut, double fin, double angle, PointPlacheux centre) {
        super(name, movable, debut, fin, new Linear(),  EasingType.EASE_NONE);
        this.setCenter(centre);
        this.setAngle(angle);
    }

    public Rotation(Element xml){
        super(xml);
        setCenter(new PointPlacheux(Double.parseDouble(xml.getAttributeValue("pointX")), Double.parseDouble(xml.getAttributeValue("pointY"))));
        setAngle(Double.parseDouble(xml.getAttributeValue("angle")));
    }


    //          Accesseurs
    //----------------------------
    
    public PointPlacheux getCenter() {
        return center;
    }

    public void setCenter(PointPlacheux center) {
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
        
        @Override
        public void goToTime(double t){
            double angle_to_apply=this.getAngleAt(t)-this.getAngleAt(this.getCurrent());
            this.getMovable().rotation(angle_to_apply, this.getCenter());
            this.setCurrent(t);
        }
        
        protected double getAngleAt(double t){
            return this.applyEasing(0, t, this.getAngle(), this.getFin()-this.getDebut());
        }


    @Override
    public Element toXML(){
        Element el = super.toXML();

        el.setAttribute("type", "rotation");
        el.setAttribute("pointX", Double.toString(center.getX()));
        el.setAttribute("pointY", Double.toString(center.getY()));
        el.setAttribute("angle", Double.toString(angle));

        return el;
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
        builder.append(", easing_type=").append(easing_type);
        builder.append(", fin=").append(fin);
        builder.append(", movable=").append(movable);
        builder.append(", name=").append(name);
        builder.append("]");
        return builder.toString();
    }

        
}
