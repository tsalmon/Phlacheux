
package model.animation;

import XML.XMLSerializable;
import model.easing.Easing;
import model.movable.Movable;
import model.movable.MovablePool;
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

    public enum AnimationType {
        TRANSLATION,
        SCALING,
        ROTATION,
        CHANGE_STROKE_THICKNESS,
        CHANGE_COLOR,
        CHANGE_BORDER_COLOR
    }

    //          Attributs
    //---------------------------

        protected Movable movable;
        protected Easing easing;
        protected double debut;
        protected double fin;  
        protected double current;
        protected EasingType easing_type;
        protected String name;
        
    
    //          Constructeur
    //----------------------------
        
        protected Animation(String name, Movable m, double d, double f, Easing e, EasingType et){
            this.setMovable(m);
            this.setEasing(e);
            this.setDebut(d);
            this.setEasing_type(et);
            this.setFin(f);
            this.setCurrent(0);
            this.name=name;
        }

        protected Animation(Element xml){
            this.setName(xml.getAttributeValue("name"));
            this.setDebut(Double.parseDouble(xml.getAttributeValue("startTime")));
            this.setFin(Double.parseDouble(xml.getAttributeValue("endTime")));
            this.setEasing_type(EasingType.getType(xml.getAttributeValue("easingType")));
            String objectName = xml.getAttributeValue("objectName");
            this.setMovable(MovablePool.getInstance().getMovable(objectName));
            try {
                this.setEasing(Easing.withString(xml.getAttributeValue("easing")));
            } catch (Exception e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            this.setCurrent(0);
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

        public EasingType getEasing_type() {
            return easing_type;
        }

        public void setEasing_type(EasingType easing_type) {
            this.easing_type = easing_type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
        
        public void isAdded(){}
        public void isRemoved(){}

    //          Methodes
    //----------------------------
        
        abstract public void goToTime(double t);
        
        protected double applyEasing(double s,double t,double c,double d){
            if(t>=this.getFin()){
                t=this.getFin();
            }   
            switch(this.getEasing_type()){
                case EASE_IN:
                    return this.getEasing().easeIn(c,s,t,d);
                case EASE_OUT:
                    return this.getEasing().easeOut(c,s,t,d);
                case EASE_IN_OUT:
                    return this.getEasing().easeInOut(c,s,t,d);
                case EASE_NONE:
                    return this.getEasing().easeNone(c,s,t,d);
                default:
                    return this.getEasing().easeNone(c,s,t,d);
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

            el.setAttribute("name", name);
            el.setAttribute("objectName", this.movable.getName());
            el.setAttribute("startTime", Double.toString(debut));
            el.setAttribute("endTime", Double.toString(fin));
            el.setAttribute("easing", easing.toString());
            el.setAttribute("easingType", easing_type.toString());

            return el;
        }

        public static Animation parseXML(Element xml) throws Exception {
            if (xml.getName().equals("animation")){
                String type = xml.getAttributeValue("type");
                switch (type) {
                    case "change_stroke_thickness":
                        return new ChangeStrokeThickness(xml);
                    case "rotation":
                        return new Rotation(xml);
                    case "translation":
                        return new Translation(xml);
                    case "scaling":
                        return new Scaling(xml);
                    case "changeColor":
                        return new ChangeColor(xml);
                    default:
                        throw new Exception("Unrecognized animation type");
                }
            } else {
                throw  new Exception("Not an animation");
            }
        }
}
