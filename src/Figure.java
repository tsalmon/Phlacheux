import java.awt.Polygon;


public class Figure extends Polygon{
	int id_fig;
	int xPol[];
	int yPol[];
	int nPol;
	
	Figure(int id, int[] xP, int[] yP, int size){
		super(xP, yP, size);
		id_fig = id;
		xPol = xP;
		yPol = yP;
		nPol = size;
	}
}
