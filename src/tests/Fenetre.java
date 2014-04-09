package tests;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

public class Fenetre extends JFrame implements MouseListener{
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		Fenetre f = new Fenetre();
	}

	private Panneau pan = new Panneau(0);

	public Fenetre() {
		this.setTitle("Animation");
		this.setSize(440, 440);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(pan);
		//ctrl = new Controller(pan);
		pan.addMouseListener(this);

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

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		go();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}