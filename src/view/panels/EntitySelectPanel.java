package view.panels;

import model.ety.Entity;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/// This class is the panel that allows the player to select an entity to attack
public class EntitySelectPanel extends BattleBaseInteract{

    private final ArrayList<Entity> enemyList;


    public EntitySelectPanel(ArrayList<Entity> list){
        this.enemyList = list;
    }

    // === METHODS ===
    @Override
    public ArrayList<JButton> initializeButtons() {
        ArrayList<JButton> buttonsList = new ArrayList<>();
        for(Entity entity : enemyList){
            JButton entityButton = createButton(entity.getEntityName(),null);
            buttonsList.add(entityButton);
        }
        return buttonsList;
    }




}
