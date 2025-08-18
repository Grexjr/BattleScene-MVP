package model.btl;

import model.ety.Entity;
import view.panels.TurnActionPanel;
import view.panels.BattleDisplayPanel;

import java.util.ArrayList;
import java.util.Arrays;

public class TurnSet {

    private final BattleState battleState;
    private final BattleDisplayPanel battleDisplay;
    private final TurnActionPanel battleInteract;

    private final ArrayList<Entity> goOrder;

    // TODO: Battle state does calculation and adds goers to arraylist. Passes that in here. This uses that

    public TurnSet(BattleState state, BattleDisplayPanel viewer, TurnActionPanel interactor, Entity... goers) {
        this.battleState = state;
        this.battleDisplay = viewer;
        this.battleInteract = interactor;
        this.goOrder = new ArrayList<Entity>();
        goOrder.addAll(Arrays.asList(goers)); // Intellij suggestion: TODO: Learn this
    }


    // === GETTERS ===
    public ArrayList<Entity> getGoOrder(){return this.goOrder;}

























































    /*public void setUpTurn(){
        if(this.battleState.getCurrentPhase() == BattlePhase.PLAYER_TURN){
            this.battleInteract.toggleButtons(true); // step where player must select
        } else if(this.battleState.getCurrentPhase() == BattlePhase.ENEMY_TURN){
            this.battleState.getEnemy().makeBattleChoice(BattleChoice.ATTACK); // temporary; enemy always attacks
            runTurn(this.battleState.getPlayer());
        }
    }

    /// Runs the turn of the first in the go order, then removes them
    public void runTurn(Entity reactor){ // TEMP: only supports one, eventually with choice will support many
        executeEntityTurn(this.goOrder.getFirst(),reactor);
    }

    public boolean checkTurnSetDone(){
        if(isTurnSetDone()){
            advanceTurnPhase(BattlePhase.DETERMINE_TURN_ORDER);
            return true;
        } else {
            if(this.goOrder.getFirst() instanceof Player){
                advanceTurnPhase(BattlePhase.PLAYER_TURN);
            } else {
                advanceTurnPhase(BattlePhase.ENEMY_TURN);
            }
            setUpTurn();
            return false;
        }
    }

    private void executeEntityTurn(Entity chooser, Entity other){
        switch(chooser.getBattleChoice()){
            case ATTACK -> onAttackChoice(chooser, other);
            case DEFEND -> onDefendChoice(chooser);
            case USE_ITEM -> onItemUseChoice(chooser);
            case RUN -> onRunChoice(chooser, other);
        }
        this.goOrder.remove(chooser);
    }

    private boolean isTurnSetDone(){
        return this.goOrder.isEmpty();
    }

    private void onAttackChoice(Entity attacker, Entity target){
        // Run the attack itself
        int damage = this.battleState.handleAttack(attacker,target);

        // Update UI
        printAttack(attacker,target,damage);
        this.battleDisplay.updateStatDisplayer(target);

        // System log
        System.out.println(
                attacker.getEntityName() + " attacks " + target.getEntityName() +" for " + damage + " damage!"
        );
        this.battleInteract.toggleButtons(false);
    }

    private void onDefendChoice(Entity defender){
        battleState.handleDefend(defender);

        // Update the UI
        printDefense(defender);
        this.battleDisplay.updateStatDisplayer(defender);

        // Disable the buttons
        this.battleInteract.toggleButtons(false);
    }

    private void onItemUseChoice(Entity user){
        this.battleState.handleItemUse(this.battleState.getPlayer());
        this.battleInteract.toggleButtons(false);
        System.out.println("NOT YET IMPLEMENTED!");
    }

    private void onRunChoice(Entity runner, Entity... runFroms){
        battleState.handleRun(runner,runFroms[0]); // TEMP: will need to rework for multiple battlers
        this.battleInteract.toggleButtons(false);
        new Timer(3000, _ -> {System.exit(0);}).start();
    }

    private void printAttack(Entity attacker, Entity target, int damage){
        this.battleDisplay.print(String.format("%s Attacks %s for %d damage!",
                attacker.getEntityName(),
                target.getEntityName(),
                damage
        ));
    }

    private void printDefense(Entity defender){
        this.battleDisplay.print(String.format("%s defends!",
                defender.getEntityName()));
    }

    private void advanceTurnPhase(BattlePhase phase){
        this.battleState.setCurrentPhase(phase);
    }

















































    /*public void runTurnSet() {
        for (Entity goer : this.goOrder) {
            if (goer instanceof Player) {
                this.battleState.setPlayerTurn(true);
                runEntityTurn(goer,this.battleState.getEnemy());
            } else{
                runEntityTurn(goer,this.battleState.getPlayer());
            } // TEMP: A big gerry-rigged but we do what we can
        }
    }

    public void executeBattleChoice(Entity chooser, BattleChoice choice, Entity other){
        // switch case based on what the choice is
        switch(choice){
            case ATTACK -> onAttackChoice(chooser,other);
            case DEFEND -> onDefendChoice(chooser);
            case USE_ITEM -> onItemUseChoice(chooser);
            case RUN -> onRunChoice(chooser,other); // May need to rework this for multiple battlers
        }
    }

    private void runEntityTurn(Entity goer, Entity... others){
        goer.resetGuard();
        if(goer instanceof Player){
            advanceTurnPhase(BattlePhase.PLAYER_TURN);
            this.battleInteract.toggleButtons(true);
        } else {
            if(!this.battleState.getPlayerTurn()) {
                advanceTurnPhase(BattlePhase.ENEMY_TURN);
                goer.makeBattleChoice(BattleChoice.ATTACK);
                executeBattleChoice(goer, goer.getBattleChoice(), others[0]); //TEMP
            }
        }
    }


























































    /*public void onAttackChoice(Entity attacker, Entity target){
        // Run the attack itself
        int damage = this.battleState.handleAttack(attacker,target);

        // Update UI
        printAttack(attacker,target,damage);
        this.battleDisplay.updateStatDisplayer(target);

        // System log
        System.out.println(
                attacker.getEntityName() + " attacks " + target.getEntityName() +" for " + damage + " damage!"
        );

        endEntityTurn(attacker);
    }

    public void onDefendChoice(Entity defender){


        endEntityTurn(defender);
    }

    public void onItemChoice(Entity user){


        endEntityTurn(user);
    }

    public void onRunChoice(Entity runner, Entity runFrom){

    }







    private void advanceTurnPhase(Entity goer){
        if(goer instanceof Player){
            this.battleState.setCurrentPhase(BattlePhase.PLAYER_TURN);
        }
    }

    private void endEntityTurn(Entity ender){
        this.goOrder.remove(ender);

            // Turn off player buttons
            this.battleInteract.toggleButtons(false);


            if(!this.goOrder.isEmpty()){

                // If enemy must still make turn, set to enemy turn
                this.battleState.setCurrentPhase(BattlePhase.ENEMY_TURN);
                runEntityTurn(this.goOrder.getFirst());
            } else{

                // Otherwise go back to determining turn order
                this.battleState.setCurrentPhase(BattlePhase.DETERMINE_TURN_ORDER);
            }
    }



    */

}
