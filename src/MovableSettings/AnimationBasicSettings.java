package MovableSettings;

import model.animation.Animation;
import model.animation.EasingType;
import model.easing.Easing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimationBasicSettings extends JPanel {
    Object[] easings = new Object[]{"Linear", "Back", "Bounce", "Circ", "Cubic", "Elastic", "Expo", "Quad", "Quart", "Quint", "Sine"};
    Object[] easingTypes = new Object[]{"None", "Ease In", "Ease Out", "Ease In-Out"};


    private Easing.EASING easing = Easing.EASING.LINEAR;
    private EasingType easingType = EasingType.EASE_IN;


    JLabel startTimeLabel = new JLabel("Temps de debut");
    JSpinner startTimeSpinner = new JSpinner(new SpinnerNumberModel(0,0,1000,1));

    JLabel durationLabel = new JLabel("Durée");
    JSpinner durationSpinner = new JSpinner(new SpinnerNumberModel(0,0,1000,1));

    JLabel easingLabel = new JLabel("Easing");
    JComboBox easingComboBox = new JComboBox(easings);

    JLabel easingTypeLabel = new JLabel("Easing type");
    JComboBox easingTypeComboBox = new JComboBox(easingTypes);

    JLabel customSettingsLabel = new JLabel("Reglages particuliers");
    JPanel customSettingsPanel;

    JButton saveButton = new JButton("Save");

    public AnimationBasicSettings(Animation.AnimationType animationType){
        setPreferredSize(new Dimension(200,300));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(startTimeLabel);
        add(startTimeSpinner);
        add(durationLabel);
        add(durationSpinner);
        add(easingLabel);
        add(easingComboBox);
        add(easingTypeLabel);
        add(easingTypeComboBox);
        add(customSettingsLabel);

        switch(animationType){
            case TRANSLATION:{
                customSettingsPanel = new TransitionSettingsPanel();
            }
            break;

            case ROTATION:{
                customSettingsPanel = new RotationSettingsPanel();
            }
            break;

            case SCALING:{
                customSettingsPanel = new ScalingSettingsPanel();
            }
            break;

            case CHANGE_COLOR:{
                customSettingsPanel = new ChangeColorSettingsPanel("Couleur ciblé");
            }
            break;

            case CHANGE_BORDER_COLOR:{
                customSettingsPanel = new ChangeColorSettingsPanel("Couleur de bordure ciblé");
            }
            break;

            case CHANGE_STROKE_THICKNESS:{
                customSettingsPanel = new ChangeBorderThicknessSettingsPanel();
            }
            break;

            default:{
                System.out.println("Unrecognized animation");
            }
        }

        easingComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int index = ((JComboBox)actionEvent.getSource()).getSelectedIndex();
                switch (index){
                    case 0:{
                        easing= Easing.EASING.LINEAR;
                    }
                    break;

                    case 1:{
                        easing = Easing.EASING.BACK;
                    }
                    break;

                    case 2:{
                        easing = Easing.EASING.BOUNCE;
                    }
                    break;

                    case 3:{

                    }
                    break;

                    case 4:{

                    }
                    break;

                    case 5:{

                    }
                    break;

                    case 6:{

                    }
                    break;

                    case 7:{

                    }
                    break;

                    case 8:{

                    }
                    break;

                    case 9:{

                    }
                    break;

                    case 10:{

                    }
                    break;

                    case 11:{

                    }
                    break;
                }
            }
        });

        easingTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int index = ((JComboBox)actionEvent.getSource()).getSelectedIndex();

            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        add(customSettingsPanel);
        add(saveButton);
    }
}
