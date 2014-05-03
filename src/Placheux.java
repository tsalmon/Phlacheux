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

	JPopupMenu menu = new JPopupMenu();

	JButton lecture_pause = new JButton("lecture");
	JButton stop = new JButton("stop");	
	JButton rendu = new JButton("rendu");

	JTable tab;

	JPanel view;

	//listener of popupmenu
	ActionListener aListener = new ActionListener() {
		public void actionPerformed(ActionEvent event) {
		}
	};

	PopupMenuListener pListener = new PopupMenuListener(){
		public void popupMenuCanceled(PopupMenuEvent event) {}
		public void popupMenuWillBecomeInvisible(PopupMenuEvent event) {}
		public void popupMenuWillBecomeVisible(PopupMenuEvent event) {}
	};

	Placheux(){
		Controller controller = new Controller();

		view = new JPanel();
		view.setBorder(BorderFactory.createLineBorder(Color.red));
		view.setPreferredSize(new Dimension(1200, 900));
		final JScrollPane panel_view = new JScrollPane(view);

		Object[] tab_colonnes = new Object[3600];
		String[][] tab_data = new String[1][3600];

		int min = 0;
		for(int i = 0; i < 3600; i++){
			double sec = (((double)i)/2.0);
			if(i > 0 && (sec % 60 == 0)){
				min++;
			}
			tab_colonnes[i] =  min + "m" + sec % 60 + " s"; 
		}

		tab = new JTable(tab_data, tab_colonnes);
		tab.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tab.setColumnSelectionAllowed(false);
		tab.setRowSelectionAllowed(false);
		tab.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		final JScrollPane panel_tab = 
				new JScrollPane(tab, 
						JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
						JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel_tab.setPreferredSize(new Dimension(500, 54));

		setLayout(new BorderLayout());

		JPanel panel_south = new JPanel();
		panel_south.add(panel_tab); /**TODO: add buttons play/stop**/

		this.add("Center", panel_view);
		this.add("South", panel_south);

		view.addMouseListener(controller);
		view.addMouseMotionListener(controller);
		tab.addMouseListener(controller);
		panel_view.addMouseListener(controller);
		panel_view.addMouseMotionListener(controller);
	}

	public void init_popup_menu()
	{
		System.out.println("init_popup_menu");
		menu.addPopupMenuListener(pListener);

		JMenuItem editItem = new JMenuItem("Rectangle");
		editItem.addActionListener(aListener);
		menu.add(editItem);		
	}

	class Controller 
	extends MouseInputAdapter 
	implements ActionListener, ComponentListener{

		public void setViewatTime(int t){
			System.out.println("setViewatTime : " + t);
			//for(Figure f : liste_fig){	}
		}

		public Figure getFigureSelected(int x, int y){
			return null;
		}
		
		public boolean clickG(MouseEvent e){
			return (SwingUtilities.isLeftMouseButton(e));
		}

		public boolean clickD(MouseEvent e){
			return (SwingUtilities.isRightMouseButton(e));
		}
		
		public void actionPerformed(ActionEvent e) {
			System.out.print ("ActionPerformed: ");
		}
		public void mousePressed(MouseEvent e) {
			System.out.print("mousePressed: ");
			if(e.getSource() == tab){
				System.out.println("tab");			
			}
			if(e.getSource() == view){ 
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
				System.out.println("Column : " + tab.getSelectedColumn() + "  Row: " + tab.getSelectedRow());
				this.setViewatTime(tabx);
			}
			if(e.getSource() == view && clickD(e)){
				System.out.println("view");
				Figure f = getFigureSelected(e.getX(), e.getY());
				if(f == null){ // click on void screen
					System.out.println("vide");
				} else {
					System.out.println("une figure");
				}
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