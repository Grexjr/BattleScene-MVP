package controller;

import model.btl.BattleState;
import model.ety.BattleChoice;
import model.ety.Entity;
import model.ety.Player;
import view.panels.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class TurnSet {

    private final Runnable onEnd;
    private final BattleState battleState;
    private final ArrayList<Entity> goOrder;
    private final TurnActionPanel actionSuite;
    private final ContainerPanel ownerPanel;
    private final BattleDisplayPanel battleDisplay;

    private int goOrderNum;

    /**
     * Constructor for the TurnSet that sets all defaults, resets all temporary values, sets up action listeners, and
     * creates the go Order based on a sorting algorithm.
     * @param state the battle state that gives the turn set its battlers
     * @param owner the container panel that contains the displayer
     * @param endRun the method that the TurnSet runs when it is finished (passed in by the battle controller)
     * */
    public TurnSet(BattleState state, ContainerPanel owner, Runnable endRun){
        // Set instance fields
        this.onEnd = endRun;
        this.battleState = state;
        this.ownerPanel = owner;
        this.actionSuite = new TurnActionPanel();
        this.battleDisplay = (BattleDisplayPanel) ownerPanel.getDisplayer(); // ERROR will need to catch if not true

        // Calculate the go order using bubble sort to sort into descending speeds
        this.goOrder = new ArrayList<>(
                Arrays.asList(
                        this.battleState.calculateGoOrder(this.battleState.getBattlers())
                )
        );

        // Set non-final variables and reset stats
        this.goOrderNum = 0;
        this.battleState.resetTemporaryValues(this.goOrder);

        // Deal with buttons; disable them at start before turn has been run, set up the listeners
        this.actionSuite.toggleButtons(false);
        setUpActionListeners();

        // Run the first turn of the turn set
        runNextTurn();
    }

    // === GETTERS ===
    /// @return the turn action panel that has the turn buttons (attack, defend, etc.) of the turn set
    public TurnActionPanel getActionSuite() {
        return actionSuite;
    }


    ///  @return the current goer in the turn order
    private Entity getGoer(){return this.goOrder.get(this.goOrderNum);}

    /// @return the other entity in the turn order (only works because there are two)
    private Entity getOther(){
        if(this.goOrderNum == 0){
            return this.goOrder.get(1);
        } else if(this.goOrderNum == 1){
            return this.goOrder.getFirst();
        } else {
            System.out.println("FAILURE: TURN SET ORDER NUM WRONG!!!");
            return this.goOrder.getFirst();
        }
    }

    /// Increments the go Order Num, which tracks which index of the go Order the turn is on
    private void incrementGoOrderNum(){this.goOrderNum += 1;}

    /// Runs the turn: conditional if player or enemy, if player waits for input, if enemy just attacks
    private void runNextTurn(){
        if(this.getGoer() instanceof Player){
            waitForPlayerInput();
            this.ownerPanel.getDisplayer().printToGUI("Waiting for player...\n");
        } else {
            this.battleState.getEnemy().makeBattleChoice(BattleChoice.ATTACK); // TEMPORARY!!!
            executeEntityAction(getGoer(), getOther()); // TEMPORARY!!!
            runEndTurn();
        }
    }

    /**
     * Executes the entity action based on the executor and the target, switching based on the executor's choice.
     * @param executor the entity executing the action
     * @param other the possible target of the executor's action
     * */
    private void executeEntityAction(Entity executor, Entity other){ // Other will eventually be decided by choice
        switch(executor.getBattleChoice()){
            case BattleChoice.ATTACK -> onAttackChoice(executor,other);
            case BattleChoice.DEFEND -> onDefendChoice(executor);
            case BattleChoice.USE_ITEM -> onItemUseChoice(executor);
            case BattleChoice.RUN -> onRunChoice(executor,other);
            default -> System.out.println("ERROR"); // ERROR: throw error here
        }
    }

    /// Activates the turn battle panel for the player to click buttons
    private void waitForPlayerInput(){
        this.actionSuite.toggleButtons(true);
    }

    /**
     * Runs an entity attacking another.
     * @param attacker the entity doing the attacking
     * @param target the target of the attacks
     * */
    private void onAttackChoice(Entity attacker, Entity target){
        // Run the attack itself
        int damage = this.battleState.handleAttack(attacker,target);

        // Update UI
        printAttack(attacker,target,damage);
        this.battleDisplay.updateStatDisplayers();

        // System log
        System.out.println(
                attacker.getEntityName() + " attacks " + target.getEntityName() +" for " + damage + " damage!"
        );
    }

    /**
     * Runs an entity defending.
     * @param defender the entity defending
     * */
    private void onDefendChoice(Entity defender){
        battleState.handleDefend(defender);

        // Update the UI
        printDefense(defender);
        this.battleDisplay.updateStatDisplayers();

        // Disable the buttons
    }

    /**
     * Runs an entity using an item.
     * @param user the entity using the item
     * */
    private void onItemUseChoice(Entity user){
        this.battleState.handleItemUse(this.battleState.getPlayer());
        System.out.println("NOT YET IMPLEMENTED!");
    }

    /**
     * Runs an entity running away from battle.
     * @param runner the entity running
     * @param runFroms the entities being run from
     * */
    private void onRunChoice(Entity runner, Entity... runFroms){
        battleState.handleRun(runner,runFroms[0]); // TEMP: will need to rework for multiple battlers
        new Timer(3000, _ -> {System.exit(0);}).start();
    }

    /// Sets up action listeners for the turn buttons with conditional in each to determine how to proceed
    private void setUpActionListeners(){
        ArrayList<JButton> buttonsList = this.actionSuite.getButtonsList();

        buttonsList.getFirst().addActionListener(_ ->{
            this.battleState.getPlayer().makeBattleChoice(BattleChoice.ATTACK);
            System.out.println(getGoer().getEntityName());
            executeEntityAction(getGoer(),getOther());
            System.out.println("Player attacks");
            runEndTurn();
        });
        buttonsList.get(1).addActionListener(_ -> {
            this.battleState.getPlayer().makeBattleChoice(BattleChoice.DEFEND);
            executeEntityAction(getGoer(),getOther());
            runEndTurn();
        });
        buttonsList.get(2).addActionListener(_ -> {
            this.battleState.getPlayer().makeBattleChoice(BattleChoice.USE_ITEM);
            executeEntityAction(getGoer(),getOther());
            runEndTurn();
        });
        buttonsList.getLast().addActionListener(_ -> {
            this.battleState.getPlayer().makeBattleChoice(BattleChoice.RUN);
            executeEntityAction(getGoer(),getOther());
            runEndTurn();
        });

    }

    ///  Runs the ending of an enemy turn, which checks if everyone in the go order has gone or if anyone is left
    private void runEndTurn(){
        incrementGoOrderNum();
        if(this.goOrderNum < this.goOrder.size()){
            runNextTurn();
        } else {
            endTurnSet();
        }
    }

    /**
     * Prints an entity attacking another entity into the display text log
     * @param attacker the entity attacking
     * @param target the entity being attacked
     * @param damage the amount of damage done by the attacking entity
     * */
    private void printAttack(Entity attacker, Entity target, int damage){
        this.ownerPanel.getDisplayer().printToGUI(String.format("%s Attacks %s for %d damage!",
                attacker.getEntityName(),
                target.getEntityName(),
                damage
        ));
    }

    /**
     * Prints an entity defending in to the display text log
     * @param defender the entity doing the defending
     * */
    private void printDefense(Entity defender){
        this.ownerPanel.getDisplayer().printToGUI(String.format("%s defends!",
                defender.getEntityName()));
    }

    ///  @return boolean value if entity is below or at 0 health
    private boolean checkIfDead(Entity queriedEntity){
        return queriedEntity.getEntityStatBlock().isDead();
    }

    /// Runs the on End runnable
    private void endTurnSet(){
        this.onEnd.run();
    }

}
