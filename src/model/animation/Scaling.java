
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
public class Scaling extends Animation{


    //          Attributs
    //---------------------------

        protected double scale;
        
    //        Constructeur
    //---------------------------

    public Scaling(String name, Movable movable, double debut, double fin, double current_time, Easing easing, Easing_Type easing_type,  double scale) {
        super(name, movable, debut, fin, current_time, easing, easing_type);
        this.setScale(scale);
    }

    public Scaling(String name, Movable movable, double debut, double fin, double current_time,  double scale) {
        super(name, movable, debut, fin, current_time, new Linear(),  Easing_Type.EASE_NONE);
        this.setScale(scale);
    }

    //          Accesseurs
    //----------------------------
    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }
    

    //          Methodes
    //----------------------------

        
        public void goToTime(double t){
            double scale_to_apply=(this.getScaleAt(t)/this.getScaleAt(this.getCurrent()));
            this.getMovable().scaling(scale_to_apply);
        }
        
        protected double getScaleAt(double t){
            return this.applyEasing(1, t, this.getScale(), this.getFin()-this.getDebut());
        }

        @Override
        public Element toXML(){
            Element el = super.toXML();

            el.setAttribute("type", "scalling");
            el.setAttribute("scale", Double.toString(this.getScale()));

            return el;
        }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Scaling [");
        builder.append("scale=").append(scale);
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
