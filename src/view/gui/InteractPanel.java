package view.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/***********************************************************************************************************************
 * This class is the general class for interaction panels, which will all be at the bottom of the screen.
 * These include panels with buttons, panels with text fields, panels with whatever interacts with user input.
 *
***********************************************************************************************************************/

public class InteractPanel extends ContentPanel {


    // === CONSTRUCTOR ===
    public InteractPanel(LayoutManager layout){
        super(layout);
    }


    // === METHODS ===
    private void addButtons(ArrayList<JButton> buttonsList){
        for(JButton b : buttonsList){
            this.add(b);
        }
    }






}
