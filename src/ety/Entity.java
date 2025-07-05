package ety;

/*
 * This class is the abstract, non-concrete entity class that all entities (in this case slime and player, since
 * there are no others, extend from).
 * It provides all parameters for entities as well as most functionality for entities.
 */
public class Entity {
    // TODO: Use direct access rather than getters and setters for the methods in here -- refactoring

    // === VARIABLES AND FIELDS ===
    private final String entityName, entityDescription;
    private final StatBlock entityStatBlock;


    // === ENTITY CONSTRUCTOR ===
    public Entity(String name, String description, StatBlock statBlock){
        this.entityName = name;
        this.entityDescription = description;
        this.entityStatBlock = statBlock;
    }

    // === GETTERS AND SETTERS ===
    public String getEntityName() {return this.entityName;}

    public String getEntityDescription() {return this.entityDescription;}

    public StatBlock getEntityStatBlock() {return this.entityStatBlock;}


    // === OTHER METHODS ===

    // -- Basic Methods --
    // method that returns the resulting health from subtracting param from currentHealth
    private int calculateResultingHealth(int damage){
        return Math.max(this.getEntityStatBlock().getEntityCurrentHealth() - damage, 0);
    }

    // Method for enemy to take in a value and apply it as damage to its current health
    public void takeDamage(int damage) {
        this.getEntityStatBlock().setEntityCurrentHealth(calculateResultingHealth(damage));
    }

    // method that returns the next level
    private int getNextLevel(){
        return this.getEntityStatBlock().getEntityLevel() + 1;
    }

    // method that returns the previous level, but cannot go below 0
    private int getLastLevel(){
        return Math.max(this.getEntityStatBlock().getEntityLevel() - 1,0);
    }

    // method that increments the entity level by one
    public void incrementLevel(){
        this.getEntityStatBlock().setEntityLevel(getNextLevel());
    }

    // method that decrements the entity level by one
    public void decrementLevel(){
        this.getEntityStatBlock().setEntityLevel(getLastLevel());
    }


    // -- Stat Methods --
    // method that compares level to param and returns higher value
    public int compareLevel(int comparison){
        return Math.max(this.getEntityStatBlock().getEntityLevel(),comparison);
    }

    // method that compares max health to param and returns higher value
    public int compareMaxHealth(int comparison){
        return Math.max(this.getEntityStatBlock().getEntityMaxHealth(),comparison);
    }

    // method that compares current health to param and returns higher value
    public int compareCurrentHealth(int comparison){
        return Math.max(this.getEntityStatBlock().getEntityCurrentHealth(),comparison);
    }

    // method that compares attack to param and returns higher value
    public int compareAttack(int comparison){
        return Math.max(this.getEntityStatBlock().getEntityAttack(),comparison);
    }

    // method that compares defense to param and returns higher value
    public int compareDefense(int comparison){
        return Math.max(this.getEntityStatBlock().getEntityDefense(),comparison);
    }

    // method that compares speed to param and returns higher value
    public int compareSpeed(int comparison){
        return Math.max(this.getEntityStatBlock().getEntitySpeed(),comparison);
    }


    // -- Battle methods --



}
