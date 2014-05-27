import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Launcher {
	Placheux screen;
	NewElem elem;
	
	Launcher(){

        Viewer viewer = new Viewer();
        try {
            viewer.addFrame(ImageIO.read(new File("Resources/Viewer/Iceberg.jpg")));
            viewer.addFrame(ImageIO.read(new File("Resources/Viewer/Leaf.jpg")));
            viewer.addFrame(ImageIO.read(new File("Resources/Viewer/Misty-Mountains.jpg")));
            viewer.addFrame(ImageIO.read(new File("Resources/Viewer/Nighthawks.jpg")));
            viewer.addFrame(ImageIO.read(new File("Resources/Viewer/Pinstripe.jpg")));
            viewer.addFrame(ImageIO.read(new File("Resources/Viewer/Pond-Reeds.jpg")));
            viewer.addFrame(ImageIO.read(new File("Resources/Viewer/Redwoods.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        viewer.pack();
        viewer.setVisible(true);


//		JFrame window = new JFrame();
//		screen = new Placheux();
//
//
//
//		window.setContentPane(screen);
//		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		window.pack();
//
//		window.setMinimumSize(new Dimension(1152, 700));
//		window.setSize(new Dimension(1152, 700));
//		window.setMaximumSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize());
//
//		window.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Launcher();
	}
}