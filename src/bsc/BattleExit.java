package bsc;

public enum BattleExit {

    // === VALUES FOR HOW A BATTLE CAN EXIT ===
    // TODO: add codes as params?

    // Standard win/loss
    PLAYER_WIN,
    ENEMY_WIN,

    // Battle ends in other ways
    BATTLE_INTERRUPT,

    // Default value; no exit
    BATTLE_ACTIVE

}
