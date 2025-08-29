package model.btl;

import model.ety.BattleChoice;
import model.ety.Entity;

public class Turn {

    private final BattleState parentBattle;
    private final Entity turnExecutor;
    private BattleChoice executorChoice;

    private int damage;


    public Turn(Entity executor, BattleState battle){
        this.parentBattle = battle;
        this.turnExecutor = executor;
        this.executorChoice = BattleChoice.INVALID; // Maybe at first starts as invalid
    }

    public Entity getTurnExecutor(){return this.turnExecutor;}

    public BattleChoice getExecutorChoice(){return this.executorChoice;}

    public int getDamage(){return this.damage;}

    public void makeTurnChoice(BattleChoice choice){
        this.executorChoice = choice;
    }

    public void runTurn(Entity target){
        System.out.println("Turn.runTurn choice: " + this.executorChoice); //DEBUG
        this.executorChoice = this.turnExecutor.getBattleChoice();
        switch(this.executorChoice){
            case ATTACK -> {
                this.damage = this.parentBattle.handleAttack(this.turnExecutor, target);
                System.out.println("Turn damage val = " + this.damage);
                System.out.println(damage);
            }
            case DEFEND -> this.parentBattle.handleDefend(this.turnExecutor);
            case USE_ITEM -> this.parentBattle.handleItemUse(this.turnExecutor);
            case RUN -> this.parentBattle.handleRun(this.turnExecutor,target);
        }
    }

}
