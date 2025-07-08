package bsc;

import ety.Entity;
import ety.Player;
import ety.enemy.Enemy;
import gui.parts.BattlePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BattleController {

    // === VARIABLES AND FIELDS ===
    private final BattleScene battleScene;
    private final BattlePanel battlePanel;
    private final Player player;
    private final Enemy enemy;


    // === BATTLE CONTROLLER CONSTRUCTOR ===
    public BattleController(BattleScene bsc){
        this.battleScene = bsc;
        this.battlePanel = new BattlePanel(bsc.getEnemy().getEntityName());
        // TODO: Create variables/defaults of player and enemy for easier reference

        this.player = bsc.getPlayer();
        this.enemy = bsc.getEnemy();

        setUpActionListeners();

        startBattle();
    }

    // === GETTERS AND SETTERS ===
    public BattlePanel getBattlePanel() {return battlePanel;}


    // === CONSTRUCTOR METHODS ===

    // method to set the action listeners for the buttons
    private void setUpActionListeners(){
        this.battlePanel.getAttackButton().addActionListener(handlePlayerAttack());

        this.battlePanel.getDefendButton().addActionListener(handlePlayerDefend());

        this.battlePanel.getItemButton().addActionListener(handlePlayerItem());

        this.battlePanel.getRunButton().addActionListener(handlePlayerRun());
    }

    // button handling methods
    private ActionListener handlePlayerAttack(){
        return _ -> {
            this.battlePanel.printPlayerAttack(this.enemy.getEntityName());
            this.battleScene.attackEntity(this.player,this.enemy);
            if(checkWin()){
                System.exit(0);
            }
            this.battlePanel.printHealth(this.enemy);
            endPlayerTurn();
        };
    }

    private ActionListener handlePlayerDefend(){
        return _ -> {
            this.battlePanel.printPlayerDefend();
            this.player.guard();
            endPlayerTurn();
            this.player.getEntityStatBlock().resetTempStats();
        };
    }

    private ActionListener handlePlayerItem() {
        return _ -> {
            this.battlePanel.printPlayerItemUse();
            endPlayerTurn();
        };
    }

    private ActionListener handlePlayerRun(){ // TODO: Refactor and make smaller
        return _ -> {
            boolean runSuccess = this.player.run(this.enemy);

            if(runSuccess){
                // Debug
                System.out.println("Run Successful.");

                this.battlePanel.printSuccessfulRun(this.player.getEntityName());

                new Timer(1000,_ -> {
                    endPlayerTurn(); // TODO: Player still takes damaged, though not displayed; fix with boolean
                    System.exit(0);
                }).start();

            } else{
                // Debug
                System.out.println("Run failed.");

                this.battlePanel.printFailedRun(this.player.getEntityName());
                endPlayerTurn();
            }
        };
    }


    // === PLAYER TURN METHODS ===
    private void endPlayerTurn(){
        this.battlePanel.disableButtons();
        if(this.battleScene.getFirstGoer() instanceof Player){
            runEnemyTurn();
        } else{
            runTurnSetOrder();
        }
    }

    // running player turn
    private void runPlayerTurn(){
        this.battlePanel.printPlayerStartTurn();
        this.battlePanel.enableButtons();
    }


    // === ENEMY TURN METHODS ===
    private void runEnemyTurn(){
        this.battlePanel.printEnemyAttack(this.enemy);
        this.battleScene.attackEntity(this.enemy,this.player);
        this.battlePanel.printHealth(this.player);
        if(this.battleScene.getFirstGoer() instanceof Enemy){
            runPlayerTurn();
        } else{
            runTurnSetOrder();
        }
    }

    // === BATTLE START METHODS ===
    private void startBattle(){
        this.battlePanel.printBattleStart(this.player,this.enemy);
        this.battlePanel.disableButtons();
        if(this.battleScene.getFirstGoer() instanceof Player){
            runPlayerTurn();
        } else {
            runEnemyTurn();
        }
    }

    // === BATTLE END METHODS ===
    //TODO: Refactor the below with battleExits and one singular method

    // method to check if battle won
    private boolean checkWin(){
        return this.enemy.isDead();
    }

    // method to check if battle lost
    private boolean checkLoss(){
        return this.player.isDead();
    }


    // === BATTLE PROGRESSION METHODS ===
    private boolean checkFirstGoerIsPlayer(){
        return this.battleScene.determineWhoGoesFirst(this.player,this.enemy) instanceof Player;
    }

    private void runTurnSetOrder(){
        if(checkFirstGoerIsPlayer()){
            this.battleScene.setFirstGoer(this.player);
            runPlayerTurn();
        } else {
            this.battleScene.setFirstGoer(this.enemy);
            runEnemyTurn();
        }
    }
}
