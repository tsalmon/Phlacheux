import org.jdom2.*;

public class XMLHelper {




    public static void buildFilm(){
        Element rootElement = new Element("Film");
        Document doc = new Document(rootElement);
        rootElement.setAttribute("backgroundR", "255");
        rootElement.setAttribute("backgroundG", "255");
        rootElement.setAttribute("backgroundB", "255");
        rootElement.setAttribute("width", "640");
        rootElement.setAttribute("height", "480");
        rootElement.setAttribute("duration", "1000");
        rootElement.setAttribute("backgroundB", "255");

        Element shapes = new Element("shapes");
        Element groups = new Element("groups");
        rootElement.addContent(shapes);
        rootElement.addContent(groups);
    }





    private static Element group(String name){
        Element group = new Element("group");

        group.addContent(new Element("grouplinks"));
        group.addContent(new Element("shapelinks"));
        group.addContent(new Element("animations"));

        return group;
    }

    private static Element grouplink(String name){
        Element glink = new Element("grouplink");

        glink.setAttribute("name", name);

        return glink;
    }

    private static Element shapeLink(String name){
        Element slink = new Element("shapelink");

        slink.setAttribute("name", name);

        return slink;
    }






}
