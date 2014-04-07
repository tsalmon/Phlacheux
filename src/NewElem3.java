
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

//import java.util.LinkedList;

class NewElem3 extends JDialog implements ActionListener {
	//LinkedList<Figure> list_elem;
	Ecran ecran;
	int id_fig;
	JButton valider = new JButton("Valider");
	JButton annuler = new JButton("Annuler");
	PanElem draw = new PanElem();
	Color border_color;
	Color fil_color;
	//private Figure poly;
	private JComboBox<Integer> border_size = new JComboBox();

	private JButton btn_border_color;
	private JButton[] btn_fig = new JButton[8];
	private JButton btn_fil;

	public NewElem3() {
		//define
		setModal(true);
		setLocation(400, 200);
		setTitle("Nouvel Element");
		setSize(800, 600);

		//list_elem = new LinkedList<Figure>();
		JPanel board = new JPanel();
		JPanel menu_forms = new JPanel();
		Box border_box = new Box(BoxLayout.Y_AXIS);
		CtrlElem controller = new CtrlElem(draw);
		this.ecran = ecran;
		id_fig = 0;
		btn_fil = new JButton("Couleur forme");
		btn_border_color = new JButton("couleur bordure");
		String[] choix_label = {"line", "circle", "rect", "cross", "iso", "equi", "arrow", "star"};
		Color colors[] = {Color.BLACK, Color.BLUE, Color.GREEN, Color.RED, Color.GRAY, Color.YELLOW, Color.ORANGE};
		JPanel panneau ; //part page of fenetre
		JPanel fenetre = new JPanel(); //content page
		fenetre.setLayout(new BorderLayout());

		//border_size.addItem("Taille bordure");
		for(int i = 0; i < 16; i++){
			border_size.addItem(i);
		}
		border_size.setSelectedIndex(1);

		board.setLayout(new BorderLayout());
		menu_forms.setLayout(new GridLayout(2, 4));

		//Menu
		panneau = new JPanel();
		panneau.add(menu_forms);
		//border_box.add(border_size);
		//border_box.add(btn_border_color);
		panneau.add(border_box);
		panneau.add(btn_fil);
		border_box.add(border_size);
		border_box.add(btn_border_color);

		board.setLayout(new BorderLayout());
		menu_forms.setLayout(new GridLayout(2, 4));

		for(int i = 0; i < btn_fig.length; i++){
			btn_fig[i] = new JButton(choix_label[i]);
			btn_fig[i].addActionListener(controller);
			menu_forms.add(btn_fig[i]);
		}
		menu_forms.setLayout(new GridLayout(2, 4));
		fenetre.add("North", panneau);

		//Dessin
		panneau = new JPanel();
		panneau.add(draw);
		fenetre.add("Center", panneau);

		//Validation
		panneau = new JPanel();
		panneau.add(valider);
		panneau.add(annuler);
		fenetre.add("South", panneau);

		add(fenetre) ;

		valider.addActionListener(this);
		annuler.addActionListener(this);
		btn_fil.addActionListener(controller);
		btn_border_color.addActionListener(controller);
		draw.addMouseListener(controller);
		draw.addMouseMotionListener(controller);
		//border_size.addMouseListener(controller);
		border_size.addActionListener(controller);

		pack();
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == valider) {
			//dispose();
			System.exit(0);
		}
		else if (e.getSource() == annuler) { 
			//dispose();
			System.exit(0);
		}
	}

	class PanElem extends JPanel{
		final Color couleurBord = Color.red;
		final Color couleurInterieur = Color.blue;
		final Color couleurFond = Color.black;
		Shape form;
		int a, b;
		int x, y;
		//int sizePol = 1;
		//int xPol[] = new int[1];
		//int yPol[] = new int[1];
		//private Polygon poly;

		PanElem() { 
			//poly = new Figure(xPol, yPol, sizePol);
			setPreferredSize(new Dimension(600, 400));
			form = new GeneralPath();
		}

		
		/*It's might be usefull for drawing stars
		private double points[][] = { 
				{ 0, 85 }, { 75, 75 }, { 100, 10 }, { 125, 75 }, 
				{ 200, 85 }, { 150, 125 }, { 160, 190 }, { 100, 150 }, 
				{ 40, 190 }, { 50, 125 }, { 0, 85 } 
		};*/

		private void doDrawing(Graphics g) {

			Graphics2D g2d = (Graphics2D)g;

			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);

			g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
					RenderingHints.VALUE_RENDER_QUALITY);

			g2d.setColor(fil_color);
	        
			GeneralPath p;
			if(id_fig == 0){
				p = new GeneralPath();
				p.moveTo(a, b);
				p.lineTo(x, y);
				p.closePath();
			} else if (id_fig == 1){//circle
				p = draw_circle();
			} else if(id_fig == 2){
				p = draw_rect();
			}  else if(id_fig == 3){				
				p = draw_cross();
			} else if(id_fig == 4){	
				p = draw_iso();
			} else if(id_fig == 5){
				p = draw_equi();
			} else if(id_fig == 6){
				p = draw_arrow();
			} else if(id_fig == 7){
				p = this.draw_star();
			} else {
				return; //no fig choice
			}
			g2d.fill(p);  

			g2d.setColor(border_color);
			g2d.setStroke(new BasicStroke(border_size.getSelectedIndex()));

	        g2d.dispose();
		}

		public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        if(form != null){
	        	doDrawing(g);
	        }
			/*
			//if(poly == null){
			//	return ;
			//}
			Graphics2D g2 = (Graphics2D) g;
			super.paintComponent(g2); 
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			 *
			for(int i = 0; i < list_elem.size(); i++){
				g2.setColor(list_elem.get(i).border_color);
				g2.setPaint(list_elem.get(i).fil_color);
				g2.setStroke(new BasicStroke(list_elem.get(i).border_width));
				if(list_elem.get(i).id_fig == 0){
					g2.drawLine(list_elem.get(i).xPol[0], list_elem.get(i).yPol[0], list_elem.get(i).xPol[1], list_elem.get(i).yPol[1]);
				} else if(list_elem.get(i).id_fig == 1){
					g2.drawOval(list_elem.get(i).xPol[0], list_elem.get(i).yPol[0], list_elem.get(i).xPol[1], list_elem.get(i).yPol[1]);
				} else {
					g2.drawPolygon(list_elem.get(i));
				}				
			}
			 *

			g2.setColor(border_color);
			g2.setPaint(fil_color);
			g2.setStroke(new BasicStroke(border_size.getSelectedIndex()));

			//figure inc
			if(id_fig == 0){
				//g2.drawLine(xPol[0], yPol[0], xPol[1], yPol[1]);
			} else if(id_fig == 1){
				//g2.drawOval(xPol[0], yPol[0], xPol[1], yPol[1]);
			} else {
				//g2.drawPolygon(poly);
			}
			 */
			/*
			if(form == null){
				System.out.println("retour");
				return ;
			}
			Rectangle r = form.getBounds();
			// create a transparent image with 1 px padding.
			BufferedImage tmp = new BufferedImage(
					r.width+2,r.height+2,BufferedImage.TYPE_INT_ARGB);
			// get the graphics object
			Graphics2D g2 = tmp.createGraphics();
			// set some nice rendering hints
			g2.setRenderingHint(
					RenderingHints.KEY_ANTIALIASING, 
					RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setRenderingHint(
					RenderingHints.KEY_COLOR_RENDERING, 
					RenderingHints.VALUE_COLOR_RENDER_QUALITY);
			// create a transform to center the shape in the image
			AffineTransform centerTransform = AffineTransform.
					getTranslateInstance(-r.x+1, -r.y+1);
			// set the transform to the graphics object
			g2.setTransform(centerTransform);
			// set the shape as the clip
			g2.setClip(form);
			g2.setColor(Color.RED);
			g2.setStroke(new BasicStroke(1f));
			g2.draw(form);
			// dispose of any graphics object we explicitly create
			//g2.dispose();
			//tmp.getGraphics();
			g.drawImage(tmp, 0, 0, this);
			*/
		}

		public void init_a_b(int a, int b){
			this.a = a;
			this.b = b;
		}

		public void dessiner(int x, int y){
			if(id_fig == 0 || id_fig == 1){ //line, rect, circle
				/*
				sizePol = 2;
				xPol = new int[sizePol];
				yPol = new int[sizePol];
				xPol[0] = a;
				yPol[0] = b;
				xPol[1] = x;
				yPol[1] = y;
				 */
			} else if(id_fig == 2){
				/*
				sizePol = 4;
				xPol = new int[sizePol];
				yPol = new int[sizePol];
				 */
				//form = draw_rect(x, y);
			} else if(id_fig == 3){
				/*
				sizePol = 12;
				xPol = new int[sizePol];
				yPol = new int[sizePol];
				 */
				//draw_cross(x, y);
			} else if(id_fig == 4){
				/*
				sizePol = 3;
				xPol = new int[sizePol];
				yPol = new int[sizePol];
				 */
				//draw_iso(x, y);
			} else if(id_fig == 5){
				/*
				sizePol = 3;
				xPol = new int[sizePol];
				yPol = new int[sizePol];
				 */
				//draw_equi(x, y);
			} else if(id_fig == 6){
				/*
				sizePol = 7;
				xPol = new int[sizePol];
				yPol = new int[sizePol];
				 */
				//draw_arrow(x, y);
			} else if(id_fig == 7){
				/*
				sizePol = 10;
				xPol = new int[sizePol];
				yPol = new int[sizePol];
				 */
				//draw_star();
			}
			//poly = new Figure(id_fig, xPol, yPol, sizePol);		
		}

		public GeneralPath draw_circle(){
			double radius = Math.sqrt(Math.pow(a-x, 2) + Math.pow(b-y, 2));
			int points = 100;	
			double angle = Math.PI * 2 / points;
			
	        GeneralPath p = new GeneralPath();

	        for (int ii = 0; ii < points; ii++) {
	            double an = angle * ii;

	            double x = a+((Math.cos(an) * radius) + radius);
	            double y = b+((Math.sin(an) * radius) + radius);
	            if (ii == 0) {
	                p.moveTo(x, y);
	            } else {	
	                p.lineTo(x, y);
	            }
	        }
	        return p;
		}
		
		public GeneralPath draw_cross(){
			GeneralPath p = new GeneralPath();
			p.moveTo((3*x + a)/4, b);//0
			p.lineTo((3*a + x)/4, b);//1
			p.lineTo((3*a + x)/4, (3*b + y)/4);//2
			p.lineTo(a			, (3*b + y)/4);//3
			p.lineTo(a			, (3*y + b)/4);//4
			p.lineTo((3*a + x)/4, (3*y + b)/4);//5
			p.lineTo((3*a + x)/4, y);//6
			p.lineTo((3*x + a)/4, y); //7
			p.lineTo((3*x + a)/4, (3*y + b)/4); //8
			p.lineTo(x			, (3*y + b)/4); //9
			p.lineTo(x			, (3*b + y)/4); //10
			p.lineTo((3*x + a)/4, (3*b + y)/4); //11
			p.closePath();
			return p;
			/*
			xPol[0] = (3*x + a)/4;
			xPol[1] = (3*a + x)/4; 
			xPol[2] = (3*a + x)/4;
			xPol[3] = a;
			xPol[4] = a;
			xPol[5] = (3*a + x)/4;
			xPol[6] = (3*a + x)/4;
			xPol[7] = (3*x + a)/4;
			xPol[8] = (3*x + a)/4;
			xPol[9] = x;
			xPol[10] = x;
			xPol[11] = (3*x + a)/4;
			
			yPol[0] = b;
			yPol[1] = b; 
			yPol[2] = (3*b + y)/4; 
			yPol[3] = (3*b + y)/4;
			yPol[4] = (3*y + b)/4;
			yPol[5] = (3*y + b)/4;
			yPol[6] = y;
			yPol[7] = y;
			yPol[8] = (3*y + b)/4;
			yPol[9] = (3*y + b)/4;	
			yPol[10] = (3*b + y)/4;
			yPol[11] = (3*b + y)/4;
			 */
		}

		public GeneralPath draw_rect(){
			GeneralPath p = new GeneralPath();
			p.moveTo(x, b);
			p.lineTo(a, b);
			p.lineTo(a, y);
			p.lineTo(x, y);
			p.closePath();
			return p;
		}

		public GeneralPath draw_iso(){	
			GeneralPath p = new GeneralPath();
			
			p.moveTo((x+a)/2, b);
			p.lineTo(a, y);
			p.lineTo(x, y);
			return p;
		}

		public GeneralPath draw_star(){
			GeneralPath p = new GeneralPath();
			double radius = Math.sqrt(Math.pow(a-x, 2) + Math.pow(b-y, 2));
			double angle = Math.PI * 2 / 10;
			
			for (int ii = 0; ii < 10; ii++) {
	            double an = angle * ii;

	            double x = a + ((Math.cos(an) * (radius + radius * (1 * (ii%2)))));
	            double y = b + ((Math.sin(an) * (radius + radius * (1 * (ii%2)))));
	            if (ii == 0) {
	                p.moveTo(x, y);
	            } else {	
	                p.lineTo(x, y);
	            }
	        }
			p.closePath();
			return p;
		}

		public GeneralPath draw_equi(){
			GeneralPath p = new GeneralPath();
			double angle = Math.toRadians(60);
			
			p.moveTo(a, b);
			p.lineTo(x, y);
			p.lineTo((int)((Math.cos(angle) * ((a-x)) - Math.sin(angle) * ((b-y))) + x), (int)((Math.sin(angle) * ((a-x)) + Math.cos(angle) * ((b-y))) + y));
			return p;
		}

		public GeneralPath draw_arrow(){			
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

	class CtrlElem extends MouseInputAdapter implements ActionListener {
		static final int ENTREE = 0, REMPL = 1;
		PanElem pan;
		int x0, y0, xc, yc;    
		int mode;

		CtrlElem(PanElem pan) {
			this.pan = pan;
		}

		public void actionPerformed(ActionEvent e) {
			for(int i = 0; i < btn_fig.length; i++){
				if(e.getSource() == btn_fig[i]){
					System.out.println(btn_fig[i].getLabel());
					id_fig = i;
				}
			}
			if(e.getSource() == btn_fil){
				fil_color = JColorChooser.showDialog(null, "couleur de bordure",Color.WHITE);
				System.out.println(fil_color);
				btn_fil.setForeground(fil_color);	
			}
			if(e.getSource() == btn_border_color){
				border_color = JColorChooser.showDialog(null, "couleur de bordure",Color.WHITE);
				System.out.println(border_color);
				btn_border_color.setForeground(border_color);
			}
			if(e.getSource() == border_size){
				System.out.println(border_size.getSelectedIndex());
			}
		}
		public void mousePressed(MouseEvent e) {
			System.out.println("Pressed");
			pan.init_a_b(e.getX(), e.getY());
		}
		public void mouseReleased(MouseEvent e) {
			System.out.println("Released");
			if(e.getSource() == draw){
				pan.x = e.getX();
				pan.y = e.getY();
				//list_elem.add(new Figure(id_fig, poly.xpoints, poly.ypoints, poly.npoints));
				//list_elem.getLast().border_width = border_size.getSelectedIndex();
				//list_elem.getLast().border_color = border_color;
				//list_elem.getLast().fil_color = fil_color;
				//System.out.println(list_elem);
			}
		}
		public void mouseDragged (MouseEvent e) {		
			System.out.println("Dragged");			
			//pan.dessiner(e.getX(), e.getY());
			pan.x = e.getX();
			pan.y = e.getY();
			pan.repaint();
		}
	}

	public static void main(String [] args){
		new NewElem3();
	}
}