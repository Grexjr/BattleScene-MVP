package view.panels;

import javax.swing.border.Border;
import java.awt.*;

public class ContainerPanel extends ContentPanel{
    // === CONSTANTS ===
    private static final LayoutManager LAYOUT = new BorderLayout();

    // === VARIABLES AND FIELDS ===
    private final DisplayPanel displayer;
    private final InteractPanel interactor;

    public ContainerPanel(DisplayPanel display, InteractPanel interact){
        super(LAYOUT);

        this.displayer = display;
        this.interactor = interact;

        this.add(displayer,BorderLayout.NORTH);
        this.add(interactor,BorderLayout.SOUTH);
    }

    // === GETTERS ===
    public DisplayPanel getDisplayer() {
        return displayer;
    }

    public InteractPanel getInteractor() {
        return interactor;
    }







}
