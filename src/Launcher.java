import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Launcher{
	Placheux screen;
	
	Launcher(){	
		run();
	}

	public void run(){
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