package ety;

public class Player extends Entity{

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
    }


    // === OTHER METHODS ===

    // -- Battle Methods --

}
