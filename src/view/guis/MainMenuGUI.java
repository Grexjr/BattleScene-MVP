package view.guis;

import controller.MainMenuController;
import view.guiparts.buttonpanels.MainMenuButtonPanel;

import javax.swing.*;

public class MainMenuGUI extends GUI {

    // === VARIABLES AND FIELDS ===

    // === CONSTRUCTOR ===
    public MainMenuGUI(MainMenuController mmc){
        super(new JLabel("RogueCrawler Timescapes"),new MainMenuButtonPanel(mmc));

        // TODO: Load in an image in the center so buttons go to the bottom


    }




}
