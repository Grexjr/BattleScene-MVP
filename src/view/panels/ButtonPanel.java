package view.panels;

import view.ButtonInputtable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public abstract class ButtonPanel extends InteractPanel implements ButtonInputtable {

    // === FIELDS AND VARIABLES ===
    protected final ArrayList<JButton> buttonsList;

    // === CONSTRUCTOR ===
    public ButtonPanel(LayoutManager layout){
        super(layout);

        this.buttonsList = initializeButtons();
        this.addButtons();
        this.toggleButtons(false); // DEFAULT buttons are off
    }

    // === GETTERS ===
    public ArrayList<JButton> getButtonsList(){return this.buttonsList;}


    // === ButtonInputtable Defaults ===
    @Override
    public JButton createButton(String label, ActionListener onPressed){
        JButton button = new JButton(label);
        button.addActionListener(onPressed);
        return button;
    }

    @Override
    public void addButtons(){
        for(JButton b : buttonsList){
            this.add(b);
        }
    }

    @Override
    public void toggleButtons(boolean status){
        for(JButton b: buttonsList){
            this.setEnabled(status);
        }
    }








}
