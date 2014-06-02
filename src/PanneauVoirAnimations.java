import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

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

import model.animation.Animation;
import model.animation.ChangeBorderColor;
import model.animation.ChangeColor;
import model.animation.ChangeStrokeThickness;
import model.animation.EasingType;
import model.animation.Rotation;
import model.animation.Scaling;
import model.animation.Translation;
import model.easing.Back;
import model.easing.Bounce;
import model.easing.Circ;
import model.easing.Cubic;
import model.easing.Easing;
import model.easing.Easing.EASING;
import model.easing.Elastic;
import model.easing.Expo;
import model.easing.Linear;
import model.easing.Quad;
import model.easing.Quart;
import model.easing.Quint;
import model.gestionnary.StateGestionnary;
import model.movable.Figure;
import model.movable.Movable;
import model.movable.PointPlacheux;


public class PanneauVoirAnimations extends JDialog implements ActionListener{
	private static final long serialVersionUID = 1L;

	public PanneauVoirAnimations() {
		this.setModal(true);
		this.setLocation(400, 200);
		this.setTitle("Nouvelle animation");
		this.setSize(800, 600);
		this.setLayout(new BorderLayout());

		pack();
		setVisible(true);
	}

	public PanneauVoirAnimations(
			LinkedList<LinkedList<Animation>> distributeAnimations) {

		System.out.println("enter");
		
		for(int i = 0; i < distributeAnimations.size(); i++){
			for(int j = 0; j < distributeAnimations.get(i).size(); j++){
				System.out.println(distributeAnimations.get(i).get(j));
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
	}
}
