package XML;


import org.jdom2.Element;

public class ShapeXML extends Element{
    public enum Shape {
        rectangle, square, oval, circle, arrow, star, isosceles, equilateral, line
    }

    private static String shapeString(Shape s){
        switch(s){
            case square:{
                return "square";
            }

            case rectangle:{
                return "rectangle";
            }

            case oval:{
                return "oval";
            }

            case circle:{
                return "circle";
            }

            case arrow:{
                return "arrow";
            }

            case star:{
                return "star";
            }

            case isosceles:{
                return "isosceles";
            }

            case equilateral:{
                return "equilateral";
            }

            case line:{
                return "line";
            }

            default: {
                return "unknown";
            }
        }
    }

    private ShapeXML(String name, int colorR, int colorG, int colorB){
        setName("shape");

        setAttribute("name", name);
        setAttribute("colorR", Integer.toString(colorR));
        setAttribute("colorG", Integer.toString(colorG));
        setAttribute("colorB", Integer.toString(colorB));

        addContent(new Element("animations"));
    }

    public static ShapeXML square(String name, int colorR, int colorG, int colorB, int srcX, int srcY, int side){
        ShapeXML shape = new ShapeXML(name, colorR, colorG, colorB);
        shape.setAttribute("type", shapeString(Shape.square));

        shape.setAttribute("srcX", Integer.toString(srcX));
        shape.setAttribute("srcY", Integer.toString(srcY));
        shape.setAttribute("side", Integer.toString(side));

        return shape;
    }

    public static ShapeXML rectangle(String name, int colorR, int colorG, int colorB, int srcX, int srcY, int sideH, int sideV){
        ShapeXML shape = new ShapeXML(name, colorR, colorG, colorB);
        shape.setAttribute("type", shapeString(Shape.rectangle));

        shape.setAttribute("srcX", Integer.toString(srcX));
        shape.setAttribute("srcY", Integer.toString(srcY));
        shape.setAttribute("sideH", Integer.toString(sideH));
        shape.setAttribute("sideV", Integer.toString(sideV));

        return shape;
    }

    public static ShapeXML oval(String name, int colorR, int colorG, int colorB, int srcX, int srcY, int sideH, int sideV){
        ShapeXML shape = new ShapeXML(name, colorR, colorG, colorB);
        shape.setAttribute("type", shapeString(Shape.oval));

        shape.setAttribute("srcX", Integer.toString(srcX));
        shape.setAttribute("srcY", Integer.toString(srcY));
        shape.setAttribute("sideH", Integer.toString(sideH));
        shape.setAttribute("sideV", Integer.toString(sideV));

        return shape;
    }

    public static ShapeXML circle(String name, int colorR, int colorG, int colorB, int srcX, int srcY, int diameter){
        ShapeXML shape = new ShapeXML(name, colorR, colorG, colorB);
        shape.setAttribute("type", shapeString(Shape.square));

        shape.setAttribute("srcX", Integer.toString(srcX));
        shape.setAttribute("srcY", Integer.toString(srcY));
        shape.setAttribute("diameter", Integer.toString(diameter));

        return shape;
    }

    public static ShapeXML arrow(String name, int colorR, int colorG, int colorB){
        ShapeXML shape = new ShapeXML(name, colorR, colorG, colorB);
        shape.setAttribute("type", shapeString(Shape.arrow));

        //????

        return shape;
    }

    public static ShapeXML star(String name, int colorR, int colorG, int colorB){
        ShapeXML shape = new ShapeXML(name, colorR, colorG, colorB);
        shape.setAttribute("type", shapeString(Shape.star));

        //????

        return shape;
    }

    public static ShapeXML isosceles(String name, int colorR, int colorG, int colorB, int srcX, int srcY, int sideAB, int sideC){
        ShapeXML shape = new ShapeXML(name, colorR, colorG, colorB);
        shape.setAttribute("type", shapeString(Shape.isosceles));

        shape.setAttribute("srcX", Integer.toString(srcX));
        shape.setAttribute("srcY", Integer.toString(srcY));
        shape.setAttribute("sideAB", Integer.toString(sideAB));
        shape.setAttribute("sideC", Integer.toString(sideC));

        return shape;
    }

    public static ShapeXML equilateral(String name, int colorR, int colorG, int colorB, int srcX, int srcY, int side){
        ShapeXML shape = new ShapeXML(name, colorR, colorG, colorB);
        shape.setAttribute("type", shapeString(Shape.equilateral));

        shape.setAttribute("srcX", Integer.toString(srcX));
        shape.setAttribute("srcY", Integer.toString(srcY));
        shape.setAttribute("side", Integer.toString(side));

        return shape;
    }

    public static ShapeXML line(String name, int colorR, int colorG, int colorB, int srcX, int srcY, int dstX, int dstY){
        ShapeXML shape = new ShapeXML(name, colorR, colorG, colorB);
        shape.setAttribute("type", shapeString(Shape.line));

        shape.setAttribute("srcX", Integer.toString(srcX));
        shape.setAttribute("srcY", Integer.toString(srcY));
        shape.setAttribute("dstX", Integer.toString(dstX));
        shape.setAttribute("dstY", Integer.toString(dstY));

        return shape;
    }

    public ShapeXML addAnimation(AnimationXML a){
        Element animations = this.getChild("animations");
        animations.addContent(a);

        return this;
    }
}
