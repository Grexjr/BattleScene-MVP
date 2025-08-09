package model.btl;


/// Enum storing battle end codes, a.k.a. what status the battle ends with
public enum EndCode {

    // === VALUES OF THE ENUM ===

    PLAYER_LOSE,
    PLAYER_WIN,
    PLAYER_RUN,

    ENEMY_RUN,

    NOT_OVER,


    // System codes for improper battle termination or continuation
    INTERRUPTED,
    INVALID







}
