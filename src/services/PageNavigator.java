package services;

import javax.swing.*;

public class PageNavigator {
    private static PageNavigator instance;
    private JPanel root;

    public JPanel getRoot() {
        return root;
    }

    public void setRoot(JPanel root) {
        this.root = root;
    }

    private PageNavigator() {}

    public static PageNavigator getInstance() {
        if (instance == null) {
            instance = new PageNavigator();
        }
        return instance;
    }

    public void goTo(JPanel currentPage, JPanel newPage) {
        currentPage.removeAll();
        currentPage.revalidate();
    }
}
