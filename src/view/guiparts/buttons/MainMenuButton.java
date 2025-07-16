package view.guiparts.buttons;

import controller.MainMenuController;

import java.util.function.Consumer;

public enum MainMenuButton {

    // === VALUES OF ENUM ===
    NEW_GAME(
            "NEW GAME",
            MainMenuController::handleNewGame
    ),
    CONTINUE(
            "CONTINUE",
            MainMenuController::handleContinue
    ),
    SETTINGS(
            "SETTINGS",
            MainMenuController::handleSettings
    ),
    QUIT(
            "QUIT GAME",
            MainMenuController::handleQuit
    );


    // === FIELDS FOR THE BUTTONS ===
    private final String buttonDisplayName;
    private final Consumer<MainMenuController> buttonAction;

    // === CONSTRUCTOR FOR THE BUTTONS ===
    MainMenuButton(String title, Consumer<MainMenuController> action){
        this.buttonDisplayName = title;
        this.buttonAction = action;
    }


    // === METHODS FOR ENUM ===

    // --- Getters and Setters ---
    public String getButtonDisplayName() {return buttonDisplayName;}

    public Consumer<MainMenuController> getButtonAction() {return buttonAction;}


    // --- Button Actions ---
    public void performAction(MainMenuController mmc){
        this.buttonAction.accept(mmc);
    }






}
