package controller;

import model.btl.BattleState;
import model.ety.Entity;
import model.itm.Item;
import view.BattleDisplayPanel;

public class BattleController {


    // === VARIABLES AND FIELDS ===
    private final BattleState battleScene;
    private final BattleDisplayPanel battleDisplay;


    // === BATTLE CONTROLLER CONSTRUCTOR ===
    public BattleController(BattleState bsc, BattleDisplayPanel view){
        this.battleScene = bsc;
        this.battleDisplay = view;
    }


    // === METHODS ===
    private void EntityAttack(Entity attacker, Entity target){}

    private void EntityDefend(Entity defender){}

    private void EntityItemUse(Entity user, Item usable){}

    private void EntityRun(Entity runner, Entity runFrom){}

}
