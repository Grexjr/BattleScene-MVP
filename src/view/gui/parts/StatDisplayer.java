package view.gui.parts;

import model.ety.Entity;

import javax.swing.*;
import java.awt.*;

// NOTE: may want one for player and one for enemy to do the hidden stat feature

public class StatDisplayer extends JTextArea {

    // === VARIABLES AND FIELDS ===


    // === CONSTRUCTOR ===
    public StatDisplayer(Entity displayedEntity){
        this.update(displayedEntity);
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY,5));
    }


    // === METHODS ===
    private void update(Entity displayedEntity){
        this.setText(displayedEntity.getEntityStatBlock().toString());
    }





}
