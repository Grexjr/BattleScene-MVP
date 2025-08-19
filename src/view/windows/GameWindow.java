package view.windows;

import view.panels.ContainerPanel;

import javax.swing.*;
import java.awt.*;

public class GameWindow {

    // === VARIABLES AND FIELDS ===
    private final JFrame baseFrame;
    private final ContainerPanel container;


    // === GAME WINDOW CONSTRUCTOR ===
    public GameWindow(){
        // === CONSTANTS === | NOTE: some of these may be changed if settings for window size get added.
        String GAME_WINDOW_TITLE = "GAME";
        int width = 1290;
        int height = 700;

        // === BASE FRAME CREATION ===
        this.baseFrame = new JFrame(GAME_WINDOW_TITLE);
        this.baseFrame.setSize(width,height);

        this.baseFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.baseFrame.setVisible(true);
        this.baseFrame.setResizable(false);
        this.baseFrame.setLocationRelativeTo(null);

        this.container = new ContainerPanel();
        getContentPane().add(container,BorderLayout.CENTER);
    }

    // === GETTERS ===
    public ContainerPanel getContainerPanel() {return this.container;}

    // === OTHER METHODS ===

    // -- Helper Methods --
    // method to retrieve the content pane
    public Container getContentPane() {return this.baseFrame.getContentPane();}

    // method to repaint and revalidate the baseFrame content pane
    public void refresh() {
        this.baseFrame.revalidate();
        this.baseFrame.repaint();
    }

    // method to clear the content pane
    public void clear() {
        this.baseFrame.getContentPane().removeAll();
        this.refresh();
    }

    // method to add to the content pane
    public void add(Component part) {this.baseFrame.add(part);}







}
