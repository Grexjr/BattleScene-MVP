package ety;

import itm.Item;
import itm.healers.Healable;

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
    public Item getFromIndex(int index) { return this.inventoryContents.get(index); }

    public void put(Item item){
        this.inventoryContents.add(item);
    }

    public void remove(Item item){
        this.inventoryContents.remove(item);
    }

    public void removeFirst(){
        this.inventoryContents.removeFirst();
    }

    public void removeLast(){
        this.inventoryContents.removeLast();
    }

    public void removeAtIndex(int index){
        this.inventoryContents.remove(index);
    }

    public boolean checkEmpty(){
        return this.inventoryContents.isEmpty();
    }


    // --- Helper Methods ---
    // calculates the total weight of all items in the inventory
    public int calculateTotalWeight(){
        int totalWeight = 0;

        for(Item item : this.inventoryContents){
            totalWeight = totalWeight + item.getItemWeight();
        }

        return totalWeight;
    }

    // checks if the total weight is past the max allowed weight
    public boolean checkOverEncumbered(int weight){
        return weight >= this.inventoryMaxWeight;
    }




}
