package model.ety;

import model.itm.Item;

import java.util.EnumMap;
import java.util.Random;

public class Player extends Entity{

    // === VARIABLES AND FIELDS ===
    private final Inventory playerInventory;
    private int currentEXP, expForNextLevel;

    // === CONSTRUCTOR FOR PLAYER ===
    public Player(String name){
        super(
                name,
                "It's you!",
                new StatBlock(
                        1,
                        20,
                        1,
                        0,
                        1
                ));
        this.playerInventory = new Inventory(50);
        this.currentEXP = 0;
        this.expForNextLevel = calculateNextLevelEXP();
    }

    // === CONSTRUCTOR METHODS ===
    private int calculateNextLevelEXP(){
        int level = this.getEntityStatBlock().getStatsMap().get(Stats.LEVEL);

        // Some method for level growth; for now just linear
        // === CONSTANTS ===
        int expGrowthMod = 50;
        return level * expGrowthMod;
    }


    // === GETTERS AND SETTERS ===
    public Inventory getPlayerInventory() {return this.playerInventory;}


    // === OTHER METHODS ===
    public void gainEXP(int expGain){
        this.currentEXP = this.currentEXP + expGain;
    }



    /**
     * Method to level up the player. Randomly increases stats by a certain amount. Right now just 1-5.
     * */
    public void levelUp(){
        int upperBound = 5;
        int lowerBound = 1;
        Random statIncrease = new Random();
        EnumMap<Stats,Integer> stats = this.getEntityStatBlock().getStatsMap();

        stats.replace(Stats.LEVEL,stats.get(Stats.LEVEL) + 1);
        int healthIncrease = statIncrease.nextInt(lowerBound,upperBound);

        int oldHealth = stats.get(Stats.MAX_HEALTH);
        int newHealth = oldHealth + healthIncrease;
        stats.replace(Stats.MAX_HEALTH,newHealth);
        stats.replace(Stats.CURRENT_HEALTH,newHealth);

        int attackIncrease = statIncrease.nextInt(lowerBound,upperBound);

        int oldAttack = stats.get(Stats.ATTACK);
        int newAttack = oldAttack + attackIncrease;
        stats.replace(Stats.ATTACK,newAttack);

        int defenseIncrease = statIncrease.nextInt(lowerBound,upperBound);

        int oldDefense = stats.get(Stats.DEFENSE);
        int newDefense = oldDefense + defenseIncrease;
        stats.replace(Stats.DEFENSE,newDefense);

        int speedIncrease = statIncrease.nextInt(lowerBound,upperBound);

        int oldSpeed = stats.get(Stats.SPEED);
        int newSpeed = oldSpeed + speedIncrease;
        stats.replace(Stats.SPEED,newSpeed);

        this.expForNextLevel = calculateNextLevelEXP();
    }



    // -- Item methods --
    // method to use an item
    public void useItem(Item item){
        // TEMP: hard coded to just use healing items. Will need to change with expanded item infrastructure
        //this.getEntityStatBlock().takeHeal(item.getUsed());
        this.playerInventory.remove(item);
    }

    // -- Battle Methods --

}
