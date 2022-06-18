package gui.views;

import services.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JPanel {
    private UserService userService = UserService.getInstance();

    private JPanel page;
    private JLabel lbl_login;
    private JPanel loginContent;
    private JPanel imageContainer;
    private JTextField usernameField;
    private JPasswordField passwordField1;
    private JButton confirmButton;
    private JLabel Message;
    private JButton registerButton;

    public LoginPage() {

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("login-image.jpg"));
        imageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(600, 600, Image.SCALE_DEFAULT));
        JLabel image2 = new JLabel(imageIcon);
        imageContainer.setLayout(new GridLayout(1, 1));
        imageContainer.add(image2);
        add(imageContainer);
        add(loginContent);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = usernameField.getText();
                String password = String.copyValueOf(passwordField1.getPassword());
                if (userService.login(email, password)) {
                    JOptionPane.showMessageDialog(page, "Logged in succcessfully");
                } else {
                    System.out.println("Not logged");
                }
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginContent.removeAll();
                loginContent.revalidate();
                loginContent.repaint();
                loginContent.setLayout(new GridLayout(1, 1));
                loginContent.add(new RegisterContent());
            }
        });
    }
}
