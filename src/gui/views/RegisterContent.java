package gui.views;

import models.User;
import services.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterContent extends JPanel {
    private UserService userService = UserService.getInstance();
    private JPanel content;
    private JTextField firstnameField;
    private JTextField lastnameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton confirmButton;

    public RegisterContent() {
        add(content);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = new User();
                user.setLastname(lastnameField.getText());
                user.setFirstname(firstnameField.getText());
                user.setEmail(emailField.getText());
                user.setPassword(String.copyValueOf(passwordField.getPassword()));
                if (userService.register(user)) {
                    System.out.println("Registered");
                    JRootPane root = getRootPane();
                    root.removeAll();
                    root.revalidate();
                    root.repaint();
                    root.setLayout(new GridLayout(1, 1));
                    AppContent appContent = new AppContent();
                    appContent.setSideBar(new SideBar());
                    appContent.setContent(new HomePage(user.getFirstname()));
                    appContent.init();
                    root.add(appContent);

                }
            }
        });
    }
}
