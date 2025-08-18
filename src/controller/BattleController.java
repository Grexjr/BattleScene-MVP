package controller;

import model.btl.BattleState;
import model.btl.TurnSet;
import model.ety.Entity;
import view.panels.TurnActionPanel;
import view.panels.BattleDisplayPanel;


/** The battle controller class that controls the flow of battle. Battle is broken up into "turn sets," which are just
 * sets of entity turns. One turn set is all the entities' turns in the battle state. Each turn set involves the
 * controller calculating who goes first, then running that entities turn, then running the turn of the entity who goes
 * after. Then, a new turn set is calculated.
 * */
//QUESTION: Should turn sets be their own object?
public class BattleController {
    // === CONSTANTS ===


    // === VARIABLES AND FIELDS ===
    private final BattleState battleState;
    private final BattleDisplayPanel battleDisplay;
    // TODO: Make the interactivity an interface so you can pass interactive panel into here too

    private TurnSet currentTurnSet;


    // === BATTLE CONTROLLER CONSTRUCTOR ===
    public BattleController(BattleState state, BattleDisplayPanel view){
        this.battleState = state;
        this.battleDisplay = view;

        this.currentTurnSet = null;
    }

    // GETTER
    public TurnSet getCurrentTurnSet(){return this.currentTurnSet;}


    public void runBattle(){
        initializeBattle();
        runBattleIntro();
        setCurrentTurnSet();
        runTurnSet(this.currentTurnSet);
    }


    private void initializeBattle(){
        if(this.battleState.getCurrentPhase() == BattlePhase.INITIALIZATION) {// Reset temporary values
            for (Entity battlers : this.battleState.getBattlers()) {
                this.battleState.resetTemporaryValues(battlers);
            }
            advancePhase(BattlePhase.INTRO_SCENE);
        } // ERROR: Catch if improper phase
    }

    private void runBattleIntro(){
        if(this.battleState.getCurrentPhase() == BattlePhase.INTRO_SCENE){// Print out the start of battle message
            this.battleDisplay.log("A battle has begun!");
            advancePhase(BattlePhase.DETERMINE_TURN_ORDER);
        } // ERROR: out of phase error
    }

    private void setCurrentTurnSet(){
        if(this.battleState.getCurrentPhase() == BattlePhase.DETERMINE_TURN_ORDER){
            this.currentTurnSet = createTurnSet(this.battleState.getBattlers());
            advancePhase(BattlePhase.START_TURN_SET);
        } // ERROR: out of phase error
    }

    private void runTurnSet(TurnSet turnSet){
        if(this.battleState.getCurrentPhase() == BattlePhase.START_TURN_SET){
            this.currentTurnSet.runTurn();
        }
    }

    private void advancePhase(BattlePhase phase){
        this.battleState.setCurrentPhase(phase);
    }



    /**
     * Returns new turn set object
     * @param battlers - the list of battlers in this battle
     */
    private TurnSet createTurnSet(Entity... battlers) {
        // return new turn set object with battlers parameter
        TurnSet turnSet = new TurnSet(
                this.battleState,
                this.battleDisplay,
                new TurnActionPanel(),
                // battlers sorted in descending order based on their speeds
                this.battleState.calculateGoOrder(battlers)
        );
        System.out.println(turnSet);
        return turnSet;
    }
}
