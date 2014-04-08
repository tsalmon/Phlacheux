package tests;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class Fenetre extends JFrame implements MouseListener {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new Fenetre();
	}

	private Panneau pan = new Panneau();

	public Fenetre() {
		this.setTitle("Animation");
		this.setSize(700, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(pan);
		//pan.addMouseListener(this);
		this.setVisible(true);
		go(200, 200);
	}

	private void go(int u, int v) {

		int j = 1;
		int a = 0;
		for(int k = 0; k < 200; k++) {
			pan.setTheta(1);
			pan.repaint();

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			pan.setPosX(a);
			pan.setPosY(a++);
		}
		for(int k = 0; k < 200; k++){
			pan.setDelta(j+=10);
			pan.repaint();

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
		for(int k = 0; k < 100; k++) {
			pan.setTheta(1);
			pan.repaint();

			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			pan.setPosX(a);
			pan.setPosY(a++);
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
		//System.out.println("in");
		//go(e.getX(), e.getY());
		//System.out.println("out");
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