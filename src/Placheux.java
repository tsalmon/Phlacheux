import java.awt.Dimension;

import javax.swing.JFrame;


public class Placheux {
	public static void main(String[] args) {
		JFrame window = new JFrame();
		//window.setResizable(false);
		Ecran screen = new Ecran();
		Controller controller = new Controller(screen);
		screen.addMouseListener(controller);
		screen.addMouseMotionListener(controller);
		window.setContentPane(new Ecran());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();		

		window.setMinimumSize(new Dimension(1152, 486));
		window.setSize(new Dimension(1152, 486));
		window.setMaximumSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize());
		
		window.setVisible(true);
	}
}
