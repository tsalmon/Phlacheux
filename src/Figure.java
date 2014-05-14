import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Figure{
	public int id;
	private Shape path;
	private Color border_color;
	private Color fill_color;
	private int border_weight;
	LinkedList<Animation_Translate> translation;
	LinkedList<Animation_Bezier> bezier;
	LinkedList<Animation_RotationPoint> rotation_point;
	LinkedList<Animation_RotationCentre> rotation_centre;
	LinkedList<Animation_Echelle> anim_echelle;
	LinkedList<Animation_Couleur> anim_couleur;
	LinkedList<Animation_Bordure> anim_bordure;
	
	Figure(){
		this.id = -1;
	}
	
	Figure(Shape shape, Color border, int border_weight, Color fill){
		this.path = shape;
		this.setBorder_weight(border_weight);
		this.setBorder_color(border);
		this.setFill_color(fill);
		//this.liste_animations = new LinkedList<Animation>();
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

	public Shape getShape(){
		return path;
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
	
	public String toString(){
		return "<" + this.path + ">";
	}

	//move the barycenter of the figure to the point (x, y) in time 
	public void addAnim_translate(int x, int y, int time){
		
	}
	
	//make a rotation around point (x, y)
	public void addAnim_rotation(int x, int y, int sens, int time){
		
	}

	public int getBorder_weight() {
		return border_weight;
	}

	public void setBorder_weight(int border_weight) {
		this.border_weight = border_weight;
	}	
}
