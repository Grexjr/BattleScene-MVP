package model.btl;

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


    // == CONSTRUCTOR FOR BATTLESCENE ===
    public BattleState(Player player, Enemy enemy){
        this.player = player;
        this.enemy = enemy;
        this.isPlayerTurn = false;
        this.ending = EndCode.NOT_OVER;
    }

    // === GETTERS AND SETTERS ===
    public Player getPlayer() {return this.player;}

    public Enemy getEnemy() {return this.enemy;}

    public boolean getPlayerTurn() {return this.isPlayerTurn;}
    public void setPlayerTurn(boolean turn) {this.isPlayerTurn = turn;}

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
    public void attackEntity(Entity attacker, Entity target){
        target.takeDamage(attacker.attack());
    }


}
