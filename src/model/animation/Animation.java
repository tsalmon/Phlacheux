
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
        protected Easing_Type easing_type;
        protected String name;
        
    
    //          Constructeur
    //----------------------------
        
        protected Animation(String name, Movable m, double d, double f, double current_time, Easing e, Easing_Type et){
            this.setMovable(m);
            this.setEasing(e);
            this.setDebut(d);
            this.setEasing_type(et);
            this.setFin(f);
            this.setCurrent(current_time);
            this.name=name;
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

        public Easing_Type getEasing_type() {
            return easing_type;
        }

        public void setEasing_type(Easing_Type easing_type) {
            this.easing_type = easing_type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


    //          Methodes
    //----------------------------
        
        abstract public void goToTime(double t);
        
        protected double applyEasing(double s,double t,double c,double d){
            switch(this.getEasing_type()){
                case EASE_IN:
                    return this.getEasing().easeIn(s,t,c,d);
                case EASE_OUT:
                    return this.getEasing().easeOut(s,t,c,d);
                case EASE_IN_OUT:
                    return this.getEasing().easeInOut(s,t,c,d);
                case EASE_NONE:
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
            builder.append(", easing_Type=").append(easing_type);
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
