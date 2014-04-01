import java.awt.Dimension;

import javax.swing.JFrame;


public class Placheux {
	public static void main(String[] args) {
		JFrame window = new JFrame();
		Controller controller = new Controller();
		Ecran screen = new Ecran(controller);
		controller.setEcran(screen);
		
		screen.addMouseListener(controller);
		screen.addMouseMotionListener(controller);
		window.setContentPane(screen);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();		

		window.setMinimumSize(new Dimension(1152, 486));
		window.setSize(new Dimension(1152, 486));
		window.setMaximumSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize());
		
		window.setVisible(true);
	}
}
