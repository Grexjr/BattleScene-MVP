package controller;

import model.btl.BattleState;
import model.ety.BattleChoice;
import model.ety.Entity;
import model.ety.Player;
import view.panels.TurnActionPanel;
import view.panels.BattleDisplayPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class TurnSet {

    private final Runnable onEnd;
    private final BattleState battleState;
    private final ArrayList<Entity> goOrder;
    private final TurnActionPanel actionSuite;
    private final BattleDisplayPanel displayer;

    private int goOrderNum;

    public TurnSet(BattleState state, BattleDisplayPanel display, Runnable endRun){
        this.onEnd = endRun;
        this.battleState = state;
        this.displayer = display;
        this.actionSuite = new TurnActionPanel();

        this.goOrder = new ArrayList<>(
                Arrays.asList(
                        this.battleState.calculateGoOrder(this.battleState.getBattlers())
                )
        );

        this.goOrderNum = 0;
        this.battleState.resetTemporaryValues(this.goOrder);

        this.actionSuite.toggleButtons(false);
        setUpActionListeners();


        runNextTurn();
    }

    // === GETTERS ===
    public TurnActionPanel getActionSuite() {
        return actionSuite;
    }

    private Entity getGoer(){return this.goOrder.get(this.goOrderNum);}

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

    private void incrementGoOrderNum(){this.goOrderNum += 1;}

    private void runNextTurn(){
        if(this.getGoer() instanceof Player){
            waitForPlayerInput();
            System.out.println("Wiating for player");
        } else {
            this.battleState.getEnemy().makeBattleChoice(BattleChoice.ATTACK); // TEMPORARY!!!
            executeEntityAction(getGoer(), getOther()); // TEMPORARY!!!
            runEndTurn();
        }
    }

    private void executeEntityAction(Entity executor, Entity other){ // Other will eventually be decided by choice
        switch(executor.getBattleChoice()){
            case BattleChoice.ATTACK -> onAttackChoice(executor,other);
            case BattleChoice.DEFEND -> onDefendChoice(executor);
            case BattleChoice.USE_ITEM -> onItemUseChoice(executor);
            case BattleChoice.RUN -> onRunChoice(executor,other);
            default -> System.out.println("ERROR"); // ERROR: throw error here
        }
    }

    private void waitForPlayerInput(){
        this.actionSuite.toggleButtons(true);
    }


    private void onAttackChoice(Entity attacker, Entity target){
        // Run the attack itself
        int damage = this.battleState.handleAttack(attacker,target);

        // Update UI
        printAttack(attacker,target,damage);
        this.displayer.updateStatDisplayer(target);

        // System log
        System.out.println(
                attacker.getEntityName() + " attacks " + target.getEntityName() +" for " + damage + " damage!"
        );
    }

    private void onDefendChoice(Entity defender){
        battleState.handleDefend(defender);

        // Update the UI
        printDefense(defender);
        this.displayer.updateStatDisplayer(defender);

        // Disable the buttons
    }

    private void onItemUseChoice(Entity user){
        this.battleState.handleItemUse(this.battleState.getPlayer());
        System.out.println("NOT YET IMPLEMENTED!");
    }

    private void onRunChoice(Entity runner, Entity... runFroms){
        battleState.handleRun(runner,runFroms[0]); // TEMP: will need to rework for multiple battlers
        new Timer(3000, _ -> {System.exit(0);}).start();
    }


    private void printAttack(Entity attacker, Entity target, int damage){
        this.displayer.print(String.format("%s Attacks %s for %d damage!",
                attacker.getEntityName(),
                target.getEntityName(),
                damage
        ));
    }

    private void printDefense(Entity defender){
        this.displayer.print(String.format("%s defends!",
                defender.getEntityName()));
    }

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

    private void runEndTurn(){
        incrementGoOrderNum();
        if(this.goOrderNum < this.goOrder.size()){
            runNextTurn();
        } else {
            this.onEnd.run();
        }
    }







}
