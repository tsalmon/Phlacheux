package XML;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;

public class FilmXML extends Element {
    public FilmXML(int backgroundR, int backgroundG, int backgroundB, int width, int height, int duration){
        super("film");
        setAttribute("backgroundR", Integer.toString(backgroundR));
        setAttribute("backgroundG", Integer.toString(backgroundG));
        setAttribute("backgroundB", Integer.toString(backgroundB));
        setAttribute("width", Integer.toString(width));
        setAttribute("height", Integer.toString(height));
        setAttribute("duration", Integer.toString(duration));
        addContent(new Element("shapes"));
        addContent(new Element("groups"));
    }

    public FilmXML addShape(ShapeXML s){
        Element shapes = this.getChild("shapes");
        shapes.addContent(s);

        return this;
    }

    public FilmXML addGroup(GroupXML g){
        Element groups = this.getChild("groups");
        groups.addContent(g);

        return this;
    }

    public void saveToFile(String filename){
        try {
            XMLOutputter outputter = new XMLOutputter();
            outputter.setFormat(Format.getPrettyFormat());
            FileWriter fw = new FileWriter(filename+".xml");
            outputter.output(new Document(this), fw);
            fw.close();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public GroupXML getGroup(String groupName){
        List<Element> groups = this.getChild("groups").getChildren();

        Iterator i = groups.iterator();
        while(i.hasNext()){
            GroupXML group = (GroupXML) i.next();
            if (group.getAttribute("name").equals(groupName)){
                return group;
            }
        }

        return null;
    }

    public ShapeXML getShape(String shapeName){
        List<Element> shapes = this.getChild("shapes").getChildren();

        Iterator i = shapes.iterator();
        while(i.hasNext()){
            ShapeXML shape = (ShapeXML) i.next();
            if (shape.getAttribute("name").equals(shapeName)){
                return shape;
            }
        }

        return null;
    }
}
