import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

public class NewElem extends JFrame{
	static final int WIDTH  = 800;
	static final int HEIGHT = 600;
	PanElem pan = new PanElem();
    private Polygon poly;

	NewElem(){
		PanElem ecran = new PanElem();	
		CtrlElem controller = new CtrlElem(ecran);
		ecran.addMouseListener(controller);
		ecran.addMouseMotionListener(controller);
		Box box = new Box(BoxLayout.Y_AXIS);
		box = new Box(BoxLayout.X_AXIS);
		box.add(ecran);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
		getContentPane().add(box);
		pack();
		setVisible(true);
	}	

	class PanElem extends JPanel{
		static final int WIDTH  = 600;
		static final int HEIGHT = 400;

		 final Color couleurBord = Color.red;
		 final Color couleurInterieur = Color.blue;
		 final Color couleurFond = Color.black;

		int a, b;
	    int xArrow[];
	    int yArrow[];
	    private Polygon poly;

		PanElem() { 
			xArrow = new int[7];
			yArrow = new int[7];
			poly = new Polygon(xArrow, yArrow, 7);
			setPreferredSize(new Dimension(WIDTH, HEIGHT));
		}

		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			super.paintComponent(g2); 
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                RenderingHints.VALUE_ANTIALIAS_ON);
			g2.drawPolygon(poly);
		}
		
		public void init_a_b(int a, int b){
			this.a = a;
			this.b = b;
		}
		
		public void dessiner(int x, int y){
			
		}
	}
	
	class CtrlElem extends MouseInputAdapter implements ActionListener {
		static final int ENTREE = 0, REMPL = 1;
		PanElem pan;
		int x0, y0, xc, yc;    
		int mode;
		CtrlElem(PanElem pan) {
			this.pan = pan;
		}
		public void actionPerformed(ActionEvent e) {}
		public void mousePressed(MouseEvent e) {
			pan.init_a_b(e.getX(), e.getY());
		}
		public void mouseReleased(MouseEvent e) {
			System.out.println("Released");
		}
		public void mouseDragged (MouseEvent e) {		
			pan.dessiner(e.getX(), e.getY());
			pan.repaint();
		}
	}

}
