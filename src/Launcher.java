import model.animation.*;
import model.easing.Bounce;
import model.easing.Quad;
import model.easing.Sine;
import model.movable.*;
import model.movable.PointPlacheux;
import model.movable.circle.Circle;
import model.movable.line.CubicCurve;
import model.movable.line.QuadraticCurve;
import model.movable.line.Segment;
import model.movable.polygon.*;
import model.movable.polygon.Rectangle;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class Launcher extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JButton ok = new JButton("ok");
	private JButton cancel = new JButton("cancel");
	private JTextField name = new JTextField(20);
	private JButton XMLfile = new JButton("Choisir un film");
	private JSpinner size = new JSpinner(new SpinnerNumberModel(100,1,3600,25));
	private JSpinner width = new JSpinner(new SpinnerNumberModel(100,100,3500,25));
	private JSpinner height = new JSpinner(new SpinnerNumberModel(100,100,3500,25));

	public void testinitproject(){
		name.setText("test");
		size.setValue(3600);
		width.setValue(1920);
		height.setValue(1080);
	}
	
	public Launcher() {
		super("Nouveau projet Placheux");
		testinitproject();
		setContenu();
		ecouteurs();
		launcherConfig();
	}

	public Launcher(String nomFile){
		if(this.estUnFichierXML(nomFile)){
			initVue(new File(nomFile));
		} else {
			System.out.println("L'argument n'est un fichier xml d'animation");
			usage();
		}
	}

	public Launcher(String nom, int duree, int largeur, int hauteur){
		initVue(nom, duree, largeur, hauteur);
	}

	public void	ecouteurs(){
		ok.addActionListener(this);
		cancel.addActionListener(this);
		XMLfile.addActionListener(this);
	}

	public void launcherConfig(){
		this.setSize(500, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);		
	}

	public void setContenu(){
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		getContentPane().add(mainPanel);
		mainPanel.add(setContenuMainPanel());
		JPanel file_panel = new JPanel(new FlowLayout());
		file_panel.setBorder(BorderFactory.createTitledBorder("Ouvrir un film"));
		file_panel.add(XMLfile);
		file_panel.add(new JLabel("Importer le film"));
		mainPanel.add(file_panel);
		JPanel buttons = new JPanel(new FlowLayout());
		buttons.add(ok);
		buttons.add(cancel);
		mainPanel.add(buttons);		
	}

	public JPanel setContenuMainPanel(){
		JPanel addrPanel = new JPanel(new GridLayout(2, 2));
		addrPanel.setBorder(BorderFactory.createTitledBorder("Nouveau film"));
		addrPanel.add(new JLabel("Nom"));
		addrPanel.add(name);
		addrPanel.add(new JLabel("durée(ms)"));
		addrPanel.add(size);
		addrPanel.add(new JLabel("Largeur"));
		addrPanel.add(width);
		addrPanel.add(new JLabel("Hauteur"));
		addrPanel.add(height);
		return addrPanel;
	}

	public void initVue(File film){
		JFrame frame = new JFrame();
		Placheux screen = new Placheux(frame, film);
		frame.setContentPane(screen);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setMinimumSize(new Dimension(1152, 700));
		frame.setSize(new Dimension(1152, 700));
		frame.setMaximumSize(
				java.awt.Toolkit.getDefaultToolkit().getScreenSize());
		frame.setVisible(true);
	}

	public void initVue(String nom, int size, int width, int height){
		JFrame frame = new JFrame(nom);
		Placheux screen = new Placheux(frame, nom, size, width, height);
		frame.setContentPane(screen);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setMinimumSize(new Dimension(1152, 700));
		frame.setSize(new Dimension(1152, 700));
		frame.setMaximumSize(
				java.awt.Toolkit.getDefaultToolkit().getScreenSize());
		frame.setVisible(true);		
	}
	
	public static void usage(){
		System.out.println("Usage:\n"+
				"0 argument: launcher gui\n" +
				"1 argument: fichier xml d'animation\n" +
				"4 arguments: nom_film duree_film largeur_film hauteur_film");
	}

	public void ouvrirFilm(){
		JFileChooser chooser = new JFileChooser();
		File f = new File("");
		chooser.setSelectedFile(f);
		int result = chooser.showOpenDialog(null);
        if (result != JFileChooser.CANCEL_OPTION){
            File select = chooser.getSelectedFile();
            if(select.toString() != ""){
                if(!this.estUnFichierXML(select.toString())){
                    JOptionPane.showMessageDialog(this, "Le fichier n'est pas au format XML.");
                } else {
                    this.setVisible(false);
                    initVue(select);
                }
            } else {
                initVue(chooser.getSelectedFile());
            }
        }
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ok){
			setVisible(false);
			initVue(name.getText(), 
					(Integer)size.getValue(),
					(Integer)width.getValue(), 
					(Integer)height.getValue());
		} else if(e.getSource() == XMLfile) {
			ouvrirFilm();
		} else {
			System.exit(0);
		}
	}

	public boolean estUnFichierXML(String nomFichier){
		int posLastPoint = nomFichier.lastIndexOf(".");
		if(posLastPoint == -1){
			return false;
		}
		return (nomFichier.substring(posLastPoint + 1, 
				nomFichier.length()
				).equals("xml"));
	}

	public static void main(String [] args){  
		if(args.length == 1){
			new Launcher(args[0]);
		} else if(args.length == 4){
			new Launcher(args[0], 
					Integer.parseInt(args[1]), 
					Integer.parseInt(args[2]), 
					Integer.parseInt(args[3]));
		} else if(args.length == 0) {
			new Launcher();
		} else {
			usage();
		}
	}
}
