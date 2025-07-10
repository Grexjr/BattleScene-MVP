package ety;

import itm.Item;
import itm.healers.Healable;

import java.util.Random;

/*
 * This class is the abstract, non-concrete entity class that all entities (in this case slime and player, since
 * there are no others, extend from).
 * It provides all parameters for entities as well as most functionality for entities.
 */
public abstract class Entity {
    // TODO: Use direct access rather than getters and setters for the methods in here -- refactoring

    // === VARIABLES AND FIELDS ===
    private final String entityName, entityDescription;
    private final StatBlock entityStatBlock;


    // === ENTITY CONSTRUCTOR ===
    public Entity(String name, String description, StatBlock statBlock){
        this.entityName = name;
        this.entityDescription = description;
        this.entityStatBlock = statBlock;
    }

    // === GETTERS AND SETTERS ===
    public String getEntityName() {return this.entityName;}

    public String getEntityDescription() {return this.entityDescription;}

    public StatBlock getEntityStatBlock() {return this.entityStatBlock;}


    // === OTHER METHODS ===

    // -- Battle methods --
    // method to check if dead
    public boolean isDead(){return this.getEntityStatBlock().getEntityCurrentHealth() <= 0;}

    // method to guard
    public void guard(){
        int defense = this.entityStatBlock.getEntityDefense();
        if(defense == 0){
            this.entityStatBlock.setTempDefenseMod(1);
        } else {
            this.entityStatBlock.setTempDefenseMod((int) Math.ceil(defense / 2.0));
        }

        // Debug
        System.out.println((int) Math.ceil(defense / 2.0));
        System.out.println(this.getEntityStatBlock().getEffectiveDefense());
    }

    // method to calculate run chance using speed, returns boolean of run success or not | TODO: Devise a test for this
    public boolean run(Entity runFrom){
        int runnerSpeed = this.getEntityStatBlock().getEffectiveSpeed();
        int runFromSpeed = runFrom.getEntityStatBlock().getEffectiveSpeed();

        int totalSpeed =  runnerSpeed + runFromSpeed;

        // Debug
        System.out.println(totalSpeed);

        Random rand = new Random();
        int runSuccessNum = rand.nextInt(1,totalSpeed+1);

        // Debug
        System.out.println(runSuccessNum);

        // Should give a ~50% chance to escape when speeds are the same
        return runSuccessNum <= runnerSpeed;
    }



}
