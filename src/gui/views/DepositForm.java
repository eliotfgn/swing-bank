package gui.views;

import database.DatabaseConnector;
import models.Account;
import models.Deposit;
import models.Transaction;
import services.BankService;

import javax.swing.*;
import java.awt.event.*;

public class DepositForm extends JDialog {
    private BankService bankService = BankService.getInstance();
    private DatabaseConnector databaseConnector = DatabaseConnector.getInstance();
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField accountTextField;
    private JTextField amountTextField;

    public DepositForm() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        long id = Long.parseLong(accountTextField.getText());
        double amount = Double.parseDouble(amountTextField.getText());
        bankService.deposit(id, amount);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        DepositForm dialog = new DepositForm();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
