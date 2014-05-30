
package model.animation;

import XML.XMLSerializable;
import model.easing.Easing;
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
public abstract class Animation implements XMLSerializable{

    //          Attributs
    //---------------------------

        protected Movable movable;
        protected Easing easing;
        protected double debut;
        protected double fin;  
        protected double current;
        
    
    //          Constructeur
    //----------------------------
        
        protected Animation(Movable m, double d, double f, double current_time, Easing e, String et){
            this.setMovable(m);
            this.setEasing(e);
            this.setDebut(d);
            this.setEasing_Type(et);
            this.setFin(f);
            this.setCurrent(current_time);
        }

    //          Accesseurs
    //----------------------------
        public double getCurrent() {
            return current;
        }

        public void setCurrent(double current) {
            this.current = current;
        }

        public Movable getMovable() {
            return movable;
        }

        public void setMovable(Movable movable) {
            this.movable = movable;
        }

        public Easing getEasing() {
            return easing;
        }

        public void setEasing(Easing easing) {
            this.easing = easing;
        }

        public double getDebut() {
            return debut;
        }

        public void setDebut(double debut) {
            this.debut = debut;
        }

        public double getFin() {
            return fin;
        }

        public void setFin(double fin) {
            this.fin = fin;
        }

        public enum getEasing_Type() {
            return easing_Type;
        }

        public void setEasing_Type(String easing_Type) {
            this.easing_Type = easing_Type;
        }


    //          Methodes
    //----------------------------
        
        protected double applyEasing(double s,double t,double c,double d){
            switch(this.getEasing_Type()){
                case IN:
                    return this.getEasing().easeIn(s,t,c,d);
                case OUT:
                    return this.getEasing().easeOut(s,t,c,d);
                case INOUT:
                    return this.getEasing().easeInOut(s,t,c,d);
                case NONE:
                    return this.getEasing().easeNone(s,t,c,d);
                default:
                    return this.getEasing().easeNone(s,t,c,d);
            }
            
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("Animation [");
            builder.append("current=").append(current);
            builder.append(", debut=").append(debut);
            builder.append(", easing=").append(easing);
            builder.append(", easing_Type=").append(easing_Type);
            builder.append(", fin=").append(fin);
            builder.append(", movable=").append(movable);
            builder.append("]");
            return builder.toString();
        }


        @Override
        public Element toXML(){
            Element el = new Element("animation");

            el.setAttribute("startTime", Double.toString(debut));
            el.setAttribute("endTime", Double.toString(fin));
            //TODO::easing STRING!
            //el.setAttribute("easing", easingString(easing));

            return el;
        }


}
