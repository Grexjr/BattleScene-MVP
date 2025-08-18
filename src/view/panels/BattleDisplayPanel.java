package view.panels;

import model.ety.Entity;
import model.ety.Player;
import view.textdisplayers.StatDisplayer;

import javax.swing.*;
import java.awt.*;

public class BattleDisplayPanel extends DisplayPanel {
    // === CONSTANTS ===
    private static final String TITLE = "Battle vs. ";
    private static final int BORDER_NORTH = 10;
    private static final int BORDER_SOUTH = 10;
    private static final int BORDER_WEST = 10;
    private static final int BORDER_EAST = 10;

    // === VARIABLES AND FIELDS ===
    private final StatDisplayer playerStats, enemyStats;

    // === THE CONSTRUCTOR ===
    public BattleDisplayPanel(JTextArea textLog, Entity enemy, Entity player){
        super(textLog,TITLE + enemy.getEntityName());

        this.playerStats = new StatDisplayer(player);
        this.enemyStats = new StatDisplayer(enemy);

        this.add(playerStats,BorderLayout.EAST);
        this.add(enemyStats,BorderLayout.WEST);

        // formatting the panel with edges
        this.setBorder(BorderFactory.createEmptyBorder(BORDER_NORTH,BORDER_WEST,BORDER_SOUTH,BORDER_EAST));
            // NORTH WEST SOUTH EAST





        setVisible(true); //NOTE: other ways to do this
    }


    // === GETTERS ===
    public StatDisplayer getPlayerStats(){return this.playerStats;}
    public StatDisplayer getEnemyStats(){return this.enemyStats;}

    // === METHODS ===
    public void print(String... printStrings){
        for(String string : printStrings){
            this.log(string);
        }
    }

    public void updateStatDisplayer(Entity entity){
        if(entity instanceof Player){
            playerStats.update(entity);
        } else{
            enemyStats.update(entity);
        }
    }



}
