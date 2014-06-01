
    package model.movable.polygon;

    import java.util.ArrayList;
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
    public class Rectangle extends Polygon{


        //          Attributs
        //---------------------------

        protected double length;
        protected double width;
        protected Point pointhg;
        protected Point pointbg;
        protected Point pointhd;
        protected Point pointbd;


        //       Constructeurs
        //----------------------------

            public Rectangle(double length, double width, Point point_haut_gauche) {
                super(new ArrayList<Point>());

                this.length = length;
                this.width = width;

                double x=point_haut_gauche.getX();
                double y=point_haut_gauche.getY();

                this.pointhg = new Point(x, y);
                this.pointbg = new Point(x,y+width);
                this.pointhd = new Point(x+length,y);
                this.pointbd = new Point(x+length,y+width);

                this.addLesPoints();
            }

            public Rectangle(Element xml){
                super(xml);
                this.length = Double.parseDouble(xml.getAttributeValue("sideV").toString());
                this.width = Double.parseDouble(xml.getAttributeValue("sideH").toString());
                double x = Double.parseDouble(xml.getAttributeValue("srcX").toString());
                double y = Double.parseDouble(xml.getAttributeValue("srcY").toString());

                this.pointhg = new Point(x,y);
                this.pointbg = new Point(x,y-width);
                this.pointhd = new Point(x+length,y);
                this.pointbd = new Point(x+length,y-width);
                this.addLesPoints();
            }

            private void addLesPoints(){
                this.addPoint(this.pointhg);
                this.addPoint(this.pointbg);
                
                this.addPoint(this.pointbd);
                this.addPoint(this.pointhd);
            }        


        //          Accesseurs
        //----------------------------


            public double getLength() {
                return length;
            }

            public void setLength(double length) {
                this.length = length;
            }

            public double getWidth() {
                return width;
            }

            public void setWidth(double width) {
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

            public void setPointhg(double x, double y) {
                this.setPointhg(new Point(x,y));
            }

            public void setPointbg(double x, double y) {
                this.setPointbg(new Point(x,y));
            }

            public void setPointhd(double x, double y) {
                this.setPointhd(new Point(x,y));
            }

            public void setPointbd(double x, double y) {
                this.setPointbd(new Point(x,y));
            }


        //          Methodes
        //----------------------------

            @Override
            public String toString() {
                StringBuilder builder = new StringBuilder();
                builder.append(super.toString("Rectangle"));
                builder.append(",\nlength=").append(length);
                builder.append(", width=").append(width);
                builder.append("]");
                return builder.toString();
            }

        @Override
            protected String toString(String name) {
                StringBuilder builder = new StringBuilder();
                builder.append(super.toString(name));
                builder.append(",\nlength=").append(length);
                builder.append(", width=").append(width);
                return builder.toString();
            }

        @Override
        public Element toXML() {
            Element el = super.toXML();

            el.setAttribute("type", "rectangle");

            el.setAttribute("srcX", Double.toString(pointhg.getX()));
            el.setAttribute("srcY", Double.toString(pointhg.getY()));
            el.setAttribute("sideH", Double.toString(width));
            el.setAttribute("sideV", Double.toString(length));

            return el;
        }
    }
