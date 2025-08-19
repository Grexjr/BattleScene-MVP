package controller;

import model.btl.BattleState;
import view.panels.BattleBaseInteract;
import view.panels.BattleDisplayPanel;
import view.panels.ContainerPanel;


public class BattleController {

    private final BattleState battleState;
    private final ContainerPanel guiContainer;


    /**
     * Constructor for the battle controller which controls general battle flow. This includes initialization,
     * cutscenes, and turn sets (a set of player and enemy moves).
     * @param state - the battle state that this controller will control
     * @param owner - the container panel that owns this battle controller's GUI
     * */
    public BattleController(BattleState state, ContainerPanel owner){
        this.battleState = state;
        this.guiContainer = owner;
    }

    // === GETTERS ===
    public BattleState getBattleState() {
        return battleState;
    }

    public ContainerPanel getGuiContainer() {
        return guiContainer;
    }






}