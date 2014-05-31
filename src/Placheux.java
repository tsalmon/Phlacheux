import static java.awt.event.InputEvent.CTRL_DOWN_MASK;

import java.awt.*;
import java.awt.Point;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import java.util.*;

import XML.FilmParser;
import model.movable.*;
import model.movable.circle.*;
import model.gestionnary.*;
import model.animation.*;

import org.jdom2.Document;


public class Placheux extends JPanel implements MouseListener, 
MouseMotionListener, 
ActionListener, 
TreeSelectionListener{

	private static final long serialVersionUID = 1L;

	StateGestionnary data = new StateGestionnary();
	JFrame frame;
	JMenuBar bar = new JMenuBar();
	JMenu fichier = new JMenu("Fichier");
	JMenu figure = new JMenu("Figure");
	JMenuItem nouveau_film = new JMenuItem("Nouveau film");
	JMenuItem ouvrir_film = new JMenuItem("Ouvrir un film");
	JMenuItem enregistrer_film = new JMenuItem("Enregistrer");
	JMenuItem enregistrer_sous_film = new JMenuItem("Enregistrer Sous");
	JMenuItem rendu_film = new JMenuItem("Faire un rendu");
	JMenuItem quitter_film = new JMenuItem("Quitter");

	BufferedImage[] img_icon = new BufferedImage[8]; 
	DragComponent[] label_img = new DragComponent[8];

	private JTree tree;
	DefaultMutableTreeNode top =
			new DefaultMutableTreeNode("Liste des figures");

	JPopupMenu menu = new JPopupMenu();
	boolean menu_launched = false;
	boolean translation_mode = false;
	boolean rotation_point_mode = false; 
	boolean	changement_echelle_mode = false;
	boolean create_figure = false;

	private Point arrowStart;
	private Point arrowEnd;
	private PointyThing pointyThing = new PointyThing();

	JTable tab;
	PanElem view;

	LinkedList<Figure> liste_fig = new LinkedList<Figure>();
	int origin_x, origin_y; 
	Shape fig_inc = new GeneralPath();
	int id_fig = -1; 

	Placheux(JFrame frame, String nom, int size, int width, int height){       
		this.frame = frame;
		this.setLayout(new BorderLayout());
		JPopupMenu.setDefaultLightWeightPopupEnabled(false);

		init_menu_bar();
		init_bouton_image();
		init_bouton_dessin();

		panel_west();
		view = new PanElem(data, tree, top);
		view.setTransferHandler(new ImgTransferHandler(view)); //DnD
		view.setPreferredSize(new Dimension(width, height));
		panel_center(width, height);
		panel_south(size);

		init_hotkey();
		init_listeners();

		frame.setJMenuBar(bar);
	}

	public Placheux(JFrame frame, File animeFile) {
		this.frame = frame;
		Document xmlDoc = FilmParser.readFile(animeFile);
		System.out.println(xmlDoc.toString());
	}


   	public void init_hotkey(){
		nouveau_film.setAccelerator(KeyStroke.getKeyStroke('N', CTRL_DOWN_MASK));
		ouvrir_film.setAccelerator(KeyStroke.getKeyStroke('O', CTRL_DOWN_MASK));
		enregistrer_film.setAccelerator(KeyStroke.getKeyStroke('S', CTRL_DOWN_MASK));
		enregistrer_sous_film.setAccelerator(KeyStroke.getKeyStroke('E', CTRL_DOWN_MASK));
		rendu_film.setAccelerator(KeyStroke.getKeyStroke('R', CTRL_DOWN_MASK));
		quitter_film.setAccelerator(KeyStroke.getKeyStroke('Q', CTRL_DOWN_MASK));		
	}

	public void init_listeners(){
		view.addMouseListener(this);
		view.addMouseMotionListener(this);
		tab.addMouseListener(this);

		nouveau_film.addActionListener(this);
		ouvrir_film.addActionListener(this);
		enregistrer_film.addActionListener(this);
		enregistrer_sous_film.addActionListener(this);
		rendu_film.addActionListener(this);
		quitter_film.addActionListener(this);
	}

	public void 
	init_timelineTexte(Object[] tab_colonnes, String[][] tab_data, int size){
		int min = 0;
		for(int i = 0; i < size; i++){
			double sec = (((double)i)/2.0);
			if(i > 0 && (sec % 60 == 0)){
				min++;
			}
			tab_colonnes[i] =  min + "m" + sec % 60 + " s"; 
		}
	}

	public JScrollPane init_timeline(Object[] tab_colonnes, String[][] tab_data){
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
		return panel_tab;
	}

	public void panel_south(int size){
		Object[] tab_colonnes = new Object[size];
		String[][] tab_data = new String[1][size];
		init_timelineTexte(tab_colonnes, tab_data, size);

		JPanel panel_south = new JPanel();
		panel_south.add(init_timeline(tab_colonnes, tab_data));

		this.add("South", panel_south);

	}

	public void init_drag(){

	}

	public void panel_center(int width, int height){
		final JScrollPane panel_center;
		JPanel panel_view = new JPanel();
		panel_view.add(view);
		if(width < 1100 && height < 700){
			panel_center = new JScrollPane(panel_view, 
					JScrollPane.VERTICAL_SCROLLBAR_NEVER,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);			
		} else if(width < 1100){
			panel_center = new JScrollPane(panel_view, 
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);				
		} else if(height < 700){
			panel_center = new JScrollPane(panel_view, 
					JScrollPane.VERTICAL_SCROLLBAR_NEVER, 
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		}else {
			panel_center = new JScrollPane(panel_view, JScrollPane.
					VERTICAL_SCROLLBAR_ALWAYS, 
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);			
		}

		this.add("Center", panel_center);

	}

	public JPanel panel_listefigure(Dimension minDim){
		JPanel fig_select = new JPanel();

		fig_select.setMinimumSize(minDim);
		fig_select.setLayout(new GridLayout(4, 2));

		for(int i = 0; i < label_img.length; i++){
			fig_select.add(label_img[i]);
		}
		return fig_select;
	}

	public void panel_west(){
		Dimension minimumSize = new Dimension(300, 250);
		tree = new JTree(top);
		tree.getSelectionModel().setSelectionMode
		(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);

		tree.addTreeSelectionListener(this);

		JScrollPane sp_fig = new JScrollPane(panel_listefigure(minimumSize));
		JScrollPane sp_tree = new JScrollPane(tree);
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane.setBottomComponent(sp_tree);
		splitPane.setTopComponent(sp_fig);

		sp_tree.setMinimumSize(minimumSize);
		splitPane.setDividerLocation(100); 
		splitPane.setPreferredSize(new Dimension(300, 300));

		//Add the split pane to this panel.
		this.add("West", splitPane);
	}

	ActionListener aListener = new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			choix_menu(event.getActionCommand());
		}
	};

	PopupMenuListener pListener = new PopupMenuListener(){
		public void popupMenuCanceled(PopupMenuEvent event) {}
		public void popupMenuWillBecomeInvisible(PopupMenuEvent event) {}
		public void popupMenuWillBecomeVisible(PopupMenuEvent event) {}
	};

	public void init_icones_drag(){
		for(int i = 0; i < img_icon.length; i++){
			label_img[i].setTransferHandler(new TransferHandler("icon"));
		}
		view.setTransferHandler(new TransferHandler("icon"));
	}	

	public void init_bouton_image(){
		try {
			img_icon[0] = ImageIO.read(new File("Resources/Vue/square.png"));
			img_icon[1] = ImageIO.read(new File("Resources/Vue/square.png")); //TODO: trouver une putain d'image
			img_icon[2] = 
					ImageIO.read(new File("Resources/Vue/circle.png"));
			img_icon[3] = ImageIO.read(new File("Resources/Vue/cross.png"));
			img_icon[4] = 
					ImageIO.read(new File("Resources/Vue/triangle_equi.png"));
			img_icon[5] = 
					ImageIO.read(new File("Resources/Vue/triangle_equi.png")); //TODO: trouver une putain d'image
			img_icon[6] = 
					ImageIO.read(new File("Resources/Vue/fleche.png"));
			img_icon[7] = ImageIO.read(new File("Resources/Vue/star.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void init_bouton_dessin(){
		for(int i = 0; i < img_icon.length; i++){
			label_img[i] = new DragComponent(img_icon[i], i);
			label_img[i].repaint();
		}
	}

	public void init_menu_bar(){
		fichier.add(nouveau_film);
		fichier.add(ouvrir_film);
		fichier.add(enregistrer_film);
		fichier.add(enregistrer_sous_film);
		fichier.addSeparator();
		fichier.add(rendu_film);
		fichier.addSeparator();
		fichier.add(quitter_film);
		bar.add(fichier);
		bar.add(figure);
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
			create_figure = true;
		} else if(choix.equals("Rectangle")){
			fig_inc = view.draw_rect();
			this.id_fig = 2;
			create_figure = true;
		} else if(choix.equals("Croix")){
			fig_inc = view.draw_cross();
			this.id_fig = 3;
			create_figure = true;
		} else if(choix.equals("Triangle Isocele")){
			fig_inc = view.draw_iso();
			this.id_fig = 4;
			create_figure = true;
		} else if(choix.equals("Triangle Equilateral")){
			fig_inc = view.draw_equi();
			this.id_fig = 5;
			create_figure = true;
		} else if(choix.equals("Fleche")){
			fig_inc = view.draw_arrow();
			this.id_fig = 6;
			create_figure = true;
		} else if(choix.equals("Etoile")){
			fig_inc = view.draw_star();
			this.id_fig = 7;
			create_figure = true;
		} else if(choix.equals("Do it yourself")) { //b spline

		} else if(choix.equals("Translation")){
			this.translation_mode = true;
		} else if(choix.equals("Rotation autour un point")){

		} else if(choix.equals("Rotation autour du centre")){

		} else if(choix.equals("Bezier")){

		} else if(choix.equals("Changement d'échelle")){

		} else if(choix.equals("Changement de bordure")){

		} else if(choix.equals("Changement de couleur")){

		}
	}

	public Point getCentroid(Shape s) {
		PathIterator pi = s.getPathIterator(null);
		LinkedList<Point> points = new LinkedList<Point>();
		int x = 0, x_n = 0, y = 0, y_n = 0;
		while(pi.isDone() == false){
			double[] coordinates = new double[6];
			if(pi.currentSegment(coordinates) > 0){
				points.add(new Point((int)coordinates[0],
						(int)coordinates[1]));
				x += coordinates[0];
				x_n++;
				y += coordinates[1];
				y_n++;
			}
			pi.next();
		}		
		return new Point(x/x_n, y/y_n);
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
		StateGestionnary data;
		JTree liste_figures;
		DefaultMutableTreeNode top;

		public PanElem(StateGestionnary data, JTree liste_figures, DefaultMutableTreeNode top) {
			this.top = top;
			this.data = data;
			this.liste_figures = liste_figures;
		}
		
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
				return ;
			}
		}

		public void draw_arrowTranslation(Graphics2D g2d){
			System.out.println("print arrow");
			double rotation = 0f;
			if (arrowEnd != null) {
				int x = arrowStart.x;
				int y = arrowStart.y;
				int deltaX = arrowEnd.x - x;
				int deltaY = arrowEnd.y - y;
				rotation = -Math.atan2(deltaX, deltaY);
				rotation = Math.toDegrees(rotation) + 180;
			}
			Rectangle bounds = pointyThing.getBounds();
			g2d.setStroke(new BasicStroke(3));
			g2d.setColor(Color.RED);
			g2d.draw(new Line2D.Float(arrowStart, arrowEnd));
			AffineTransform at = new AffineTransform();
			at.translate(arrowEnd.x - (bounds.width / 2), 
					arrowEnd.y - (bounds.height / 2));
			at.rotate(Math.toRadians(rotation), 
					bounds.width / 2, 
					bounds.height / 2);
			Shape shape = new Path2D.Float(pointyThing, at);
			g2d.fill(shape);
			g2d.draw(shape);			
		}

		public void paintComponent(Graphics g) {
			Iterator it = data.getMovables().entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pairs = (Map.Entry)it.next();
		        //System.out.println(pairs.getKey() + " <=> " + pairs.getValue());
		        
		    }
			
			/*
			super.paintComponent(g);

			if(fig_inc == null){
				doDrawing(g);				
			} else {

				Graphics2D g2d = (Graphics2D)g;

				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);
				g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
						RenderingHints.VALUE_RENDER_QUALITY);

				g2d.setColor(Color.BLACK); /**  TODO: fill color 

				//doDrawing(g);



				//for(Figure sh : liste_fig){
				//	g2d.setColor(Color.BLACK); /** TODO: fill color 
				//	g2d.fill(sh.getShape());
				//	g2d.setColor(Color.blue); /** TODO: border color & size 
				//	g2d.setStroke(new BasicStroke(3));
				//	g2d.draw(sh.getShape());
				//}

				g2d.setColor(Color.BLACK);  TODO: fill color 
				//this.doDrawing(g);
				g2d.fill(fig_inc);  
				g2d.setColor(Color.blue); /** TODO: border color & size 
				g2d.setStroke(new BasicStroke(3));
				//g2d.draw(fig_inc);

				/*if (arrowStart != null && arrowEnd != null) {
					this.draw_arrowTranslation(g2d);
				}

				g2d.dispose();
			}
			 */
		}

		public void init_a_b(int a, int b){
			this.a = a;
			this.b = b;
		}

		public void init_x_y(int x, int y){
			this.x = x;
			this.y = y;
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

				double x = a + 
						((Math.cos(an) * (radius + radius * (1 * (i%2)))));
				double y = b + 
						((Math.sin(an) * (radius + radius * (1 * (i%2)))));
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
			p.lineTo(
					(int)
					((Math.cos(angle) * ((a-x)) - Math.sin(angle) * ((b-y))) + x),
					(int)
					((Math.sin(angle) * ((a-x)) + Math.cos(angle) * ((b-y))) + y));
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



		public void createNodes() {
			this.top.removeAllChildren();
			Iterator it = data.getMovables().entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pairs = (Map.Entry)it.next();
		        this.top.add(new DefaultMutableTreeNode(pairs.getKey()));
		    }
		}
	}

	public class PointyThing extends Path2D.Float {
		private static final long serialVersionUID = 1L;
		public PointyThing() {
			moveTo(15, 0);
			lineTo(30, 15);
			lineTo(0, 15);
			lineTo(15, 0);
		}
	}

	public void setViewatTime(int t){
		System.out.println("setViewatTime : " + t);
	}

	public Figure getFigureSelected(int x, int y){
		for(Figure sh : liste_fig){
			PathIterator pi = sh.getShape().getPathIterator(null);
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
			for(int i = 0, j = points.size() - 1; 
					i < points.size(); 
					j = i++){
				if ((points.get(i).y > y) != (points.get(j).y > y) &&
						(x < (points.get(j).x - points.get(i).x) * (y - points.get(i).y) / (points.get(j).y-points.get(i).y) + points.get(i).x)) {
					System.out.println("ok");
					result = !result;
				}
			}
			if(result){
				//return new Figure();
			}
		}
		return null;
	}

	/**
	 * @param coord x of click
	 * @param coord y of click 
	 */
	public boolean voidClick(int x, int y){
		BufferedImage img = new BufferedImage(view.getWidth(), 
				view.getHeight(), 
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = img.createGraphics();
		view.paint(g);
		int[] colors = new int[3];
		img.getRaster().getPixel(x, y, colors);
		if(view.getBackground().getRed() == colors[0] && 
				view.getBackground().getGreen()  == colors[1] &&
				view.getBackground().getBlue() == colors[2]){
			return true;
		}
		return false;
	}

	public boolean clickG(MouseEvent e){
		return (SwingUtilities.isLeftMouseButton(e));
	}

	public boolean clickD(MouseEvent e){
		return (SwingUtilities.isRightMouseButton(e));
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == nouveau_film){
			System.out.println("nouveau");
		}
		if(e.getSource() ==  ouvrir_film){
			System.out.println("ouvrir");
		}
		if(e.getSource() == enregistrer_film){
			System.out.println("enregistrer");
		}
		if(e.getSource() == enregistrer_sous_film){
			System.out.println("engistrer sous");
		}
		if(e.getSource() == rendu_film){
			System.out.println("rendu");				
		}
		if(e.getSource() == quitter_film){
			System.out.println("quitter");
			System.exit(0);
		}
	}
	public void mousePressed(MouseEvent e) {
		System.out.print("mousePressed: ");
		if(e.getSource() == tab){
			System.out.println("tab");			
		}
		if(e.getSource() == view && clickG(e)){ 
			if(translation_mode){
				System.out.println("translation");
				arrowStart = e.getPoint();
			} else if(create_figure){
				System.out.println("view");
				view.init_a_b(e.getX(), e.getY());
			}
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
				/**/

				Figure f = getFigureSelected(e.getX(), e.getY());
				if(f == null){ // click on void screen
					init_menu_createFigure();
				} else {
					init_menu_createAnime();
				}
				menu.show(e.getComponent(), e.getX(), e.getY());
				menu_launched = true;
			} else {
				if(create_figure){
					view.x = e.getX();
					view.y = e.getY();
					//liste_fig.add(
					//new Figure(fig_inc, Color.blue, 5, Color.BLACK));
					id_fig = -1;
					create_figure = false;
				} else if(translation_mode){

				}
			}
		}
	}	

	public void mouseDragged (MouseEvent e) {
		//System.out.print("mouseDragged: ");
		if(e.getSource() == view && clickG(e)){
			if(create_figure){
				System.out.println("create figure");
				view.x = e.getX();
				view.y = e.getY();
				view.repaint();
			} else if(translation_mode){
				System.out.println("dragged: translation");
				arrowEnd = e.getPoint();
				repaint();
			}
		}		
	}

	public void componentMoved(ComponentEvent e) {}
	public void componentResized(ComponentEvent e) {}
	public void componentShown(ComponentEvent e) {}
	public void componentHidden(ComponentEvent e) {	}
	@Override
	public void mouseMoved(MouseEvent e) {}
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void valueChanged(TreeSelectionEvent e) {}
}