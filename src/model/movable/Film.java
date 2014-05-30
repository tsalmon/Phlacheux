package model.movable;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import javax.print.Doc;
import java.awt.*;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class Film {
    Color backgroundColor = Color.white;
    int width = 640;
    int height = 480;
    int duration = 1000;

    ArrayList<MovableGroup> groups = new ArrayList<MovableGroup>();
    ArrayList<Figure> shapes = new ArrayList<Figure>();

    public Film(){}

    public Film(int width, int height, int duration, Color backgroundColor){
        this.width = width;
        this.height = height;
        this.duration = duration;
        this.backgroundColor = backgroundColor;
    }

    public void addShape(Figure f){
        shapes.add(f);
    }

    public void addGroup(MovableGroup g){
        groups.add(g);
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

        Element groupsXML = new Element("groups");
        it = this.groups.iterator();

        while (it.hasNext()){
            MovableGroup groupXML = (MovableGroup) it.next();
            groupsXML.addContent(groupXML.toXML());
        }

        rootFilm.addContent(shapesXML);
        rootFilm.addContent(groupsXML);

        film.setRootElement(rootFilm);

        return film;
    }

    public void saveToFile(){
        try {
            XMLOutputter outputter = new XMLOutputter();
            outputter.setFormat(Format.getPrettyFormat());
            FileWriter fw = new FileWriter("film.xml");
            outputter.output(this.getXML(), fw);
            fw.close();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
