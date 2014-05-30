
package model.movable;
import org.jdom2.Element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Josian Chevalier, Vladislav Fitc, Thomas Salmon
 *
 * Modèle
 *
 * Projet Interface Graphique, Paris 7, Master 1, 2013-2014
 *
 */
public class MovableGroup extends Movable{


    //          Attributs
    //---------------------------

     protected ArrayList<Movable> movables = new ArrayList<Movable>();

    
    
    //         Constructeur
    //----------------------------

        public MovableGroup(){
        }

        protected MovableGroup(Element xml){
            super(xml);

            List<Element> grouplinks = xml.getChild("grouplinks").getChildren();
            List<Element> shapelinks = xml.getChild("shapelinks").getChildren();

            Iterator it = grouplinks.iterator();

            while (it.hasNext()){
                Element e = (Element) it.next();
                String eName = e.getAttributeValue("name").toString();
                addMovable(MovablePool.getInstance().getMovable(eName));
            }

            it = shapelinks.iterator();

            while (it.hasNext()){
                Element e = (Element) it.next();
                String eName = e.getAttributeValue("name").toString();
                addMovable(MovablePool.getInstance().getMovable(eName));
            }

            //TODO: load animations!
            List<Element> animations = xml.getChild("animations").getChildren();

        }

     
    //          Accesseurs
    //----------------------------

        public void addMovable(Movable m) {
            this.movables.add(m);
            this.gcResfresh();
        }

        public void removeMovable(Movable m){
            this.movables.remove(m);
            this.gcResfresh();
        }
        
        //  calcul automatique du centre de gravité

     @Override
            protected void autoGravityCenter(){
                if(this.movables.size()!=0){
                    double sumx=0;
                    double sumy=0;
                    for(Movable m : movables){
                        sumx+=m.getGravityCenter().getX();
                        sumy+=m.getGravityCenter().getY();
                    }
                    this.setGravityCenter(new Point(sumx/(movables.size()),sumy/movables.size()));
                }
                this.setGravityCenterPerso(false);
            }

    //          Methodes
    //----------------------------

        @Override
           public void rotation(double angle, Point p){
               for (Movable m : this.movables){
                   m.rotation(angle,p);
               }
           }

        @Override
           public void rotation(double angle){
               for (Movable m : this.movables){
                   m.rotation(angle,this.getGravityCenter());
               }
           }

        @Override
           public void translation(Point from, Point to){
               for (Movable m : this.movables){
                   m.translation(from,to);
               }
           }

        @Override
           public void translation(double x_from, double y_from, double x_to, double y_to){
               for (Movable m : this.movables){
                   m.translation(x_from,y_from,x_to,y_to);
               }
           }

        @Override
           public void changeStrokeThickness(double thickness){
               for (Movable m : this.movables){
                   m.changeStrokeThickness(thickness);
               }
           }

        @Override
           public void scaling(double scale){
               for (Movable m : this.movables){
                   m.scaling(scale);
               }
           }

       @Override
       public String toString() {
           StringBuilder builder = new StringBuilder();
           builder.append("MovableGroup [\n");
           builder.append(super.toString());
           builder.append(",\nmovables=").append(movables);
           builder.append("]\n");
           return builder.toString();
       }

        public Element toXML(){
            Element el = new Element("group");
            el.setAttribute("name", name);

            Element animations = new Element("animations");
            //TODO: add animations!
            Element grouplinks = new Element("grouplinks");
            Element shapelinks = new Element("shapelinks");

            Iterator it = movables.iterator();

            while (it.hasNext()){
                Movable m = (Movable) it.next();

                if (m instanceof Figure){
                    String shapeName = m.toXML().getAttribute("name").toString();
                    Element shapeLink = new Element("shapeLink");
                    shapeLink.setAttribute("name", shapeName);
                    shapelinks.addContent(shapeLink);
                }

                if (m instanceof MovableGroup){
                    String groupName = m.toXML().getAttribute("name").toString();
                    Element groupLink = new Element("grouplink");
                    groupLink.setAttribute("name", groupName);
                    grouplinks.addContent(groupLink);
                }
            }

            el.addContent(animations);
            el.addContent(grouplinks);
            el.addContent(shapelinks);

            return el;
        }

}
