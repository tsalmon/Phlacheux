package model.movable;

import XML.XMLSerializable;
import java.awt.Color;
import java.util.ArrayList;
import model.movable.circle.Circle;
import model.movable.line.CubicCurve;
import model.movable.line.QuadraticCurve;
import model.movable.line.Segment;
import model.movable.polygon.*;
import org.jdom2.Document;
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

abstract public class Movable implements XMLSerializable{

    //         Constructeur
    //----------------------------

        protected Movable(){
            MovablePool.getInstance().storeMovable(this);
        }

        protected Movable(Element xml){
            this.name = xml.getAttributeValue("name");
            MovablePool.getInstance().storeMovable(this);

            //TODO: Add animations
        }

    //          Attributs
    //---------------------------

        protected Point gravity_center;  
        protected boolean gravity_center_personalised; 
        protected String name;
        protected ArrayList<MovableGroup> groups=new ArrayList<MovableGroup>();
    
    
    
    //          Accesseurs
    //----------------------------
    
        public Point getGravityCenter(){
            return this.gravity_center;
        }
        public boolean getGravityCenterPerso(){
            return this.gravity_center_personalised;
        }
        
        public void setGravityCenterPerso(boolean b){
            this.gravity_center_personalised=b;
        }
        //set avec un Point (/!\ PASSAGE PAR REFERENCE, 
        //uniquement si on veut qu'il se deplace avec ce point)
        public void setGravityCenter(Point p){
            this.gravity_center=p;
            this.setGravityCenterPerso(true);
        }
        
        //set avec les coordonnées
        public void setGravityCenter(double x, double y){
            this.gravity_center.moveTo(x,y);
            this.setGravityCenterPerso(true);
        }
        
        protected void gcResfresh(){
            if(!this.getGravityCenterPerso()){
                this.autoGravityCenter();
            }
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void addGroup(MovableGroup g) {
            this.groups.add(g);
        }

        public void removeGroup(MovableGroup g) {
            this.groups.remove(g);
        }
        
        public ArrayList<MovableGroup> getGroups(){
            return this.groups;
        }
        
        abstract protected void autoGravityCenter();
        

        
    //     Transformations
    //----------------------------
        
        abstract public void rotation(double angle);

        abstract public void rotation(double angle, Point p);

        abstract public void translation(double x_from, double y_from, double x_to, double y_to);

        abstract public void translation(Point from, Point to);

        abstract public void changeStrokeThickness(double thickness);

        abstract public void scaling(double scale);
        
        abstract public void changeColor(Color c);
        
        abstract public void changeColorFlow(Color c);

        abstract public void changeBorderColor(Color c);



        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("name=").append(name);
            builder.append("gravity_center=").append(gravity_center);
            builder.append(", gravity_center_personalised=").append(gravity_center_personalised);
            return builder.toString();
        }

        public static Movable parseXML(Element xml) throws Exception {
            if (xml.getName().equals("group")){
                return new MovableGroup(xml);
            } else
            if (xml.getName().equals("shape")){
                String type = xml.getAttributeValue("type");
                if (type.equals("circle")){
                    return new Circle(xml);
                } else
                if (type.equals("segment")){
                    return new Segment(xml);
                } else
                if (type.equals("cubicCurve")){
                    return new CubicCurve(xml);
                } else
                if (type.equals("quadraticCurve")){
                    return new QuadraticCurve(xml);
                } else
                if (type.equals("equilateralTriangle")){
                    return new EquilateralTriangle(xml);
                } else
                if (type.equals("polygonPerso")){
                    return new PolygonPerso(xml);
                } else
                if (type.equals("rectangle")){
                    return new Rectangle(xml);
                } else
                if (type.equals("square")){
                    return new Square(xml);
                } else
                if (type.equals("triangle")){
                    return new Triangle(xml);
                } else {
                    throw new Exception("Unknown object");
                }
            }
            return null;
        }
}
