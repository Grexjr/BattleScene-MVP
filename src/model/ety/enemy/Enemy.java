package model.ety.enemy;

import model.ety.BattleChoice;
import model.ety.Entity;
import model.ety.StatBlock;

public class Enemy extends Entity {

    // === CONSTRUCTOR FOR ENEMY ===
    public Enemy(String name, String description, StatBlock statBlock){
        super(name, description, statBlock);
    }

    // === OTHER METHODS ===

    // -- Battle Methods --
    public BattleChoice makeBattleChoice(){
        //TODO: add enemy AI
        return BattleChoice.ATTACK;
    }

}
