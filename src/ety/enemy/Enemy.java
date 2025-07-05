package ety.enemy;

import ety.BattleChoice;
import ety.Entity;
import ety.StatBlock;

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
