
package model.easing;

/**
 *
 * @author Josian Chevalier, Vladislav Fitc, Thomas Salmon
 *
 * Modèle
 *
 * Projet Interface Graphique, Paris 7, Master 1, 2013-2014
 *
 */
abstract public class Easing {                
	
        public Easing(){
            
        }
    
	public float easeNone (float t,float b , float c, float d) {
		return c*t/d + b;
	}
	
	abstract public float easeIn (float t,float b , float c, float d);
	
	abstract public float easeOut (float t,float b , float c, float d);
	
	abstract public float easeInOut (float t,float b , float c, float d);
	


}
