import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Figure{
	public int id;
	private String name;
	private Shape path;
	private Color border_color;
	private Color fill_color;
	int a; //firts points;
	int b; //first points;
	LinkedList<Animation>liste_animations;
	
	Figure(){
		this.id = -1;
	}
	
	Figure(Shape shape, Color border, Color fill, String name){
		this.path = shape;
		this.setBorder_color(border);
		this.name = name;
		this.setFill_color(fill);
		this.liste_animations = new LinkedList<Animation>();
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

	public int getId(){
		return this.id;
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
	
	public String getName(){
		return this.name;
	}
	
	public String toString(){
		return "<" + this.name + ": (" + this.path + ")>";
	}

	//move the barycenter of the figure to the point (x, y) in time 
	public void addAnim_translate(int x, int y, int time){
		
	}
	
	//make a rotation around point (x, y)
	public void addAnim_rotation(int x, int y, int sens, int time){
		
	}	
}
