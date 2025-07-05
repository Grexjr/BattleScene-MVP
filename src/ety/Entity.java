package ety;

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



}
