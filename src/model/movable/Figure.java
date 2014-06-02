package model.movable;
import XML.XMLSerializable;

import org.jdom2.Element;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
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

        protected ArrayList<PointPlacheux> points = new ArrayList<PointPlacheux>();
        protected double strokeThickness = 1;
        protected Color borderColor;
        protected Color color;
        protected int z_position=1;
        
        protected double initial_strokeThickness=1;
        protected Color initial_borderColor;
        protected Color initial_color;
        
        
        
    //         Constructeur
    //----------------------------

        protected Figure(){            
            this.setColor(Color.BLACK);
            this.setBorderColor(Color.BLACK);
        }

        protected Figure(ArrayList<PointPlacheux> p){
            for(PointPlacheux point : p){
                this.addPoint(point);
            }
            this.setColor(Color.BLACK);
            this.setBorderColor(Color.BLACK);
        }

        protected Figure(Element xml){
            super(xml);
            int R = Integer.parseInt(xml.getAttributeValue("colorR"));
            int G = Integer.parseInt(xml.getAttributeValue("colorG"));
            int B = Integer.parseInt(xml.getAttributeValue("colorB"));
            this.setColor(new Color(R,G,B));
            this.setBorderColor(Color.BLACK);
        }
        
    //          Accesseurs
    //----------------------------
       
        //    Accesseurs publiques
        //----------------------------
        
            public double getStrokeThickness(){
                return this.strokeThickness;
            }
            
            public void setStrokeThickness(double stroke_thickness){
                this.strokeThickness = stroke_thickness;
                this.initial_strokeThickness = stroke_thickness;
            }

            public Color getBorderColor() {
                return borderColor;
            }

            public void setBorderColor(Color borderColor) {
                this.borderColor = borderColor;
                this.initial_borderColor = borderColor;
            }

            public Color getColor() {
                return color;
            }

            public void setColor(Color color) {
                this.color = color;
                this.initial_color = color;
            }

            public ArrayList<PointPlacheux> getPoints() {
                return points;
            }

            public double getInitial_strokeThickness() {
                return initial_strokeThickness;
            }

            public Color getInitial_borderColor() {
                return initial_borderColor;
            }

            public Color getInitial_color() {
                return initial_color;
            }

            @Override
            public ArrayList<Movable> getChildren() {
                ArrayList<Movable> res=new ArrayList<Movable>();
                res.add(this);
                return res;
            }

            @Override
            public ArrayList<Figure> getAllFigures() {
                ArrayList<Figure> res=new ArrayList<Figure>();
                res.add(this);
                return res;
            }

            public int getZ_position() {
                return z_position;
            }

            public void setZ_position(int z_position) {
                this.z_position = z_position;
            }

        
        //    Accesseurs protected
        //----------------------------
        
            protected void addPoint(PointPlacheux p){
                this.points.add(p);
                this.gcResfresh();
            }

            protected void removePoint(PointPlacheux p){
                this.points.remove(p);
                this.gcResfresh();
            }
        
        //  calcul automatique du centre de gravité

        @Override
            public void autoGravityCenter(){
                double sumx=0;
                double sumy=0;
                for(PointPlacheux p : points){
                    sumx+=p.getX();
                    sumy+=p.getY();
                }
                this.setGravityCenter(new PointPlacheux(sumx/(points.size()),sumy/points.size()));
                this.setGravityCenterPerso(false);
            }            
        
        
    //     Transformations
    //----------------------------

        @Override
        public void rotation(double angle, PointPlacheux p){
            double angle_rad=(Math.PI*(angle))/180;
            for(PointPlacheux point : points){
                point.rotateAroundRadian(angle_rad, p);
            }
            this.getGravityCenter().rotateAroundRadian(angle, p);
        }

        @Override
        public void rotation(double angle){
            this.rotation(angle,this.getGravityCenter());
        }

        @Override
        public void translation(PointPlacheux from, PointPlacheux to) {
            for(PointPlacheux point : points){
                point.translation(from, to);
            }            
            this.getGravityCenter().translation(from, to);
            System.out.println(this.gravity_center.toString());
        }

        @Override
        public void translation(double x_from, double y_from, double x_to, double y_to){
            for(PointPlacheux point : points){
                point.translation(x_from, y_from, x_to, y_to);
            }            
            this.getGravityCenter().translation(x_from, y_from, x_to, y_to);
            System.out.println(this.gravity_center.toString());
        }

        @Override
        public void changeStrokeThickness(double thickness) {
            this.strokeThickness+=thickness;
        }

        @Override
        public void scaling(double scale){
            double gravity_x=this.getGravityCenter().getX();
            double gravity_y=this.getGravityCenter().getY();
            for(PointPlacheux point : points){
                double translate_x=(point.getX()-gravity_x)*scale;
                double translate_y=(point.getY()-gravity_y)*scale;
                point.moveTo(gravity_x+translate_x,gravity_y+translate_y);
            }            
        }

        @Override
        public void changeColor(Color c) {
            this.setColor(c);
        }

        @Override
        public void changeBorderColor(Color c) {
            this.setBorderColor(c);
        }

        @Override
        public void changeColorFlow(Color c) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        
        
    //         Methodes
    //----------------------------

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

        public Element toXML(){
            Element el = new Element("shape");

            el.setAttribute("name", name);
            if (color != null){
                el.setAttribute("colorR", Integer.toString(color.getRed()));
                el.setAttribute("colorG", Integer.toString(color.getGreen()));
                el.setAttribute("colorB", Integer.toString(color.getBlue()));
            }

            return el;
        }

        public Shape getShape() {
        	GeneralPath gp = new GeneralPath();
        	boolean start = true;
        	for(PointPlacheux p : points){
        		if(start){
        			gp.moveTo(p.x, p.y);
        			start = false;
        		} else {
        			gp.lineTo(p.x, p.y);					
        		}
        	}
                gp.lineTo(points.get(0).x, points.get(0).y);
        	return gp;
        }
        
}
