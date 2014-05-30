
package model.animation;

/**
 *
 * @author Josian Chevalier, Vladislav Fitc, Thomas Salmon
 *
 * Modèle
 *
 * Projet Interface Graphique, Paris 7, Master 1, 2013-2014
 *
 */

public enum Easing_Type {
    EASE_IN("easeIn"),
    EASE_OUT("easeOut"),
    EASE_IN_OUT("easeInOut"),
    EASE_NONE("easeNone");	  

  private String name = "";

    //Constructeur
    Easing_Type(String name){
      this.name = name;
    }

    public String toString(){
      return name;
    }      
}
