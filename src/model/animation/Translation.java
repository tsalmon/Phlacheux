
package model.animation;

import model.easing.*;
import model.movable.Movable;
import model.movable.Point;
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
public class Translation extends Animation{


    //          Attributs
    //---------------------------

        protected Point depart;
        protected Point arrivee;
        
        
    //        Constructeur
    //---------------------------

    public Translation(String name, Movable movable, double debut, double fin, Easing easing, EasingType easing_type,  Point point_depart, Point point_arrivee) {
        super(name, movable, debut, fin, easing, easing_type);
        this.setPointDepart(point_depart);
        this.setPointArrivee(point_arrivee);
    }

    public Translation(String name, Movable movable, double debut, double fin, Easing easing, EasingType easing_type,  Point point_arrivee) {
        super(name, movable, debut, fin, easing, easing_type);
        this.setPointDepart(movable.getGravityCenter());
        this.setPointArrivee(point_arrivee);
    }

    public Translation(String name, Movable movable, double debut, double fin, Point point_arrivee) {
        super(name, movable, debut, fin, new Linear(), EasingType.EASE_NONE );
        this.setPointDepart(movable.getGravityCenter());
        this.setPointArrivee(point_arrivee);
    }

    public Translation(String name, Movable movable, double debut, double fin, Point point_depart, Point point_arrivee) {
        super(name, movable, debut, fin, new Linear(), EasingType.EASE_NONE );
        this.setPointDepart(point_depart);
        this.setPointArrivee(point_arrivee);
    }

    public Translation(Element xml){
        super(xml);
        double srcX = Double.parseDouble(xml.getAttributeValue("srcX"));
        double srcY = Double.parseDouble(xml.getAttributeValue("srcY"));
        double dstX = Double.parseDouble(xml.getAttributeValue("dstX"));
        double dstY = Double.parseDouble(xml.getAttributeValue("dstY"));
        setPointDepart(new Point(srcX, srcY));
        setPointArrivee(new Point(dstX, dstY));
    }


    //          Accesseurs
    //----------------------------
        
        public Point getPointDepart(){
         return depart;
        }

        public void setPointDepart(Point depart) {
            this.depart = depart;
        }

        public Point getPointArrivee() {
            return arrivee;
        }

        public void setPointArrivee(Point arrivee) {
            this.arrivee = arrivee;
        }
    
    
    
    //          Methodes
    //----------------------------
        
        public void goToTime(double t){
            double dx_to_apply=this.getDXAt(t)-this.getDXAt(this.getCurrent());
            double dy_to_apply=this.getDYAt(t)-this.getDYAt(this.getCurrent());
            Point depart_translation=this.getMovable().getGravityCenter();
            this.getMovable().translation(depart_translation,new Point(dx_to_apply, dy_to_apply));
        }
        
        protected double getDXAt(double t){
            return this.applyEasing(0, t, this.getPointArrivee().getX()-this.getPointDepart().getX(), this.getFin()-this.getDebut());
        }
        
        protected double getDYAt(double t){
            return this.applyEasing(0, t, this.getPointArrivee().getY()-this.getPointDepart().getY(), this.getFin()-this.getDebut());
        }


        @Override
        public Element toXML(){
            Element el = super.toXML();

            el.setAttribute("type", "translation");
            el.setAttribute("srcX", Double.toString(depart.getX()));
            el.setAttribute("srcY", Double.toString(depart.getY()));
            el.setAttribute("dstX", Double.toString(arrivee.getX()));
            el.setAttribute("dstY", Double.toString(arrivee.getY()));

            return el;
        }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Translation [");
        builder.append("arrivee=").append(arrivee);
        builder.append(", depart=").append(depart);
        builder.append(", current=").append(current);
        builder.append(", debut=").append(debut);
        builder.append(", easing=").append(easing);
        builder.append(", easing_type=").append(easing_type);
        builder.append(", fin=").append(fin);
        builder.append(", movable=").append(movable);
        builder.append(", name=").append(name);
        builder.append("]");
        return builder.toString();
    }
        
        

        
}
