package model.ety;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Spliterator;

/**
 * This class is the stat block for entities. It contains only a Hash Map, and is a separate class so that all stat
 * related calculations take place here instead of within the more general Entity class, and so they can have separate
 * to String values.
 */
// NOTE: It might be a good idea to keep an original version of the to String if I need to see the instance value or
//  see the information without printing everything else. I'm not too sure, but that might be a toLog method.
public class StatBlock {

    // TODO: Should eventually be a Hash Map

    // === VARIABLES AND FIELDS ===
    private final EnumMap<Stats,Integer> statsMap;

    // === CONSTRUCTOR FOR STAT BLOCK ===
    public StatBlock(int level, int health, int attack, int defense, int speed){

        this.statsMap = new EnumMap<>(Stats.class){{
            put(Stats.LEVEL,level);
            put(Stats.MAX_HEALTH,health);
            put(Stats.CURRENT_HEALTH,health);
            put(Stats.ATTACK,attack);
            put(Stats.DEFENSE,defense);
            put(Stats.SPEED,speed);
            put(Stats.TEMP_HEALTH_BONUS,0);
            put(Stats.TEMP_ATTACK_BONUS,0);
            put(Stats.TEMP_DEFENSE_BONUS,0);
            put(Stats.TEMP_SPEED_BONUS,0);
        }};

    }

    // === GETTER ===
    public EnumMap<Stats,Integer> getStatsMap() {return this.statsMap;}

    // === METHODS ===
    public boolean isDead() {return this.statsMap.get(Stats.CURRENT_HEALTH) <= 0;}

    public void levelUp(){this.statsMap.replace(Stats.LEVEL,this.statsMap.get(Stats.LEVEL) + 1);}

    public void levelDown(){this.statsMap.replace(Stats.LEVEL,this.statsMap.get(Stats.LEVEL) - 1);}

    public void reduceCurrentHealth(int damage){
        this.statsMap.replace(Stats.CURRENT_HEALTH,this.statsMap.get(Stats.CURRENT_HEALTH) - damage);
        if(this.statsMap.get(Stats.CURRENT_HEALTH) <= 0){
            this.statsMap.replace(Stats.CURRENT_HEALTH, 0);
        }
    }

    public void increaseCurrentHealth(int heal){
        this.statsMap.replace(Stats.CURRENT_HEALTH,this.statsMap.get(Stats.CURRENT_HEALTH) + heal);
        if(this.statsMap.get(Stats.CURRENT_HEALTH) >= this.statsMap.get(Stats.MAX_HEALTH)){
            this.statsMap.replace(Stats.CURRENT_HEALTH,statsMap.get(Stats.MAX_HEALTH));
        }
    }

    public int calcFullHealth(){
        return this.statsMap.get(Stats.MAX_HEALTH) + this.statsMap.get(Stats.TEMP_HEALTH_BONUS);
    }

    public int calcFullAttack(){
        return this.statsMap.get(Stats.ATTACK) + this.statsMap.get(Stats.TEMP_ATTACK_BONUS);
    }

    public int calcFullDefense(){
        return this.statsMap.get(Stats.DEFENSE) + this.statsMap.get(Stats.TEMP_DEFENSE_BONUS);
    }

    public int calcFullSpeed(){
        return this.statsMap.get(Stats.SPEED) + this.statsMap.get(Stats.TEMP_SPEED_BONUS);
    }

    public void increaseTemporaryHealth(int increase){
        this.statsMap.replace(Stats.TEMP_HEALTH_BONUS,this.statsMap.get(Stats.TEMP_HEALTH_BONUS) + increase);
    }

    public void increaseTemporaryAttack(int increase){
        this.statsMap.replace(Stats.TEMP_ATTACK_BONUS,this.statsMap.get(Stats.TEMP_ATTACK_BONUS) + increase);
    }

    public void increaseTemporaryDefense(int increase){
        this.statsMap.replace(Stats.TEMP_DEFENSE_BONUS,this.statsMap.get(Stats.TEMP_DEFENSE_BONUS) + increase);
    }

    public void increaseTemporarySpeed(int increase){
        this.statsMap.replace(Stats.TEMP_SPEED_BONUS,this.statsMap.get(Stats.TEMP_SPEED_BONUS) + increase);
    }


    // === RESET METHODS ===
    public void resetTemporaryStats(){
        this.statsMap.replace(Stats.TEMP_HEALTH_BONUS,0);
        this.statsMap.replace(Stats.TEMP_ATTACK_BONUS,0);
        this.statsMap.replace(Stats.TEMP_DEFENSE_BONUS,0);
        this.statsMap.replace(Stats.TEMP_SPEED_BONUS,0);
    }

    // === COMPARE METHODS ===
    public int compareLevel(int comparedLevel){
        return this.statsMap.get(Stats.LEVEL) - comparedLevel;
    }

    public int compareBaseMaxHealth(int comparedHealth){
        return this.statsMap.get(Stats.MAX_HEALTH) - comparedHealth;
    }

    public int compareCurrentHealth(int comparedHealth){
        return this.statsMap.get(Stats.CURRENT_HEALTH) - comparedHealth;
    }

    public int compareBaseAttack(int comparedAttack){
        return this.statsMap.get(Stats.ATTACK) - comparedAttack;
    }

    public int compareBaseDefense(int comparedDefense){
        return this.statsMap.get(Stats.DEFENSE) - comparedDefense;
    }

    public int compareBaseSpeed(int comparedSpeed){
        return this.statsMap.get(Stats.SPEED) - comparedSpeed;
    }

    public int compareFullMaxHealth(int comparedHealth){
        return this.calcFullHealth() - comparedHealth;
    }

    public int compareFullAttack(int comparedAttack){
        return this.calcFullAttack() - comparedAttack;
    }

    public int compareFullDefense(int comparedDefense){
        return this.calcFullDefense() - comparedDefense;
    }

    public int compareFullSpeed(int comparedSpeed){
        return this.calcFullSpeed() - comparedSpeed;
    }

    ///  To String for the Stat Block of the Entity
    @Override
    public String toString(){
        int level = statsMap.get(Stats.LEVEL);
        int currentHealth = statsMap.get(Stats.CURRENT_HEALTH);
        int maxHealth = statsMap.get(Stats.MAX_HEALTH);
        int healthBonus = statsMap.get(Stats.TEMP_HEALTH_BONUS);
        int attack = statsMap.get(Stats.ATTACK);
        int attackBonus = statsMap.get(Stats.TEMP_ATTACK_BONUS);
        int defense = statsMap.get(Stats.DEFENSE);
        int defenseBonus = statsMap.get(Stats.TEMP_DEFENSE_BONUS);
        int speed = statsMap.get(Stats.SPEED);
        int speedBonus = statsMap.get(Stats.TEMP_SPEED_BONUS);

        return String.format("Level: %d%n%nHealth: %d/%d + %d%nAttack: %d + %d%nDefense: %d + %d%nSpeed: %d + %d",
                level,currentHealth,maxHealth,healthBonus,attack,attackBonus,defense,defenseBonus,speed,speedBonus
        );

    }


}
