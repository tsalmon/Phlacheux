
package model.easing;

import javax.lang.model.type.UnknownTypeException;

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

    public static Easing withString(String s) throws Exception {
        if (s.equals("back")){
            return new Back();
        }else
        if (s.equals("bounce")){
            return new Bounce();
        }else
        if (s.equals("circ")){
            return new Circ();
        }else
        if (s.equals("cubic")){
            return new Cubic();
        }else
        if (s.equals("elastic")){
            return new Elastic();
        }else
        if (s.equals("expo")){
            return new Expo();
        }else
        if (s.equals("linear")){
            return new Linear();
        }else
        if (s.equals("quad")){
            return new Quad();
        }else
        if (s.equals("quart")){
            return new Quart();
        }else
        if (s.equals("quint")){
            return new Quint();
        }else
        if (s.equals("sine")){
            return new Sine();
        }else{
            throw new Exception("Unknown easing");
        }
    }
    
	public double easeNone (double t,double b , double c, double d) {
		return c*t/d + b;
	}
	
	abstract public double easeIn (double t,double b , double c, double d);
	
	abstract public double easeOut (double t,double b , double c, double d);
	
	abstract public double easeInOut (double t,double b , double c, double d);


}
