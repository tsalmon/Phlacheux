
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.LineBorder;
import javax.swing.event.MouseInputAdapter;

public class NewElem extends JFrame{
	static final int WIDTH  = 800;
	static final int HEIGHT = 600;
	PanElem draw = new PanElem();
    private Polygon poly;
    private JComboBox<String> border_size = new JComboBox();

    private JButton border_color;
    private JButton[] btn_fig = new JButton[8];
    private JButton btn_fil;
    
	NewElem(){
		btn_fil = new JButton("Couleur forme");
		
		border_size.addItem("Taille bordure");
		for(int i = 0; i < 16; i++){
			border_size.addItem(i+"");
		}
		
		String[] choix_label = {"line", "circle", "sqrt", "cross", "iso", "equi", "cross", "star"};
        Color colors[] = {Color.BLACK, Color.BLUE, Color.GREEN, Color.RED, Color.GRAY, Color.YELLOW, Color.ORANGE};

        border_color = new JButton("couleur bordure");

		CtrlElem controller = new CtrlElem(draw);
		draw.addMouseListener(controller);
		draw.addMouseMotionListener(controller);
		
		JPanel board = new JPanel();
		JPanel menu = new JPanel();
		JPanel menu_forms = new JPanel();
		Box border_box = new Box(BoxLayout.Y_AXIS);
		
		border_box.add(border_size);
		border_box.add(border_color);

		board.setLayout(new BorderLayout());
		menu_forms.setLayout(new GridLayout(2, 4));
		
		for(int i = 0; i < btn_fig.length; i++){
			btn_fig[i] = new JButton(choix_label[i]);
			btn_fig[i].addActionListener(controller);
			menu_forms.add(btn_fig[i]);
		}
		
		menu.add(menu_forms);
		menu.add(border_box);
		menu.add(btn_fil);
		board.add("North", menu);
		board.add("Center", draw);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
		getContentPane().add(board);
		pack();
		setVisible(true);
	}	

	class PanElem extends JPanel{
		static final int WIDTH  = 600;
		static final int HEIGHT = 400;

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
			setPreferredSize(new Dimension(WIDTH, HEIGHT));
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
