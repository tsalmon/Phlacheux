
    package model.movable.polygon;

    import java.util.ArrayList;
    import model.movable.PointPlacheux;
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
    public class Rectangle extends Polygon{


        //          Attributs
        //---------------------------


        protected PointPlacheux pointhg;
        protected PointPlacheux pointbg;
        protected PointPlacheux pointhd;
        protected PointPlacheux pointbd;


        //       Constructeurs
        //----------------------------

            public Rectangle(double length, double width, PointPlacheux point_haut_gauche) {
                super(new ArrayList<PointPlacheux>());

                //this.length = length;
                //this.width = width;

                double x=point_haut_gauche.getX();
                double y=point_haut_gauche.getY();

                this.pointhg = new PointPlacheux(x, y);
                this.pointbg = new PointPlacheux(x,y+width);
                this.pointhd = new PointPlacheux(x+length,y);
                this.pointbd = new PointPlacheux(x+length,y+width);

                this.addLesPoints();
            }

            public Rectangle(Element xml){
                super(xml);
                double length = Double.parseDouble(xml.getAttributeValue("sideV").toString());
                double width = Double.parseDouble(xml.getAttributeValue("sideH").toString());
                double x = Double.parseDouble(xml.getAttributeValue("srcX").toString());
                double y = Double.parseDouble(xml.getAttributeValue("srcY").toString());

                this.pointhg = new PointPlacheux(x,y);
                this.pointbg = new PointPlacheux(x,y-width);
                this.pointhd = new PointPlacheux(x+length,y);
                this.pointbd = new PointPlacheux(x+length,y-width);
                this.addLesPoints();
            }

            private void addLesPoints(){
                this.addPoint(this.pointhg);
                this.addPoint(this.pointbg);
                
                this.addPoint(this.pointbd);
                this.addPoint(this.pointhd);
            }

            private void removeAllPoints(){
                this.removePoint(this.pointhg);
                this.removePoint(this.pointbg);

                this.removePoint(this.pointbd);
                this.removePoint(this.pointhd);
            }


        //          Accesseurs
        //----------------------------


            public double getLength() {
                return (this.pointhd.getX()-this.pointhg.getX());
            }

            public void setLength(double length) {
                removeAllPoints();
                this.pointbd = new PointPlacheux(this.pointbg.getX()+length, this.pointbg.getY());
                this.pointhd = new PointPlacheux(this.pointhg.getX()+length, this.pointhg.getY());
                addLesPoints();
            }

            public double getWidth() {
                return (this.pointbg.getY()-this.pointhg.getY());

            }

            public void setWidth(double width) {
                removeAllPoints();
                this.pointbd = new PointPlacheux(this.pointhd.getX(), this.pointhd.getY()+width);
                this.pointbg = new PointPlacheux(this.pointhg.getX(), this.pointhg.getY()+width);
                addLesPoints();
            }

            public PointPlacheux getPointhg(){
                return this.pointhg;
            }

            public void setPointhg(PointPlacheux pointhg) {
                removeAllPoints();
                this.pointhg = pointhg;
                this.pointhd=new PointPlacheux(this.pointhd.getX(),pointhg.getY());
                this.pointbg=new PointPlacheux(pointhg.getX(),this.pointbg.getY());
                this.addLesPoints();
            }

            public void setPointbg(PointPlacheux pointbg) {
                removeAllPoints();
                this.pointbg = pointbg;
                this.pointhg=new PointPlacheux(this.pointbg.getX(),this.pointhg.getY());
                this.pointbd=new PointPlacheux(this.pointbd.getX(),this.pointbg.getY());
                this.addLesPoints();
            }

            public void setPointhd(PointPlacheux pointhd) {
                removeAllPoints();
                this.pointhd = pointhd;
                this.pointhg=new PointPlacheux(this.pointhg.getX(),this.pointhd.getY());
                this.pointbd=new PointPlacheux(this.pointhd.getX(),this.pointbd.getY());
                this.addLesPoints();
            }

            public void setPointbd(PointPlacheux pointbd) {
                removeAllPoints();
                this.pointbd = pointbd;
                this.pointhd=new PointPlacheux(this.pointbd.getX(),this.pointhd.getY());
                this.pointbg=new PointPlacheux(this.pointbg.getX(),this.pointbd.getY());
                this.addLesPoints();
            }

            public void setPointhg(double x, double y) {
                this.setPointhg(new PointPlacheux(x,y));
            }

            public void setPointbg(double x, double y) {
                this.setPointbg(new PointPlacheux(x,y));
            }

            public void setPointhd(double x, double y) {
                this.setPointhd(new PointPlacheux(x,y));
            }

            public void setPointbd(double x, double y) {
                this.setPointbd(new PointPlacheux(x,y));
            }


        //          Methodes
        //----------------------------

            @Override
            public String toString() {
                StringBuilder builder = new StringBuilder();
                builder.append(super.toString("Rectangle"));
                builder.append(",\nlength=").append(getLength());
                builder.append(", width=").append(getWidth());
                builder.append("]");
                return builder.toString();
            }

        @Override
            protected String toString(String name) {
                StringBuilder builder = new StringBuilder();
                builder.append(super.toString(name));
                builder.append(",\nlength=").append(getLength());
                builder.append(", width=").append(getWidth());
                return builder.toString();
            }

        @Override
        public Element toXML() {
            Element el = super.toXML();

            el.setAttribute("type", "rectangle");

            el.setAttribute("srcX", Double.toString(pointhg.getX()));
            el.setAttribute("srcY", Double.toString(pointhg.getY()));
            el.setAttribute("sideH", Double.toString(getWidth()));
            el.setAttribute("sideV", Double.toString(getLength()));

            return el;
        }
    }
