package view;

public interface Writeable {

    void write(String... messages);

    void clear();

    void update(String newText);

    void erase(int start, int end);

    void scrollDown();

}
