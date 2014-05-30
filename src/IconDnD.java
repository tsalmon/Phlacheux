


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.TransferHandler;


public class IconDnD extends JFrame {
	JLabel label1, label2;
	public IconDnD() {

		setTitle("Icon Drag & Drop");

		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 50, 15));

		BufferedImage icon1 = null;
		BufferedImage icon2 =  null;
		BufferedImage icon3 = null;

		try{
			icon1 =  ImageIO.read(new File("Resources/Vue/square.png"));
			icon2 =  ImageIO.read(new File("Resources/Vue/star.png"));
			icon3 =  ImageIO.read(new File("Resources/Vue/cross.png"));
		} catch(IOException e){
			e.printStackTrace();
		}

		JPanel pan = new JPanel();
		pan.setLayout(new FlowLayout());
		pan.setPreferredSize(new Dimension(50, 50));
		
		label1  = new JLabel(new ImageIcon(icon1), JLabel.CENTER);
		label2  = new JLabel(new ImageIcon(icon3), JLabel.CENTER);

		MouseListener listener = new DragMouseAdapter();
		label1.addMouseListener(listener);
		label2.addMouseListener(listener);

		label1.setTransferHandler(new TransferHandler("icon"));
		pan.setTransferHandler(new TransferHandler("icon"));
		//pan.setTransferHandler(new TransferHandler("icon"));
		label2.setTransferHandler(new TransferHandler("icon"));

		panel.add(label1);
		panel.add(pan);
		panel.add(label2);
		add(panel);

		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	class DragMouseAdapter extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			JComponent c = (JComponent) e.getSource();
			TransferHandler handler = c.getTransferHandler();
			handler.exportAsDrag(c, e, TransferHandler.COPY);			
		}
	}

	public static void main(String[] args) {
		new IconDnD();
	}
}
