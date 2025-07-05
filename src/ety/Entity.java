package ety;

/*
 * This class is the abstract, non-concrete entity class that all entities (in this case slime and player, since
 * there are no others, extend from).
 * It provides all parameters for entities as well as most functionality for entities.
 */
public class Entity {
    // TODO: Use direct access rather than getters and setters for the methods in here -- refactoring

    // === VARIABLES AND FIELDS ===
    private int entityLevel, entityCurrentHealth, entityAttack, entityDefense, entitySpeed;
    private final int entityMaxHealth;
    private final String entityName, entityDescription;


    // === ENTITY CONSTRUCTOR ===
    public Entity(String name, String description, int level, int maxHealth, int attack, int defense, int speed){
        this.entityName = name;
        this.entityDescription = description;
        this.entityLevel = level;
        this.entityMaxHealth = maxHealth;
        this.entityCurrentHealth = maxHealth;
        this.entityAttack = attack;
        this.entityDefense = defense;
        this.entitySpeed = speed;
    }

    // === GETTERS AND SETTERS ===
    public int getEntityLevel() {return this.entityLevel;}
    public void setEntityLevel(int level) {this.entityLevel = level;}

    public int getEntityCurrentHealth() {return this.entityCurrentHealth;}
    public void setEntityCurrentHealth(int health) {this.entityCurrentHealth = health;}

    public int getEntityAttack() {return this.entityAttack;}
    public void setEntityAttack(int attack) {this.entityAttack = attack;}

    public int getEntityDefense() {return this.entityDefense;}
    public void setEntityDefense(int defense) {this.entityDefense = defense;}

    public int getEntitySpeed() {return this.entitySpeed;}
    public void setEntitySpeed(int speed) {this.entitySpeed = speed;}

    public int getEntityMaxHealth() {return this.entityMaxHealth;}

    public String getEntityName() {return this.entityName;}

    public String getEntityDescription() {return this.entityDescription;}


    // === OTHER METHODS ===

    // -- Basic Methods --
    // method that returns the resulting health from subtracting param from currentHealth
    private int calculateResultingHealth(int damage){
        return Math.max(this.getEntityCurrentHealth() - damage, 0);
    }

    // Method for enemy to take in a value and apply it as damage to its current health
    public void takeDamage(int damage) {
        this.setEntityCurrentHealth(calculateResultingHealth(damage));
    }

    // method that returns the next level
    private int getNextLevel(){
        return this.getEntityLevel() + 1;
    }

    // method that returns the previous level, but cannot go below 0
    private int getLastLevel(){
        return Math.max(this.getEntityLevel() - 1,0);
    }

    // method that increments the entity level by one
    public void incrementLevel(){
        this.setEntityLevel(getNextLevel());
    }

    // method that decrements the entity level by one
    public void decrementLevel(){
        this.setEntityLevel(getLastLevel());
    }


    // -- Stat Methods --
    // method that compares level to param and returns higher value
    public int compareLevel(int comparison){
        return Math.max(this.getEntityLevel(),comparison);
    }

    // method that compares max health to param and returns higher value
    public int compareMaxHealth(int comparison){
        return Math.max(this.getEntityMaxHealth(),comparison);
    }

    // method that compares current health to param and returns higher value
    public int compareCurrentHealth(int comparison){
        return Math.max(this.getEntityCurrentHealth(),comparison);
    }

    // method that compares attack to param and returns higher value
    public int compareAttack(int comparison){
        return Math.max(this.getEntityAttack(),comparison);
    }

    // method that compares defense to param and returns higher value
    public int compareDefense(int comparison){
        return Math.max(this.getEntityDefense(),comparison);
    }

    // method that compares speed to param and returns higher value
    public int compareSpeed(int comparison){
        return Math.max(this.getEntitySpeed(),comparison);
    }


    // -- Battle methods --



}
