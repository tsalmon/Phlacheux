import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;
import java.util.ArrayList;
import java.util.List;


public class Figure{
	private GeneralPath path;
	private Color border_color;
	private Color fill_color;
	
	Figure(GeneralPath shape, Color border, Color fill){
		this.path = shape;
		this.setBorder_color(border);
		this.setFill_color(fill);
	}
	
	public List<double[]> getPoints(){
		List<double[]> l = new ArrayList<double[]>();
		double[] coords = new double[2];
		
		for(PathIterator i = path.getPathIterator(null); !i.isDone(); i.next()){
			i.currentSegment(coords);
			l.add(coords);
		}
		
		return l;
	}

	public Color getBorder_color() {
		return border_color;
	}

	public void setBorder_color(Color border_color) {
		this.border_color = border_color;
	}

	public Color getFill_color() {
		return fill_color;
	}

	public void setFill_color(Color fill_color) {
		this.fill_color = fill_color;
	}
}
