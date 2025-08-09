package controller;

import model.btl.BattleState;
import model.ety.BattleChoice;
import model.ety.Entity;
import model.ety.Player;
import model.ety.enemy.Enemy;
import model.itm.Item;
import view.BattleButtonPanel;
import view.BattleDisplayPanel;


/** The battle controller class that controls the flow of battle. Battle is broken up into "turn sets," which are just
 * sets of entity turns. One turn set is all the entities' turns in the battle state. Each turn set involves the
 * controller calculating who goes first, then running that entities turn, then running the turn of the entity who goes
 * after. Then, a new turn set is calculated.
 * */
//QUESTION: Should turn sets be their own object?
public class BattleController {


    // === VARIABLES AND FIELDS ===
    private final BattleState battleState;
    private final BattleDisplayPanel battleDisplay;
    private final BattleButtonPanel battleInteract;


    // === BATTLE CONTROLLER CONSTRUCTOR ===
    public BattleController(BattleState state, BattleDisplayPanel view, BattleButtonPanel interact){
        this.battleState = state;
        this.battleDisplay = view;
        this.battleInteract = interact;
        runBattle(); //TEMP: just for testing
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
                        System.out.println("ATTACK");
                        this.battleState.handleAttack(chooser,other);
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
    private void runEntityTurn(Entity goer, Entity other, BattleChoice choice){
        // TODO: Need to add button functionality, for now just does the battle choice attack
        //    also need to add enemy AI.
        this.battleState.calcEntityBattleChoice(goer,choice);
        handleBattleAction(goer,other);
    }

    /**
     * Runs the turn order
     * */
    private void runTurnOrder(){
        Player player = this.battleState.getPlayer();
        Enemy enemy = this.battleState.getEnemy();

        Entity firstGoer = this.battleState.determineFirst(player,enemy);

        if(firstGoer instanceof Player){
            player.makeBattleChoice(readPlayerInput());
            runEntityTurn(player,enemy,player.getBattleChoice());
        } else{
            enemy.calcEnemyBattleChoice();
            runEntityTurn(enemy,player,enemy.getBattleChoice());
        }
    }

    /**
     * Runs progression of the battle
     * */
    public void runBattle(){
        runTurnOrder();
    }

}
