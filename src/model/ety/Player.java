package model.ety;

import model.itm.Item;

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
    public Inventory getPlayerInventory() {return this.playerInventory;}


    // === OTHER METHODS ===
    // -- Item methods --
    // method to use an item
    public void useItem(Item item){
        // TEMP: hard coded to just use healing items. Will need to change with expanded item infrastructure
        this.getEntityStatBlock().takeHeal(item.getUsed());
        this.playerInventory.remove(item);
    }

    // -- Battle Methods --

}
