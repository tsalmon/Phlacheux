import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;

public class Controller extends MouseInputAdapter implements ActionListener, ComponentListener{
	Placheux ecran;
	NewElem elem;
	
	Controller() {
	}

	void setEcran(Placheux e){
		ecran = e;
	}

	void setElem(NewElem e){
		elem = e;
	}

	public void actionPerformed(ActionEvent e) {
		System.out.print ("ActionPerformed: ");
		if(ecran.lecture_pause == e.getSource()){
			System.out.println("lecture/pause");
		} 
		if(ecran.stop == e.getSource()){
			System.out.println("stop"); 
		}
		if(ecran.avance_10 == e.getSource()){
			System.out.println("+10");			
		}
		if(ecran.recule_10 == e.getSource()){
			System.out.println("-10");
		}
		if(ecran.add_seq == e.getSource()){
			System.out.println("add seq");			
		}
		if(ecran.add_elem == e.getSource()){
			System.out.println("add elem");			
			elem = new NewElem(ecran);
			System.out.println(elem);
		}
		if(ecran.rendu == e.getSource()){
			System.out.println("rendu");			
		}
	}
	public void mousePressed(MouseEvent e) {
		System.out.print("mousePressed: ");
		if(e.getSource() == ecran.tab){
			System.out.println("tab");			
		}
		if(e.getSource() == ecran.seq){
			System.out.println("seq");			
		}
		if(e.getSource() == ecran.panel_modif){
			System.out.println("modif");			
		}
		if(e.getSource() == ecran.panel_view){ 
			//getElem select on the screen
			System.out.println("view");
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		System.out.print("mouseReleased: ");
		if(e.getSource() == ecran.tab){
			System.out.println("tab");			
			System.out.println(ecran.tab.getSelectedColumn() + "  " + ecran.tab.getSelectedRow());
		}
		if(e.getSource() == ecran.seq){
			System.out.println("seq");
			System.out.println(ecran.seq.getSelectedColumn() + "  " + ecran.seq.getSelectedRow());
		}
		if(e.getSource() == ecran.panel_modif){
			System.out.println("modif");			
		}
		if(e.getSource() == ecran.panel_view){
			System.out.println("view");
		}
	}
	public void mouseDragged (MouseEvent e) {
		System.out.print("mouseDragged: ");
		if(e.getSource() == ecran.panel_view){
			//TODO: 
			//if there is no elements selected, move the view: +/- e.getX, x/- e.getY
			//if there is elem select: make an arrow beetwen 
			///the barycenter of the figure and the point of the moose, to see the translation
			System.out.println("view");
		}		
	}
	
	public void componentMoved(ComponentEvent e) {
		
	}
	
	public void componentResized(ComponentEvent e) {
		System.out.println("resize");
	}

	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

}