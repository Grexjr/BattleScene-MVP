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
        runBattle(); //TEMP: just for testing
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
    private void runEntityTurn(Entity goer, Entity other){
        // TODO: Need to add button functionality, for now just does the battle choice attack
        //    also need to add enemy AI.
        this.battleState.calcEntityBattleChoice(goer,BattleChoice.ATTACK);
        System.out.println("Player attacks!");
        handleBattleAction(goer,other);
    }

    ///  Method to run the turn order each time a new turn "set" is done
    private void runTurnOrder(){
        Player player = this.battleState.getPlayer();
        Enemy enemy = this.battleState.getEnemy();

        Entity firstGoer = this.battleState.determineFirst(player,enemy);

        if(firstGoer instanceof Player){
            runEntityTurn(player,enemy);
            System.out.println("Player attacks!");
        } else{
            runEntityTurn(enemy,player);
            System.out.println("Enemy attacks!");
        }
    }

    /// Method to run the actual battle progression
    public void runBattle(){
        runTurnOrder();
    }

}
