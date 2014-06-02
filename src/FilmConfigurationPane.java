
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import model.movable.Film;


/**
 *
 * @author Josian Chevalier, Vladislav Fitc, Thomas Salmon
 *
 * Vue
 *
 * Projet Interface Graphique, Paris 7, Master 1, 2013-2014
 *
 */
public class FilmConfigurationPane extends JDialog implements ChangeListener {


    //          Attributs
    //---------------------------

        protected JTextField name=new JTextField(20);
        protected JTextField duree=new JTextField(5);
        private static JColorChooser sky_сolor_chooser = new JColorChooser();
        protected JButton validate = new JButton("Ok");
        protected JButton sky_color_button=new JButton();
        protected Placheux.PanElem view;
        protected Film film;

    
    //          Constructeur
    //----------------------------

        public FilmConfigurationPane(Placheux.PanElem view, Film film){
            setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
            this.view=view;
            this.film=film;
            
//            this.name.setText(film.getName());
            this.duree.setText(Double.toString(film.getDuration()));
            sky_color_button.setOpaque(true);
            sky_color_button.setBorderPainted(false);
            sky_color_button.setBackground(view.getBackground());
            sky_color_button.addActionListener(sky_сolor_chooser_listener);
            
            
            pack();
            setVisible(true);
            
        }



    //          Listener
    //----------------------------
        
    private Action sky_сolor_chooser_listener = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            chooseSkyColor();
            sky_color_button.setBackground(sky_сolor_chooser.getColor());
            validate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    view.setBackground(sky_color_button.getBackground());
                    film.setBackgroundColor(sky_color_button.getBackground());
                }
                
            });
        }
    };
        
    //          Methodes
    //----------------------------

        private static void chooseSkyColor(){
            final JDialog dialog = JColorChooser.createDialog(null,"Choose sky color...",true, sky_сolor_chooser,null,null);
            dialog.setVisible(true);
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

}
