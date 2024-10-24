import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMGUI {
    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JTextField inputField;
    private JButton checkBalanceBtn, withdrawBtn, depositBtn, changePinBtn, transactionHistoryBtn, submitPinBtn;
    private ATM atm;

    public ATMGUI() {
        atm = new ATM("1234");  // Default PIN for testing

        frame = new JFrame("ATM");
        panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        label = new JLabel("Enter PIN:");
        inputField = new JTextField();
        submitPinBtn = new JButton("Submit PIN");

        checkBalanceBtn = new JButton("Check Balance");
        withdrawBtn = new JButton("Withdraw");
        depositBtn = new JButton("Deposit");
        changePinBtn = new JButton("Change PIN");
        transactionHistoryBtn = new JButton("Transaction History");

        checkBalanceBtn.setEnabled(false);
        withdrawBtn.setEnabled(false);
        depositBtn.setEnabled(false);
        changePinBtn.setEnabled(false);
        transactionHistoryBtn.setEnabled(false);

        submitPinBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pinInput = inputField.getText();
                if (atm.validatePIN(pinInput)) {
                    JOptionPane.showMessageDialog(null, "PIN validated");
                    enableButtons();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid PIN");
                }
            }
        });

        checkBalanceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atm.checkBalance();
            }
        });

        withdrawBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountStr = JOptionPane.showInputDialog("Enter amount to withdraw:");
                double amount = Double.parseDouble(amountStr);
                atm.withdrawCash(amount);
            }
        });

        depositBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountStr = JOptionPane.showInputDialog("Enter amount to deposit:");
                double amount = Double.parseDouble(amountStr);
                atm.depositCash(amount);
            }
        });

        changePinBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String oldPin = JOptionPane.showInputDialog("Enter old PIN:");
                String newPin = JOptionPane.showInputDialog("Enter new PIN:");
                atm.changePIN(oldPin, newPin);
            }
        });

        transactionHistoryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atm.showTransactionHistory();
            }
        });

        panel.add(label);
        panel.add(inputField);
        panel.add(submitPinBtn);
        panel.add(checkBalanceBtn);
        panel.add(withdrawBtn);
        panel.add(depositBtn);
        panel.add(changePinBtn);
        panel.add(transactionHistoryBtn);

        frame.add(panel);
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void enableButtons() {
        checkBalanceBtn.setEnabled(true);
        withdrawBtn.setEnabled(true);
        depositBtn.setEnabled(true);
        changePinBtn.setEnabled(true);
        transactionHistoryBtn.setEnabled(true);
    }

    public static void main(String[] args) {
        new ATMGUI();
    }
}
