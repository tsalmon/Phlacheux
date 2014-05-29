package model.movable;
import XML.XMLSerializable;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
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

abstract public class Figure extends Movable implements XMLSerializable{
    

    //          Attributs
    //---------------------------

        protected int strokeThickness;
        protected ArrayList<Point> points;
        protected Color borderColor;
        private Color color;

        
        
    //         Constructeur
    //----------------------------

        protected Figure(){
            System.out.print("test");
            points=new ArrayList<Point>();
            this.changeStrokeThickness(1);
        }
        
        protected Figure(ArrayList<Point> p){
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

            public Color getBorderColor() {
                return borderColor;
            }

            public void setBorderColor(Color borderColor) {
                this.borderColor = borderColor;
            }

            public Color getColor() {
                return color;
            }

            public void setColor(Color color) {
                this.color = color;
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
                this.setGravityCenter(new Point((int)Math.round(sumx/(points.size())),(int)Math.round(sumy/points.size())));
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
                int translate_x=(int)Math.round((point.getX()-gravity_x)*scale);
                int translate_y=(int)Math.round((point.getY()-gravity_y)*scale);
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

        protected String toString(String name) {
            StringBuilder builder = new StringBuilder();
            builder.append(name).append(" [\n");
            builder.append(super.toString());
            builder.append(", strokeThickness=").append(strokeThickness);
            return builder.toString();
    }

		public Shape getShape() {
			GeneralPath gp = new GeneralPath();
			for(Point p : points){
				
			}
			return gp;
		}
        
}
