package itm.healers;

import itm.Item;

public class Healable extends Item {

    // === VARIABLES AND FIELDS ===
    private final int recoveryPower;

    // === CONSTRUCTOR ===
    public Healable(HealingItem healingItem){
        super(
                healingItem.getName(),
                healingItem.getDescription(),
                healingItem.getWeight(),
                healingItem.getCost()
        );
        this.recoveryPower = healingItem.getRecoveryPower();
    }


    // === GETTERS AND SETTERS ===
    public int getRecoveryPower() {return recoveryPower;}


    // === OTHER METHODS ===
    @Override
    public int getUsed(){
        return getRecoveryPower();
    }






}
