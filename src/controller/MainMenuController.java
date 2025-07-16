package controller;

import view.guis.MainMenuGUI;

public class MainMenuController {

    // === VARIABLES AND FIELDS ===
    private final MainMenuGUI mainMenuPanel;

    // === CONSTRUCTOR ===
    public MainMenuController(){
        this.mainMenuPanel = new MainMenuGUI(this);
    }

    // === GETTERS ===
    public MainMenuGUI getMainMenuPanel() {return mainMenuPanel;}


    // === BUTTON METHODS ===
    public void handleNewGame(){
        //TEMP
        System.out.println("Not yet implemented!");
    }

    public void handleContinue(){
        //TEMP
        System.out.println("Not yet implemented!");
    }

    public void handleSettings(){
        //TEMP
        System.out.println("Not yet implemented!");
    }

    public void handleQuit(){
        System.exit(0);
    }




}
