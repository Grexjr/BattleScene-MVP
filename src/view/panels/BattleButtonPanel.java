package view.panels;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.util.ArrayList;

public abstract class BattleButtonPanel extends ButtonPanel {
    // === CONSTANTS ===
    private static final LayoutManager LAYOUT = new GridLayout(1,0);
    private static final int BORDER_BUFFER = 25;
    private static final int INNER_BORDER_BUFFER = 35;


    public BattleButtonPanel(){
        super(LAYOUT);

        createBorders();
    }

    @Override
    protected void createBorders(){
        Border outerBorder = BorderFactory.createEmptyBorder(BORDER_BUFFER,BORDER_BUFFER,BORDER_BUFFER,BORDER_BUFFER);
        Border innerBorder = BorderFactory.createEmptyBorder(
                INNER_BORDER_BUFFER,
                INNER_BORDER_BUFFER,
                INNER_BORDER_BUFFER,
                INNER_BORDER_BUFFER
        );

        CompoundBorder firstBorder = BorderFactory.createCompoundBorder(
                outerBorder,
                BorderFactory.createBevelBorder(1,Color.GRAY,Color.DARK_GRAY)
        );

        CompoundBorder finalBorder = BorderFactory.createCompoundBorder(
                firstBorder,
                innerBorder
        );

        this.setBorder(finalBorder);
    }




}
