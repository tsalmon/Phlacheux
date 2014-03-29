import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.geom.*;

import javax.swing.*;
import javax.swing.event.*;

import java.util.*;

public class Placheux extends JPanel{
	JPanel panel_modif = new JPanel(new GridLayout(1, 5));
	JPanel panel_board = new JPanel(new GridLayout(1, 2));
	JPanel panel_elem_part = new JPanel(new BorderLayout());
	JPanel panel_view_part = new JPanel(new BorderLayout());
	JPanel panel_elem_menu = new JPanel(new GridLayout(1,5));
	JPanel panel_view_menu = new JPanel(new GridLayout(1,5));
	JPanel panel_tab = new JPanel();
	JPanel panel_view = new JPanel();
	
	Placheux(){
		setLayout(new BorderLayout());

		panel_elem_part.add("North", panel_elem_menu);
		panel_elem_part.add( "Center", panel_tab);

		panel_view_part.add("North", panel_view_menu);
		panel_view_part.add("Center", panel_view);
		
		panel_board.add(panel_elem_part);
		panel_board.add(panel_view_part);
		
		this.add("Center", panel_board);
		this.add("South", panel_modif);
	}

	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setContentPane(new Placheux());
		window.pack();		
		window.setVisible(true);
	}
}

