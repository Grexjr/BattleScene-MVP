package view;

import javax.swing.*;
import java.awt.*;

/***********************************************************************************************************************
 * This class is the generic abstract content panel that holds all the methods that all game panels will use.
 * It will handle generic layout set up and formatting, and any methods that are generic from the classes.
 *
 **********************************************************************************************************************/


public abstract class ContentPanel extends JPanel {


    // === CONSTRUCTOR ===
    public ContentPanel(LayoutManager layout) {
        super(layout);
    }


    // === METHODS ===
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

    // method to hide a component
    public void hide(Component part){
        part.setVisible(false);
    }

    // method to show a component
    public void show(Component part){
        part.setVisible(true);
    }
}
