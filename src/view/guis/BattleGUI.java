package view.guis;

import controller.BattleController;
import model.ety.Entity;
import model.ety.Player;
import view.guiparts.buttonpanels.BattleButtonPanel;
import view.guiparts.TextLog;
import model.itm.Item;

import javax.swing.*;
import java.awt.*;

public class BattleGUI extends GameplayGUI {

    // === VARIABLES AND FIELDS ===
    private final JButton attackButton, defendButton, itemButton, runButton;


    // === THE CONSTRUCTOR ===
    public BattleGUI(BattleController controller, String enemyName, TextLog log){
        super(log, new BattleButtonPanel(controller));

        //add all buttons, for now just do manual below | TODO: FIX THIS SO THIS CAN GO AWAY!!!
        this.attackButton = new JButton("Attack");
        this.defendButton = new JButton("Defend");
        this.itemButton = new JButton("Items");
        this.runButton = new JButton("Run");
    }


    // === GETTERS AND SETTERS ===
    public JButton getAttackButton() {return this.attackButton;}
    public JButton getDefendButton() {return this.defendButton;}
    public JButton getItemButton() {return this.itemButton;}
    public JButton getRunButton() {return this.runButton;}


    // === OTHER METHODS ===
    // method to enable buttons | TODO: Override these from gamePanel
    public void enableButtons(){
        this.getAttackButton().setEnabled(true);
        this.getDefendButton().setEnabled(true);
        this.getItemButton().setEnabled(true);
        this.getRunButton().setEnabled(true);
    }

    // method to disable buttons
    public void disableButtons(){
        this.getAttackButton().setEnabled(false);
        this.getDefendButton().setEnabled(false);
        this.getItemButton().setEnabled(false);
        this.getRunButton().setEnabled(false);
    }


    // -- Printing Methods --
    // method to print battle start
    public void printBattleStart(Entity player, Entity enemy){
        this.print("The battle between " +
                player.getEntityName() +
                " and " +
                enemy.getEntityName() +
                " has begun!\n");
    }

    // method to print battle over
    public void printBattleOver(){
        this.print("Battle over!\n\n\n");
    }

    // method to print health
    public void printHealth(Entity battler){
        int currentHealth = battler.getEntityStatBlock().getEntityCurrentHealth();
        int maxHealth = battler.getEntityStatBlock().getEntityMaxHealth();

        this.print(battler.getEntityName() + " health: " + currentHealth + "/" + maxHealth +"\n");
    }

    // method to print loss
    public void printLoss(Entity finisher){
        if(finisher instanceof Player){
            this.print(finisher.getEntityName() + " has lost!");
        } else{
            this.print(finisher.getEntityName() + " has won!");
        }
    }

    // generic start turn print method
    public void printStartTurn(Entity starter){
        this.print("\n\n" + starter.getEntityName() + " act time!\n\n");
    }

    // generic attack print method
    public void printAttack(Entity attacker, Entity target){
        this.print(attacker.getEntityName() + " attacks " + target.getEntityName() + "!");
    }

    // generic defend print method
    public void printDefend(Entity defender){
        this.print(defender.getEntityName() + " defends!");
    }

    // generic item use attempt print method
    public void printItemUseAttempt(Entity user){
        this.print(user.getEntityName() + " tries to use an item!");
    }

    // generic no items print
    public void printNoItems(Entity user){
        this.print(user.getEntityName() + " has no items!");
    }

    // generic item use success print
    public void printSuccessfulItemUse(Entity entity, Item item){
        this.print("\n" + entity.getEntityName() + " uses " + item.getItemName() + "!\n");
    }

    // print generic entity run
    private void printRun(String runnerName){
        this.print(runnerName + " attempted to escape!");
    }

    // print generic entity successful run
    public void printSuccessfulRun(String runnerName){
        printRun(runnerName);
        this.print(runnerName +  " escaped!\n");
    }

    // print entity failed run
    public void printFailedRun(String runnerName){
        printRun(runnerName);
        this.print(runnerName + " failed to escape!\n");
    }

}
