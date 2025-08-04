package view;

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


    // === METHODS ===
    protected void addButtons(ArrayList<JButton> buttonsList){
        for(JButton b : buttonsList){
            this.add(b);
        }
    }

    protected void toggleButtons(boolean activeStatus){
        for(JButton b : this.buttonsList){
            b.setEnabled(activeStatus);
        }
    }

    // === ABSTRACT METHODS ===
    protected abstract ArrayList<JButton> initializeButtons();

    protected abstract JButton createButton(String label, ActionListener action);

    protected abstract void createBorders();









}
