package controller;

import model.ety.BattleChoice;
import model.ety.Entity;

public interface TurnListener {

    void runTurnExecute(Entity executor, Entity other, int damage); // prints to the GUI

    void runTurnEnd(); // starts the next turn of the round

    void runRoundEnd(); // starts the next round, signals to bc to start next round

    void runEntityChoice(); // signals to bc to swap to entity choice panel
















}
