package model.bsc;

import model.ety.Entity;
import model.ety.Player;
import model.ety.StatBlock;
import model.ety.enemy.Enemy;

import java.util.Random;

/** The battlescene runs thusly:
 *      The battlescene determines who goes first, either player or enemy.
 *      If player goes first, then they make their move.
 *      After player makes their move, enemy MUST go.
 *      After enemy makes their move, run the turn calculation again to determine who goes first.
 *      If enemy goes first, then they make their move.
 *      After enemy makes their move, player MUST go.
 *      After player makes their move, run the turn calculation again to determine who goes first.
 * */

public class BattleState {

    // === VARIABLES AND FIELDS FOR BATTLESCENE ===



    // == CONSTRUCTOR FOR BATTLESCENE ===
    public BattleState(Player player, Enemy enemy){

    }


    // === GETTERS AND SETTERS ===



    // === OTHER METHODS ===
    /**
     * Randomly decides between two entities and returns one of them. Useful for turn calculations.
     * */
    private Entity runRandomEntityChoice(Entity trueEntity, Entity falseEntity){
        Random rand = new Random();
        boolean entityChoice = rand.nextBoolean();

        if(entityChoice){
            return trueEntity;
        } else{
            return falseEntity;
        }
    }


    /**
     * Returns which entity goes first in the battle scene based on a speed comparison.
     * */
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

    /*

    // check who goes next to change flags, changes isPlayerTurn to true if player, otherwise makes variable false
    protected void checkIfPlayerGoesNext(Entity entity1, Entity entity2){
        Entity goer = determineWhoGoesFirst(entity1,entity2);
        this.isPlayerTurn = goer instanceof Player;
    }

    // attack entity method
    public void attackEntity(Entity attacker, Entity target){

        StatBlock targetStats = target.getEntityStatBlock();
        StatBlock attackerStats = attacker.getEntityStatBlock();
        int defensePower = targetStats.getEffectiveDefense();
        int attackPower = attackerStats.getEntityAttack();

        int damage = Math.max(attackPower - defensePower,0);

        targetStats.takeDamage(damage);

        // Debug
        System.out.println(attacker.getEntityName() + " attacks " + target.getEntityName());
    }*/





}
