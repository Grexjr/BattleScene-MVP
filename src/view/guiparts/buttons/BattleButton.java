package view.guiparts.buttons;

import controller.BattleController;

import java.util.function.Consumer;

public enum BattleButton {

    // === VALUES OF ENUM ===
    ATTACK(
            "Attack",
            BattleController::handlePlayerAttack
    ),
    DEFEND(
            "Defend",
            BattleController::handlePlayerDefend
    ),
    ITEM(
            "Item",
            BattleController::handlePlayerItem
    ),
    RUN(
            "Run",
            BattleController::handlePlayerRun
    );


    // === VARIABLES AND FIELDS ===
    private final String buttonDisplayName;
    private final Consumer<BattleController> buttonAction;


    // === CONSTRUCTOR FOR ENUM ===
    BattleButton(String buttonTitle, Consumer<BattleController> action){
        this.buttonDisplayName = buttonTitle;
        this.buttonAction = action;
    }


    // === METHODS FOR ENUM ===

    // --- Getters and Setters ---
    public String getButtonDisplayName() {return buttonDisplayName;}

    public Consumer<BattleController> getButtonAction() {return buttonAction;}


    // --- Button Actions ---
    public void performAction(BattleController controller){
        this.buttonAction.accept(controller);
    }





}
