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
    private static final LayoutManager LAYOUT = new BorderLayout();
    private static final int TEXT_ROWS = 30;
    private static final int TEXT_COLUMNS = 65;


    // === VARIABLES AND FIELDS ===
    protected final JScrollPane contentDisplayer;
    private final JTextArea textLog;
    protected final String displayTitle;


    // === CONSTRUCTOR ===
    public DisplayPanel(JTextArea text, String title){
        super(LAYOUT);

        // The title of the display panel
        this.displayTitle = title;
        this.add(new JLabel(displayTitle),BorderLayout.NORTH);

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
