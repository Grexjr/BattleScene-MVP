package controller;

/**
 * This enum represents the different phases battles can be in, which will be the main way in which program flow is
 * controlled by the buttons and swing GUI.
 * */
public enum BattlePhase {

    // === VALUES OF ENUM ===
    INITIALIZATION,
    INTRO_SCENE,
    DETERMINE_TURN_ORDER,
    START_TURN_SET,
    PLAYER_TURN,
    ENEMY_TURN,
    TURN_SET_ENDING,
    BATTLE_ENDING,
    BATTLE_EXITING,

    INVALID



}
