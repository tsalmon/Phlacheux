package model.easing;

public class Sine extends Easing {
	
	public  double  easeIn(double t,double b , double c, double d) {
		return -c * (double)Math.cos(t/d * (Math.PI/2)) + c + b;
	}
	
	public  double  easeOut(double t,double b , double c, double d) {
		return c * (double)Math.sin(t/d * (Math.PI/2)) + b;	
	}
	
	public  double  easeInOut(double t,double b , double c, double d) {
		return -c/2 * ((double)Math.cos(Math.PI*t/d) - 1) + b;
	}
	
}
