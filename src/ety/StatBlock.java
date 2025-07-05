package ety;

import static java.lang.Math.ceil;

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

    // -- Helper Methods -- | TODO: do all other stat modifications that might happen
    // method to calculate the defense modifier
    public int calculateDefenseMod() {return this.entityDefense + (int) Math.ceil(this.entityDefense / 2.0);}

    // method to get the effective defense
    public int getEffectiveDefense() {return this.entityDefense + this.tempDefenseMod;}







}
