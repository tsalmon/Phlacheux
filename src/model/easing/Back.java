package model.easing;

public class Back extends Easing {
	
	public  double  easeIn(double t,double b , double c, double d) {
		double s = 1.70158f;
		return c*(t/=d)*t*((s+1)*t - s) + b;
	}
	
	public  double  easeIn(double t,double b , double c, double d, double s) {
		return c*(t/=d)*t*((s+1)*t - s) + b;
	}
	
	public  double  easeOut(double t,double b , double c, double d) {
		double s = 1.70158f;
		return c*((t=t/d-1)*t*((s+1)*t + s) + 1) + b;
	}
	
	public  double  easeOut(double t,double b , double c, double d, double s) {
		return c*((t=t/d-1)*t*((s+1)*t + s) + 1) + b;
	}
	
	public  double  easeInOut(double t,double b , double c, double d) {
		double s = 1.70158f;
		if ((t/=d/2) < 1) return c/2*(t*t*(((s*=(1.525f))+1)*t - s)) + b;
		return c/2*((t-=2)*t*(((s*=(1.525f))+1)*t + s) + 2) + b;
	}
	
	public  double  easeInOut(double t,double b , double c, double d, double s) {	
		if ((t/=d/2) < 1) return c/2*(t*t*(((s*=(1.525f))+1)*t - s)) + b;
		return c/2*((t-=2)*t*(((s*=(1.525f))+1)*t + s) + 2) + b;
	}

    public String toString(){
        return "back";
    }
}
