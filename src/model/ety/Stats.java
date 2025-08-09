package model.ety;

/***********************************************************************************************************************
 * This enum is the stats for enemies to make them easier to refer to in the coming stat block hash map.
 *
 *
 **********************************************************************************************************************/

public enum Stats {

    // === VALUES FOR ENUM ===

    // basic stats
    LEVEL("LEVEL: "),
    MAX_HEALTH("UNUSED "),
    CURRENT_HEALTH("HEALTH: "),
    ATTACK("ATTACK: "),
    DEFENSE("DEFENSE: "),
    SPEED("SPEED: "),

    // temporary stats | NOTE: these likely won't be printed in the final version
    TEMP_HEALTH_BONUS("UNUSED "),
    TEMP_ATTACK_BONUS("UNUSED "),
    TEMP_DEFENSE_BONUS("UNUSED "),
    TEMP_SPEED_BONUS("UNUSED ");

    // === VARIABLES AND FIELDS ===
    private final String label;

    // === CONSTRUCTOR ===
    Stats(String title){
        this.label = title;
    }

    // === METHODS ===
    public String toString(){
        return this.label;
    }


}
