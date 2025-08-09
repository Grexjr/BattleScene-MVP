package controller;

import model.btl.BattleState;
import model.ety.BattleChoice;
import model.ety.Entity;
import model.ety.Player;
import model.ety.enemy.Enemy;
import model.itm.Item;
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


    // === BATTLE CONTROLLER CONSTRUCTOR ===
    public BattleController(BattleState state, BattleDisplayPanel view){
        this.battleState = state;
        this.battleDisplay = view;
    }


    // === METHODS ===
    /// Method to reset temporary values at the beginning of the battle before anything happens
    private void resetAllTempValues(){
        // NOTE: Should find some way to do this without explicit reference to the players... should be a list/array
        Player player = this.battleState.getPlayer();
        Enemy enemy = this.battleState.getEnemy();

        player.getEntityStatBlock().resetTemporaryStats();
        enemy.getEntityStatBlock().resetTemporaryStats();
    }

    ///  Method that specifically resets an entity's defense by removing temporary defense based on guard.
    // NOTE: This WILL NOT WORK as currently implemented with status AND guard, but for now it is okay.
    private void resetDefenseTurnStart(Entity guardingEntity){
        guardingEntity.resetGuard();
    }

    ///  Method to handle battle choices generically, depending on the battle choice input
    private void handleBattleAction(Entity chooser, Entity other){
        BattleChoice choice = chooser.getBattleChoice();
        switch(choice){
            case ATTACK -> this.battleState.handleAttack(chooser,other);
            case DEFEND -> this.battleState.handleDefend(chooser);
            case USE_ITEM -> this.battleState.handleItemUse(chooser); // NOTE: does nothing
            case RUN -> this.battleState.handleRun(chooser,other);
        }
    }

    ///  Method to run entity turn that tests if entity is player or enemy and gives them a battle choice respectively.
    public void runEntityTurn(Entity goer){
        if(goer instanceof Player){
            // TODO: Need to add button functionality
            //this.battleState.calcEntityBattleChoice(goer,BUTTON FUNCTIONALITY);
        } else {
            this.battleState.calcEntityBattleChoice(goer,BattleChoice.ATTACK); //TODO: Make enemy AI and use it here
        }
    }

    ///  Method to run the turn order each time a new turn "set" is done
    public void runTurnOrder(){
        Player player = this.battleState.getPlayer();
        Enemy enemy = this.battleState.getEnemy();

        Entity firstGoer = this.battleState.determineFirst(player,enemy);

        //if for whether first goer is enemy or player and run their separate logic?
    }

}
