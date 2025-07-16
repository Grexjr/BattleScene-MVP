package view.guis;

import view.guiparts.buttonpanels.ButtonPanel;
import view.guiparts.TextLog;

import javax.swing.*;
import java.awt.*;

public class GameplayGUI extends GUI{

    // === VARIABLES AND FIELDS ===
    private final TextLog textLog;
    private final JScrollPane scroller;


    // === CONSTRUCTOR ===
    public GameplayGUI(TextLog log, ButtonPanel panel){
        super(new JLabel("Game"),panel);

        // TextLog, shared by all gameplay panels
        this.textLog = log;
        this.scroller = new JScrollPane(textLog);
        this.add(scroller, BorderLayout.CENTER);
        this.refresh();
    }


    // === GETTERS ===
    public TextLog getTextLog() {return textLog;}

    public JScrollPane getScroller() {return scroller;}


    // === BASIC METHODS ===
    // always scroll to bottom of text
    public void scrollDown(){
        JScrollBar scroller = this.scroller.getVerticalScrollBar();
        scroller.setValue(scroller.getMaximum());
    }

    // printing text for any panel
    public void print(String message){
        this.textLog.log(message);
        SwingUtilities.invokeLater(this::scrollDown);
    }


}
