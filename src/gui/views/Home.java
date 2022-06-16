package gui.views;

import gui.components.RoundedBorder;

import javax.swing.*;

public class Home extends JFrame{
    private JPanel panel;
    private JButton btn;
    private JButton button1;

    public Home() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        btn.setBorderPainted(false);
        btn.setBorder(new RoundedBorder(20));
        setContentPane(panel);
        setVisible(true);
        setSize(300, 300);

    }
}
