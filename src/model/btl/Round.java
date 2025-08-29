package model.btl;

import model.ety.BattleChoice;
import model.ety.Entity;
import model.ety.Player;
import model.ety.enemy.Enemy;

import java.util.ArrayList;
import java.util.Arrays;

public class Round {

    private final ArrayList<Turn> turnsList;
    private final BattleState parentState;

    public Round(BattleState battle){
        this.parentState = battle;
        this.turnsList = createTurns(parentState.calculateGoOrder(parentState.getBattlers()));
    }

    public ArrayList<Turn> getTurnsList(){return this.turnsList;}

    public Entity retrieveEntity(int index){return this.turnsList.get(index).getTurnExecutor();}

    public Player retrievePlayer(){
        for(Turn turn : this.turnsList){
            if(turn.getTurnExecutor() instanceof Player){
                return (Player)turn.getTurnExecutor();
            }
        }
        return null;
    }

    public Enemy retrieveEnemy(){ // CAUTION! Only works with one enemy in the list
        for(Turn turn : this.turnsList){
            if(turn.getTurnExecutor() instanceof Enemy){
                return (Enemy)turn.getTurnExecutor();
            }
        }
        return null;
    }

    public Turn retrieveTurn(Entity query){
        for(Turn turn : this.turnsList){
            if(turn.getTurnExecutor().equals(query)){
                return turn;
            }
        }
        return null;
    }

    public void runTurn(int index, Entity target){
        System.out.println("Round.runTurn index: " + index); //DEBUG
        System.out.println("Turn: " + this.turnsList.get(index)); //DEBUG
        System.out.println("Entity of turn: " + this.turnsList.get(index).getTurnExecutor().getEntityName());
        this.turnsList.get(index).runTurn(target);
    }


    private ArrayList<Turn> createTurns(Entity[] sortedBattlers){
        ArrayList<Turn> listOfTurns = new ArrayList<Turn>();
        for(Entity e : sortedBattlers){
            Turn entityTurn = new Turn(e,parentState);
            listOfTurns.add(entityTurn);
        }
        return listOfTurns;
    }

}
