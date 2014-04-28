package model.easing;

public class Linear extends Easing {
	
	public  float easeIn (float t,float b , float c, float d) {
		return c*t/d + b;
	}
	
	public  float easeOut (float t,float b , float c, float d) {
		return c*t/d + b;
	}
	
	public  float easeInOut (float t,float b , float c, float d) {
		return c*t/d + b;
	}
	
}
