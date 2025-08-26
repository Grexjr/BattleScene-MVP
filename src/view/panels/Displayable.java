package view.panels;

public interface Displayable {

    // meant for just changing text content, not appending or anything
    void updateTextBox(); // Will need to pass in the text box here, need to make separate text box object
    void scrollTextBoxDown(); // Will need to pass in text box here


    void printToGUI(String... printStrings);




}
