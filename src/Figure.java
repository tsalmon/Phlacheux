import java.awt.Color;
import java.awt.Polygon;
import java.awt.Shape;

public class Figure extends Polygon{
	int id_fig;
	int xPol[];
	int yPol[];
	int nPol;
	
	Color border_color;
	int   border_width;
	Color fil_color;
	
	Figure(int id, int[] xP, int[] yP, int size){
		super(xP, yP, size);
		id_fig = id;
		xPol = xP;
		yPol = yP;
		nPol = size;
	}
}
