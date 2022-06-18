package gui.views;

import javax.swing.*;

public class Main extends JFrame {
    public Main() {
        super();
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args){
        LoginPage loginPage = new LoginPage();
        JFrame app = new Main();
        app.setContentPane(loginPage);
        app.pack();
        app.setVisible(true);

    }
}
