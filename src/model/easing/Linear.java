package model.easing;

public class Linear extends Easing {
	
	public  double easeIn (double t,double b , double c, double d) {
		return c*t/d + b;
	}
	
	public  double easeOut (double t,double b , double c, double d) {
		return c*t/d + b;
	}
	
	public  double easeInOut (double t,double b , double c, double d) {
		return c*t/d + b;
	}

    public String toString(){
        return "linear";
    }
}
