
package model.animation;

import model.easing.*;
import model.movable.Movable;
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

    public ChangeStrokeThickness(String name, Movable movable, double debut, double fin, Easing easing, EasingType easing_type,  double thickness_difference) {
        super(name, movable, debut, fin, easing, easing_type);
        this.setThickness(thickness_difference);
    }

    public ChangeStrokeThickness(String name, Movable movable, double debut, double fin,  double thickness_difference) {
        super(name, movable, debut, fin, new Linear(),  EasingType.EASE_NONE);
        this.setThickness(thickness_difference);
    }

    public ChangeStrokeThickness(Element xml){
        super(xml);
        setThickness(Double.parseDouble(xml.getAttributeValue("thickness")));
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
            el.setAttribute("thickness", Double.toString(this.getThickness()));

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
