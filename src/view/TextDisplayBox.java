package view;

import javax.swing.*;

/// The basic text display box of the game
public class TextDisplayBox extends JTextArea implements Writeable {

    // === FIELDS AND VARIABLES ===
    private JScrollPane scrollView;

    /**
     * Basic Text Display Box Object Constructor
     * @param rows - the number of rows the text box will have
     * @param columns - the number of columns the text box will have
     * */
    public TextDisplayBox(int rows, int columns){
        super(rows,columns);
        this.setEditable(false);
        this.setFocusable(false);
        this.setLineWrap(true);
        this.scrollView = new JScrollPane(this);
    }


    // === Writeable Methods ===
    @Override
    public void write(String... messages){
        for(String message : messages){
            this.append(message + '\n');
            SwingUtilities.invokeLater(this::scrollDown);
        }
    }

    @Override
    public void clear(){
        this.setText("");
    }

    @Override
    public void update(String newText){
        this.setText(newText);
    }

    @Override
    public void erase(int start, int end){
        this.replaceRange("",start,end);
    }

    @Override
    public void scrollDown(){
        JScrollBar scroller = this.scrollView.getVerticalScrollBar();
        scroller.setValue(scroller.getMaximum());
    }

}
