import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.geom.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import java.util.*;

public class Placheux extends JPanel{
	private static final long serialVersionUID = 1L;
	 LinkedList<Sequence> liste_seq; //content figures for sequences
	 LinkedList<Figure> liste_fig = new LinkedList<Figure>(); //content all of figures
	Controller controller;
	//private JComboBox<String> name_elem[] = new JComboBox[100];
	JComboBox<String> comboBox = new JComboBox<String>();
	Object[][] tab_data = new Object[1][3600];

	//view menu-+
	JButton lecture_pause = new JButton("lecture");
	JButton stop = new JButton("stop");
	JButton avance_10 = new JButton("+10");
	JButton recule_10 = new JButton("-10"); //avancer ou reculer de 10 sec sur le rendu

	//elem menu
	JButton add_seq = new JButton("Seq");
	JButton add_elem = new JButton("Elem");

	JButton rendu = new JButton("rendu");

	//JTable seq;
	JTable tab;

	JPanel view;
	JPanel panel_modif;

	private void initColumnSizes() {
		TableModel model = (TableModel)tab.getModel();
		TableColumn column = null;
		Component comp = null;
		int headerWidth = 0;
		int cellWidth = 0;
		//Object[] longValues = model.longValues;
		TableCellRenderer headerRenderer =
				tab.getTableHeader().getDefaultRenderer();

		for (int i = 0; i < 3600; i++) {
			column = tab.getColumnModel().getColumn(i);

			comp = headerRenderer.getTableCellRendererComponent(
					null, column.getHeaderValue(),
					false, false, 0, 0);
			headerWidth = comp.getPreferredSize().width;

			comp = tab.getDefaultRenderer(model.getColumnClass(i)).
					getTableCellRendererComponent(
							tab, null,
							false, false, 0, i);
			cellWidth = comp.getPreferredSize().width;
			column.setPreferredWidth(Math.max(headerWidth, cellWidth));
		}
	}

	Placheux(Controller c){
		controller = c;
		liste_seq = new LinkedList<Sequence>();

		
		JPanel panel_elem_part = new JPanel(new BorderLayout());
		JPanel panel_view_part = new JPanel(new BorderLayout());
		JPanel panel_elem_menu = new JPanel(new GridLayout(1,3));
		JPanel panel_view_menu = new JPanel(new GridLayout(1,4));
		view = new JPanel();
		view.setBorder(BorderFactory.createLineBorder(Color.red));
		view.setPreferredSize(new Dimension(1200, 900));
		final JScrollPane panel_view = new JScrollPane(view);
		
		/*
		String[] seq_colonnes = {"Liste des sÃ©quences"};
		Object[][] seq_data = {{"la sequence sans nom1"}};
		*/

		String[] tab_colonnes = new String[3601];

		for(int j = 0; j < 3601; j++){
			tab_data[0][j] = "";	
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
		

		tab = new JTable(new TableModel(tab_colonnes, tab_data));
		//initColumnSizes();
		//setUpElemColumn();
		tab.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		//seq = new JTable(new TableModel(seq_colonnes, seq_data));

		JPanel panel_menu_boutons = new JPanel(new GridLayout(1,3));

		tab.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	    tab.setColumnSelectionAllowed(false);
	    tab.setRowSelectionAllowed(false);
		//JScrollPane list_seq = new JScrollPane(seq); 
		JScrollPane list_tab = new JScrollPane(tab, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		list_tab.setPreferredSize(new Dimension(500, 54));

		panel_view_menu.setSize(panel_elem_menu.getSize());

		//seq.setPreferredScrollableViewportSize(new Dimension(300, 70));
		//seq.setFillsViewportHeight(true);

		panel_menu_boutons.add(add_seq);
		panel_menu_boutons.add(add_elem);

		panel_elem_menu.add(panel_menu_boutons);
		//panel_elem_menu.add(list_seq);
		panel_elem_menu.add(rendu);

		panel_view_menu.add(recule_10);
		panel_view_menu.add(lecture_pause);
		panel_view_menu.add(stop);
		panel_view_menu.add(avance_10);

		setLayout(new BorderLayout());

		panel_elem_part.add( "Center", list_tab);

		//panel_view_part.add("North", panel_view_menu);
		panel_view_part.add("Center", panel_view);

		JPanel panel_north = new JPanel();
		JPanel panel_south = new JPanel();

		panel_north.add(panel_view_part);
		panel_south.add(panel_elem_part);
		
		panel_south.setMaximumSize(new Dimension(200, 500));
		
		//this.add("North", panel_north);
		//this.add("South", panel_south);
	
		this.add("Center", panel_view);
		this.add("South", panel_south);
		System.out.println(panel_view);
			
		/*
		controller = c;
		panel_modif = new JPanel(new GridLayout(1, 6)); //animation, translate, rot, color, mp3, growth/shrink
		liste_seq = new LinkedList<Sequence>();
		JPanel panel_board = new JPanel(new GridLayout(1, 2));
		JPanel panel_elem_part = new JPanel(new BorderLayout());
		JPanel panel_view_part = new JPanel(new BorderLayout());
		JPanel panel_elem_menu = new JPanel(new GridLayout(1,3));
		JPanel panel_view_menu = new JPanel(new GridLayout(1,4));

		panel_view = new Vue();

		panel_modif.setPreferredSize(new Dimension(1, 200));

		//panel_view.add(new JLabel("LA VUE"));
		//panel_view.setBackground(Color.BLUE);

		String[] seq_colonnes = {"Liste des sÃ©quences"};
		Object[][] seq_data = {{"la sequence sans nom1"}};


		String[] tab_colonnes = new String[3601];

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
		
		tab = new JTable(new TableModel(tab_colonnes, tab_data));
		initColumnSizes();
		setUpElemColumn();
		tab.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		seq = new JTable(new TableModel(seq_colonnes, seq_data));

		JPanel panel_menu_boutons = new JPanel(new GridLayout(1,3));

		JScrollPane list_seq = new JScrollPane(seq); 
		JScrollPane list_tab = new JScrollPane(tab, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 

		panel_view_menu.setSize(panel_elem_menu.getSize());

		seq.setPreferredScrollableViewportSize(new Dimension(500, 70));
		seq.setFillsViewportHeight(true);

		panel_menu_boutons.add(add_seq);
		panel_menu_boutons.add(add_elem);

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

		//panel_board.add(panel_elem_part);
		//panel_board.add(panel_view_part);

		this.add("South", panel_view_part);
		this.add("North", panel_elem_part);
		
		//this.add(panel_board);
		
		//ctrl
		*/
		lecture_pause.addActionListener(controller);	
		stop.addActionListener(controller);
		avance_10.addActionListener(controller);
		recule_10.addActionListener(controller);
		add_seq.addActionListener(controller);
		add_elem.addActionListener(controller);
		rendu.addActionListener(controller);
		tab.addMouseListener(controller);
		panel_view.addMouseListener(controller);
		panel_view.addMouseMotionListener(controller);

	}
}