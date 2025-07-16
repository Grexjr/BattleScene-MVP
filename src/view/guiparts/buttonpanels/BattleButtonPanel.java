package view.guiparts.buttonpanels;


import controller.BattleController;
import view.guiparts.buttons.BattleButton;

import javax.swing.*;
import java.awt.*;

public class BattleButtonPanel extends ButtonPanel {

    // === VARIABLES AND FIELDS ===


    // === CONSTRUCTOR ===
    public BattleButtonPanel(BattleController controller){
        // Set up panel peripheries
        this.add(new JLabel("Actions:"), BorderLayout.NORTH);

        // Set up the buttons | TODO: Abstract this to general ButtonPanel, then override it
        for(BattleButton b : BattleButton.values()){
            JButton btn = new JButton(b.getButtonDisplayName());
            btn.addActionListener(_ -> b.performAction(controller));
            this.add(btn);
        }

    }



}
