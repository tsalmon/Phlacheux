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
	String[][] tab_data = new String[1][3600];

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

	Placheux(){
		controller = new Controller();
		liste_seq = new LinkedList<Sequence>();

		JPanel panel_elem_part = new JPanel(new BorderLayout());
		JPanel panel_view_part = new JPanel(new BorderLayout());
		JPanel panel_elem_menu = new JPanel(new GridLayout(1,3));
		JPanel panel_view_menu = new JPanel(new GridLayout(1,4));
		view = new JPanel();
		view.setBorder(BorderFactory.createLineBorder(Color.red));
		view.setPreferredSize(new Dimension(1200, 900));
		final JScrollPane panel_view = new JScrollPane(view);

		Object[] tab_colonnes = new Object[3600];

		for(int j = 0; j < 360; j++){
			tab_data[0][j] = "";	
		}

		int min = 0;
		for(int i = 0; i < 3600; i++){
			double sec = (((double)i)/2.0);
			if(i > 0 && (sec % 60 == 0)){
				min++;
			}
			tab_colonnes[i] =  new String(min + "m" + sec % 60 + " s"); 
		}

		tab = new JTable(tab_data, tab_colonnes);
		tab.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tab.setColumnSelectionAllowed(false);
		tab.setRowSelectionAllowed(false);
		tab.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane list_tab = new JScrollPane(tab, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		list_tab.setPreferredSize(new Dimension(500, 54));

		JPanel panel_menu_boutons = new JPanel(new GridLayout(1,3));		

		panel_view_menu.setSize(panel_elem_menu.getSize());

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

	class Controller extends MouseInputAdapter implements ActionListener, ComponentListener{
		Placheux ecran;
		NewElem elem;

		Controller() {
		}

		void setEcran(Placheux e){
			ecran = e;
		}

		void setElem(NewElem e){
			elem = e;
		}


		public void setViewatTime(int t){
			for(Figure f : liste_fig){

			}
		}

		public void actionPerformed(ActionEvent e) {
			System.out.print ("ActionPerformed: ");
			if(lecture_pause == e.getSource()){
				System.out.println("lecture/pause");
			} 
			if(stop == e.getSource()){
				System.out.println("stop"); 
			}
			if(avance_10 == e.getSource()){
				System.out.println("+10");			
			}
			if(recule_10 == e.getSource()){
				System.out.println("-10");
			}
			if(add_seq == e.getSource()){
				System.out.println("add seq");			
			}
			if(add_elem == e.getSource()){
				System.out.println("add elem");			
				elem = new NewElem(ecran);
				System.out.println(liste_fig);
			}
			if(rendu == e.getSource()){
				System.out.println("rendu");			
			}
		}
		public void mousePressed(MouseEvent e) {
			System.out.print("mousePressed: ");
			if(e.getSource() == tab){
				System.out.println("tab");			
			}
			/*
			if(e.getSource() == seq){
				System.out.println("seq");			
			}
			 */
			if(e.getSource() == panel_modif){
				System.out.println("modif");			
			}
			if(e.getSource() == view){ 
				//getElem select on the screen
				System.out.println("view");
			}
		}

		public void mouseReleased(MouseEvent e) {
			System.out.print("mouseReleased: ");
			System.out.println(getSize());
			if(e.getSource() == tab){
				System.out.println("tab");			
				int tabx = tab.getSelectedColumn();
				int taby = tab.getSelectedRow();
				System.out.println("Column : " + tab.getSelectedColumn() + "  Row: " + tab.getSelectedRow() + " object selected(" + ((String) tab_data[taby][0]) + ")");
				this.setViewatTime(tabx);
			}
			/*if(e.getSource() == seq){
				System.out.println("seq");
				System.out.println(seq.getSelectedColumn() + "  " + seq.getSelectedRow());
			}*/
			if(e.getSource() == panel_modif){
				System.out.println("modif");			
			}
			if(e.getSource() == view){
				System.out.println("view");
			}
		}
		public void mouseDragged (MouseEvent e) {
			System.out.print("mouseDragged: ");
			if(e.getSource() == view){
				//TODO: 
				//if there is no elements selected, move the view: +/- e.getX, x/- e.getY
				//if there is elem select: make an arrow beetwen 
				///the barycenter of the figure and the point of the moose, to see the translation
				System.out.println("view");
			}		
		}

		public void componentMoved(ComponentEvent e) {

		}

		public void componentResized(ComponentEvent e) {
			System.out.println("resize");
		}

		public void componentShown(ComponentEvent e) {
			// TODO Auto-generated method stub

		}

		public void componentHidden(ComponentEvent e) {
			// TODO Auto-generated method stub

		}

	}
}