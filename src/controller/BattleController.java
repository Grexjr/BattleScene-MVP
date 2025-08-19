package controller;

import model.btl.BattleState;
import view.panels.BattleBaseInteract;
import view.panels.BattleDisplayPanel;


public class BattleController {

    private final BattleState battleState;
    private final BattleDisplayPanel battleDisplayer;
    private final BattleBaseInteract baseInteractor;


    /**
     * Constructor for the battle controller which controls general battle flow. This includes initialization,
     * cutscenes, and turn sets (a set of player and enemy moves).
     * @param state - the battle state that this controller will control
     * @param display - the display that this battle controller will control
     * */
    public BattleController(BattleState state, BattleDisplayPanel display){
        this.battleState = state;
        this.battleDisplayer = display;
        this.baseInteractor = new BattleBaseInteract();
    }

    // === GETTERS ===
    public BattleState getBattleState() {
        return battleState;
    }

    public BattleDisplayPanel getBattleDisplayer() {
        return battleDisplayer;
    }

    public BattleBaseInteract getBaseInteractor() {
        return baseInteractor;
    }






}