package view.guis;

import view.guiparts.TextLog;
import view.guiparts.buttonpanels.StoryButtonPanel;

public class StoryGUI extends GameplayGUI{

    // === VARIABLES AND FIELDS ===


    // === CONSTRUCTOR ===
    public StoryGUI(TextLog log){
        super(log,new StoryButtonPanel());
    }


}
