package controller;

import model.btl.Round;
import model.ety.BattleChoice;
import model.ety.Entity;
import model.ety.Player;
import view.ButtonInputtable;
import view.panels.TurnActionPanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

// NEED TO REMEMBER; LISTENER DOESNT ACTUALLY DO ANYTHING EXCEPT UPDATE GUI, NEED TO STILL RUN THE TURN HERE TOO!

public class RoundController{

    private final Round round;
    private final TurnListener listener;
    private ButtonInputtable turnPanel;
    private int turnIndexNum;

    public RoundController(Round round, TurnListener listener){
        this.round = round;
        this.listener = listener;
        this.turnPanel = new TurnActionPanel();
        setUpTurnActionListeners((TurnActionPanel)this.turnPanel); // CAST!!! make sure always turn action panel
        this.turnIndexNum = 0;
        this.turnPanel.toggleButtons(false);
    }

    public ButtonInputtable getTurnPanel(){return this.turnPanel;}

    public void runEntityTurn(){
        calculateEntityTurn(turnIndexNum);
    }

    private Entity getOther(){
        if(this.turnIndexNum == 0){
            return this.round.retrieveEntity(1);
        } else if(this.turnIndexNum == 1){
            return this.round.retrieveEntity(0);
        } else {
            System.out.println("FAILURE: TURN SET ORDER NUM WRONG!!!"); // ERROR
            return this.round.retrieveEntity(0);
        }
    }

    private void calculateEntityTurn(int turnIndex){
        if(this.round.getTurnsList().get(turnIndex).getTurnExecutor() instanceof Player){
            waitForPlayerInput();
        } else {
            // Temporary, set enemy AI to attack always
            this.round.retrieveEnemy().makeBattleChoice(BattleChoice.ATTACK);

            // Run the turn with the battle choice
            this.round.runTurn(turnIndex,this.round.retrievePlayer());

            // Call upon the battle controller to update GUI
            listener.runTurnExecute(
                    round.retrieveEntity(turnIndex),
                    getOther(),
                    round.retrieveTurn(round.retrieveEnemy()).getDamage()
            );
            runEndTurn();
        }
    }


    private void waitForPlayerInput(){
        this.turnPanel.toggleButtons(true);
    }

    private void runEndTurn(){
        listener.runTurnEnd();
        incrementTurnIndex();
        if(this.turnIndexNum < this.round.getTurnsList().size()){
            runEntityTurn();
        } else {
            endRound();
        }
    }

    private void endRound(){
        listener.runRoundEnd();
    }

    private void incrementTurnIndex(){this.turnIndexNum += 1;}

    private void setUpTurnActionListeners(TurnActionPanel turnActionSuite){
        ArrayList<JButton> buttons = turnActionSuite.getButtonsList();

        for(JButton b : buttons){
            b.addActionListener(_ -> {

                // Sets the battle choice to the selection
                switch(b.getText()){
                    case "ATTACK" -> round.retrievePlayer().makeBattleChoice(BattleChoice.ATTACK);
                    case "DEFEND" -> round.retrievePlayer().makeBattleChoice(BattleChoice.DEFEND);
                    case "ITEM" -> round.retrievePlayer().makeBattleChoice(BattleChoice.USE_ITEM);
                    case "RUN" -> round.retrievePlayer().makeBattleChoice(BattleChoice.RUN);
                    default -> round.retrievePlayer().makeBattleChoice(BattleChoice.INVALID);
                }

                // Runs the turn with that battle choice
                this.round.runTurn(turnIndexNum,this.round.retrieveEnemy());
                System.out.println("RoundController damage: " +                         // DEBUG
                        this.round.retrieveTurn(round.retrievePlayer()).getDamage()); // DEBUG

                // Call upon battle controller to update GUI
                listener.runTurnExecute(
                        round.retrievePlayer(),
                        round.retrieveEnemy(), // TEMP
                        round.retrieveTurn(round.retrievePlayer()).getDamage() // May not work as intended
                );

                // End the turn
                runEndTurn();
            });
        }
    }

}
