package model.ety;

import java.util.EnumMap;
import java.util.Random;

/**
 * This class is the abstract, non-concrete entity class that all entities (in this case slime and player, since
 * there are no others, extend from).
 * It provides all parameters for entities as well as most functionality for entities.
 */
public abstract class Entity {
    // TODO: Use direct access rather than getters and setters for the methods in here -- refactoring

    // === VARIABLES AND FIELDS ===
    private final String entityName, entityDescription;
    private final StatBlock entityStatBlock;
    private LifeState entityState;
    private BattleChoice battleChoice;


    // === ENTITY CONSTRUCTOR ===
    public Entity(String name, String description, StatBlock statBlock){
        this.entityName = name;
        this.entityDescription = description;
        this.entityStatBlock = statBlock;
        this.entityState = LifeState.ALIVE;
        this.battleChoice = BattleChoice.INVALID;
    }

    // === GETTERS AND SETTERS ===
    public String getEntityName() {return this.entityName;}

    public String getEntityDescription() {return this.entityDescription;}

    public StatBlock getEntityStatBlock() {return this.entityStatBlock;}

    public LifeState getEntityState() {return this.entityState;}

    public BattleChoice getBattleChoice() {return this.battleChoice;}


    // === ENTITY METHODS ===
    /**
     * Method for an entity to attack another entity. Returns an int that is the value of the damage done by this
     * entity.
     * */
    public int attack(){
        StatBlock stats = this.getEntityStatBlock();

        int finalAttack = stats.calcFullAttack();
        // Will be modified with anything else that increases attack; weapons, etc.

        return finalAttack;
    }

    /**
     * Method that calculates final damage based on resistances, statuses, etc. and then applies that to the entity's
     * stat block. Does not return anything, merely changes the stat block values.
     * */
    public int takeDamage(int damage){
        StatBlock stats = this.getEntityStatBlock();
        int damageReduction = stats.calcFullDefense();
        // need to also add armor

        int finalDamage = Math.max(damage - damageReduction,0);

        stats.reduceCurrentHealth(finalDamage);
        return finalDamage;
    }

    /**
     * Void method that sets the enemies temporary defense to a higher value if they guard. This value will be added in
     * other methods to calculate the full defense value, which will be used in all battle calculations. Right now,
     * it just sets that as the temporary value. If the entity's defense is 0, it sets the temporary value to 1.
     * */
    public void guard(){
        int defense = this.getEntityStatBlock().getStatsMap().get(Stats.DEFENSE);
        if(defense == 0){
            this.getEntityStatBlock().increaseTemporaryDefense(1);
        } else {
            this.getEntityStatBlock().increaseTemporaryDefense((int) Math.ceil(defense / 2.0));
        }
    }

    /// This method resets the guard and sets the entity's defense back to their standard amount.
    public void resetGuard(){
        this.getEntityStatBlock().getStatsMap().replace(Stats.TEMP_DEFENSE_BONUS,0);
    }

    /**
     * Method that will eventually be populated for the entity to use an item.
     * TODO: figure out handling of different item types. Enum?
     * */
    public void useItem(){}

    /**
     * Returns the double chance value of an entity being able to escape. It will be used in another method in battle
     * scene to return a boolean based on if runnerSpeed <= random between 1 and totalSpeed+1.
     * Currently, it just returns the double chance value.
     * If both speeds are 0, it returns 0.5 for an equal chance of escape or non-escape.
     */
    public double calculateEscapeChance(int opposingSpeed){
        StatBlock thisStatBlock = this.getEntityStatBlock();
        int runnerSpeed = thisStatBlock.calcFullSpeed();

        int totalSpeed = runnerSpeed + opposingSpeed;
        if(totalSpeed == 0) return 0.5;

        return (double) runnerSpeed / totalSpeed;
    }

    /// This method sets the entity's state to the input.
    // QUESTION: Should I just use a setter?
    public void changeState(LifeState state){
        this.entityState = state;
    }

    /// This method sets the entity's battle choice to the input.
    public void makeBattleChoice(BattleChoice choice){
        this.battleChoice = choice;
    }

    /// TO STRING OF ENTITY
    @Override
    public String toString(){
        // QUESTION: is this... readable and good?
        return String.format("%s%n%s%n%n%s%n%nState: %s",
                entityName,
                entityDescription,
                entityStatBlock,
                entityState
        );
    }

}
