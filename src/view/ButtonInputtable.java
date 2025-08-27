package view;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public interface ButtonInputtable extends Inputtable {

    ArrayList<JButton> initializeButtons();

    JButton createButton(String label, ActionListener onPress);

    void addButtons();

    void toggleButtons(boolean status);


}
