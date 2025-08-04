package controller;

import model.bsc.BattleScene;
import model.ety.Entity;
import model.ety.Player;
import model.ety.enemy.Enemy;
import model.itm.Item;
import view.BattleDisplayPanel;

import javax.swing.*;
import java.awt.event.ActionListener;

public class BattleController {


    // === VARIABLES AND FIELDS ===
    private final BattleScene battleScene;
    private final BattleDisplayPanel battleDisplay;


    // === BATTLE CONTROLLER CONSTRUCTOR ===
    public BattleController(BattleScene bsc, BattleDisplayPanel view){
        this.battleScene = bsc;
        this.battleDisplay = view;
    }


    // === METHODS ===
    private void EntityAttack(Entity attacker, Entity target){}

    private void EntityDefend(Entity defender){}

    private void EntityItemUse(Entity user, Item usable){}

    private void EntityRun(Entity runner, Entity runFrom){}

}
