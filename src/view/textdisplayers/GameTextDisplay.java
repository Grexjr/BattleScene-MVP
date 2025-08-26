package view.textdisplayers;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/// The central text display box for all front-facing game text
public class GameTextDisplay extends TextDisplayBox{
    // === CONSTANTS ===
    Border gameTextBorder = BorderFactory.createLineBorder(Color.BLACK);

    /// Constructor for the central game text box.
    public GameTextDisplay(){
        super(30,60);
        this.buildBorder(gameTextBorder);
    }







}
