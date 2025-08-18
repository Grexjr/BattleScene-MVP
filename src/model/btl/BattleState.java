package model.btl;

import controller.BattlePhase;
import model.ety.*;
import model.ety.enemy.Enemy;

import java.util.ArrayList;
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

    private final Entity[] battlers;


    // == CONSTRUCTOR FOR BATTLESCENE ===
    public BattleState(Entity... battlers){
        // This isn't a great system, but it works for now
        this.player = (Player)battlers[0];
        this.enemy = (Enemy)battlers[1];


        this.isPlayerTurn = false;

        this.battlers = battlers;

        this.ending = EndCode.NOT_OVER;
        this.currentPhase = BattlePhase.INITIALIZATION;
    }

    // === GETTERS AND SETTERS ===
    public Player getPlayer() {return this.player;}

    public Enemy getEnemy() {return this.enemy;}

    public boolean getPlayerTurn() {return this.isPlayerTurn;}
    public void setPlayerTurn(boolean turn) {this.isPlayerTurn = turn;}

    public Entity[] getBattlers() {return this.battlers;}

    public BattlePhase getCurrentPhase() {return this.currentPhase;}
    public void setCurrentPhase(BattlePhase phase) {this.currentPhase = phase;}

    public EndCode getEnding() {return this.ending;}
    public void setEnding(EndCode end) {this.ending = end;}


    // === OTHER METHODS ===
    ///  resets temporary values of the entities within
    public void resetTemporaryValues(Entity... battlers){
        for(Entity battler : battlers){
            battler.getEntityStatBlock().resetTemporaryStats();
        }
    }

     /**
      * Determines the order of which entities go by using a bubble sort algorithm. Algorithm from this lesson
      * (which I did only a day before needing this!):
      * <a href="https://www.w3schools.com/dsa/dsa_data_arrays.php">Link</a>
      *
      * @param battlers - The battlers involved in this battle
      * @return the sorted battlers array from highest to lowest
      * */
    public Entity[] calculateGoOrder(Entity... battlers){

        // Bubble sort algorithm | Look into using the built-in java array sorting methods
        int index;
        for(int i = 0; i < battlers.length - 1; i++){
            boolean swapped = false;
            for(index = 0; index < battlers.length - i - 1; index++){
                int battlerSpeed = battlers[index].getEntityStatBlock().calcFullSpeed();
                int nextBattlerSpeed = battlers[index + 1].getEntityStatBlock().calcFullSpeed();

                // less than sorts from highest to lowest, so the fastest entity is first in the list
                if(battlerSpeed < nextBattlerSpeed){
                    Entity temp = battlers[index];
                    battlers[index] = battlers[index+1];
                    battlers[index+1] = temp;
                    swapped = true;
                }

                // Else, if they are equal in speed, choose randomly between them which to put first
                else if(battlerSpeed == nextBattlerSpeed){
                    Entity first = runRandomEntityChoice(battlers[index],battlers[index+1]);
                    if(first.equals(battlers[index+1])){
                        Entity temp = battlers[index];
                        battlers[index] = battlers[index+1];
                        battlers[index+1] = temp;
                        swapped = true;
                    }
                }

            }
            if(!swapped) break;
        }
        System.out.println(battlers[0] + " " + battlers[1]);
        return battlers;
    }

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

    /// attack entity method uses the take damage function and attack functions from the entity class
    private int attackEntity(Entity attacker, Entity target){
        return target.takeDamage(attacker.attack());
    }

    /// Checks if an entity is dead
    public boolean checkDeath(Entity queriedEntity){
        return queriedEntity.getEntityStatBlock().getStatsMap().get(Stats.CURRENT_HEALTH) <= 0;
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

        System.out.println(escapeChance);
        System.out.println(escapeRoll);

        if(escapeRoll <= escapeChance){
            System.out.println("RUN SUCCESS!");
        } else {
            System.out.println("RUN FAILED!");
        }
    }


}
