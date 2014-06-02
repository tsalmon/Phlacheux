
package model.animation;

import java.awt.Color;
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
public class ChangeBorderColor extends Animation{


    //          Attributs
    //---------------------------

        protected Color color_to;
        
        
    //        Constructeur
    //---------------------------

    public ChangeBorderColor(String name, Movable movable, double debut, double fin, Easing easing, EasingType easing_type,  Color color) {
        super(name, movable, debut, fin, easing, easing_type);
        this.setColor_to(color);
        
    }
    public ChangeBorderColor(String name, Movable movable, double debut, double fin, Color color) {
        super(name, movable, debut, fin, new Linear(),  EasingType.EASE_NONE);
        this.setColor_to(color);
    }

    public ChangeBorderColor(Element xml){
        super(xml);
        this.setColor_to(new Color(Float.parseFloat(xml.getAttributeValue("color_R")),Float.parseFloat(xml.getAttributeValue("color_G")), Float.parseFloat(xml.getAttributeValue("color_B"))));
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
                StateGestionnary.getInstance().addBorderColor(f.getName(),  this.getColor_to(), this.getFin());
            }
        }

        @Override
        public void isRemoved() {
            for(Figure f : this.movable.getAllFigures()){
                StateGestionnary.getInstance().removeBorderColor(f.getName(), this.getFin());
            }
        }
        
        @Override
        public void goToTime(double t){
            StateGestionnary sg = StateGestionnary.getInstance();
            for(Figure f : this.movable.getAllFigures()){
                f.changeBorderColor(this.getColorAt(t, f));
            }
            this.setCurrent(t);
        }
        
        protected Color getColorAt(double t, Figure m){
            StateGestionnary sg=StateGestionnary.getInstance();
            Set<Double> times = sg.getBorderColorsTimes(m.getName());
            Color from;
            double max=0;
            for (double time : times){
                if(time>max && time < t){max=time;}
            }
            if(max==0 && sg.getBorderColor(m.getName(), max)==null){
                from=m.getInitial_borderColor();
            }else {
            from=sg.getBorderColor(m.getName(), max);
            }
            float r = (float)this.applyEasing(from.getRed(), t, this.color_to.getRed()-from.getRed(), this.getFin()-this.getDebut());
            float g = (float)this.applyEasing(from.getGreen(), t, this.color_to.getGreen()-from.getGreen(), this.getFin()-this.getDebut());
            float b = (float)this.applyEasing(from.getBlue(), t, this.color_to.getBlue()-from.getBlue(), this.getFin()-this.getDebut());
            return new Color(r/255,g/255,b/255);
        }


    @Override
    public Element toXML(){
        Element el = super.toXML();

        el.setAttribute("type", "changeBorderColor");
        el.setAttribute("color_R", Float.toString(this.getColor_to().getRed()));
        el.setAttribute("color_G", Float.toString(this.getColor_to().getGreen()));
        el.setAttribute("color_B", Float.toString(this.getColor_to().getBlue()));

        return el;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ChangeBorderColor [");
        builder.append("color_to=").append(color_to);
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
