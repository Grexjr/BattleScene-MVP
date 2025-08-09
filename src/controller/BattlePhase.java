package controller;

/**
 * This enum represents the different phases battles can be in, which will be the main way in which program flow is
 * controlled by the buttons and swing GUI.
 * */
public enum BattlePhase {

    // === VALUES OF ENUM ===
    INITIALIZATION,
    TEXT_EVENT,
    DETERMINE_TURN_ORDER,
    PLAYER_TURN,
    ENEMY_TURN,
    ENDING,
    EXITING,

    INVALID



}
