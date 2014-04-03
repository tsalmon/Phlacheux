
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

public class NewElem extends JDialog implements ActionListener {
	Ecran ecran;
	int id_fig;
	JButton valider = new JButton("Valider");
	JButton annuler = new JButton("Annuler");
	PanElem draw = new PanElem();
	private Polygon poly;
	private JComboBox<String> border_size = new JComboBox();

	private JButton border_color;
	private JButton[] btn_fig = new JButton[8];
	private JButton btn_fil;

	public NewElem(Ecran ecran) {
		//define
		setModal(true);
		setLocation(400, 200);
		setTitle("Nouvel Element");
		setSize(800, 600);
		
		JPanel board = new JPanel();
		JPanel menu_forms = new JPanel();
		Box border_box = new Box(BoxLayout.Y_AXIS);
		CtrlElem controller = new CtrlElem(draw);
		this.ecran = ecran;
		id_fig = 0;
		btn_fil = new JButton("Couleur forme");
		border_color = new JButton("couleur bordure");
		String[] choix_label = {"line", "circle", "sqrt", "cross", "iso", "equi", "cross", "star"};
		Color colors[] = {Color.BLACK, Color.BLUE, Color.GREEN, Color.RED, Color.GRAY, Color.YELLOW, Color.ORANGE};
		JPanel panneau ; //part page of fenetre
		JPanel fenetre = new JPanel(); //content page
		fenetre.setLayout(new BorderLayout());

		border_size.addItem("Taille bordure");
		for(int i = 0; i < 16; i++){
			border_size.addItem(i+"");
		}

		board.setLayout(new BorderLayout());
		menu_forms.setLayout(new GridLayout(2, 4));

		//Menu
		panneau = new JPanel();
		panneau.add(menu_forms);
		border_box.add(border_size);
		border_box.add(border_color);
		panneau.add(border_box);
		panneau.add(btn_fil);
		border_box.add(border_size);
		border_box.add(border_color);

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
		border_color.addActionListener(controller);
		draw.addMouseListener(controller);
		draw.addMouseMotionListener(controller);

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
		int xArrow[];
		int yArrow[];
		private Polygon poly;

		PanElem() { 
			xArrow = new int[7];
			yArrow = new int[7];
			poly = new Polygon(xArrow, yArrow, 7);
			setPreferredSize(new Dimension(600, 400));
		}

		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			super.paintComponent(g2); 
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g2.drawPolygon(poly);
		}

		public void init_a_b(int a, int b){
			this.a = a;
			this.b = b;
		}

		public void dessiner(int x, int y){
			
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
				}
			}
			if(e.getSource() == btn_fil){
				Color choix_couleur = JColorChooser.showDialog(null, "couleur de bordure",Color.WHITE);
				System.out.println(choix_couleur);
				btn_fil.setBackground(choix_couleur); //TODO: not working				
			}
			if(e.getSource() == border_color){
				Color choix_couleur = JColorChooser.showDialog(null, "couleur de bordure",Color.WHITE);
				System.out.println(choix_couleur);
				border_color.setBackground(choix_couleur); //TODO : not working
			}
		}
		public void mousePressed(MouseEvent e) {
			System.out.println("Pressed");
			pan.init_a_b(e.getX(), e.getY());
		}
		public void mouseReleased(MouseEvent e) {
			System.out.println("Released");
		}
		public void mouseDragged (MouseEvent e) {		
			System.out.println("Dragged");			
			pan.dessiner(e.getX(), e.getY());
			pan.repaint();
		}
	}
}