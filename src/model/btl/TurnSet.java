package model.btl;

import model.ety.BattleChoice;
import model.ety.Entity;
import model.ety.Player;
import view.panels.TurnActionPanel;
import view.panels.BattleDisplayPanel;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class TurnSet {

    private final Runnable onEnd;
    private final BattleState battleState;
    private final ArrayList<Entity> goOrder;


    public TurnSet(Runnable endRun, BattleState state){
        this.onEnd = endRun;
        this.battleState = state;
        this.goOrder = new ArrayList<>();
    }

    public int runFirstGoer(){

    }

    private Entity getFirstGoer(){
        return this.goOrder.getFirst();
    }



    private void executeTurn(Entity executor){

    }








}
