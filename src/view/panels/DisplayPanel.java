package view.panels;

import view.Displayable;
import view.Formattable;
import view.textdisplayers.GameTextDisplay;
import view.textdisplayers.TextDisplayBox;
import view.Writeable;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/***********************************************************************************************************************
 * This class is the display panel for the game, which will include subclasses like the panel for displaying the battle,
 * story, etc.
 * This class includes the layout to be passed in as well as a common JTextArea to pass into all of its subclasses,
 * so all share the same text area (unless they need to be different for some reason).
 *
 **********************************************************************************************************************/

public abstract class DisplayPanel extends ContentPanel implements Displayable, Formattable {
    // === CONSTANTS ===
    private static final int horizontalGap = 15;
    private static final int verticalGap = 0;
    private static final LayoutManager LAYOUT = new BorderLayout(horizontalGap,verticalGap);


    // === VARIABLES AND FIELDS ===
    private final Writeable textBox;
    protected final String displayTitle;


    // === CONSTRUCTOR ===
    /**
     * General display panel constructor
     * @param text The main game text box that persists through all screens
     * @param title The title that appears to label the display panel
     * */
    public DisplayPanel(Writeable text, String title){
        super(LAYOUT);

        // The title of the display panel
        this.displayTitle = title;
        JLabel titleLabel = new JLabel(displayTitle);
        this.add(titleLabel,BorderLayout.NORTH);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // The central text log of all display panels
        this.textBox = text;
        this.add(((GameTextDisplay)textBox).getScrollView(),BorderLayout.CENTER);
    }

    // === Getters and Setters ===
    public Writeable getTextBox(){return this.textBox;}

    // === Displayable Methods ===
    @Override
    public void printToGUI(String... printMessages){
        this.textBox.write(printMessages);
    }

    // === Formattable Methods ===
    @Override
    public Border buildBorder(Border border){
        return border;
    }






}
