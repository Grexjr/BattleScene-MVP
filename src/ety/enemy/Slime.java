package ety.enemy;

import ety.StatBlock;

public class Slime extends Enemy{

    // === CONSTRUCTOR FOR SLIME ===
    public Slime(int level){
        super(
                "Slime",
                "A gooey, disgusting blob!",
                new StatBlock(
                        level,
                        level + 9,
                        level,
                        level - 1,
                        level - 1
                ));
    }

}
