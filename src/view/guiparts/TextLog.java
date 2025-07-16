package view.guiparts;

import javax.swing.*;

public class TextLog extends JTextArea {

    // === VARIABLES AND FIELDS ===

    // === CONSTRUCTOR FOR TEXT LOG ===
    public TextLog(){
        // TODO: Far in future, make this change with the window size
        // Initialize text log
        super(30,65);
        this.setEditable(false);
    }


    // GETTERS AND SETTERS || NOTE: Inherits from the super JTextArea class


    // === HELPER METHODS ===

    // === BASIC METHODS ===
    // generic log method
    public void log(String message){
        this.append(message + "\n");
    }


}
