package view.panels;

import model.ety.BattleChoice;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TurnActionPanel extends BattleButtonPanel {
    // === CONSTANTS ===
    private static final String[] BUTTON_LABELS = new String[]{
            "ATTACK",
            "DEFEND",
            "ITEM",
            "RUN"
    };


    // === FIELDS AND VARIABLES ===
    private BattleChoice choice;


    // === CONSTRUCTOR ===
    public TurnActionPanel(){
        super();
        this.choice = BattleChoice.INVALID;
        setVisible(true); //NOTE: other ways to do this
    }

    // === ButtonInputtable Methods ===
    @Override
    public ArrayList<JButton> initializeButtons(){
        JButton attack = createButton(BUTTON_LABELS[0],null);
        JButton defend = createButton(BUTTON_LABELS[1],null);
        JButton item = createButton(BUTTON_LABELS[2],null);
        JButton run = createButton(BUTTON_LABELS[3],null);
        ArrayList<JButton> buttonsList = new ArrayList<JButton>();
        buttonsList.add(attack);
        buttonsList.add(defend);
        buttonsList.add(item);
        buttonsList.add(run);
        return buttonsList;
    }

}
