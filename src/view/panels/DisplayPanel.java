package view.panels;

import view.Displayable;
import view.Formattable;
import view.textdisplayers.TextDisplayBox;
import view.Writeable;

import javax.swing.*;
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
    public DisplayPanel(TextDisplayBox text, String title){
        super(LAYOUT);

        // The title of the display panel
        this.displayTitle = title;
        JLabel titleLabel = new JLabel(displayTitle);
        this.add(titleLabel,BorderLayout.NORTH);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // The central text log of all display panels
        this.textBox = text;

        // Formatting
        text.setBorder(text.buildBorder(BorderFactory.createLineBorder(Color.BLACK)));
    }

    // === Getters and Setters ===
    public Writeable getTextBox(){return this.textBox;}

    // === Displayable Methods ===
    @Override
    public void printToGUI(String... printMessages){
        this.textBox.write(printMessages);
    }

    // === Formattable Methods ===







}
