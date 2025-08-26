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
    Entity displayedEntity;


    // === CONSTRUCTOR ===
    public StatDisplayer(Entity entity){
        super(ROWS,COLUMNS);

        this.displayedEntity = entity;
        this.update();
        this.buildBorder(BorderFactory.createLineBorder(Color.GRAY,5));
    }


    // === METHODS ===
    public void update(){ // RELIES ON ENTITY TO STRING
        super.update(this.displayedEntity.toString());
    }





}
