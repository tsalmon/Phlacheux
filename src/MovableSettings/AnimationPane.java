package MovableSettings;

import model.animation.Animation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AnimationPane extends JPanel {
    DefaultListModel listModel = new DefaultListModel();
    JList animationsList = new JList(listModel);
    ArrayList<Animation> animations = new ArrayList<Animation>();
    AnimationBasicSettings animationSettingsPanel = new AnimationBasicSettings(Animation.AnimationType.TRANSLATION);

    Object[] animationNames = new Object[]{"Transition", "Rotation", "Scaling", "Change color", "Change border", "Change border color"};

    JComboBox addAnimationBox = new JComboBox(animationNames);

    public AnimationPane(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        listModel.addElement("Element 1");
        listModel.addElement("Element 2");
        listModel.addElement("Element 3");

        animationsList.setFixedCellWidth(200);

        addAnimationBox.setToolTipText("Ajouter une animation...");
        addAnimationBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int index = ((JComboBox)actionEvent.getSource()).getSelectedIndex();
                Animation.AnimationType type = Animation.AnimationType.TRANSLATION;
                System.out.println(index);
                switch (index){
                    case 0:{
                        type = Animation.AnimationType.TRANSLATION;
                    }
                    break;

                    case 1:{
                        type = Animation.AnimationType.ROTATION;
                    }
                    break;

                    case 2:{
                        type = Animation.AnimationType.SCALING;
                    }
                    break;

                    case 3:{
                        type = Animation.AnimationType.CHANGE_COLOR;
                    }
                    break;

                    case 4:{
                        type = Animation.AnimationType.CHANGE_STROKE_THICKNESS;
                    }
                    break;

                    case 5:{
                        type = Animation.AnimationType.CHANGE_BORDER_COLOR;
                    }
                    break;
                }
                remove(animationSettingsPanel);
                animationSettingsPanel = new AnimationBasicSettings(type);
                add(animationSettingsPanel);
                revalidate();
            }
        });


        JLabel animationsListLabel = new JLabel("Liste d'animations");
        animationsListLabel.setHorizontalAlignment(JLabel.CENTER);

        animationSettingsPanel.setVisible(false);

        add(animationsListLabel);
        add(animationsList);
        add(addAnimationBox);
        add(animationSettingsPanel);

    }
}
