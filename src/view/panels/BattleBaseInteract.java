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

    @Override
    protected void createBorders() {
        super.createBorders();
    }

    @Override
    protected ArrayList<JButton> initializeButtons() {
        return new ArrayList<>();
    }


    @Override
    public JButton createButton(String label, ActionListener action) {
        return new JButton();
    }
}
