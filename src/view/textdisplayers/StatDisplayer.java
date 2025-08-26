package view.textdisplayers;

import model.ety.Entity;
import view.Writeable;

import javax.swing.*;
import java.awt.*;

// NOTE: may want one for player and one for enemy to do the hidden stat feature

public class StatDisplayer extends TextDisplayBox implements Writeable {
    // === CONSTANTS ===
    private static final int ROWS = 20;
    private static final int COLUMNS = 15;

    // === VARIABLES AND FIELDS ===
    private final Entity displayedEntity;

    // === CONSTRUCTOR ===
    public StatDisplayer(Entity entity){
        super(ROWS,COLUMNS);

        this.displayedEntity = entity;
        this.update();
        this.setBorder(buildBorder(BorderFactory.createLineBorder(Color.BLACK,5)));
    }


    // === METHODS ===
    public void update(){ // RELIES ON ENTITY TO STRING
        super.update(this.displayedEntity.toString());
    }





}
