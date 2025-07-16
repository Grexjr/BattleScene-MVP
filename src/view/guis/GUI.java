package view.guis;

import view.guiparts.buttonpanels.ButtonPanel;

import javax.swing.*;
import java.awt.*;

///  GENERIC GAME PANEL INHERITED BY BATTLE PANEL AND MAIN PANEL TO PRESERVE TEXT LOG AND NECESSARY COMPONENTS
/// Abstract since this class will never be instantiated directly, only through its subclasses

public abstract class GUI extends JPanel {

    // === VARIABLES AND FIELDS ===
    private final JLabel titleLabel;
    private final ButtonPanel buttonPanel;


    // === CONSTRUCTOR ===
    public GUI(JLabel titleLabel, ButtonPanel panel){
        // Setting basic layout
        this.setLayout(new BorderLayout());

        // Title label; supered by each subclass
        this.titleLabel = titleLabel;
        this.add(titleLabel,BorderLayout.NORTH); // experiment with this

        // Creating the buttons panel - no JLabel by default, added by subclasses individually
        this.buttonPanel = panel;
        this.add(buttonPanel,BorderLayout.SOUTH);
    }


    // === GETTERS AND SETTERS ===
    public JLabel getTitleLabel() {return titleLabel;}

    public ButtonPanel getButtonPanel() {return buttonPanel;}


    // === HELPER METHODS ===
    // method to refresh -- DO I need this or just on the base frame's content panel?
    public void refresh(){
        this.revalidate();
        this.repaint();
    }

    // method to clear
    public void clear(){
        this.removeAll();
        refresh();
    }

    // method to hide a component
    public void hide(Component part){
        part.setVisible(false);
    }

    // method to show a component
    public void show(Component part){
        part.setVisible(true);
    }






}
