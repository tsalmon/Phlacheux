
package model.animation;

import java.awt.Color;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;
import model.easing.*;
import model.gestionnary.StateGestionnary;
import model.movable.Figure;
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
public class ChangeColor extends Animation{


    //          Attributs
    //---------------------------

        protected Color color_to;
        
        
    //        Constructeur
    //---------------------------

    public ChangeColor(String name, Movable movable, double debut, double fin, Easing easing, EasingType easing_type,  Color color) {
        super(name, movable, debut, fin, easing, easing_type);
        this.setColor_to(color);
        
    }
    public ChangeColor(String name, Movable movable, double debut, double fin, Color color) {
        super(name, movable, debut, fin, new Linear(),  EasingType.EASE_NONE);
        this.setColor_to(color);
    }

    //          Accesseurs
    //----------------------------
    public Color getColor_to() {
        return color_to;
    }

    public void setColor_to(Color color_to) {
        this.color_to = color_to;
    }
    
    

    //          Methodes
    //----------------------------
        
        @Override
        public void isAdded(){
            for(Figure f : this.movable.getAllFigures()){
                StateGestionnary.getInstance().addColor(f.getName(),  this.getColor_to(), this.getDebut());
            }
        }

        @Override
        public void isRemoved() {
            for(Figure f : this.movable.getAllFigures()){
                StateGestionnary.getInstance().removeColor(f.getName(), this.getFin());
            }
        }
        
        @Override
        public void goToTime(double t){
            StateGestionnary sg = StateGestionnary.getInstance();
            for(Figure f : this.movable.getAllFigures()){
                f.changeColor(this.getColorAt(t, f));
            }
        }
        
        protected Color getColorAt(double t, Figure m){
            StateGestionnary sg=StateGestionnary.getInstance();
            Set<Double> times = sg.getColorTimes(m.getName());
            double max=0;
            for (double time : times){
                time>max && time < t ? max=time;
            }
            Color last= sg.getColor(m.getName(), t);
            Collections.sort(times);
            float r = (float)this.applyEasing(last.getRed(), t, this.color_to.getRed(), this.getFin()-this.getDebut());
            float g = (float)this.applyEasing(last.getGreen(), t, this.color_to.getGreen(), this.getFin()-this.getDebut());
            float b = (float)this.applyEasing(last.getBlue(), t, this.color_to.getBlue(), this.getFin()-this.getDebut());
            return new Color(r,g,b);
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
