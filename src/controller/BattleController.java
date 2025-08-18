package controller;

import model.btl.BattleState;
import model.btl.TurnSet;
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
    private final TurnActionPanel battleInteract;

    private TurnSet currentTurnSet;


    // === BATTLE CONTROLLER CONSTRUCTOR ===
    public BattleController(BattleState state, BattleDisplayPanel view, TurnActionPanel interact){
        this.battleState = state;
        this.battleDisplay = view;
        this.battleInteract = interact;
        this.currentTurnSet = null;
    }






























































    /*public void runBattle(){
        initializeBattle();
        runBattleIntro();
        setCurrentTurnSet();
        runTurnSet();
    }

    private void initializeBattle(){
        if(this.battleState.getCurrentPhase() == BattlePhase.INITIALIZATION) {// Reset temporary values
            for (Entity battlers : this.battleState.getBattlers()) {
                this.battleState.resetTemporaryValues(battlers);
            }
            advancePhase(BattlePhase.INTRO_SCENE);
            setupActionListeners();
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

    private void runTurnSet(){
        if(this.battleState.getCurrentPhase() == BattlePhase.START_TURN_SET){
            this.currentTurnSet.setUpTurn();
        }
    }



    /**
     * Returns new turn set object
     * @param battlers - the list of battlers in this battle
     *
    private TurnSet createTurnSet(Entity... battlers){
        // return new turn set object with battlers parameter
      TurnSet turnSet = new TurnSet(
                this.battleState,
                this.battleDisplay,
                this.battleInteract,
                // battlers sorted in descending order based on their speeds
                this.battleState.calculateGoOrder(battlers)
        );
      System.out.println(turnSet);
      return turnSet;
    }


    private void advancePhase(BattlePhase phase){
        this.battleState.setCurrentPhase(phase);
    }

    private void setupActionListeners(){
        ArrayList<JButton> battleButtons = battleInteract.getButtonsList();

        battleButtons.getFirst().addActionListener(_ -> {
            Player player = this.battleState.getPlayer();
            Enemy enemy = this.battleState.getEnemy();
            player.makeBattleChoice(BattleChoice.ATTACK);
            runTurn(enemy);
        });
        battleButtons.get(1).addActionListener(_ ->{
            Player player = this.battleState.getPlayer();
            Enemy enemy = this.battleState.getEnemy();
            player.makeBattleChoice(BattleChoice.DEFEND);
            runTurn(enemy);
        });
        battleButtons.get(2).addActionListener(_ ->{
            Player player = this.battleState.getPlayer();
            Enemy enemy = this.battleState.getEnemy();
            player.makeBattleChoice(BattleChoice.USE_ITEM);
            runTurn(enemy);
        });
        battleButtons.get(3).addActionListener(_ -> {
            Player player = this.battleState.getPlayer();
            Enemy enemy = this.battleState.getEnemy();
            player.makeBattleChoice(BattleChoice.RUN);
            runTurn(enemy);
        });
    }

    private void runTurn(Entity reactor){
        this.currentTurnSet.runTurn(reactor);
        checkTurnSetOver();
    }


    public void checkTurnSetOver(){
        if(this.currentTurnSet.checkTurnSetDone()){
            setCurrentTurnSet();
            runTurnSet();
        }
    } */



}
