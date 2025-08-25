package view.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public abstract class ButtonPanel extends InteractPanel {

    // === FIELDS AND VARIABLES ===
    protected final ArrayList<JButton> buttonsList;

    // === CONSTRUCTOR ===
    public ButtonPanel(LayoutManager layout){
        super(layout);

        this.buttonsList = initializeButtons();
        this.addButtons(buttonsList);
    }

    // === GETTER ===
    public ArrayList<JButton> getButtonsList() {return this.buttonsList;}


    // === METHODS ===
    protected void addButtons(ArrayList<JButton> buttonsList){
        for(JButton b : buttonsList){
            this.add(b);
        }
    }

    public void toggleButtons(boolean activeStatus){
        for(JButton b : this.buttonsList){
            b.setEnabled(activeStatus);
        }
    }

    public JButton createButton(String label, ActionListener action) {
        JButton button = new JButton(label);
        button.addActionListener(action);
        return button;
    }

    // === ABSTRACT METHODS ===
    protected abstract ArrayList<JButton> initializeButtons();

    protected abstract void createBorders();









}
