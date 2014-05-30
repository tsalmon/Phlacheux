
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
public class ChangeStrokeThickness extends Animation{


    //          Attributs
    //---------------------------

        protected double thickness_difference;
        
    //        Constructeur
    //---------------------------

    public ChangeStrokeThickness(String name, Movable movable, double debut, double fin, double current_time, Easing easing, Easing_Type easing_type,  double thickness_difference) {
        super(name, movable, debut, fin, current_time, easing, easing_type);
        this.setThickness(thickness_difference);
    }

    public ChangeStrokeThickness(String name, Movable movable, double debut, double fin, double current_time,  double thickness_difference) {
        super(name, movable, debut, fin, current_time, new Linear(),  Easing_Type.EASE_NONE);
        this.setThickness(thickness_difference);
    }

    //          Accesseurs
    //----------------------------
    
        public double getThickness() {
            return thickness_difference;
        }

        public void setThickness(double thickness) {
            this.thickness_difference = thickness;
        }

    
    //          Methodes
    //----------------------------

        
        public void goToTime(double t){
            double thickness_to_apply=(this.getThicknessAt(t)-this.getThicknessAt(this.getCurrent()));
            this.getMovable().scaling(thickness_to_apply);
        }
        
        protected double getThicknessAt(double t){
            return this.applyEasing(1, t, this.getThickness(), this.getFin()-this.getDebut());
        }

        @Override
        public Element toXML(){
            Element el = super.toXML();

            el.setAttribute("type", "change_stroke_thickness");
            el.setAttribute("thickness_difference", Double.toString(this.getThickness()));

            return el;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("ChangeStrokeThickness [");
            builder.append("thickness_difference=").append(thickness_difference);
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
