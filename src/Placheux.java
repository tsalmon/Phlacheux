import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.geom.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.AbstractTableModel;

import java.util.*;

public class Placheux extends JPanel{
    private boolean DEBUG = false;

	class TableModel extends AbstractTableModel {
		private String[] columnNames;       
		private Object[][] data; 
		
		TableModel(String[] col, Object [][] d){
			columnNames = col;
			data = d;
		}
		
        public int getColumnCount() {
            return columnNames.length;
        }
         public int getRowCount() {
            return data.length;
        }
        public String getColumnName(int col) {
            return columnNames[col];
        }
        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }
 
        public boolean isCellEditable(int row, int col) {
            return !(col < 2);
        }
 
        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
         }
	}
 
	//view menu
	JButton lecture_pause = new JButton("lecture");
	JButton stop = new JButton("stop");
	JButton avance_10 = new JButton("+10");
	JButton recule_10 = new JButton("-10"); //avancer ou reculer de 10 sec sur le rendu

	//elem menu
	JButton add_seq = new JButton("addSeq");
	JButton add_elem = new JButton("addElem");
	JButton add_elem_seq = new JButton("addElemSeq");

	JButton rendu = new JButton("rendu");
	
	Placheux(){
		JPanel panel_modif = new JPanel(new GridLayout(1, 6)); //animation, translate, rot, color, mp3, growth/shrink
		JPanel panel_board = new JPanel(new GridLayout(1, 2));
		JPanel panel_elem_part = new JPanel(new BorderLayout());
		JPanel panel_view_part = new JPanel(new BorderLayout());
		JPanel panel_elem_menu = new JPanel(new GridLayout(1,3));
		JPanel panel_view_menu = new JPanel(new GridLayout(1,4));

		JPanel panel_view = new JPanel();
		
		panel_modif.setPreferredSize(new Dimension(1, 200));
		
		String[] seq_colonnes = {"Liste des séquences"};
		Object[][] seq_data = {{"la sequence sans nom1"},
								{"la sequence sans nom2"},
								{"la sequence sans nom3"},
								{"la sequence sans nom4"},
								{"la sequence sans nom5"},
								{"la sequence sans nom6"}};

		String[] tab_colonnes = new String[3601];
		Object[][] tab_data = new Object[100][3601];

		for(int i = 0; i < 100; i++){
			for(int j = 0; j < 3601; j++){
				tab_data[i][j] = "";
			}	
		}

		int min = 0;
		tab_colonnes[0] = "Elements"; 
		for(int i = 0; i < 3600; i++){
			double sec = (((double)i)/2.0);
			if(i > 0 && (sec % 60 == 0)){
				min++;
			}
			tab_colonnes[i+1] =  min + "m" + sec % 60 + " s"; 
		}

		JTable tab = new JTable(new TableModel(tab_colonnes, tab_data));
		tab.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JTable seq = new JTable(new TableModel(seq_colonnes, seq_data));

		JPanel panel_menu_boutons = new JPanel(new GridLayout(1,3));

		JScrollPane list_seq = new JScrollPane(seq); 
		JScrollPane list_tab = new JScrollPane(tab, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 

        seq.setPreferredScrollableViewportSize(new Dimension(500, 70));
        seq.setFillsViewportHeight(true);
 
		panel_menu_boutons.add(add_seq);
		panel_menu_boutons.add(add_elem);
		panel_menu_boutons.add(add_elem_seq);

		panel_elem_menu.add(panel_menu_boutons);
		panel_elem_menu.add(list_seq);
		panel_elem_menu.add(rendu);
		
		panel_view_menu.add(recule_10);
		panel_view_menu.add(lecture_pause);
		panel_view_menu.add(stop);
		panel_view_menu.add(avance_10);
		
		setLayout(new BorderLayout());

		panel_elem_part.add("North", panel_elem_menu);
		panel_elem_part.add( "Center", list_tab);

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
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();		
		window.setVisible(true);
	}
}

