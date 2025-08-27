package view.panels;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BattleBaseInteract extends BattleButtonPanel{
    // === CONSTANTS ===
    private static final JLabel BASE_PANEL_TEXT = new JLabel("Waiting...");

    public BattleBaseInteract(){ // May extend this to a general base panel
        super();
        this.add(BASE_PANEL_TEXT,CENTER_ALIGNMENT);
    }

    // === ButtonInputtable Methods ===
    @Override
    public ArrayList<JButton> initializeButtons(){
        // Does nothing, has no buttons -- perhaps logs, perhaps throws an error
        return new ArrayList<JButton>();
    }









}
