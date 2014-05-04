import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.event.*;

import java.util.*;

public class Placheux extends JPanel{
	private static final long serialVersionUID = 1L;
	JPopupMenu menu = new JPopupMenu();
	boolean menu_launched = false;
	JButton lecture_pause = new JButton("lecture");
	JButton stop = new JButton("stop");	
	JButton rendu = new JButton("rendu");

	JTable tab;

	PanElem view;

	/*
	 * Drawing section
	 */
	LinkedList<Shape> liste_fig = new LinkedList<Shape>();
	int origin_x, origin_y; //(origin_x, origin_y) is the point of beginning paint
	Shape fig_inc = new GeneralPath(); // the figure we do
	int id_fig = -1; // current id of figure to draw

	/*
	 * listener of menu
	 */
	ActionListener aListener = new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			choix_menu(event.getActionCommand());
		}
	};

	/*
	 * listener of menu
	 */
	PopupMenuListener pListener = new PopupMenuListener(){
		public void popupMenuCanceled(PopupMenuEvent event) {}
		public void popupMenuWillBecomeInvisible(PopupMenuEvent event) {}
		public void popupMenuWillBecomeVisible(PopupMenuEvent event) {}
	};

	Placheux(){
		Controller controller = new Controller();
		JPopupMenu.setDefaultLightWeightPopupEnabled(false);

		view = new PanElem();
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

	public void init_menu_createFigure()
	{
		System.out.println("MenuFigure");
		menu.addPopupMenuListener(pListener);

		JMenuItem rectItem = new JMenuItem("Rectangle");
		rectItem.addActionListener(aListener);
		menu.add(rectItem);	

		JMenuItem sqrtItem = new JMenuItem("Croix");
		sqrtItem.addActionListener(aListener);
		menu.add(sqrtItem);	

		JMenuItem isoItem = new JMenuItem("Triangle Isocele");
		isoItem.addActionListener(aListener);
		menu.add(isoItem);	

		JMenuItem equiItem = new JMenuItem("Triangle Equilateral");
		equiItem.addActionListener(aListener);
		menu.add(equiItem);

		JMenuItem arrowItem = new JMenuItem("Fleche");
		arrowItem.addActionListener(aListener);
		menu.add(arrowItem);	

		JMenuItem starItem = new JMenuItem("Etoile");
		starItem.addActionListener(aListener);
		menu.add(starItem);	
	}

	public void init_menu_createAnime()
	{
		System.out.println("MenuAnime");
		menu.addPopupMenuListener(pListener);

		JMenuItem transItem = new JMenuItem("Translation");
		transItem.addActionListener(aListener);
		menu.add(transItem);	

		JMenuItem rotItem = new JMenuItem("Rotation autour un point");
		rotItem.addActionListener(aListener);
		menu.add(rotItem);	

		JMenuItem rotcItem = new JMenuItem("Rotation autour du centre");
		rotcItem.addActionListener(aListener);
		menu.add(rotcItem);	

		JMenuItem bzrItem = new JMenuItem("Bezier");
		bzrItem.addActionListener(aListener);
		menu.add(bzrItem);

		JMenuItem tailItem = new JMenuItem("Changement d'échelle");
		tailItem.addActionListener(aListener);
		menu.add(tailItem);	

		JMenuItem borderItem = new JMenuItem("Changement de bordure");
		borderItem.addActionListener(aListener);
		menu.add(borderItem);	

		JMenuItem colorItem = new JMenuItem("Changement de couleur");
		colorItem.addActionListener(aListener);
		menu.add(colorItem);	
	}

	public void choix_menu(String choix){
		if(choix == "Cercle"){
			fig_inc = view.draw_circle();
			this.id_fig = 1;
		} else if(choix.equals("Rectangle")){
			fig_inc = view.draw_rect();
			this.id_fig = 2;
		} else if(choix.equals("Croix")){
			fig_inc = view.draw_cross();
			this.id_fig = 3;
		} else if(choix.equals("Triangle Isocele")){
			fig_inc = view.draw_iso();
			this.id_fig = 4;
		} else if(choix.equals("Triangle Equilateral")){
			fig_inc = view.draw_equi();
			this.id_fig = 5;
		} else if(choix.equals("Fleche")){
			fig_inc = view.draw_arrow();
			this.id_fig = 6;
		} else if(choix.equals("Etoile")){
			fig_inc = view.draw_star();
			this.id_fig = 7;
		} else if(choix.equals("Do it yourself")) {

		}
	}

	class PanElem extends JPanel{
		private static final long serialVersionUID = 1L;
		final Color couleurBord = Color.red;
		final Color couleurInterieur = Color.blue;
		final Color couleurFond = Color.black;
		Shape figure = null;
		LinkedList<Integer> diy = new LinkedList<Integer>();
		int a, b;
		int x, y;

		PanElem() { 
			setPreferredSize(new Dimension(600, 400));
		}

		/*It's might be usefull for drawing stars
		private double points[][] = { 
				{ 0, 85 }, { 75, 75 }, { 100, 10 }, { 125, 75 }, 
				{ 200, 85 }, { 150, 125 }, { 160, 190 }, { 100, 150 }, 
				{ 40, 190 }, { 50, 125 }, { 0, 85 } 
		};*/

		private void doDrawing(Graphics g) {
			if(id_fig == 0){
				GeneralPath gp = new GeneralPath();
				gp.moveTo(a, b);
				gp.lineTo(x, y);
				gp.closePath();
				fig_inc = gp;
			} else if (id_fig == 1){
				fig_inc = draw_circle();
			} else if(id_fig == 2){
				fig_inc = draw_rect();
			}  else if(id_fig == 3){				
				fig_inc = draw_cross();
			} else if(id_fig == 4){	
				fig_inc = draw_iso();
			} else if(id_fig == 5){
				fig_inc = draw_equi();
			} else if(id_fig == 6){
				fig_inc = draw_arrow();
			} else if(id_fig == 7){
				fig_inc = this.draw_star();
			} else if(id_fig == 8){
				GeneralPath aux = new GeneralPath();
				aux.moveTo(diy.get(0), diy.get(1));
				for(int i = 0; i < diy.size(); i+=2){
					aux.lineTo(diy.get(i), diy.get(i+1));
				}
				aux.closePath();
				fig_inc = aux;
			} else {
				System.out.println("start: quit");
				return ;
			}
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			if(fig_inc == null){
				doDrawing(g);
			} else {

				Graphics2D g2d = (Graphics2D)g;

				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);
				g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
						RenderingHints.VALUE_RENDER_QUALITY);

				//	g2d.setColor(Color.BLACK); /** TODO: fill color **/
				//	doDrawing(g);

				/*	for(Shape sh : liste_fig){
					g2d.fill(sh);
				}
				 */
				for(Shape sh : liste_fig){
					g2d.setColor(Color.BLACK); /** TODO: fill color **/
					g2d.fill(sh);
					g2d.setColor(Color.blue); /** TODO: border color & size **/
					g2d.setStroke(new BasicStroke(3));
					g2d.draw(sh);
				}

				g2d.setColor(Color.BLACK); /** TODO: fill color **/
				this.doDrawing(g);
				g2d.fill(fig_inc);  
				g2d.setColor(Color.blue); /** TODO: border color & size **/
				g2d.setStroke(new BasicStroke(3));
				g2d.draw(fig_inc);

				g2d.dispose();

			}
		}

		public void init_a_b(int a, int b){
			this.a = a;
			this.b = b;
		}

		public Shape draw_circle(){
			int u = (x < a) ? x : a ,
					v = (y < b) ? y : b;
			int k = (x < a) ? a-x : x-a ,
					l = (y < b) ? b-y : y-b;

			Ellipse2D p = new Ellipse2D.Double(u,v, k, l);
			return p;
		}

		public Shape draw_cross(){
			GeneralPath p = new GeneralPath();
			p.moveTo((3*x + a)/4, b);
			p.lineTo((3*a + x)/4, b);
			p.lineTo((3*a + x)/4, (3*b + y)/4);
			p.lineTo(a			, (3*b + y)/4);
			p.lineTo(a			, (3*y + b)/4);
			p.lineTo((3*a + x)/4, (3*y + b)/4);
			p.lineTo((3*a + x)/4, y);
			p.lineTo((3*x + a)/4, y); 
			p.lineTo((3*x + a)/4, (3*y + b)/4);
			p.lineTo(x			, (3*y + b)/4);
			p.lineTo(x			, (3*b + y)/4);
			p.lineTo((3*x + a)/4, (3*b + y)/4); 
			p.closePath();
			return p;
		}

		public Shape draw_rect(){
			GeneralPath p = new GeneralPath();
			p.moveTo(x, b);
			p.lineTo(a, b);
			p.lineTo(a, y);
			p.lineTo(x, y);
			p.closePath();
			return p;
		}

		public Shape draw_iso(){	
			GeneralPath p = new GeneralPath();

			p.moveTo((x+a)/2, b);
			p.lineTo(a, y);
			p.lineTo(x, y);
			p.lineTo((x+a)/2, b);
			return p;
		}

		public Shape draw_star(){
			GeneralPath p = new GeneralPath();
			double radius = Math.sqrt(Math.pow(a-x, 2) + Math.pow(b-y, 2));
			double angle = Math.PI * 2 / 10;

			for (int i = 0; i < 10; i++) {
				double an = angle * i;

				double x = a + ((Math.cos(an) * (radius + radius * (1 * (i%2)))));
				double y = b + ((Math.sin(an) * (radius + radius * (1 * (i%2)))));
				if (i == 0) {
					p.moveTo(x, y);
				} else {	
					p.lineTo(x, y);
				}
			}
			p.closePath();
			return p;
		}

		public Shape draw_equi(){
			GeneralPath p = new GeneralPath();
			double angle = Math.toRadians(60);

			p.moveTo(a, b);
			p.lineTo(x, y);
			p.lineTo((int)((Math.cos(angle) * ((a-x)) - Math.sin(angle) * ((b-y))) + x), (int)((Math.sin(angle) * ((a-x)) + Math.cos(angle) * ((b-y))) + y));
			p.lineTo(a, b);
			return p;
		}

		public Shape draw_arrow(){			
			GeneralPath p = new GeneralPath();
			p.moveTo(a		, (b+y)/2);
			p.lineTo((a+x)/2, y);
			p.lineTo((a+x)/2, (b+3*y)/4);
			p.lineTo(x		, (b + 3*y)/4);//4
			p.lineTo(x		, (y + 3*b)/4);
			p.lineTo((a+x)/2, (y + 3*b)/4);
			p.lineTo((a+x)/2, b);
			p.closePath();
			return p;
		}
	}

	class Controller 
	extends MouseInputAdapter 
	implements ActionListener, ComponentListener{

		public void setViewatTime(int t){
			System.out.println("setViewatTime : " + t);
			//for(Figure f : liste_fig){	}
		}

		public Figure getFigureSelected(int x, int y){
			for(Shape sh : liste_fig){
				PathIterator pi = sh.getPathIterator(null);
				LinkedList<Point> points = new LinkedList<Point>();
				while(pi.isDone() == false){
					double[] coordinates = new double[6];
					if(pi.currentSegment(coordinates) > 0){
						points.add(new Point((int)coordinates[0],
								(int)coordinates[1]));
					}
					pi.next();
				}
				boolean result = false;
				for(int i = 0, j = points.size() - 1; i < points.size(); j = i++){
					if ((points.get(i).y > y) != (points.get(j).y > y) &&
							(x < (points.get(j).x - points.get(i).x) * (y - points.get(i).y) / (points.get(j).y-points.get(i).y) + points.get(i).x)) {
						System.out.println("ok");
						result = !result;
					}
				}
				if(result){
					return new Figure();
				}
			}
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
			if(e.getSource() == view && clickG(e)){ 
				System.out.println("view");
				view.init_a_b(e.getX(), e.getY());
			}
		}

		public void mouseReleased(MouseEvent e) {
			System.out.print("mouseReleased: ");
			if(menu_launched) // If right after right click: exit menu
			{
				menu.removeAll();
				menu_launched = false;
				repaint();
				//return;
			}
			if(e.getSource() == tab){
				System.out.println("tab");			
				int tabx = tab.getSelectedColumn();
				System.out.println("Column : " + tab.getSelectedColumn());
				this.setViewatTime(tabx);
			}
			if(e.getSource() == view){
				System.out.println("view");
				if(clickD(e)){
					/*BufferedImage img = new BufferedImage(view.getWidth(), view.getHeight(), BufferedImage.TYPE_INT_RGB);
					Graphics2D g = img.createGraphics();
					view.paint(g);
					int[] colors = new int[3];
					img.getRaster().getPixel(e.getX(), e.getY(), colors);
					if(view.getBackground().getRed() == colors[0] && 
							view.getBackground().getGreen()  == colors[1] &&
								view.getBackground().getBlue() == colors[2]){
						System.out.println("Vide");
						return ;
					}*/

					Figure f = getFigureSelected(e.getX(), e.getY());
					if(f == null){ // click on void screen
						init_menu_createFigure();
					} else {
						init_menu_createAnime();
					}
					menu.show(e.getComponent(), e.getX(), e.getY());
					menu_launched = true;
				} else {
					view.x = e.getX();
					view.y = e.getY();
					liste_fig.add(fig_inc); 
				}
			}
		}	

		public void mouseDragged (MouseEvent e) {
			//System.out.print("mouseDragged: ");
			if(e.getSource() == view && clickG(e)){
				//TODO: 
				//if there is no elements selected, move the view: +/- e.getX, x/- e.getY
				//if there is elem select: make an arrow beetwen 
				///the barycenter of the figure and the point of the moose, to see the translation
				//System.out.println("view");
				view.x = e.getX();
				view.y = e.getY();
				view.repaint();
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