package view.guiparts;

import javax.swing.*;
import java.awt.*;

public class GameWindow {

    // === VARIABLES AND FIELDS ===
    private final JFrame baseFrame;


    // === GAME WINDOW CONSTRUCTOR ===
    public GameWindow(int width, int height, String title){
        this.baseFrame = new JFrame(title);
        this.baseFrame.setSize(width,height);
        this.baseFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.baseFrame.setVisible(true);
        this.baseFrame.setResizable(false);
        this.baseFrame.setLocationRelativeTo(null);
        // set icon image one day
    }

    // === GETTERS AND SETTERS ===
    public JFrame getBaseFrame() {return this.baseFrame;}

    // === OTHER METHODS ===

    // -- Helper Methods --
    // method to retrieve the content pane
    public Container getContentPane() {return this.baseFrame.getContentPane();}

    // method to repaint and revalidate the baseFrame content pane
    public void refresh() {
        this.baseFrame.getContentPane().revalidate();
        this.baseFrame.getContentPane().repaint();
    }

    // method to clear the content pane
    public void clear() {
        this.baseFrame.getContentPane().removeAll();
        this.refresh();
    }

    // method to add to the content pane
    public void add(Component part) {this.baseFrame.add(part);}







}
