package model.ety;

public class StatBlock {

    // === VARIABLES AND FIELDS ===
    private int entityLevel, entityCurrentHealth, entityMaxHealth, entityAttack, entityDefense, entitySpeed;
    private int tempHealthMod, tempAttackMod, tempDefenseMod, tempSpeedMod;


    // === CONSTRUCTOR FOR STAT BLOCK ===
    public StatBlock(int level, int maxHealth, int attack, int defense, int speed){
        this.entityLevel = level;
        this.entityMaxHealth = maxHealth;
        this.entityCurrentHealth = maxHealth;
        this.entityAttack = attack;
        this.entityDefense = defense;
        this.entitySpeed = speed;
        this.tempHealthMod = 0;
        this.tempAttackMod = 0;
        this.tempDefenseMod = 0;
        this.tempSpeedMod = 0;
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
    public void setEntityMaxHealth(int health) {this.entityMaxHealth = health;}

    public int getTempHealthMod() {return tempHealthMod;}
    public void setTempHealthMod(int tempHealthMod) {this.tempHealthMod = tempHealthMod;}

    public int getTempAttackMod() {return tempAttackMod;}
    public void setTempAttackMod(int tempAttackMod) {this.tempAttackMod = tempAttackMod;}

    public int getTempDefenseMod() {return tempDefenseMod;}
    public void setTempDefenseMod(int tempDefenseMod) {this.tempDefenseMod = tempDefenseMod;}

    public int getTempSpeedMod() {return tempSpeedMod;}
    public void setTempSpeedMod(int tempSpeedMod) {this.tempSpeedMod = tempSpeedMod;}

    // === OTHER METHODS ===

    // -- Basic Methods -- | TODO: do all other stat modifications that might happen
    // method that returns the resulting health from subtracting param from currentHealth
    private int calculateResultingHealth(int damage){
        return Math.max(this.getEntityCurrentHealth() - damage, 0);
    }

    // Method for entity to take in a value and apply it as damage to its current health
    // NOTE: Clamped this value as of 7/9/25 commit in case of unintended behavior; clamps health no less than 0
    public void takeDamage(int damage) {
        this.setEntityCurrentHealth(Math.max(calculateResultingHealth(damage),0));
    }

    // Method for entity to calculate the resulting health by adding a value
    private int calculateHealthFromHeal(int heal) { return Math.max(this.getEntityCurrentHealth() + heal,0);}

    // Method for entity to take in a value and apply it as heal to its current health
    // NOTE: Clamped this value as of 7/9/25 commit in case of unintended behavior; sets health no greater than max
    public void takeHeal(int heal) {
        this.setEntityCurrentHealth(Math.min(calculateHealthFromHeal(heal),this.getEffectiveMaxHealth()));
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

    // method to reset all temporary stats
    public void resetTempStats(){
        this.tempHealthMod = 0;
        this.tempAttackMod = 0;
        this.tempDefenseMod = 0;
        this.tempSpeedMod = 0;
    }


    // -- Comparison Methods --
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
        return Math.max(this.getEffectiveDefense(),comparison);
    }

    // method that compares speed to param and returns higher value
    public boolean compareSpeedTo(int comparison){
        return this.getEffectiveSpeed() > comparison;
    }


    // -- Modifier Methods --
    // method to get the effective maxHealth
    public int getEffectiveMaxHealth() {return this.entityMaxHealth + this.tempHealthMod;}

    // method to get the effective attack
    public int getEffectiveAttack() {return this.entityAttack + this.tempAttackMod;}

    // method to get the effective defense
    public int getEffectiveDefense() {return this.entityDefense + this.tempDefenseMod;}

    // method to get the effective speed
    public int getEffectiveSpeed() {return this.entitySpeed + this.tempSpeedMod;}





}
