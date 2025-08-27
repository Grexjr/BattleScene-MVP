package view.panels;

import view.Formattable;
import view.Inputtable;

import javax.swing.border.Border;
import java.awt.*;

/**
 * This class is the general class for interaction panels, which will all be at the bottom of the screen.
 * These include panels with buttons, panels with text fields, panels with whatever interacts with user input.
 *
 */
public abstract class InteractPanel extends ContentPanel implements Inputtable, Formattable {


    // === CONSTRUCTOR ===
    public InteractPanel(LayoutManager layout){
        super(layout);
    }



    // === Formattable Methods ===
    @Override
    public Border buildBorder(Border border){
        return border;
    }






}
