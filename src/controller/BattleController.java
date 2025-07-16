package controller;

import model.ety.Entity;
import model.ety.Player;
import model.ety.enemy.Enemy;
import view.guis.BattleGUI;
import view.guiparts.TextLog;
import model.BattleScene;

import javax.swing.*;

public class BattleController {
    //TODO: Make end of battle better for player loss condition, enemy loss condition, run condition.

    // === VARIABLES AND FIELDS ===
    private final BattleScene battleScene;
    private final BattleGUI battlePanel;
    private final Player player;
    private final Enemy enemy;


    // === BATTLE CONTROLLER CONSTRUCTOR ===
    public BattleController(BattleScene bsc, TextLog log){
        this.battleScene = bsc;
        this.battlePanel = new BattleGUI(this,bsc.getEnemy().getEntityName(),log);

        this.player = bsc.getPlayer();
        this.enemy = bsc.getEnemy();
    }

    // === GETTERS AND SETTERS ===
    public BattleGUI getBattlePanel() {return battlePanel;}


    // === CONSTRUCTOR METHODS ===

    // method to set the action listeners for the buttons

    // button handling methods
    public void handlePlayerAttack(){
            this.battlePanel.printAttack(this.player,this.enemy);
            this.battleScene.attackEntity(this.player,this.enemy);
            if(checkWin()){
                System.exit(0);
            }
            this.battlePanel.printHealth(this.enemy);
            endPlayerTurn();
    }

    public void handlePlayerDefend(){
            this.battlePanel.printDefend(this.player);
            this.player.guard();
            endPlayerTurn();
            this.player.getEntityStatBlock().resetTempStats();
    }

    public void handlePlayerItem() {
            this.battlePanel.printItemUseAttempt(this.player);
            if(this.player.getPlayerInventory().checkEmpty()){
                this.battlePanel.printNoItems(this.player);
            } else{
                // TEMP: Hard coded for just using healable, need to expand and genericize this.
                // TEMP: only takes from first slot, no choice | TODO: Add choice for items
                this.battlePanel.printSuccessfulItemUse(this.player,this.player.getPlayerInventory().getFromIndex(0));
                this.player.useItem(this.player.getPlayerInventory().getFromIndex(0));
                System.out.println("useItem.success");
            }
    }

    public void handlePlayerRun(){ // TODO: Refactor and make smaller
            boolean runSuccess = this.player.run(this.enemy);

            if(runSuccess){

                endBattle(); // First so set in false so ending player turn doesn't run enemy turn
                endPlayerTurn();

                // Debug
                System.out.println("Run Successful.");

                this.battlePanel.printSuccessfulRun(this.player.getEntityName());

                new Timer(1000,_ -> System.exit(0)).start(); // TODO: Better end function

            } else{
                // Debug
                System.out.println("Run failed.");

                this.battlePanel.printFailedRun(this.player.getEntityName());
                endPlayerTurn();
            }
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
        if(this.battleScene.isInBattle() && !checkLoss()){
            this.battlePanel.printStartTurn(this.player);
            this.battlePanel.enableButtons();
        }
    }


    // === ENEMY TURN METHODS ===
    // TODO: Add an endEnemyTurn method so you can check in there for battle ends
    private void runEnemyTurn(){
        if(this.battleScene.isInBattle()){
            this.battlePanel.printAttack(this.enemy,this.player);
            this.battleScene.attackEntity(this.enemy,this.player);
            this.battlePanel.printHealth(this.player);
            if(this.battleScene.getFirstGoer() instanceof Enemy){
                runPlayerTurn();
            } else{
                runTurnSetOrder();
            }
        }
    }

    // === BATTLE START METHODS ===
    public void startBattle(){
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

    // method to end the battle
    private void endBattle(){
        this.battleScene.setInBattle(false);
        // If checkWin vs if checkLoss vs returning run
        //TODO: Add better functionality for this; exit codes, etc.
    }


    // === BATTLE PROGRESSION METHODS ===
    private boolean checkFirstGoerIsPlayer(){
        Entity firstGoer = this.battleScene.determineWhoGoesFirst(this.player,this.enemy);
        return  firstGoer instanceof Player;
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
