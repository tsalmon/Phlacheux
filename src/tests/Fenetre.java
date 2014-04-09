package tests;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

public class Fenetre extends JFrame implements MouseListener {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		Fenetre f = new Fenetre();
	}

	private Panneau pan = new Panneau(1);

	public Fenetre() {
		this.setTitle("Animation");
		this.setSize(440, 440);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(pan);
		//pan.addMouseListener(this);
		this.setVisible(true);
		go_boule();
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
        
	private void go() {

            int j = 1;
            int a = 0;

            for(int k = 0; k < 175; k++) {
                pan.repaint();
                pan.setTheta(1);
                pan.setPosX(a);
                pan.setPosY(a++);
			
                try {
                    Thread.sleep(30);
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