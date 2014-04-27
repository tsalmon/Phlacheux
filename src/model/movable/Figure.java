package model.movable;
import java.lang.Math.*;
import java.util.ArrayList;

/**
 *
 * @author Josian Chevalier, Vladislav Fitc, Thomas Salmon
 *
 * Modèle
 *
 * Projet Interface Graphique, Paris 7, Master 1, 2013-2014
 *
 */

abstract public class Figure extends Movable{
    

    //          Attributs
    //---------------------------

        protected int strokeThickness;
        protected ArrayList<Point> points;
        
        
    //         Constructeur
    //----------------------------

        protected Figure(){
            System.out.print("test");
            points=new ArrayList<Point>();
            this.changeStrokeThickness(1);
        }
        
        protected Figure(ArrayList<Point> p){
            System.out.print("test");
            points=p;
            this.changeStrokeThickness(1);
        }
        
    //          Accesseurs
    //----------------------------
        
        //    Accesseurs publiques
        //----------------------------
        
        public int getStrokeThickness(){
            return this.strokeThickness;
        }
        
        
        //    Accesseurs protected
        //----------------------------
        
            protected void addPoint(Point p){
                this.points.add(p);
                this.gcResfresh();
            }

            protected void removePoint(Point p){
                this.points.remove(p);
                this.gcResfresh();
            }
        
        //  calcul automatique du centre de gravité

        @Override
            public void autoGravityCenter(){
                int sumx=0;
                int sumy=0;
                for(Point p : points){
                    sumx+=p.getX();
                    sumy+=p.getY();
                }
                this.setGravityCenter(new Point((int)(sumx/(points.size())),(int)(sumy/points.size())));
                this.setGravityCenterPerso(false);
            }            
        
        
    //     Transformations
    //----------------------------

        @Override
        public void rotation(int angle, Point p){
            double angle_rad=(Math.PI*(angle))/180;
            for(Point point : points){
                point.rotateAround(angle_rad, p);
            }
            this.getGravityCenter().rotateAround(angle,p);
        }

        @Override
        public void rotation(int angle){
            this.rotation(angle,this.getGravityCenter());
        }

        @Override
        public void translation(Point from, Point to) {
            for(Point point : points){
                point.translation(from, to);
            }            
            this.getGravityCenter().translation(from, to);
        }

        @Override
        public void translation(int x_from, int y_from, int x_to, int y_to){
            for(Point point : points){
                point.translation(x_from, y_from, x_to, y_to);
            }            
            this.getGravityCenter().translation(x_from, y_from, x_to, y_to);
        }

        @Override
        public void changeStrokeThickness(int thickness) {
            this.strokeThickness=thickness;
        }

        @Override
        public void scaling(float scale){
            int gravity_x=this.getGravityCenter().getX();
            int gravity_y=this.getGravityCenter().getY();
            for(Point point : points){
                int translate_x=(int)((point.getX()-gravity_x)*scale);
                int translate_y=(int)((point.getY()-gravity_y)*scale);
                point.moveTo(gravity_x+translate_x,gravity_y+translate_y);
            }            
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(" [\n");
            builder.append(super.toString());
            builder.append(", strokeThickness=").append(strokeThickness);
            return builder.toString();
    }
        
}
