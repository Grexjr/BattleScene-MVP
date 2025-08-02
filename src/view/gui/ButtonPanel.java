package view.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ButtonPanel extends InteractPanel {

    // === CONSTRUCTOR ===
    public ButtonPanel(LayoutManager layout){
        super(layout);
    }


    // === METHODS ===
    private void addButtons(ArrayList<JButton> buttonsList){
        for(JButton b : buttonsList){
            this.add(b);
        }
    }







}
