package tst;

import controller.BattleController;
import model.BattleScene;
import model.ety.Player;
import model.ety.enemy.Enemy;
import model.ety.enemy.Slime;
import controller.MainMenuController;
import view.guiparts.GameWindow;
import view.guiparts.TextLog;
import view.guiparts.buttonpanels.BattleButtonPanel;
import view.guis.BattleGUI;
import view.guis.MainMenuGUI;

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

    // TESTING THE MAIN RUN
    private static void runMainBattle(int enemyLevel){
        GameWindow gameWindow = new GameWindow(800,600,"ROGUE CRAWLER");
        TextLog battleLog = new TextLog();
        Player player = new Player("Play guy");
        Enemy slime = new Slime(enemyLevel);
        BattleScene bs = new BattleScene(player, slime);
        BattleController bc = new BattleController(bs,battleLog);

        gameWindow.add(bc.getBattlePanel());
        gameWindow.refresh();
    }

    // The tester main method
    public static void main(String[] args){

        runMainBattle(1);

        /*GameWindow gameWindow = new GameWindow(800,600,"ROGUE CRAWLER");
        MainMenuController mmc = new MainMenuController();
        MainMenuGUI mainMenu = new MainMenuGUI(mmc);

        gameWindow.add(mainMenu);
        gameWindow.refresh();*/

        //testGoFirstMethod(100000);


    }





}
