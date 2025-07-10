package gui.parts;

import ety.Entity;
import ety.Player;
import itm.Item;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BattlePanel extends JPanel {

    // === VARIABLES AND FIELDS ===
    private final JTextArea textLog;
    private final JScrollPane battleScroller;
    private final JButton attackButton, defendButton, itemButton, runButton;
    private final JPanel buttonPanel;


    // === THE CONSTRUCTOR ===
    public BattlePanel(String enemyName){
        // Creating the title label
        JLabel titleLabel = new JLabel("Battle versus " + enemyName);
        this.add(titleLabel, BorderLayout.NORTH);

        // Creating the text panel
        this.textLog = new JTextArea(30,65);
        this.textLog.setEditable(false);
        this.battleScroller = new JScrollPane(textLog);
        this.add(battleScroller,BorderLayout.CENTER);

        //add all buttons, for now just do manual below | TODO: Use enum or class(?) for future
        this.attackButton = new JButton("Attack");
        this.defendButton = new JButton("Defend");
        this.itemButton = new JButton("Items");
        this.runButton = new JButton("Run");

        // Creating the buttons panel
        this.buttonPanel = new JPanel(new FlowLayout());
        this.buttonPanel.add(new JLabel("Actions:"), BorderLayout.NORTH);
        this.buttonPanel.add(this.attackButton);
        this.buttonPanel.add(this.defendButton);
        this.buttonPanel.add(this.itemButton);
        this.buttonPanel.add(this.runButton);
        this.add(buttonPanel,BorderLayout.SOUTH);
    }


    // === GETTERS AND SETTERS ===
    public JPanel getButtonPanel() {return this.buttonPanel;}
    public JButton getAttackButton() {return this.attackButton;}
    public JButton getDefendButton() {return this.defendButton;}
    public JButton getItemButton() {return this.itemButton;}
    public JButton getRunButton() {return this.runButton;}


    // === OTHER METHODS ===

    // -- Helper Methods --
    // method to refresh
    public void refresh(){
        this.revalidate();
        this.repaint();
    }

    // method to clear
    public void clear(){
        this.removeAll();
        refresh();
    }


    // method to always have scrollbar go to the bottom -- added to log method so everytime logged it goes to bottom
    public void scrollDown(){
        JScrollBar scroller = this.battleScroller.getVerticalScrollBar();
        scroller.setValue(scroller.getMaximum());
    }

    // method to log something on the text field
    public void log(String message){
        this.textLog.append(message + "\n");
        SwingUtilities.invokeLater(this::scrollDown);
    }

    // method to hide a component
    public void hide(Component part){
        part.setVisible(false);
    }

    // method to show a component
    public void show(Component part){
        part.setVisible(true);
    }

    // method to enable buttons
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
        this.log("The battle between " +
                player.getEntityName() +
                " and " +
                enemy.getEntityName() +
                " has begun!\n");
    }

    // method to print battle over
    public void printBattleOver(){
        this.log("Battle over!\n\n\n");
    }

    // method to print health
    public void printHealth(Entity battler){
        int currentHealth = battler.getEntityStatBlock().getEntityCurrentHealth();
        int maxHealth = battler.getEntityStatBlock().getEntityMaxHealth();

        this.log(battler.getEntityName() + " health: " + currentHealth + "/" + maxHealth +"\n");
    }

    // method to print loss
    public void printLoss(Entity finisher){
        if(finisher instanceof Player){
            this.log(finisher.getEntityName() + " has lost!");
        } else{
            this.log(finisher.getEntityName() + " has won!");
        }
    }

    // print player turn start
    public void printPlayerStartTurn(){
        this.log("\n\nPlayer act time!\n\n");
    }

    // - Methods to print button presses -
    // player attack
    public void printPlayerAttack(String enemyName){
        this.log("You attack the " + enemyName +"!");
    }

    // player defend
    public void printPlayerDefend(){
        this.log("You defend!");
    }

    // player item use
    public void printPlayerItemUse(){
        this.log("You try to use an item!");
    }

    // player item use success | TODO: model other methods off of this one to make them more generic
    public void printSuccessfulItemUse(Entity entity, Item item){
        this.log("\n" + entity.getEntityName() + " uses " + item.getItemName() + "!\n");
    }

    // player has no items print
    public void printNoItems(){
        this.log("You have no items!\n");
    }

    // print entity run
    private void printRun(String runnerName){
        this.log(runnerName + " attempted to escape!");
    }

    // print entity successful run
    public void printSuccessfulRun(String runnerName){
        printRun(runnerName);
        this.log(runnerName +  " escaped!\n");
    }

    // print entity failed run
    public void printFailedRun(String runnerName){
        printRun(runnerName);
        this.log(runnerName + " failed to escape!\n");
    }

    // - Methods to print enemy-related things -
    // enemy attack
    public void printEnemyAttack(Entity enemy){
        this.log(enemy.getEntityName() + " attacks!\n");
    }







}
