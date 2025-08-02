package view.gui;

import model.ety.BattleChoice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BattleButtonPanel extends ButtonPanel {
    // === CONSTANTS ===
    private static final LayoutManager LAYOUT = new FlowLayout();
    private static final String[] BUTTON_LABELS = new String[]{
            "ATTACK",
            "DEFEND",
            "ITEM",
            "RUN"
    };


    // === FIELDS AND VARIABLES ===
    private BattleChoice choice;


    // === CONSTRUCTOR ===
    public BattleButtonPanel(){
        super(LAYOUT);
        setVisible(true); //NOTE: other ways to do this
    }

    // === GETTER ===
    public BattleChoice getChoice() {return this.choice;}

    // === METHODS ===
    @Override
    protected JButton createButton(String label, ActionListener action){
        JButton button = new JButton(label);
        button.addActionListener(action);
        return button;
    }

    @Override
    protected ArrayList<JButton> initializeButtons() {
        JButton attack = createButton(BUTTON_LABELS[0],_ ->this.choice = BattleChoice.ATTACK);
        JButton defend = createButton(BUTTON_LABELS[1],_ -> this.choice = BattleChoice.DEFEND);
        JButton item = createButton(BUTTON_LABELS[2],_ -> this.choice = BattleChoice.USE_ITEM);
        JButton run = createButton(BUTTON_LABELS[3],_ -> this.choice = BattleChoice.RUN);
        ArrayList<JButton> buttonsList = new ArrayList<JButton>();
        buttonsList.add(attack);
        buttonsList.add(defend);
        buttonsList.add(item);
        buttonsList.add(run);
        return buttonsList;
    }

    // TODO: Create system logs for action listeners that button has been pressed

}
