package view.panels;

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
    private static final int horizontalGap = 15;
    private static final int verticalGap = 0;
    private static final LayoutManager LAYOUT = new BorderLayout(horizontalGap,verticalGap);
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
        JLabel titleLabel = new JLabel(displayTitle);
        this.add(titleLabel,BorderLayout.NORTH);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // The central text log of all display panels
        this.textLog = text;
        this.textLog.setRows(TEXT_ROWS);
        this.textLog.setColumns(TEXT_COLUMNS);
        this.textLog.setEditable(false);
        this.textLog.setFocusable(false);
        this.textLog.setLineWrap(true);
        this.contentDisplayer = new JScrollPane(textLog);
        this.add(contentDisplayer,BorderLayout.CENTER);

        // Formatting for text Log | TODO: move to its own wrapper class
        textLog.setBorder(BorderFactory.createLineBorder(Color.BLACK));

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
