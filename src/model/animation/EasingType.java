
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

public enum EasingType {
    EASE_IN("easeIn"),
    EASE_OUT("easeOut"),
    EASE_IN_OUT("easeInOut"),
    EASE_NONE("easeNone");	  

    private String name = "";

    //Constructeur
    EasingType(String name){
      this.name = name;
    }

    public static EasingType getType(String pType) {
        for (EasingType type: EasingType.values()) {
            if (type.toString().equals(pType)) {
                return type;
            }
        }
        throw new RuntimeException("unknown type");
    }

    public String toString(){
      return name;
    }      
}
