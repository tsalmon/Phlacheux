package model.gestionnary;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
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
    protected MovablePool pool;
    protected HashMap <String, Animation> animations;
    protected LinkedList<Animation> animations_a_venir;
    protected LinkedList<Animation> animations_passees;
    
    protected HashMap<String,HashMap<Double, Color>> color;
    protected HashMap<String,HashMap<Double, Color>> border_color;
    protected HashMap<String,HashMap<Double, Double>> stroke_thickness;
    
    private static StateGestionnary instance;
    Comparator <Animation> comparator_debut;
    Comparator <Animation> comparator_fin;
    Comparator <Figure> comparator_z_order;
    

    //          Constructeur
    //----------------------------
    
        private StateGestionnary() {
            this.current_time=0;
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
            comparator_z_order=new Comparator<Figure>() {
                @Override
                public int compare(Figure f1, Figure f2) {
                    return f1.getZ_position() < f2.getZ_position() ? 1 : f1.getZ_position() == f2.getZ_position() ? 0 : -1;
                }
            };
            this.pool=MovablePool.getInstance();
        }
    

        public static synchronized StateGestionnary getInstance(){
            if (instance == null){
                instance = new StateGestionnary();
            }
            return instance;
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
        
        public Movable addMovable(Movable m){
            this.pool.storeMovable(m);
            return m;
        }
        
        public void removeMovable(Movable m){
            this.pool.removeMovable(m.getName());
        }
        
        public void addAnimation(Animation a){
            this.animations.put(a.getName(), a);
            if(a.getFin()>this.current_time){
                this.addAVenir(a);
            }
            if(a.getDebut()<this.current_time){
                this.addPassee(a);
            }
            a.isAdded();
            a.goToTime(this.current_time);
        }
        
        public Animation removeAnimation(Animation a){
            this.animations_a_venir.remove(a);
            this.animations_passees.remove(a);
            a.isRemoved();
            return this.animations.remove(a.getName());
        }
        public HashMap<String, Animation> getAnimations() {
            return animations;
        }
        
        public Movable getMovable(String name){
            return this.pool.getMovable(name);
        }
        
        public Animation getAnimation(String name){
            return this.animations.get(name);
        }
        
        public HashMap <String, Animation> getAnimations(String name){
            return new HashMap<>(this.animations);
        }
        
        public HashMap <String, Movable> getMovables( ){
            return this.pool.getMovables();
        }
        
        /**
         * Retourne toutes les animations associées à un movables
         * @param name nom du movable dont on cherche à obtenir les animations
         * @return une LinkedList contenant toutes les animations associées à ce movable
         */
        public LinkedList<Animation> getAnimationsForMovable(String name){
            Collection<Animation> anims= this.getAnimations().values();
            Iterator<Animation> it=anims.iterator();
            LinkedList<Animation> result=new LinkedList<>();

            while(it.hasNext()){
                Animation next=it.next();
                if(next.getMovable().getName() == null ? name == null : next.getMovable().getName().equals(name)){
                    result.add(next);
                }
            }
            return result;            
        }
              
        /**
         * Retourne toutes les animations associées à un movables, ainsi que celles associées aux groupes dans lesquels il est inclu
         * @param name nom du movable dont on cherche à obtenir les animations
         * @return une LinkedList contenant toutes les animations associées à ce movable
         */
        public LinkedList<Animation> getAllAnimationsForMovable(String name){
            LinkedList<Animation>  result=this.getAnimationsForMovable(name);
            Movable m = this.getMovable(name);
            for(ListIterator<MovableGroup> it = m.getGroups().listIterator() ; it.hasNext();){
                result.addAll(this.getAllAnimationsForMovable(it.next().getName()));
            }
            
            return result;            
        }
        
        public LinkedList<LinkedList<Animation>> distributeAnimations(List<Animation> animations){
            LinkedList<LinkedList<Animation>> result=new LinkedList<>();            
            for(Animation a : animations){
                boolean placed = false;                
                for(ListIterator<LinkedList<Animation>> it = result.listIterator();it.hasNext();){
                    LinkedList<Animation> next = it.next();
                    boolean can_be_placed=true;
                    for(ListIterator<Animation> it2 = next.listIterator();it2.hasNext();){
                        Animation next_a = it2.next();
                        if ((next_a.getDebut()<=a.getDebut()&& next_a.getFin()>a.getDebut()) || (next_a.getFin()>=a.getFin()&& next_a.getDebut()<a.getFin())){
                            can_be_placed=false;
                        }
                    }
                    if(can_be_placed){
                        next.add(a);
                        placed=true;
                        break;
                    }
                }
                if(placed==false){
                    result.add(new LinkedList<Animation>());
                    result.getLast().add(a);
                }
            }
            return result;
        }
        
        public void addColor(String movable_name, Color color, double t){
            if(this.color.get(movable_name)==null){
                HashMap<Double, Color> h = new HashMap<>();
                h.put(t, color);
                this.color.put(movable_name, h);
            }
            else{
                this.color.get(movable_name).put(t, color);
            }
        }
        
        public void addBorderColor(String movable_name, Color color, double t){
            if(this.border_color.get(movable_name)==null){
                HashMap<Double, Color> h = new HashMap<>();
                h.put(t, color);
                this.border_color.put(movable_name, h);
            }
            else{
                this.border_color.get(movable_name).put(t, color);
            }
        }
        
        public void addStroke_Thickness(String movable_name, double stroke_thickness, double t){
            if(this.stroke_thickness.get(movable_name)==null){
                HashMap<Double, Double> h = new HashMap<>();
                h.put(t, stroke_thickness);
                this.stroke_thickness.put(movable_name, h);
            }
            else{
                this.stroke_thickness.get(movable_name).put(t, stroke_thickness);
            }
        }
        
        public void removeColor(String movable_name, double t){
            this.color.get(movable_name).remove(t);
        }
        
        public void removeBorderColor(String movable_name, double t){
            this.border_color.get(movable_name).remove(t);
        }
        
        public void removeStroke_Thickness(String movable_name, double t){
            this.stroke_thickness.get(movable_name).remove(t);
        }
        
        public Color getColor(String movable_name, double t){
            return this.color.get(movable_name).get(t);
        }
        
        public Color getBorderColor(String movable_name, double t){
            return this.border_color.get(movable_name).get(t);
        }
        
        public double getStrokeThickness(String movable_name, double t){
            return this.stroke_thickness.get(movable_name).get(t);
        }        
        
        public void removeColor(String movable_name){
            this.color.remove(movable_name);
        }
        
        public void removeBorderColor(String movable_name, Color color, double t){
            this.border_color.remove(movable_name);
        }
        
        public void removeStroke_Thickness(String movable_name, double stroke_thickness, double t){
            this.stroke_thickness.remove(movable_name);
        }
        
        public Set<Double> getColorTimes(String movable_name){
            return this.color.get(movable_name).keySet();
        }
        
        public Set<Double> getBorderColorsTimes(String movable_name){
            return this.border_color.get(movable_name).keySet();
        }
        
        public Set<Double> getStrokeThicknessesTimes(String movable_name){
            return this.stroke_thickness.get(movable_name).keySet();
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
        
        public ArrayList<BufferedImage> BufferedImageCreator( int framerate, Film film){
            double t = 0;
            ArrayList<BufferedImage> result=new ArrayList<>();
            while (t<=film.getDuration()){
                this.goToTime(t);
                HashMap<String, Movable> movables= this.getMovables();
                ArrayList<Figure> figures = new ArrayList<>();
                for(Movable m : movables.values()){
                    for(Figure f : m.getAllFigures()){
                        if(!figures.contains(f)){
                            figures.add(f);
                        }
                    }
                }
                Collections.sort(figures, this.comparator_z_order);
                BufferedImage image=new BufferedImage(film.getWidth(), film.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
                Graphics2D graphics = image.createGraphics();
                graphics.setPaint (film.getBackgroundColor());
                graphics.fillRect ( 0, 0, image.getWidth(), image.getHeight() );
                for(ListIterator<Figure> li = figures.listIterator(); li.hasNext();){                    
                    Figure next = li.next();
                    graphics.setColor(next.getColor());
                    graphics.fill(next.getShape());
                    graphics.setColor(next.getBorderColor());
                    graphics.setStroke(new BasicStroke(Math.round(next.getStrokeThickness())));
                    graphics.draw(next.getShape());
                }                                
                result.add(image);
            }
            return result;
        }
        
}
