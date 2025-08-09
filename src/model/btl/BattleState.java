package model.btl;

import controller.BattlePhase;
import model.ety.BattleChoice;
import model.ety.Entity;
import model.ety.Player;
import model.ety.StatBlock;
import model.ety.enemy.Enemy;

import java.util.Random;

/** The Model representation of the battle. It holds a player, an enemy, a boolean for whose turn it is, and an enum
 * for how the battle is ended, which can be returned to a controller class to run code based on how the battle ends.
 * */
public class BattleState {

    // === VARIABLES AND FIELDS FOR BATTLESCENE ===
    private final Player player;
    private final Enemy enemy;
    private boolean isPlayerTurn;
    private EndCode ending;
    private BattlePhase currentPhase;


    // == CONSTRUCTOR FOR BATTLESCENE ===
    public BattleState(Player player, Enemy enemy){
        this.player = player;
        this.enemy = enemy;
        this.isPlayerTurn = false;
        this.ending = EndCode.NOT_OVER;
        this.currentPhase = BattlePhase.INITIALIZATION;
    }

    // === GETTERS AND SETTERS ===
    public Player getPlayer() {return this.player;}

    public Enemy getEnemy() {return this.enemy;}

    public boolean getPlayerTurn() {return this.isPlayerTurn;}
    public void setPlayerTurn(boolean turn) {this.isPlayerTurn = turn;}

    public BattlePhase getCurrentPhase() {return this.currentPhase;}
    public void setCurrentPhase(BattlePhase phase) {this.currentPhase = phase;}

    public EndCode getEnding() {return this.ending;}
    public void setEnding(EndCode end) {this.ending = end;}


    // === OTHER METHODS ===
    /// Randomly decides between two entities and returns one of them. Useful for turn calculations.
    private Entity runRandomEntityChoice(Entity trueEntity, Entity falseEntity){
        Random rand = new Random();
        boolean entityChoice = rand.nextBoolean();

        if(entityChoice){
            return trueEntity;
        } else{
            return falseEntity;
        }
    }


     /// Returns which entity goes first in the battle scene based on a speed comparison.
    public Entity determineFirst(Entity player, Entity enemy){
        StatBlock playerStats = player.getEntityStatBlock();
        StatBlock enemyStats = enemy.getEntityStatBlock();

        // If the comparison is positive, it means the comparer has the higher stat
        boolean playerFaster = playerStats.compareFullSpeed(enemyStats.calcFullSpeed()) > 0;
        boolean enemyFaster = playerStats.compareFullSpeed(enemyStats.calcFullSpeed()) < 0;

        if(playerFaster){
            return player;
        } else if(enemyFaster){
            return enemy;
        } else {
            return runRandomEntityChoice(player,enemy);
        }
    }

    /// attack entity method uses the take damage function and attack functions from the entity class
    private int attackEntity(Entity attacker, Entity target){
        return target.takeDamage(attacker.attack());
    }


    // === RUNNING ENTITY TURN METHODS ===
    /** Method to run generic entity turn based on the input value. For the player, from buttons; enemy, from AI.
     * It takes in the entity and the choice being made, and applies that choice to the entity.
    */
    public void calcEntityBattleChoice(Entity goer, BattleChoice choice){
        goer.makeBattleChoice(choice);
    }


    // === BATTLE CHOICE HANDLING METHODS ===
    // NOTE: These are all separated to allow for greater expandibility later
    /// Handles generic entity attacking
    public int handleAttack(Entity attacker, Entity target){
        return attackEntity(attacker,target);
    }

    /// Handles generic entity defending
    public void handleDefend(Entity defender){
        defender.guard();
    }

    /// Handles generic entity item use
    public void handleItemUse(Entity user){}

    public void handleRun(Entity runner, Entity runFrom){
        double escapeChance = runner.calculateEscapeChance(runFrom.getEntityStatBlock().calcFullSpeed());
        Random rand = new Random();
        double escapeRoll = rand.nextDouble();

        if(escapeChance <= escapeRoll){
            System.out.println("RUN SUCCESS!");
        }
    }


}
