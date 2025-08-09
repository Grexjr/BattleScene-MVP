package controller;

import model.btl.BattleState;
import model.ety.BattleChoice;
import model.ety.Entity;
import model.ety.Player;
import model.ety.enemy.Enemy;
import model.itm.Item;
import view.BattleButtonPanel;
import view.BattleDisplayPanel;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/** The battle controller class that controls the flow of battle. Battle is broken up into "turn sets," which are just
 * sets of entity turns. One turn set is all the entities' turns in the battle state. Each turn set involves the
 * controller calculating who goes first, then running that entities turn, then running the turn of the entity who goes
 * after. Then, a new turn set is calculated.
 * */
//QUESTION: Should turn sets be their own object?
public class BattleController {
    // === CONSTANTS ===


    // === VARIABLES AND FIELDS ===
    private final BattleState battleState;
    private final BattleDisplayPanel battleDisplay;
    private final BattleButtonPanel battleInteract;


    // === BATTLE CONTROLLER CONSTRUCTOR ===
    public BattleController(BattleState state, BattleDisplayPanel view, BattleButtonPanel interact){
        this.battleState = state;
        this.battleDisplay = view;
        this.battleInteract = interact;
        runBattle(); // TEMP: Testing purposes
    }

    // === Creating ActionListeners === TODO: Can one day move this to a different action class
    private void onAttackPressed(Entity target){
        if(this.battleState.getCurrentPhase() == BattlePhase.PLAYER_TURN){
            // local vars
            Player player = this.battleState.getPlayer();

            // store damage amount
            int damage = this.battleState.handleAttack(player,target);

            // Update UI
            printAttack(player, target, damage);
            this.battleDisplay.updateStatDisplayer(target);

            // System log | TODO: Proper system logging
            System.out.println(player.getEntityName() + " attacks for " + damage + " damage!");
        } // ERROR: handle if not player turn
    }

    private void onDefendPressed(){
        if(this.battleState.getCurrentPhase() == BattlePhase.PLAYER_TURN){
            // run defense if player turn
            this.battleState.handleDefend(this.battleState.getPlayer());
        } // ERROR: will need to handle if not player turn
    }

    private void onItemPressed(){
        this.battleState.handleItemUse(this.battleState.getPlayer());
    }

    private void onRunPressed(){
        this.battleState.handleRun(this.battleState.getPlayer(),this.battleState.getEnemy());
    }


    // === Creating Buttons ===
    private void setUpActionListeners(){
        ArrayList<JButton> battleButtons = battleInteract.getButtonsList();
        Entity other = this.battleState.getEnemy();  // gets current enemy

        battleButtons.getFirst().addActionListener(_ -> {
            onAttackPressed(other);
            System.out.println("Press.");
        });
        battleButtons.get(1).addActionListener(_ -> {
            onDefendPressed();
            System.out.println("Press.");
        });
        battleButtons.get(2).addActionListener(_ -> {
            onItemPressed();
            System.out.println("Press.");
        });
        battleButtons.get(3).addActionListener(_ -> {
            onRunPressed();
            System.out.println("Press.");
        });
    }

    /**
     * This method initializes the battle
     * */
    public void initializeBattle(){
        // Creating the player and enemy
        Player player = battleState.getPlayer();
        Enemy enemy = battleState.getEnemy();

        player.getEntityStatBlock().resetTemporaryStats();
        enemy.getEntityStatBlock().resetTemporaryStats();

        // Creating the buttons for the button panel
        setUpActionListeners();
    }


    // === METHODS ===
    /**
     * Reads input from the battle button panel and returns it
     * */
    public BattleChoice readPlayerInput(){
        return this.battleInteract.getChoice();
    }

    /**
     * Resets all temporary values of the entities in the battle scene, set to be used at the beginning of battle
     * */
    private void resetAllTempValues(){
        // NOTE: Should find some way to do this without explicit reference to the players... should be a list/array
        Player player = this.battleState.getPlayer();
        Enemy enemy = this.battleState.getEnemy();

        player.getEntityStatBlock().resetTemporaryStats();
        enemy.getEntityStatBlock().resetTemporaryStats();
    }

    /**
     * Method that resets the defense of the entity to prevent guarding from carrying over from turn to turn
     *
     * @param guardingEntity the entity that is guarding and will have defense reset.
     * */
    // NOTE: This WILL NOT WORK as currently implemented with status AND guard, but for now it is okay.
    private void resetDefenseTurnStart(Entity guardingEntity){
        guardingEntity.resetGuard();
    }

    /**
     * Handles individual battle actions like attacking, defending, using item, running
     *
     * @param chooser the entity choosing the battle action
     * @param other the entity not currently choosing a battle action
     * */
    private void handleBattleAction(Entity chooser, Entity other){
        BattleChoice choice = chooser.getBattleChoice();
        switch(choice){
            case ATTACK ->
                    {

                    }
            case DEFEND ->
                    {
                        System.out.println("DEFEND");
                        this.battleState.handleDefend(chooser);
                    }
            case USE_ITEM ->
                    {
                        System.out.println("ITEM");
                        this.battleState.handleItemUse(chooser);
                    } // NOTE: does nothing
            case RUN ->
                    {
                        System.out.println("RUN");
                        this.battleState.handleRun(chooser,other);
                    }
        }
    }

    /**
     * Runs an entity's turn
     *
     * @param goer the entity whose turn it is,
     * @param other the entity who is not currently going
     * @param choice the choice the goer entity is making
     * */
    /*private void runEntityTurn(Entity goer, Entity other, BattleChoice choice){
        // TODO: Need to add button functionality, for now just does the battle choice attack
        //    also need to add enemy AI.
        this.battleState.calcEntityBattleChoice(goer,choice);
        handleBattleAction(goer,other);
    }*/

    /**
     * Runs the turn order
     * */
    private Entity runTurnOrder(){
        Player player = this.battleState.getPlayer();
        Enemy enemy = this.battleState.getEnemy();

        Entity firstGoer = this.battleState.determineFirst(player,enemy);

        if(firstGoer instanceof Player){
            return player;
        } else{
            return enemy;
        }
    }

    // === PRINT METHODS === | TODO: will need to be refactored
    public void printAttack(Entity attacker, Entity target, int damage){
        this.battleDisplay.print(String.format("%s Attacks %s for %d damage!",
                attacker.getEntityName(),
                target.getEntityName(),
                damage
        ));
    }

    // === RUNNING METHODS ===
    public void runInit(){
        if(this.battleState.getCurrentPhase() == BattlePhase.INITIALIZATION){
            initializeBattle();
            this.battleState.setCurrentPhase(BattlePhase.TEXT_EVENT);
        } // NOTE: Else will throw exception that must be handled; battle ends prematurely
    }

    public void runIntro(){
        if(this.battleState.getCurrentPhase() == BattlePhase.TEXT_EVENT){
            // Printing intro, etc., any text events that occur; passed in as parameter
            this.battleState.setCurrentPhase(BattlePhase.DETERMINE_TURN_ORDER);
        }
    }

    public void runTurnOrderCalc(){
        if (this.battleState.getCurrentPhase() == BattlePhase.DETERMINE_TURN_ORDER){
            if(runTurnOrder() instanceof Player){
                this.battleState.setCurrentPhase(BattlePhase.PLAYER_TURN);
            } else {
                this.battleState.setCurrentPhase(BattlePhase.ENEMY_TURN);
            }
        }
    }



    /**
     * This method runs the entire battle
     * */
    public void runBattle(){
        runInit();
        runIntro();
    }

}
