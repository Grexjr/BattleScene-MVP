package controller;

import model.btl.BattleState;
import view.panels.BattleBaseInteract;
import view.panels.BattleButtonPanel;
import view.panels.BattleDisplayPanel;
import view.panels.ContainerPanel;


public class BattleController {
    // === CONSTANTS ===
    private static final BattleBaseInteract BASE_INTERACT = new BattleBaseInteract();

    private final BattleState battleState;
    private final ContainerPanel guiContainer;
    private final BattleDisplayPanel battleDisplay;

    private TurnSet currentTurnSet;


    /**
     * Constructor for the battle controller which controls general battle flow. This includes initialization,
     * cutscenes, and turn sets (a set of player and enemy moves).
     * @param state - the battle state that this controller will control
     * @param owner - the container panel that owns this battle controller's GUI
     * */
    public BattleController(BattleState state, BattleDisplayPanel display, ContainerPanel owner){
        this.battleState = state;
        this.guiContainer = owner;
        this.battleDisplay = display;

        owner.setPanels(display,BASE_INTERACT);
    }

    // === GETTERS ===
    public BattleState getBattleState() {
        return battleState;
    }

    public ContainerPanel getGuiContainer() {
        return guiContainer;
    }


    // === METHODS ===
    public void runBattle(){
        battleDisplay.log(
                "Battle between " +
                        battleState.getPlayer().getEntityName() +
                        " and " +
                        battleState.getEnemy().getEntityName());
        createTurnSet();
        System.out.println(this.currentTurnSet);
    }

    public void createTurnSet(){
        this.battleDisplay.log("TURN BEGIN!!!");
        this.currentTurnSet = new TurnSet(this.battleState,this.battleDisplay,this::endTurnSet);
        replaceBattleInteract(this.currentTurnSet.getActionSuite());
    }

    public void runTurnSet(){

    }

    public void endTurnSet(){
        System.out.println("Turn Set done!");
        createTurnSet();
    }



    private void replaceBattleInteract(BattleButtonPanel replacer){
        this.guiContainer.setInteractPanel(replacer);
    }









}