import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class Launcher extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;	
	private JButton ok = new JButton("ok");
	private JButton cancel = new JButton("cancel");
	private JTextField name = new JTextField(20);
	private JFileChooser XMLfilechoose;
	private JButton XMLfile = new JButton("Choisir un film");
	private JSpinner width = new JSpinner(new SpinnerNumberModel(100,100,3500,25));
	private JSpinner height = new JSpinner(new SpinnerNumberModel(100,100,3500,25));

	public Launcher() {
		super("Nouveau projet Placheux");
		setContenu();
		ecouteurs();
		launcherConfig();
	}

	public void	ecouteurs(){
		ok.addActionListener(this);
		cancel.addActionListener(this);
		XMLfile.addActionListener(this);		
	}

	public void launcherConfig(){
		this.setSize(500, 160);
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
		mainPanel.add(new JLabel(" "));
		JPanel buttons = new JPanel(new FlowLayout());
		buttons.add(ok);
		buttons.add(cancel);
		mainPanel.add(buttons);		
	}

	public JPanel setContenuMainPanel(){
		JPanel addrPanel = new JPanel(new GridLayout(2, 2));
		addrPanel.setBorder(BorderFactory.createTitledBorder("Receiver"));
		addrPanel.add(new JLabel("Nom"));
		addrPanel.add(name);
		addrPanel.add(XMLfile);
		addrPanel.add(new JLabel("Importer le film"));
		addrPanel.add(new JLabel("Largeur"));
		addrPanel.add(width);
		addrPanel.add(new JLabel("Hauteur"));
		addrPanel.add(height);
		return addrPanel;
	}

	public void initVue(){
		JFrame frame = new JFrame();
		Placheux screen = new Placheux(name.getText(), (Integer)width.getValue(), (Integer)height.getValue());
		frame.setContentPane(screen);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setMinimumSize(new Dimension(1152, 700));
		frame.setSize(new Dimension(1152, 700));
		frame.setMaximumSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize());
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ok){
			setVisible(false);
			initVue();
		} else if(e.getSource() == XMLfile) {
			JFileChooser chooser = new JFileChooser();
			File f = new File("");
			chooser.setSelectedFile(f);
			chooser.showOpenDialog(null);
			if(chooser.getSelectedFile().toString() != ""){
				setVisible(false);
			} else {
				new Placheux(chooser.getSelectedFile());
			}
		} else {
			System.exit(0);
		}
	}

	public static void main(String [] args){  
		if(args.length != 0){

		} else {
			new Launcher();
		}
	}
}
