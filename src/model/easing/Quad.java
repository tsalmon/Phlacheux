package model.easing;

public class Quad extends Easing {
	
	public  double  easeIn(double t,double b , double c, double d) {
		return c*(t/=d)*t + b;
	}
	
	public  double  easeOut(double t,double b , double c, double d) {
		return -c *(t/=d)*(t-2) + b;
	}
	
	public  double  easeInOut(double t,double b , double c, double d) {
		if ((t/=d/2) < 1) return c/2*t*t + b;
		return -c/2 * ((--t)*(t-2) - 1) + b;
	}
	
}
