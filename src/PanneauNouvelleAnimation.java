import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import model.easing.Back;
import model.easing.Bounce;
import model.easing.Circ;
import model.easing.Cubic;
import model.easing.Elastic;
import model.easing.Expo;
import model.easing.Linear;
import model.easing.Quad;
import model.easing.Quart;
import model.easing.Quint;
import model.gestionnary.StateGestionnary;


public class PanneauNouvelleAnimation extends JDialog implements ActionListener{
	private static final long serialVersionUID = 1L;

	String[] easings = new String[]{"Linear", "Back", "Bounce", "Circ", "Cubic", "Elastic", "Expo", "Quad", "Quart", "Quint", "Sine"};
	String[] easingTypes = new String[]{"None", "Ease In", "Ease Out", "Ease In-Out"};

	JSpinner debutAnimation = null;
	JSpinner finAnimation = null;
	
	private JComboBox<String> easing_select = new JComboBox<String>();
	private JComboBox<String> anim_select = new JComboBox<String>();
	JPanel panel_animation = new JPanel();
	JFrame ecran;

	JButton ok_btn = new JButton("Ok");
	JButton cancel_btn = new JButton("Annuler");
	JButton btn_color = new JButton("Choisir une couleur");

	int figure_x, figure_y;
	StateGestionnary data;

	JTextField[] champs;
	
	public PanneauNouvelleAnimation(JFrame ecran, StateGestionnary data, int currentTime, int finFilm, int x_fig, int y_fig) {
		this.setModal(true);
		this.setLocation(400, 200);
		this.setTitle("Nouvelle animation");
		this.setSize(800, 600);		
		this.setLayout(new BorderLayout());
		this.data = data;
		this.debutAnimation = new JSpinner(new SpinnerNumberModel(currentTime,0,finFilm,1));
		this.finAnimation = new JSpinner(new SpinnerNumberModel(0,0,finFilm,1));
		this.ecran = ecran;
		BoxLayout boxLayout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS); // top to bottom
		this.setLayout(boxLayout);
		this.figure_x = x_fig;
		this.figure_y = y_fig;
		
		init_easingSelect();
		init_animeSelect();

		panel_temps();
		init_panel_anime(0);		
		this.add("Center", this.panel_animation);
		panel_btn();

		this.btn_color.addActionListener(this);
		this.ok_btn.addActionListener(this);
		this.cancel_btn.addActionListener(this);
		this.anim_select.addActionListener(this);

		pack();
		setVisible(true);
	}

	public void init_easingSelect(){
		for(int i = 0; i < easings.length; i++){
			for(int j = 0; j < easingTypes.length; j++){
				easing_select.addItem(easings[i] + " - " + easingTypes[j]);				
			}
		}		
		easing_select.setSelectedIndex(0);
	}

	public void init_animeSelect(){
		anim_select.addItem("Translation");
		anim_select.addItem("Rotation");
		anim_select.addItem("Echelle");
		anim_select.addItem("Couleur de fond");
		anim_select.addItem("Bordure");
		anim_select.setSelectedIndex(0);
	}

	public void init_textfield(int size){
		champs = new JTextField[size];
		for(int i = 0; i < champs.length; i++){
			champs[i] = new JTextField(5);
		}
	}
	
	public void panel_temps(){
		JPanel panel_time = new JPanel();
		panel_time.setLayout(new GridLayout(2, 2));
		
		panel_time.add(easing_select);
		
		panel_time.add(anim_select);
		
		JPanel panel_debutAnime = new JPanel();
		panel_debutAnime.add(new JLabel("debut animation"));
		panel_debutAnime.add(this.debutAnimation);
		panel_time.add(panel_debutAnime);
		
		JPanel panel_finAnime = new JPanel();
		panel_finAnime.add(new JLabel("fin animation"));
		panel_finAnime.add(this.finAnimation);
		panel_time.add(panel_finAnime);
		this.add("North", panel_time);
	}

	public void panel_translation(){
		init_textfield(4);
		this.panel_animation.setLayout(new GridLayout(2, 2));
		
		JPanel trans_x = new JPanel();
		trans_x.add(new JLabel("position initiale x"));
		trans_x.add(champs[0]);
		this.panel_animation.add(trans_x);

		JPanel trans_y = new JPanel();
		trans_y.add(new JLabel("position initiale y"));
		trans_y.add(champs[1]);
		this.panel_animation.add(trans_y);

		JPanel trans_a = new JPanel();
		trans_a.add(new JLabel("position finale x"));
		trans_a.add(champs[2]);
		this.panel_animation.add(trans_a);

		JPanel trans_b = new JPanel();
		trans_b.add(new JLabel("position finale y"));
		trans_b.add(champs[3]);
		this.panel_animation.add(trans_b);
	}

	public void panel_rotation(){
		init_textfield(3);
		this.panel_animation.setLayout(new BoxLayout(this.panel_animation, BoxLayout.Y_AXIS));
		
		JPanel rot_x = new JPanel();
		rot_x.add(new JLabel("position initiale x"));
		rot_x.add(champs[0]);
		this.panel_animation.add(rot_x);

		JPanel rot_y = new JPanel();
		rot_y.add(new JLabel("position initiale y"));
		rot_y.add(champs[1]);
		this.panel_animation.add(rot_y);

		JPanel rot_angle = new JPanel();
		rot_angle.add(new JLabel("position finale y"));
		rot_angle.add(champs[2]);
		this.panel_animation.add(rot_angle);

	}

	public void panel_echelle(){
		init_textfield(1);
		
		JPanel panel_size = new JPanel();
		panel_size.add(new JLabel("Taille de fin"));
		panel_size.add(champs[0]);

		this.panel_animation.add(panel_size);
	}

	public void panel_background(){
		
		JPanel panel_size = new JPanel();
		btn_color.setText("Couleur de fond");
		panel_size.add(btn_color);
		
		this.panel_animation.add(panel_size);		
	}

	public void panel_bordure(){
		init_textfield(1);
		this.panel_animation.setLayout(new BoxLayout(this.panel_animation, BoxLayout.Y_AXIS));
		btn_color.setText("Couleur de bordure");
		
		JPanel panel_size = new JPanel();
		panel_size.add(new JLabel("Taille de fin"));
		panel_size.add(champs[0]);
		this.panel_animation.add(panel_size);
		
		this.panel_animation.add(btn_color);
	}

	public void init_panel_anime(int id_anim){
		switch(id_anim){
		case 0: panel_translation(); break;
		case 1: panel_rotation();break;
		case 2: panel_echelle();break;
		case 3: panel_background();break;
		case 4: panel_bordure();break;
		}
	}

	public void panel_btn(){
		JPanel panel_bouton = new JPanel();

		panel_bouton.add(this.ok_btn);	
		panel_bouton.add(this.cancel_btn);
		this.add("South", panel_bouton);	
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.anim_select){
			this.panel_animation.removeAll();
			init_panel_anime(this.anim_select.getSelectedIndex());
			this.pack();
		}
		if(e.getSource() == this.ok_btn){
			if((int)this.debutAnimation.getValue() > 
			(int)this.finAnimation.getValue()){
				JOptionPane.showMessageDialog(ecran, 
						"La durée de l'animation est incorrecte");
				return;
			}
			this.setVisible(false);
		}
		if(e.getSource() == this.cancel_btn){
			this.setVisible(false);			
		}
		if(e.getSource() == this.btn_color){
			Color c = JColorChooser.showDialog(this, "Choisir une couleur", btn_color.getBackground());
			btn_color.setForeground(c);
		}
	}
}
