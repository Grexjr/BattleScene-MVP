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
    private int turnNum;


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

        battleDisplay.updateStatDisplayer(this.battleState.getPlayer()); // this fixes bug, but why does it happen?
        battleDisplay.updateStatDisplayer(this.battleState.getEnemy());

        owner.setPanels(display,BASE_INTERACT);

        this.turnNum = 0;
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
        this.turnNum++;
        this.battleDisplay.printToGUI("\nTurn " + this.turnNum + " begins!\n");
        this.currentTurnSet = new TurnSet(this.battleState,this.guiContainer,this::endTurnSet);
        replaceBattleInteract(this.currentTurnSet.getActionSuite());
    }

    public void endTurnSet(){
        System.out.println("Turn Set done!");
        createTurnSet();
    }



    private void replaceBattleInteract(BattleButtonPanel replacer){
        this.guiContainer.setInteractPanel(replacer);
    }









}