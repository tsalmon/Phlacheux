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
    	System.out.println(e.getSource());
    }
    public void mousePressed(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {
    }
    public void mouseDragged (MouseEvent e) {
    }
}