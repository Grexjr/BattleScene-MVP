package model.itm.healers;

public enum HealingItem {

    // === VALUES OF ENUM === | TODO: move to json when it becomes the truth a.k.a. when more are added
    SMALL_HEALTH_POTION(
            "Small Health Potion",
            "A small pot of healing liquid!",
            1,
            10,
            5);


    // === ENUM FIELDS ===
    private final int weight,cost,recoveryPower;
    private final String name,description;


    // === ENUM CONSTRUCTOR ===
    HealingItem(String name, String desc, int weight, int cost, int recovery){
        this.name = name;
        this.description = desc;
        this.weight = weight;
        this.cost = cost;
        this.recoveryPower = recovery;
    }


    // === GETTERS AND SETTERS ===
    public int getWeight() {return weight;}

    public int getCost() {return cost;}

    public int getRecoveryPower() {return recoveryPower;}

    public String getName() {return name;}

    public String getDescription() {return description;}





}
