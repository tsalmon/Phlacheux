import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;

public class Controller extends MouseInputAdapter implements ActionListener {
	Ecran ecran;
	Controller() {
	}

	void setEcran(Ecran e){
		ecran = e;
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
		}
		if(ecran.add_elem_seq == e.getSource()){
			System.out.println("add elem seq");		
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
			System.out.println("view");
		}
	}
	public void mouseReleased(MouseEvent e) {
		System.out.print("mouseReleased: ");
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
			System.out.println("view");
		}
	}
	public void mouseDragged (MouseEvent e) {
		System.out.print("mouseDragged: ");
		if(e.getSource() == ecran.panel_view){
			System.out.println("view");
		}		
	}
}