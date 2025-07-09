package ety;

import itm.Item;

import java.util.ArrayList;

public class Inventory {

    // === VARIABLES AND FIELDS ===
    private int inventoryMaxWeight;
    private final ArrayList<Item> inventoryContents;


    // === CONSTRUCTOR FOR INVENTORY ===
    public Inventory(int maxWeight){
        this.inventoryMaxWeight = maxWeight;
        this.inventoryContents = new ArrayList<Item>();
    }


    // === GETTERS AND SETTERS ===
    public int getInventoryMaxWeight() {return inventoryMaxWeight;}
    public void setInventoryMaxWeight(int inventoryMaxWeight) {this.inventoryMaxWeight = inventoryMaxWeight;}

    public ArrayList<Item> getInventoryContents() {return inventoryContents;}


    // === OTHER METHODS ===

    // --- Basic Methods ---
    public void put(Item item){
        this.inventoryContents.add(item);
    }

    public void remove(Item item){
        this.inventoryContents.remove(item);
    }

    public void removeAtIndex(int index){
        this.inventoryContents.remove(index);
    }


    // --- Helper Methods ---
    public int calculateTotalWeight(){
        int totalWeight = 0;

        for(Item item : this.inventoryContents){
            totalWeight = totalWeight; // item.getWeight
        }

        return totalWeight;
    }




}
