package ety;

public class Player extends Entity{

    // === VARIABLES AND FIELDS ===
    private final Inventory playerInventory;

    // === CONSTRUCTOR FOR PLAYER ===
    public Player(String name){
        super(
                name,
                "It's you!",
                new StatBlock(
                        1,
                        20,
                        1,
                        0,
                        1
                ));
        this.playerInventory = new Inventory(50);
    }


    // === GETTERS AND SETTERS ===
    public Inventory getPlayerInventory() {return playerInventory;}


    // === OTHER METHODS ===

    // -- Battle Methods --

}
