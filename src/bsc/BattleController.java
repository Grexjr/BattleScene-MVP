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

        setUpActionListeners();

        if(!this.battleScene.isPlayerTurn()){
            this.battlePanel.getButtonPanel().setVisible(false);
        }

        startBattle();
        runBattleLoop();
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
    // method to finish up the player turn
    private void finishPlayerTurn(){
        this.battlePanel.hide(this.battlePanel.getButtonPanel());
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
            System.exit(0);
        }
        if(entity instanceof Enemy){
            // Debug
            System.out.println("Battle won player");
            System.exit(0);
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
                    System.exit(0);
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
            this.battleScene.setPlayerTurn(true);
            this.battlePanel.getButtonPanel().setVisible(true);
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
        runPlayerTurn();
    }

    // start battle method
    private void startBattle(){ // in case more logic is added
        this.battlePanel.printBattleStart(this.battleScene.getPlayer(),this.battleScene.getEnemy());
    }

    // battle loop method
    private void runBattleLoop(){
        if(this.battleScene.determineTurn(this.battleScene.getPlayer(),this.battleScene.getEnemy()) instanceof Player){
            runPlayerTurn();
        }else{
        SwingUtilities.invokeLater(this::runEnemyTurn);
        }
    }




}
