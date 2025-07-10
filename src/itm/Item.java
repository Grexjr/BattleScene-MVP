package itm;

public abstract class Item {

    // === VARIABLES AND FIELDS ===
    private final String itemName, itemDesc;
    private final int itemWeight, itemCost, itemSellPrice;


    // === CONSTRUCTOR ===
    public Item(String name, String desc, int weight, int cost){
        this.itemName = name;
        this.itemDesc = desc;
        this.itemWeight = weight;
        this.itemCost = cost;
        this.itemSellPrice = (int)(cost * 0.75);
    }


    // === GETTERS AND SETTERS ===
    public String getItemName() {return itemName;}

    public String getItemDesc() {return itemDesc;}

    public int getItemWeight() {return itemWeight;}

    public int getItemCost() {return itemCost;}

    public int getItemSellPrice() {return itemSellPrice;}


    // === BASIC METHODS ===
    // TEMP: returning int is temp because only healables. Future commits will need to rearchitect.
    public abstract int getUsed();









}
