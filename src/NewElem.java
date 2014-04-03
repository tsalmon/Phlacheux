
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxEditor;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.LineBorder;
import javax.swing.event.MouseInputAdapter;

import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.Box;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

public class NewElem extends JDialog implements ActionListener {
	LinkedList<Figure> list_elem;
	Ecran ecran;
	int id_fig;
	JButton valider = new JButton("Valider");
	JButton annuler = new JButton("Annuler");
	PanElem draw = new PanElem();
	Color border_color;
	Color fil_color;
	private Figure poly;
	private JComboBox<Integer> border_size = new JComboBox();

	private JButton btn_border_color;
	private JButton[] btn_fig = new JButton[8];
	private JButton btn_fil;

	public NewElem(Ecran ecran) {
		//define
		setModal(true);
		setLocation(400, 200);
		setTitle("Nouvel Element");
		setSize(800, 600);

		list_elem = new LinkedList<Figure>();
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

	public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource();
		if (source == valider) {
			//jeu.nom = champNom.getText();
			//jeu.aide = choixAide.isSelected();
			
			dispose();
		}
		else if (source == annuler) { 
			dispose();
		}
	}
	
	class PanElem extends JPanel{
		final Color couleurBord = Color.red;
		final Color couleurInterieur = Color.blue;
		final Color couleurFond = Color.black;

		int a, b;
		int sizePol = 1;
		int xPol[] = new int[1];
		int yPol[] = new int[1];
		//private Polygon poly;

		PanElem() { 
			//poly = new Figure(xPol, yPol, sizePol);
			setPreferredSize(new Dimension(600, 400));
		}

		public void paintComponent(Graphics g) {
			if(poly == null){
				return ;
			}
			Graphics2D g2 = (Graphics2D) g;
			super.paintComponent(g2); 
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			for(int i = 0; i < list_elem.size(); i++){
				g2.setColor(list_elem.get(i).border_color);
				g2.setBackground(list_elem.get(i).fil_color);
				g2.setStroke(new BasicStroke(list_elem.get(i).border_width));
				if(list_elem.get(i).id_fig == 0){
					g2.drawLine(list_elem.get(i).xPol[0], list_elem.get(i).yPol[0], list_elem.get(i).xPol[1], list_elem.get(i).yPol[1]);
				} else if(list_elem.get(i).id_fig == 1){
					g2.drawOval(list_elem.get(i).xPol[0], list_elem.get(i).yPol[0], list_elem.get(i).xPol[1], list_elem.get(i).yPol[1]);
				} else {
					g2.drawPolygon(list_elem.get(i));
				}				
			}
			g2.setColor(border_color);
			g2.setBackground(fil_color);
			g2.setStroke(new BasicStroke(border_size.getSelectedIndex()));
			
			//figure inc
			if(id_fig == 0){
				g2.drawLine(xPol[0], yPol[0], xPol[1], yPol[1]);
			} else if(id_fig == 1){
				g2.drawOval(xPol[0], yPol[0], xPol[1], yPol[1]);
			} else {
				g2.drawPolygon(poly);
			}
		}

		public void init_a_b(int a, int b){
			this.a = a;
			this.b = b;
		}

		public void dessiner(int x, int y){
			if(id_fig == 0 || id_fig == 1){ //line, rect, circle
				sizePol = 2;
				xPol = new int[sizePol];
				yPol = new int[sizePol];
				xPol[0] = a;
				yPol[0] = b;
				xPol[1] = x;
				yPol[1] = y;
		        poly = new Figure(id_fig, xPol, yPol, sizePol);		
		        return ;
			} else if(id_fig == 2){
				sizePol = 4;
				xPol = new int[sizePol];
				yPol = new int[sizePol];
				draw_rect(x, y);
				poly = new Figure(id_fig, xPol, yPol, sizePol);				
			} else if(id_fig == 3){
				sizePol = 12;
				xPol = new int[sizePol];
				yPol = new int[sizePol];
				draw_cross(x, y);
				poly = new Figure(id_fig, xPol, yPol, sizePol);
			} else if(id_fig == 4){
				sizePol = 3;
				xPol = new int[sizePol];
				yPol = new int[sizePol];
				draw_iso(x, y);
				poly = new Figure(id_fig, xPol, yPol, sizePol);				
			} else if(id_fig == 5){
				sizePol = 3;
				xPol = new int[sizePol];
				yPol = new int[sizePol];
				draw_equi(x, y);
				poly = new Figure(id_fig, xPol, yPol, sizePol);
			} else if(id_fig == 6){
				sizePol = 7;
				xPol = new int[sizePol];
				yPol = new int[sizePol];
				draw_arrow(x, y);
				poly = new Figure(id_fig, xPol, yPol, sizePol);
			} else if(id_fig == 7){
				
			}
		}

		public void draw_cross(int x, int y){
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
		}
		
		public void draw_rect(int x, int y){
			xPol[0] = x;
			xPol[1] = a;
			xPol[2] = a;
			xPol[3] = x;

			yPol[0] = b;
			yPol[1] = b;
			yPol[2] = y;
			yPol[3] = y;
		}
				
		public void draw_iso(int x, int y){
			xPol[0] = (x + a)/2;
			xPol[1] = a;
			xPol[2] = x;

			yPol[0] = b;
			yPol[1] = y;
			yPol[2] = y;

		}
		
		public void draw_equi(int x, int y){
			double angle = Math.toRadians(60);

			xPol[0] = a;
			xPol[1] = x;
			xPol[2] = (int)((Math.cos(angle) * ((a-x)) - Math.sin(angle) * ((b-y))) + x);

			yPol[0] = b;
			yPol[1] = y;
			yPol[2] = (int)((Math.sin(angle) * ((a-x)) + Math.cos(angle) * ((b-y))) + y);
		}
		
		public void draw_arrow(int x, int y){			
			xPol[0] = (a+x)/2;
			xPol[1] = a; 
			xPol[2] = (a+x)/2;
			xPol[3] = (a+x)/2;
			xPol[4] = x;
			xPol[5] = x;
			xPol[6] =(a+x)/2;
	
			yPol[0] = b;
			yPol[1] = (b+y)/2; 
			yPol[2] = y;
			yPol[3] = (b + 3*y)/4;
			yPol[4] = (b + 3*y)/4;
			yPol[5] = (y + 3*b)/4;
			yPol[6] = (y + 3*b)/4;	
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
				list_elem.add(new Figure(id_fig, poly.xpoints, poly.ypoints, poly.npoints));
				list_elem.getLast().border_width = border_size.getSelectedIndex();
				list_elem.getLast().border_color = border_color;
				list_elem.getLast().fil_color = fil_color;
				System.out.println(list_elem);
			}
		}
		public void mouseDragged (MouseEvent e) {		
			System.out.println("Dragged");			
			pan.dessiner(e.getX(), e.getY());
			pan.repaint();
		}
	}
}