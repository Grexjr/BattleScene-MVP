package model.ety;

///  This class is an enum for which state of life an entity is in: alive or dead.
/// NOTE: This serves as a way to expand for future, in case more are needed; respawning, etc.
public enum LifeState {

    // === VALUES OF THE ENUM ===
    ALIVE("ALIVE"),
    DEAD("DEAD");


    // === VARIABLES OF ENUM ===
    private String label;


    // === CONSTRUCTOR ===
    LifeState(String title){
        this.label = title;
    }


    // === METHODS ===
    @Override
    public String toString(){
        return String.format("%s",this.label);
    }

}
