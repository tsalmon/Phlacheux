import java.awt.Dimension;

import javax.swing.JFrame;


public class Launcher {
	Placheux screen;
	NewElem elem;
	
	Launcher(){		
		JFrame window = new JFrame();
		screen = new Placheux();
		
		window.setContentPane(screen);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();		

		window.setMinimumSize(new Dimension(1152, 700));
		window.setSize(new Dimension(1152, 700));
		window.setMaximumSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize());
		
		window.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Launcher();
	}
}