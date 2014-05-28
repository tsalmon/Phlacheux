import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class Launcher extends JFrame implements ActionListener{
 	private static final long serialVersionUID = 1L;	
	private JButton ok = new JButton("ok");
	private JButton cancel = new JButton("cancel");

	private JTextField name = new JTextField(20);
	private JFileChooser XMLfilechoose;
	private JButton XMLfile = new JButton("Choisir un film");
	private JSpinner width;
	private JSpinner height;

	public Launcher() {
		super("Nouveau projet Placheux");
		SpinnerModel sp_num = new SpinnerNumberModel(100,100,3500,1);
		width = new JSpinner(sp_num);
		height = new JSpinner(sp_num);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		getContentPane().add(mainPanel);

		
		mainPanel.add(setContenuMainPanel());

		mainPanel.add(new JLabel(" "));

		JPanel buttons = new JPanel(new FlowLayout());
		buttons.add(ok);
		buttons.add(cancel);

		mainPanel.add(buttons);

		ok.addActionListener(this);
		cancel.addActionListener(this);
		XMLfile.addActionListener(this);

		this.setSize(500, 160);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
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
			XMLfilechoose = new JFileChooser();
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
