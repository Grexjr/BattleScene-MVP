package bsc;

import ety.Entity;
import ety.Player;
import ety.enemy.Enemy;
import gui.parts.BattlePanel;

import javax.swing.*;

public class BattleController {

    // === VARIABLES AND FIELDS ===
    private final BattleScene battleScene;
    private final BattlePanel battlePanel;


    // === BATTLE CONTROLLER CONSTRUCTOR ===
    public BattleController(BattleScene bsc){
        this.battleScene = bsc;
        this.battlePanel = new BattlePanel(bsc.getEnemy().getEntityName());
        // TODO: Create variables/defaults of player and enemy for easier reference

        setUpActionListeners();

        startBattle();
        runBattleTurn();
    }

    // === CONSTRUCTOR METHODS ===

    // method to set the action listeners for the buttons
    private void setUpActionListeners(){
        this.battlePanel.getAttackButton().addActionListener(e -> {

            this.handlePlayerAttack();

            if(checkLoss(this.battleScene.getEnemy())){
                runLoss(this.battleScene.getEnemy());
            }

            finishPlayerTurn();
        });
        this.battlePanel.getDefendButton().addActionListener(e -> {
            this.handlePlayerDefense();
            finishPlayerTurn();
            SwingUtilities.invokeLater(() -> {
                    this.battleScene.getPlayer().getEntityStatBlock().setTempDefenseMod(0);
            });
        });
        this.battlePanel.getItemButton().addActionListener(e -> {
            this.handlePlayerItemUse();
            finishPlayerTurn();
        });
        this.battlePanel.getRunButton().addActionListener(e -> {
            this.handlePlayerRun();
            finishPlayerTurn();
        });
    }


    // === GETTERS AND SETTERS ===
    public BattleScene getBattleScene() {return this.battleScene;}
    public BattlePanel getBattlePanel() {return this.battlePanel;}


    // === OTHER METHODS === | TODO: Check if some of these should just go into battlescene

    // -- Helper Methods --
    // method to end battle
    private void endBattle(){
        this.battlePanel.printBattleOver();
        this.battleScene.setInBattle(false);
    }

    // method to run battle over
    private BattleExit runBattleExit(){
        endBattle();
        //Temporary, not what's actually going to happen
        SwingUtilities.invokeLater(this.battlePanel::clear);
        return this.battleScene.getExitCode();
    }



    // method to finish up the player turn
    private void finishPlayerTurn(){
        this.battlePanel.disableButtons();
        this.battleScene.setPlayerTurn(false);
        SwingUtilities.invokeLater(this::runEnemyTurn);
    }

    // method to check if entity loses
    private boolean checkLoss(Entity entity){
        return entity.getEntityStatBlock().getEntityCurrentHealth() <= 0;
    }

    // method to run the loss of the entity
    private void runLoss(Entity entity){ //TODO: add better functionality
        this.battlePanel.printLoss(entity);

        // Debug and temporary
        if(entity instanceof Player){
            // Debug
            System.out.println(this.battleScene.getPlayer() + " has lost.");
            runBattleExit();
        }
        if(entity instanceof Enemy){
            // Debug
            System.out.println("Battle won player");
            runBattleExit();
        }
    }

    // method to handle attacks
    private void handlePlayerAttack(){
        if(this.battleScene.isPlayerTurn()){
            this.battlePanel.printPlayerAttack(this.battleScene.getEnemy().getEntityName());
            this.battleScene.attackEntity(this.battleScene.getPlayer(),this.battleScene.getEnemy());
            this.battlePanel.printHealth(this.battleScene.getEnemy());
        }
    }

    // method to handle defense
    private void handlePlayerDefense(){
        if(this.battleScene.isPlayerTurn()){
            this.battlePanel.printPlayerDefend();
            this.battleScene.getPlayer().guard();
        }
    }

    // method to handle item use
    private void handlePlayerItemUse(){
        if(this.battleScene.isPlayerTurn()){
            this.battlePanel.printPlayerItemUse();
        }
    }

    // method to handle running
    private void handlePlayerRun(){
        if(this.battleScene.isPlayerTurn()){
            if(this.battleScene.getPlayer().run(this.battleScene.getEnemy())){
                // Debug
                System.out.println("Run Successful.");

                this.battlePanel.printSuccessfulRun(this.battleScene.getPlayer().getEntityName());
                this.battleScene.setPlayerTurn(false); // TODO: Find out why this isn't stopping input or enemy turn
                // TODO: Add in battle check that this changes, then add check in finishPlayerTurn that stops enemy

                new Timer(1000,e -> {
                    runBattleExit();
                }).start();

            } else{
                // Debug
                System.out.println("Run failed.");
                this.battlePanel.printFailedRun(this.battleScene.getPlayer().getEntityName());
            }
        }
    }

    // === BATTLE PROGRESS METHODS ===
    // player turn method
    private void runPlayerTurn(){
            this.battlePanel.printPlayerStartTurn();
            this.battlePanel.enableButtons();
    }

    // enemy turn method | TODO: ADD THE DIFFERENT CASES FOR ENEMY AI
    private void runEnemyTurn(){
        switch(this.battleScene.getEnemy().makeBattleChoice()){
            default:
                this.battlePanel.printEnemyAttack(this.battleScene.getEnemy());
                this.battleScene.attackEntity(this.battleScene.getEnemy(),this.battleScene.getPlayer());
                this.battlePanel.printHealth(this.battleScene.getPlayer());
                if(checkLoss(this.battleScene.getPlayer())){
                    runLoss(this.battleScene.getPlayer());
                }
                break;
        }
    }

    // start battle method
    private void startBattle(){ // in case more logic is added
        this.battlePanel.printBattleStart(this.battleScene.getPlayer(),this.battleScene.getEnemy());
    }

    // refactored battle turn method
    private void runBattleTurn(){
        this.battleScene.checkIfPlayerGoesNext(this.battleScene.getPlayer(),this.battleScene.getEnemy());
        if(this.battleScene.isPlayerTurn()){
            runPlayerTurn();
        } else{
            runEnemyTurn();
        }
    }




}
