package view.gui;

import javax.swing.*;
import java.awt.*;

/***********************************************************************************************************************
 * This class is the display panel for the game, which will include subclasses like the panel for displaying the battle,
 * story, etc.
 * This class includes the layout to be passed in as well as a common JTextArea to pass into all of its subclasses,
 * so all share the same text area (unless they need to be different for some reason).
 *
 **********************************************************************************************************************/

public class DisplayPanel extends ContentPanel {
    // === CONSTANTS ===
    private static final int TEXT_ROWS = 30;
    private static final int TEXT_COLUMNS = 65;


    // === VARIABLES AND FIELDS ===
    private final JScrollPane contentDisplayer;
    private final JTextArea textLog;


    // === CONSTRUCTOR ===
    public DisplayPanel(JTextArea text, LayoutManager layout){
        super(layout);

        // The central text log of all display panels
        this.textLog = text;
        this.textLog.setRows(TEXT_ROWS);
        this.textLog.setColumns(TEXT_COLUMNS);
        this.textLog.setEditable(false);
        this.contentDisplayer = new JScrollPane(textLog);
        this.add(contentDisplayer,BorderLayout.CENTER);
    }


    // === TEXT METHODS ===
    public void scrollDown(){
        JScrollBar scroller = this.contentDisplayer.getVerticalScrollBar();
        scroller.setValue(scroller.getMaximum());
    }

    public void log(String message){
        this.textLog.append(message + "\n");
        SwingUtilities.invokeLater(this::scrollDown);
    }






}
