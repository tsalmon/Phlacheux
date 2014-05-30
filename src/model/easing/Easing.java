
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
    
	public double easeNone (double t,double b , double c, double d) {
		return c*t/d + b;
	}
	
	abstract public double easeIn (double t,double b , double c, double d);
	
	abstract public double easeOut (double t,double b , double c, double d);
	
	abstract public double easeInOut (double t,double b , double c, double d);
	


}
