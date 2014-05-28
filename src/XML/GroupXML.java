package XML;

import org.jdom2.Element;

import java.util.Iterator;
import java.util.List;

public class GroupXML extends Element{

    private GroupXML(String name){
        setName("shape");

        setAttribute("name", name);

        addContent(new Element("animations"));
        addContent(new Element("groups"));
        addContent(new Element("shapes"));
    }

    public GroupXML addAnimation(AnimationXML a){
        Element animations = this.getChild("animations");
        animations.addContent(a);

        return this;
    }

    public GroupXML addGroup(GroupXML g){
        Element groups = this.getChild("groups");
        groups.addContent(g);

        return this;
    }

    public GroupXML addShape(ShapeXML s){
        Element shapes = this.getChild("shapes");
        shapes.addContent(s);

        return this;
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
}
