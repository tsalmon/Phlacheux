import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

public class DragComponent extends JPanel implements MouseListener{
	BufferedImage img;
	int id_fig;

	public DragComponent(BufferedImage img, int fig) {
		this.img = img;
		this.id_fig = fig;
		setPreferredSize(new Dimension(50,50));
		setTransferHandler(new ImgTransferHandlerSource(this));
		this.addMouseListener(this);
	}
	public void mousePressed(MouseEvent e) {
		JPanel p = (JPanel)e.getSource();
		p.getTransferHandler().exportAsDrag(p,e,TransferHandler.COPY);
		e.consume();
	}

	public void mouseReleased(MouseEvent e) { }
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

	public void paintComponent(Graphics g){
		g.drawImage(img, 0, 0, null);
	}
}
