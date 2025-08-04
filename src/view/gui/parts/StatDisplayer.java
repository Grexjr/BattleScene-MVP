package view.gui.parts;

import model.ety.Entity;

import javax.swing.*;
import java.awt.*;

// NOTE: may want one for player and one for enemy to do the hidden stat feature

public class StatDisplayer extends JTextArea {
    // === CONSTANTS ===
    private static final int ROWS = 20;
    private static final int COLUMNS = 15;

    // === VARIABLES AND FIELDS ===


    // === CONSTRUCTOR ===
    public StatDisplayer(Entity displayedEntity){
        super(ROWS,COLUMNS);
        this.setEditable(false);
        this.setFocusable(false);
        this.setLineWrap(true);
        this.update(displayedEntity);
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY,5));
    }


    // === METHODS ===
    private void update(Entity displayedEntity){
        this.setText(displayedEntity.getEntityName() + "\n" +
                        displayedEntity.getEntityDescription() + "\n\n" +
                        displayedEntity.getEntityStatBlock().toString()
        );
    }





}
