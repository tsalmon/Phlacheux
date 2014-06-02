
package model.animation;

import java.util.Set;
import model.easing.*;
import model.gestionnary.StateGestionnary;
import model.movable.Figure;
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

        protected double thickness;
        
        
    //        Constructeur
    //---------------------------

    public ChangeStrokeThickness(String name, Movable movable, double debut, double fin, Easing easing, EasingType easing_type,  double thickness) {
        super(name, movable, debut, fin, easing, easing_type);
        this.setThickness(thickness);
        
    }
    public ChangeStrokeThickness(String name, Movable movable, double debut, double fin, double thickness) {
        super(name, movable, debut, fin, new Linear(),  EasingType.EASE_NONE);
        this.setThickness(thickness);
    }

    public ChangeStrokeThickness(Element xml){
        super(xml);
        this.setThickness(Double.parseDouble(xml.getAttributeValue("thickness")));
    }
    
    //          Accesseurs
    //----------------------------
    
    public double getThickness() {
        return this.thickness;
    }

    public void setThickness(double thickness) {
        this.thickness = thickness;
    }
    
    

    //          Methodes
    //----------------------------
        
        @Override
        public void isAdded(){
            for(Figure f : this.movable.getAllFigures()){
                StateGestionnary.getInstance().addStroke_Thickness(f.getName(),  this.getThickness(), this.getFin());
            }
        }

        @Override
        public void isRemoved() {
            for(Figure f : this.movable.getAllFigures()){
                StateGestionnary.getInstance().removeStroke_Thickness(f.getName(), this.getFin());
            }
        }
        
        @Override
        public void goToTime(double t){
            StateGestionnary sg = StateGestionnary.getInstance();
            for(Figure f : this.movable.getAllFigures()){
                f.changeStrokeThickness(this.getThicknessAt(t, f));
            }
            this.setCurrent(t);
        }
        
        protected double getThicknessAt(double t, Figure m){
            StateGestionnary sg=StateGestionnary.getInstance();
            Set<Double> times = sg.getStrokeThicknessesTimes(m.getName());
            double from;
            double max=0;
            for (double time : times){
                if(time>max && time < t){max=time;}
            }
            if(max==0 && sg.getStrokeThickness(m.getName(), max)==null){
                from=m.getInitial_strokeThickness();
            }else {
                from=sg.getStrokeThickness(m.getName(), max);
            }
            return this.applyEasing(from, t, this.thickness-from, this.getFin()-this.getDebut());
        }


    @Override
    public Element toXML(){
        Element el = super.toXML();

        el.setAttribute("type", "changeColor");
        el.setAttribute("thickness", Double.toString(this.getThickness()));

        return el;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ChangeStrokeThickness [");
        builder.append("thickness=").append(thickness);
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
