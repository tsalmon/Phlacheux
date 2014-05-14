package tests;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class CellPrinting extends JPanel implements MouseListener{
	JTable tab[] = new JTable[30];
	int origin_x, origin_y;	
	public CellPrinting(){
		JScrollPane sp = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		JPanel p = new JPanel();
		sp.setPreferredSize(new Dimension(300, 300));
		for(int i = 0; i < 30; i++){
			tab[i] = new JTable(30, 1);
			tab[i].setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			tab[i].setColumnSelectionAllowed(true);
			tab[i].setRowSelectionAllowed(true);	
			tab[i].addMouseListener(this);
			p.add(tab[i]);
		}
		sp.add(p);
		add(sp);
	}
	public static void main(String args[]){
		JFrame fen = new JFrame();
		fen.setSize(300, 300);
		fen.setContentPane(new CellPrinting());
		fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fen.setVisible(true);
	}
	public void mouseReleased(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
