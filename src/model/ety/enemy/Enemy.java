package model.ety.enemy;

import model.ety.BattleChoice;
import model.ety.Entity;
import model.ety.StatBlock;
import model.ety.Stats;

import java.util.Random;

public class Enemy extends Entity {

    // === VARIABLES AND FIELDS ===
    private final int expAmount;
    private final int expMod;

    // === CONSTRUCTOR FOR ENEMY ===
    public Enemy(String name, String description, StatBlock statBlock, int expMod){
        super(name, description, statBlock);
        this.expMod = expMod;
        this.expAmount = calculateEXP();
    }

    // === GETTERS ===
    public int getEXPAmount(){return this.expAmount;}

    // === OTHER METHODS ===
    private int calculateEXP(){
        StatBlock stats = this.getEntityStatBlock();
        int minEXP = stats.getStatsMap().get(Stats.LEVEL);
        int maxEXP = minEXP * this.expMod;

        Random rand = new Random();
        return rand.nextInt(minEXP,maxEXP);
    }

    public void calcEnemyBattleChoice(){
        //TODO: Enemy AI
        this.makeBattleChoice(BattleChoice.ATTACK);
    }

}
