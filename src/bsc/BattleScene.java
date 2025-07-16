package bsc;

import ety.Entity;
import ety.Player;
import ety.StatBlock;
import ety.enemy.Enemy;

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

public class BattleScene {

    // === VARIABLES AND FIELDS FOR BATTLESCENE ===
    private boolean inBattle, isPlayerTurn;
    private final Enemy enemy;
    private final Player player;
    private Entity firstGoer;


    // == CONSTRUCTOR FOR BATTLESCENE ===
    public BattleScene(Player player, Enemy enemy){
        this.enemy = enemy;
        this.player = player;
        this.inBattle = true;
        this.isPlayerTurn = false;
        this.firstGoer = determineWhoGoesFirst(player,enemy);

        // Debug
        System.out.println("Starting battle...");
    }


    // === GETTERS AND SETTERS ===
    public boolean isInBattle() {return this.inBattle;}
    public void setInBattle(boolean value) {this.inBattle = value;}

    public Enemy getEnemy() {return this.enemy;}
    public Player getPlayer() {return this.player;}

    public boolean isPlayerTurn() {return this.isPlayerTurn;}
    public void setPlayerTurn(boolean value) {this.isPlayerTurn = value;}

    public Entity getFirstGoer() {return this.firstGoer;}
    public void setFirstGoer(Entity goer) {this.firstGoer = goer;}


    // === OTHER METHODS ===

    // -- Helper Methods --
    // method that determines who goes next in a battle
    // TODO: Refactor...
    public Entity determineWhoGoesFirst(Entity entity1, Entity entity2){
        Entity goer;
        StatBlock entity1Stats = entity1.getEntityStatBlock();
        int entity1Speed = entity1.getEntityStatBlock().getEffectiveSpeed();
        int entity2Speed = entity2.getEntityStatBlock().getEffectiveSpeed();

        if(entity1Stats.compareSpeedTo(entity2Speed)){
            goer = entity1;
        } else {
            if(entity1Speed == entity2Speed){
                boolean rand = new Random().nextBoolean();
                if(rand){
                    goer = entity1;
                } else{
                    goer = entity2;
                }
                return goer;
            }
            goer = entity2;
        }
        return goer;
    }

    // check who goes next to change flags, changes isPlayerTurn to true if player, otherwise makes variable false
    protected void checkIfPlayerGoesNext(Entity entity1, Entity entity2){
        Entity goer = determineWhoGoesFirst(entity1,entity2);
        this.isPlayerTurn = goer instanceof Player;
    }

    // attack entity method
    protected void attackEntity(Entity attacker, Entity target){

        StatBlock targetStats = target.getEntityStatBlock();
        StatBlock attackerStats = attacker.getEntityStatBlock();
        int defensePower = targetStats.getEffectiveDefense();
        int attackPower = attackerStats.getEntityAttack();

        int damage = Math.max(attackPower - defensePower,0);

        targetStats.takeDamage(damage);

        // Debug
        System.out.println(attacker.getEntityName() + " attacks " + target.getEntityName());
    }





}
