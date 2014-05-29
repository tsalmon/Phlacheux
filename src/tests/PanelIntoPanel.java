package tests;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

class Vue extends JPanel{
	
	public void paintComponent(Graphics g){
		g.setColor(Color.RED);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
}

public class PanelIntoPanel extends JFrame implements ActionListener, MouseListener{
	JButton btn = new JButton("ok");
	JSpinner size = new JSpinner(new SpinnerNumberModel(100,100,3500,25));
	Vue vue = new Vue();
	
	PanelIntoPanel(){
		JPanel pan = new JPanel();
		pan.setLayout(new BorderLayout());

		vue.setSize(new Dimension((int)size.getValue(), (int)size.getValue()));
		
		JPanel pan_nord = new JPanel();
		pan_nord.add(btn);
		pan_nord.add(size);
		JPanel pan_center = new JPanel();
		pan_center.add(vue);
		pan.add("North", pan_nord);
		pan.add("Center", pan_center);

		btn.addActionListener(this);
		vue.addMouseListener(this);
		
		this.setContentPane(pan);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 500);
		this.setVisible(true);	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn){
			vue.setSize(new Dimension((int)size.getValue(), (int)size.getValue()));
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
		// TODO Auto-generated method stub
		if(e.getSource() == vue){
			System.out.println("Click sur la vue");
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String args []){
		new PanelIntoPanel();
	}
}
