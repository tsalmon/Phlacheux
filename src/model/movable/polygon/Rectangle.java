
package model.movable.polygon;

import java.util.ArrayList;
import model.movable.Point;

/**
 *
 * @author Josian Chevalier, Vladislav Fitc, Thomas Salmon
 *
 * Modèle
 *
 * Projet Interface Graphique, Paris 7, Master 1, 2013-2014
 *
 */
public class Rectangle extends Polygon{


    //          Attributs
    //---------------------------

    protected int length;
    protected int width;
    protected Point pointhg;
    protected Point pointbg;
    protected Point pointhd;
    protected Point pointbd;

    
    //       Constructeurs
    //----------------------------
    
        public Rectangle(int length, int width, Point point_haut_gauche) {
            super(new ArrayList<Point>());
            this.length = length;
            this.width = width;
            int x=point_haut_gauche.getX(); int y=point_haut_gauche.getY();
            this.pointhg = point_haut_gauche;
            this.pointbg = new Point(x,y-width);
            this.pointhd = new Point(x+length,y);
            this.pointbd = new Point(x+length,y-width);
            this.addLesPoints();
        }
        
        protected void addLesPoints(){
            this.addPoint(this.pointhg);
            this.addPoint(this.pointbg);
            this.addPoint(this.pointhd);
            this.addPoint(this.pointbd);
        }
    
    
    //          Accesseurs
    //----------------------------
    

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }        

        public void setPointhg(Point pointhg) {
            this.removePoint(this.pointhg);
            this.removePoint(this.pointhd);
            this.removePoint(this.pointbg);
            this.pointhg = pointhg;
            this.pointhd=new Point(this.pointhd.getX(),pointhg.getY());
            this.pointbg=new Point(pointhg.getX(),this.pointbg.getY());
            this.addLesPoints();
        }

        public void setPointbg(Point pointbg) {
            this.removePoint(this.pointbg);
            this.removePoint(this.pointhg);
            this.removePoint(this.pointbd);
            this.pointbg = pointbg;
            this.pointhg=new Point(this.pointbg.getX(),this.pointhg.getY());
            this.pointbd=new Point(this.pointbd.getX(),this.pointbg.getY());
            this.addLesPoints();
        }

        public void setPointhd(Point pointhd) {
            this.removePoint(this.pointhd);
            this.removePoint(this.pointhg);
            this.removePoint(this.pointbd);
            this.pointhd = pointhd;
            this.pointhg=new Point(this.pointhg.getX(),this.pointhd.getY());
            this.pointbd=new Point(this.pointhd.getX(),this.pointbd.getY());
            this.addLesPoints();
        }

        public void setPointbd(Point pointbd) {
            this.removePoint(this.pointbd);
            this.removePoint(this.pointhd);
            this.removePoint(this.pointbg);
            this.pointbd = pointbd;
            this.pointhd=new Point(this.pointbd.getX(),this.pointhd.getY());
            this.pointbg=new Point(this.pointbg.getX(),this.pointbd.getY());
            this.addLesPoints();
        }

        public void setPointhg(int x, int y) {
            this.setPointhg(new Point(x,y));
        }

        public void setPointbg(int x, int y) {
            this.setPointbg(new Point(x,y));
        }

        public void setPointhd(int x, int y) {
            this.setPointhd(new Point(x,y));
        }

        public void setPointbd(int x, int y) {
            this.setPointbd(new Point(x,y));
        }
    

    //          Methodes
    //----------------------------

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("Rectangle [");
        builder.append("strokeThickness=").append(strokeThickness);
        builder.append(", gravity_center=").append(gravity_center);
        builder.append(", gravity_center_personalised=").append(gravity_center_personalised);
            builder.append(",\nlength=").append(length);
            builder.append(", width=").append(width);
            builder.append(",\npoint_haut_gauche=").append(pointhg);
            builder.append(", point_haut_droit=").append(pointhd);
            builder.append(",\npoint_bas_gauche=").append(pointbg);
            builder.append(", point_bas_droite=").append(pointbd);
            builder.append("]");
            return builder.toString();
        }


}
