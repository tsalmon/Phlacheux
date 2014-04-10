package tests;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

public class Fenetre extends JFrame implements MouseListener{
	private static final long serialVersionUID = 1L;
	int choix_fig, np;
	Point2D[] P = new Point2D[4];
	private Panneau pan;


	public static void main(String[] args) {
		Fenetre f = new Fenetre(2);
	}

	public Fenetre(int i) {
		this.choix_fig = i;
		this.setSize(440, 440);
		pan = new Panneau(i);
		this.initgr();
		this.setTitle("Animation");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.add(pan);
		pan.addMouseListener(this);
		if(i == 1){
			this.go_boule();
		}
		this.setVisible(true);		
	}

	private void go_boule(){
		int i = 0;
		while(true){
			pan.repaint();
			pan.setDelta(i+=10);
			try {
				Thread.sleep(30);
			} catch (InterruptedException ex) {
				Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	void go() {
		Graphics g = pan.getGraphics();
		int j = 1;
		int a = 0;

		for(int k = 0; k < 175; k++) {
			g.setColor(pan.getBackground());
			g.fillRect(0,0,pan.getSize().width, pan.getSize().height);
			pan.paint(g);
			pan.setTheta(1);
			pan.setPosX(a);
			pan.setPosY(a++);

			try {
				Thread.sleep(24);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}

	void initgr() {
		Dimension d = getSize();
		int maxX = d.width - 1, maxY = d.height - 1;
		pan.pixelSize = Math.max(pan.rWidth/maxX, pan.rHeight/maxY);
		pan.centerX = maxX/2; pan.centerY = maxY/2;
	}
	
	Point2D middle(Point2D A, Point2D B){
		return new Point2D.Double((A.getX() + B.getX())/2, (A.getY() + B.getY())/2);
	}

	int iX(double d){
		return (int) Math.round(pan.centerX + d/pan.pixelSize);
	}
	int iY(double d){
		return (int) Math.round(pan.centerY - d/pan.pixelSize);
	}
	double fx(int X){
		System.out.println("fx() " + X + " " + pan.centerX + " " + pan.getSize());
		return (X - pan.centerX) * pan.pixelSize;
	}
	double fy(int Y){
		return (pan.centerY - Y) * pan.pixelSize;
	}   
	
	void go_bezier(Graphics g, Point2D P0, Point2D P1, Point2D P2, Point2D P3){ 
		int x0 = iX(P0.getX()), 
				y0 = iY(P0.getY()),
				x3 = iX(P3.getX()), 
				y3 = iY(P3.getY());
		if (Math.abs(x0 - x3) <= 1 && Math.abs(y0 - y3) <= 1){
			pan.paint(g);
			g.drawOval(x0, y0, 30, 30);
			try {
				Thread.sleep(12);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			Point2D A = middle(P0, P1), B = middle(P3, P2),
					C = middle(P1, P2), A1 = middle(A, C),
					B1 = middle(B, C), C1 = middle(A1, B1);
			go_bezier(g, P0, A, A1, C1);
			go_bezier(g, C1, B1, B, P3);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (this.choix_fig == 0) 
			go();
		if(this.choix_fig == 2){
			double x =  fx(e.getX()), 
					y = fy(e.getY());

			P[np++] = new Point2D.Double(x, y);
			System.out.println(x + " " + y);
			
			if (np == 4) {
				System.out.println("in");
				go_bezier(pan.getGraphics(), P[0], P[1], P[2], P[3]);
				np = 0;
				System.out.println("out");
			}
			
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}