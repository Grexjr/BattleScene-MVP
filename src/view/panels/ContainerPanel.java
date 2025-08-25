package view.panels;

import javax.swing.border.Border;
import java.awt.*;

public class ContainerPanel extends ContentPanel{
    // === CONSTANTS ===
    private static final LayoutManager LAYOUT = new BorderLayout();

    // === VARIABLES AND FIELDS ===
    private DisplayPanel displayer;
    private InteractPanel interactor;

    public ContainerPanel(){
        super(LAYOUT);
    }

    // === GETTERS ===
    public DisplayPanel getDisplayer() {
        return displayer;
    }

    public InteractPanel getInteractor() {
        return interactor;
    }

    // === SETTERS ===
    public void setPanels(DisplayPanel display, InteractPanel interact){
        setDisplayPanel(display);
        setInteractPanel(interact);
    }

    public void setDisplayPanel(DisplayPanel display){
        if(this.displayer != null){
            this.remove(this.displayer);
        }
        this.displayer = display;
        if(displayer != null){
            this.add(displayer,BorderLayout.NORTH);
        }
        this.refresh();
    }

    public void setInteractPanel(InteractPanel interact){
        if(this.interactor != null){
            this.remove(this.interactor);
        }
        this.interactor = interact;
        if(interactor != null){
            this.add(interactor,BorderLayout.SOUTH);
        }
        this.refresh();
    }







}
