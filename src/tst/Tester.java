package tst;

import bsc.BattleController;
import bsc.BattleScene;
import ety.Player;
import ety.enemy.Enemy;
import ety.enemy.Slime;
import gui.GameWindow;
import gui.parts.BattlePanel;
import itm.healers.Healable;
import itm.healers.HealingItem;

public class Tester {

    // output color variables!
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";


    // Testing who goes first | TODO: REFACTOR!
    public static void testGoFirstMethod(int iterations){
        Slime slime = new Slime(1);
        Player player = new Player("Test Guy");
        BattleScene bsc = new BattleScene(player,slime);

        // Testing variables
        int playerWins = 0;
        int slimeWins = 0;

        // Test 1: slime with lower speed than player (1)
        slime.getEntityStatBlock().setEntitySpeed(0);

        for(int i = 0; i <= iterations; i++){
            if(bsc.determineWhoGoesFirst(player,slime) instanceof Player){
                playerWins++;
            } else {
                slimeWins++;
            }
        }

        // TEST 1 RESULTS
        System.out.println("TEST ONE RESULTS: ");
        System.out.printf("%d player wins\n", playerWins);
        System.out.printf("%d slime wins\n",slimeWins);

        double playerPercent = ((double) playerWins / (playerWins + slimeWins)) * 100;
        System.out.printf("%f player win percent\n",playerPercent);


        if(playerPercent == 100.00){
            System.out.println(ANSI_GREEN + "SUCCESSFUL TEST" + ANSI_RESET);
        } else{
            System.out.println(ANSI_RED + "FAILURE" + ANSI_RESET);
        }

        // TEST 2: slime with equal speed to player (1)
        playerWins = 0;
        slimeWins = 0;
        slime.getEntityStatBlock().setEntitySpeed(1);

        for(int i = 0; i <= iterations; i++){
            if(bsc.determineWhoGoesFirst(player,slime) instanceof Player){
                playerWins++;
            } else {
                slimeWins++;
            }
        }

        // TEST 2 RESULTS
        System.out.println("TEST TWO RESULTS: ");
        System.out.printf("%d player wins\n", playerWins);
        System.out.printf("%d slime wins\n",slimeWins);

        double playerPercent2 = ((double) playerWins / (playerWins + slimeWins)) * 100;
        System.out.printf("%f player win percent\n",playerPercent2);


        if(49.00 < playerPercent2 && playerPercent2 < 51.00){
            System.out.println(ANSI_GREEN + "SUCCESSFUL TEST" + ANSI_RESET);
        } else{
            System.out.println(ANSI_RED + "FAILURE" + ANSI_RESET);
        }

        // TEST 3: slime with speed greater than player
        playerWins = 0;
        slimeWins = 0;
        slime.getEntityStatBlock().setEntitySpeed(2);

        for(int i = 0; i <= iterations; i++){
            if(bsc.determineWhoGoesFirst(player,slime) instanceof Player){
                playerWins++;
            } else {
                slimeWins++;
            }
        }

        // TEST 3 RESULTS
        System.out.println("TEST THREE RESULTS: ");
        System.out.printf("%d player wins\n", playerWins);
        System.out.printf("%d slime wins\n",slimeWins);

        double playerPercent3 = ((double) playerWins / (playerWins + slimeWins)) * 100;
        System.out.printf("%f player win percent\n",playerPercent3);


        if(playerPercent3 == 0.00){
            System.out.println(ANSI_GREEN + "SUCCESSFUL TEST" + ANSI_RESET);
        } else{
            System.out.println(ANSI_RED + "FAILURE" + ANSI_RESET);
        }

    }

    // The tester main method
    public static void main(String[] args){

        GameWindow gameWindow = new GameWindow(800,600,"Game");
        Player player = new Player("Player");
        player.getPlayerInventory().put(new Healable(HealingItem.SMALL_HEALTH_POTION));
        Enemy slime = new Slime(5);
        System.out.println(player.getPlayerInventory().getInventoryContents());
        BattleScene bsc = new BattleScene(player,slime);

        BattleController bc = new BattleController(bsc);

        gameWindow.add(bc.getBattlePanel());

        gameWindow.refresh();

        //testGoFirstMethod(100000);


    }





}
