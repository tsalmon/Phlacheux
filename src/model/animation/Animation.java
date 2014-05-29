
package model.animation;

import model.easing.Easing;
import model.movable.Movable;

/**
 *
 * @author Josian Chevalier, Vladislav Fitc, Thomas Salmon
 *
 * Modèle
 *
 * Projet Interface Graphique, Paris 7, Master 1, 2013-2014
 *
 */
public abstract class Animation {


    //          Attributs
    //---------------------------

        protected Movable movable;
        protected Easing easing;
        protected double debut;
        protected double fin;
        protected String easing_Type;
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

        public String getEasing_Type() {
            return easing_Type;
        }

        public void setEasing_Type(String easing_Type) {
            this.easing_Type = easing_Type;
        }


    //          Methodes
    //----------------------------

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

        

}
