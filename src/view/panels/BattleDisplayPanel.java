package view.panels;

import model.ety.Entity;
import model.ety.Player;
import view.textdisplayers.TextDisplayBox;
import view.textdisplayers.StatDisplayer;

import javax.swing.*;
import javax.swing.border.Border;
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
    public BattleDisplayPanel(TextDisplayBox text, Entity enemy, Entity player){
        super(text,TITLE + enemy.getEntityName());

        this.playerStats = new StatDisplayer(player);
        this.enemyStats = new StatDisplayer(enemy);

        this.add(enemyStats,BorderLayout.WEST);
        this.add(playerStats,BorderLayout.EAST);

        createBorder();
        setVisible(true); //NOTE: other ways to do this
    }

    public void updateStatDisplayers(){
        this.playerStats.update();
        this.enemyStats.update();
    }

    private void createBorder(){
        this.setBorder(
                super.buildBorder(BorderFactory.createEmptyBorder(
                        BORDER_NORTH,
                        BORDER_WEST,
                        BORDER_SOUTH,
                        BORDER_EAST)
                )
        );
    }

}
