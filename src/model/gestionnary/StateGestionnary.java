
package model.gestionnary;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import model.animation.Animation;
import model.movable.*;

/**
 *
 * @author Josian Chevalier, Vladislav Fitc, Thomas Salmon
 *
 * Modèle
 *
 * Projet Interface Graphique, Paris 7, Master 1, 2013-2014
 *
 */

public class StateGestionnary {


    //          Attributs
    //---------------------------

    protected double current_time;
    protected HashMap <String, Movable> movables;
    protected HashMap <String, Animation> animations;
    protected LinkedList<Animation> animations_a_venir;
    protected LinkedList<Animation> animations_passees;
    
    Comparator <Animation> comparator_debut;
    Comparator <Animation> comparator_fin;
    

    //          Constructeur
    //----------------------------
    
        public StateGestionnary() {
            this.current_time=0;
            this.movables = new HashMap<String, Movable>();
            comparator_debut=new Comparator<Animation>() {
                @Override
                public int compare(Animation a1, Animation a2) {
                    return a1.getDebut() < a2.getDebut() ? -1 : a1.getDebut() == a2.getDebut() ? 0 : 1;
                }
            };
            comparator_fin=new Comparator<Animation>() {
                @Override
                public int compare(Animation a1, Animation a2) {
                    return a1.getFin() < a2.getFin() ? 1 : a1.getFin() == a2.getFin() ? 0 : -1;
                }
            };
        }
    
    
    //          Accesseurs
    //----------------------------

        protected void addAVenir(Animation a){
            if(!this.animations_a_venir.contains(a)){
                ListIterator<Animation> a_venir_iterator=this.animations_a_venir.listIterator();
                while(a_venir_iterator.hasNext() && a_venir_iterator.next().getDebut()<a.getDebut()){
                }
                a_venir_iterator.add(a);
            }
        }

        protected void addPassee(Animation a){
            if(!this.animations_passees.contains(a)){
                ListIterator<Animation> passees_iterator=this.animations_passees.listIterator();
                while(passees_iterator.hasNext() && passees_iterator.next().getFin()>a.getFin()){
                }
                passees_iterator.add(a);
            }
        }
        
        public void addMovable(Movable m){
            movables.put(m.getName(), m);
        }
        
        public Movable removeMovable(Movable m){
            return movables.remove(m);
        }
        
        public void addAnimation(Animation a){
            this.animations.put(a.getName(), a);
            if(a.getFin()>this.current_time){
                this.addAVenir(a);
            }
            if(a.getDebut()<this.current_time){
                this.addPassee(a);
            }
        }
        
        public Animation removeAnimation(Animation a){
            this.animations_a_venir.remove(a);
            this.animations_passees.remove(a);
            return animations.remove(a);
        }

        public HashMap<String, Movable> getMovables() {
            return movables;
        }

        public HashMap<String, Animation> getAnimations() {
            return animations;
        }
        
        public Movable getMovable(String name){
            return this.movables.get(name);
        }
        
        public Animation getAnimation(String name){
            return this.animations.get(name);
        }

        
    //          Methodes
    //----------------------------
        
        public void goToTime(double time){           
            if(time>this.current_time){
                ListIterator<Animation> a_venir_iterator=this.animations_a_venir.listIterator();
                double last_debut=0;
                while(a_venir_iterator.hasNext() && last_debut<time){     
                        Animation next=a_venir_iterator.next();
                        last_debut=next.getDebut();
                        if (last_debut<time){
                            next.goToTime(time);
                        }
                        if(next.getFin()<=time){
                            this.animations_a_venir.remove(next);
                        }
                        this.addPassee(next);
                }
            }             
            if(time<this.current_time){
                ListIterator<Animation> passees_iterator=this.animations_passees.listIterator();
                double last_fin=0;
                while(passees_iterator.hasNext() && last_fin>time){     
                        Animation next=passees_iterator.next();
                        last_fin=next.getFin();
                        if (last_fin>time){
                            next.goToTime(time);
                        }
                        if(next.getDebut()>=time){
                            this.animations_passees.remove(next);
                        }
                        this.addAVenir(next);
                }
            }            
        }
        
}
