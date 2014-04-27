
package model.movable;
import java.util.ArrayList;
import java.util.Arrays;

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

     protected ArrayList<Movable> movables;

    
    
    //         Constructeur
    //----------------------------

        public void FigureGroup(){
            movables=new ArrayList<Movable>();
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
                if(this.movables.size()!=0){int sumx=0;
                    int sumy=0;
                    for(Movable m : movables){
                        sumx+=m.getGravityCenter().getX();
                        sumy+=m.getGravityCenter().getY();
                    }
                    this.setGravityCenter(new Point((int)(sumx/(movables.size())),(int)(sumy/movables.size())));
                }
                this.setGravityCenterPerso(false);
            }

    //          Methodes
    //----------------------------

        @Override
           public void rotation(int angle, Point p){
               for (Movable m : this.movables){
                   m.rotation(angle,p);
               }
           }

        @Override
           public void rotation(int angle){
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
           public void translation(int x_from, int y_from, int x_to, int y_to){
               for (Movable m : this.movables){
                   m.translation(x_from,y_from,x_to,y_to);
               }
           }

        @Override
           public void changeStrokeThickness(int thickness){
               for (Movable m : this.movables){
                   m.changeStrokeThickness(thickness);
               }
           }

        @Override
           public void scaling(float scale){
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


}
