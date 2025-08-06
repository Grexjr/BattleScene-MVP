package model.ety;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Spliterator;

/**
 * This class is the stat block for entities. It contains only a Hash Map, and is a separate class so that all stat
 * related calculations take place here instead of within the more general Entity class, and so they can have separate
 * to String values.
 * NOTE: It might be a good idea to keep an original version of the to String if I need to see the instance value or
 *    see the information without printing everything else. I'm not too sure, but that might be a toLog method.
 **********************************************************************************************************************/

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

    public void takeDamage(int damage){
        this.statsMap.replace(Stats.CURRENT_HEALTH,this.statsMap.get(Stats.CURRENT_HEALTH) - damage);
        if(this.statsMap.get(Stats.CURRENT_HEALTH) <= 0){
            this.statsMap.replace(Stats.CURRENT_HEALTH, 0);
        }
    }

    public void healHealth(int heal){
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

    // === COMPARE METHODS ===
    public int compareLevel(int comparedLevel){
        return this.statsMap.get(Stats.LEVEL) - comparedLevel;
    }

    public int compareMaxHealth(int comparedHealth){
        return this.statsMap.get(Stats.MAX_HEALTH) - comparedHealth;
    }

    public int compareCurrentHealth(int comparedHealth){
        return this.statsMap.get(Stats.CURRENT_HEALTH) - comparedHealth;
    }

    public int compareAttack(int comparedAttack){
        return this.statsMap.get(Stats.ATTACK) - comparedAttack;
    }

    public int compareDefense(int comparedDefense){
        return this.statsMap.get(Stats.DEFENSE) - comparedDefense;
    }

    public int compareSpeed(int comparedSpeed){
        return this.statsMap.get(Stats.SPEED) - comparedSpeed;
    }







    ///  To String for the Stat Block of the Entity
    @Override
    public String toString(){ //TODO: NEED A BETTER WAY TO DO THIS
        ArrayList<String> rawStatsStrings = new ArrayList<>();
        ArrayList<String> rawStatsValues = new ArrayList<>();

        for(Stats stat : this.statsMap.keySet()){
            String statString = stat.toString();

            rawStatsStrings.add(statString);
        }


        for(Stats stat : this.statsMap.keySet()){
            String valueString = this.statsMap.get(stat).toString();

            rawStatsValues.add(valueString);
        }

        String[] finalDisplay = new String[]{
                rawStatsStrings.get(0) + rawStatsValues.get(0),                      //Level
                "HEALTH: " + rawStatsValues.get(2) + "/" + rawStatsValues.get(1),    //Health
                rawStatsStrings.get(3) + rawStatsValues.get(3),                      //Attack
                rawStatsStrings.get(4) + rawStatsValues.get(4),                      //Defense
                rawStatsStrings.get(5) + rawStatsValues.get(5),                      //Speed
        }; //TODO: Add the temporary values to this printing... might make things complex

        return Arrays.toString(finalDisplay)
                .replace(",","\n")
                .replace("[","")
                .replace("]","")
                .trim();

    }


}
