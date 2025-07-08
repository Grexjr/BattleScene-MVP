package tst;

import bsc.BattleController;
import bsc.BattleScene;
import ety.Player;
import ety.enemy.Enemy;
import ety.enemy.Slime;
import gui.GameWindow;
import gui.parts.BattlePanel;

public class Tester {

    /* TODO: Tester method with param of slime level, does ~1000 runs of a first turn with expected values w lvl 1 plyr
           level 1 slime: 0% slime goes first expected
           level 2 slime: 50% slime goes first expected
           level 3slime: 100% slime goes first expected */

    // The tester main method
    public static void main(String[] args){

        GameWindow gameWindow = new GameWindow(800,600,"Game");
        Player player = new Player("Player");
        Enemy slime = new Slime(1);
        BattleScene bsc = new BattleScene(player,slime);

        BattleController bc = new BattleController(bsc);

        gameWindow.add(bc.getBattlePanel());

        gameWindow.refresh();

    }





}
