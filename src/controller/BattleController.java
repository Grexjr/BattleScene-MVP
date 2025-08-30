package controller;

import model.btl.BattleState;
import model.btl.Round;
import model.ety.BattleChoice;
import model.ety.Entity;
import model.ety.Player;
import view.panels.*;

import javax.swing.*;

// just need to fix first turn not registering damage... nvm its a deeper issue
public class BattleController implements TurnListener {
    // === CONSTANTS ===
    private static final BattleBaseInteract BASE_INTERACT = new BattleBaseInteract();

    private final BattleState battleState;
    private final ContainerPanel guiContainer;
    private final BattleDisplayPanel battleDisplay;

    private int turnNum;
    private RoundController roundController;


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

        battleDisplay.updateStatDisplayers();

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
        battleDisplay.printToGUI(
                "Battle between " +
                        battleState.getPlayer().getEntityName() +
                        " and " +
                        battleState.getEnemy().getEntityName());
        runRound();
    }

    private void createRound(){
        this.turnNum++;
        this.battleDisplay.printToGUI(String.format("\nTurn %d begins!\n",this.turnNum));
        this.roundController = new RoundController(new Round(this.battleState),this);
        System.out.println("Round: " + this.roundController); // DEBUG
        replaceBattleInteract((BattleButtonPanel)this.roundController.getTurnPanel());
    }

    private void runRound(){
        createRound();
        System.out.println("Running " + this.roundController); // DEBUG
        this.roundController.runEntityTurn();
    }

    private void replaceBattleInteract(BattleButtonPanel replacer){
        this.guiContainer.setInteractPanel(replacer);
    }


    // Print methods
    /**
     * Prints an entity attacking another entity into the display text log
     * @param attacker the entity attacking
     * @param target the entity being attacked
     * @param damage the amount of damage done by the attacking entity
     * */
    private void printAttack(Entity attacker, Entity target, int damage){
        this.guiContainer.getDisplayer().printToGUI(String.format("%s Attacks %s for %d damage!",
                attacker.getEntityName(),
                target.getEntityName(),
                damage
        ));
        System.out.println(attacker.getEntityName()); // DEBUG
        System.out.println(target.getEntityName()); // DEBUG
        System.out.println(damage); // DEBUG
    }

    /**
     * Prints an entity defending in to the display text log
     * @param defender the entity doing the defending
     * */
    private void printDefense(Entity defender){
        this.guiContainer.getDisplayer().printToGUI(String.format("%s defends!",
                defender.getEntityName()));
    }



    // INTERFACE METHODS
    @Override
    public void runTurnExecute(Entity executor, Entity other, int damage){
        switch(executor.getBattleChoice()){
            case ATTACK -> printAttack(executor,other,damage);
            case DEFEND -> printDefense(executor);
            case USE_ITEM -> System.out.println("NOT YET IMPLEMENTED");
            case RUN -> System.out.println("NOT YET IMPLEMENTED");
            default -> System.out.println("ERROR!"); // ERROR; catch if this occurs
        }
        this.battleDisplay.updateStatDisplayers();
    }

    @Override
    public void runEntityDeath(Entity deadEntity){
        this.guiContainer.getDisplayer().printToGUI(String.format("%s is dead!\n",deadEntity.getEntityName()));
        this.battleDisplay.updateStatDisplayers();
        new Timer(2000,_ -> System.exit(0)).start();
    }

    @Override
    public void runTurnEnd(){

    }

    @Override
    public void runRoundEnd(){
        this.battleState.resetTemporaryValues(this.battleState.getBattlers());
        runRound();
    }

    @Override
    public void runEntityChoice(){
        // Swap interact panel to new entity choice panel, swap turn controller to it too
    }








}