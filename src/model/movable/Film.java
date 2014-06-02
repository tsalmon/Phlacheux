package model.movable;

import model.animation.Animation;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import javax.print.Doc;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;
import java.util.List;

public class Film {
    Color backgroundColor = Color.white;
    int width = 640;
    int height = 480;
    int duration = 1000;
    String nom = "lefilm";
    
    ArrayList<MovableGroup> groups = new ArrayList<MovableGroup>();
    ArrayList<Figure> shapes = new ArrayList<Figure>();
    ArrayList<Animation> animations = new ArrayList<Animation>();


    public Film(){}

    public Film(String nom, int width, int height, int duration, Color backgroundColor){
    	this.nom = nom;
    	this.width = width;
        this.height = height;
        this.duration = duration;
        this.backgroundColor = backgroundColor;
    }

    public Film(Document xml) throws Exception {
        Element film = xml.getRootElement();
        if (!film.getName().equals("film")){
            throw new Exception("Wrong xml format");
        }
        int backgroundR = Integer.parseInt(film.getAttributeValue("backgroundR"));
        int backgroundG = Integer.parseInt(film.getAttributeValue("backgroundG"));
        int backgroundB = Integer.parseInt(film.getAttributeValue("backgroundB"));
        this.backgroundColor = new Color(backgroundR, backgroundG, backgroundB);

        int width = Integer.parseInt(film.getAttributeValue("width"));
        this.width = width;

        int height = Integer.parseInt(film.getAttributeValue("height"));
        this.height = height;

        int duration = Integer.parseInt(film.getAttributeValue("duration"));
        this.duration = duration;


        java.util.List<Element> shapes = film.getChild("shapes").getChildren();

        Iterator it = shapes.iterator();

        while (it.hasNext()){
            Movable m = Movable.parseXML((Element) it.next());
            addShape((Figure)m);
        }

        java.util.List<Element> groups = film.getChild("groups").getChildren();

        it = groups.iterator();

        while (it.hasNext()){
            Movable m = Movable.parseXML((Element) it.next());
            addGroup((MovableGroup)m);
        }

        java.util.List<Element> animations = film.getChild("animations").getChildren();

        it = animations.iterator();

        while (it.hasNext()){
            Animation a = Animation.parseXML((Element) it.next());
            addAnimation((Animation) a);
        }

    }

    public static Film fromFile(String path) {
        try {
            SAXBuilder parser = new SAXBuilder();
            FileReader fr = new FileReader(path);
            Document rDoc = parser.build(fr);
            System.out.println(rDoc.getRootElement().getName());
            List<Element> temp = rDoc.getRootElement().getChildren();
            for (int i = 0; i < temp.size(); ++i) {
                System.out.println(temp.get(i).getName());
            }
            return new Film(rDoc);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }

    public void addShape(Figure f){
        shapes.add(f);
    }
    public void addGroup(MovableGroup g){
        groups.add(g);
    }
    public void addAnimation(Animation a){
        animations.add(a);
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    

    public Document getXML(){
        Document film = new Document();

        Element rootFilm = new Element("film");
        rootFilm.setAttribute("backgroundR", Integer.toString(backgroundColor.getRed()));
        rootFilm.setAttribute("backgroundG", Integer.toString(backgroundColor.getGreen()));
        rootFilm.setAttribute("backgroundB", Integer.toString(backgroundColor.getBlue()));
        rootFilm.setAttribute("width", Integer.toString(width));
        rootFilm.setAttribute("height", Integer.toString(height));
        rootFilm.setAttribute("duration", Integer.toString(duration));

        Element shapesXML = new Element("shapes");
        Iterator it = this.shapes.iterator();

        while (it.hasNext()){
            Figure shapeXML = (Figure) it.next();
            shapesXML.addContent(shapeXML.toXML());
        }

        rootFilm.addContent(shapesXML);


        Element groupsXML = new Element("groups");
        it = this.groups.iterator();

        while (it.hasNext()){
            MovableGroup groupXML = (MovableGroup) it.next();
            groupsXML.addContent(groupXML.toXML());
        }

        rootFilm.addContent(groupsXML);

        Element animationsXML = new Element("animations");
        it = this.animations.iterator();

        while (it.hasNext()){
            Animation animationXML = (Animation) it.next();
            animationsXML.addContent(animationXML.toXML());
        }

        rootFilm.addContent(animationsXML);

        film.setRootElement(rootFilm);

        return film;
    }

    public void saveToFile(String filename){
        try {
            XMLOutputter outputter = new XMLOutputter();
            outputter.setFormat(Format.getPrettyFormat());
            FileWriter fw = new FileWriter(filename);
            outputter.output(this.getXML(), fw);
            fw.close();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
