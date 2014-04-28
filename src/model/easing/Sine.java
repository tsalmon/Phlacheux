package model.easing;

public class Sine extends Easing {
	
	public  float  easeIn(float t,float b , float c, float d) {
		return -c * (float)Math.cos(t/d * (Math.PI/2)) + c + b;
	}
	
	public  float  easeOut(float t,float b , float c, float d) {
		return c * (float)Math.sin(t/d * (Math.PI/2)) + b;	
	}
	
	public  float  easeInOut(float t,float b , float c, float d) {
		return -c/2 * ((float)Math.cos(Math.PI*t/d) - 1) + b;
	}
	
}
