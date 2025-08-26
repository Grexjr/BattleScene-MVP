package view;

/// All displayable must be a JComponent
public interface Displayable {

    /// Printing method to the GUI, appending strings to the text box
    void printToGUI(String... printStrings);

}
