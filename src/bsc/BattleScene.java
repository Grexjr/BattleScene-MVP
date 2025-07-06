package bsc;

import ety.Entity;
import ety.Player;
import ety.enemy.Enemy;

import java.util.Random;

public class BattleScene {

    // === VARIABLES AND FIELDS FOR BATTLESCENE ===
    private boolean inBattle, isPlayerTurn;
    private final Enemy enemy;
    private final Player player;


    // == CONSTRUCTOR FOR BATTLESCENE ===
    public BattleScene(Player player, Enemy enemy){
        this.enemy = enemy;
        this.player = player;
        this.inBattle = true;
        this.isPlayerTurn = false;

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


    // === OTHER METHODS ===

    // -- Helper Methods --
    // method that compares speeds of enemy and player and returns higher | TODO: effective speeds
    private int compareSpeeds(Entity comparer, Entity target){
        return comparer.getEntityStatBlock().compareSpeed(target.getEntityStatBlock().getEntitySpeed());
    }

    // method to determine which of two entities goes first | TODO: Effective speeds
    protected Entity determineTurn(Entity entity1, Entity entity2){
        Entity goer = null;
        if(entity1.getEntityStatBlock().getEntitySpeed() > entity2.getEntityStatBlock().getEntitySpeed()){
            goer = entity1;
            return goer;
        } else if(entity1.getEntityStatBlock().getEntitySpeed() < entity2.getEntityStatBlock().getEntitySpeed()){
            goer = entity2;
            return goer;
        } else { //TODO: Test if this is actually working, doesn't seem to be
            int rand = new Random().nextInt(0,1);
            if(rand == 0){
                goer = entity1;
                return goer;
            } else if(rand == 1){
                goer = entity2;
                return goer;
            }
        }
        return goer; //TODO: Throw exception here
    }

    // attack entity method
    //TODO: Split this up, put some in entity, some here? | REFACTOR!
    protected void attackEntity(Entity attacker, Entity target){
        int attackPower = Math.max(attacker.getEntityStatBlock().getEntityAttack() -
                target.getEntityStatBlock().getEffectiveDefense(),0);
        target.getEntityStatBlock().setEntityCurrentHealth(target.getEntityStatBlock().getEntityCurrentHealth() -
                attackPower);
        // Debug
        System.out.println(attacker.getEntityName() + " attacks " + target.getEntityName());
    }




}
