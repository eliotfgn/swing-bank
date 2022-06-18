package gui.views;

import javax.swing.*;
import java.awt.*;

public class AppContent extends JPanel{
    private JPanel sideBar;
    private JPanel content;

    public AppContent() {
        setLayout(new GridLayout(1, 3));
    }

    public void init() {
        add(sideBar);
        add(content, 1, 1);
    }

    public JPanel getSideBar() {
        return sideBar;
    }

    public void setSideBar(JPanel sideBar) {
        this.sideBar = sideBar;
    }

    public JPanel getContent() {
        return content;
    }

    public void setContent(JPanel content) {
        this.content = content;
    }
}
