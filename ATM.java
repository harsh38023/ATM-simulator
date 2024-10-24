import javax.swing.*;
import java.util.ArrayList;

class ATM {
    private double balance;
    private String pin;
    private ArrayList<String> transactionHistory;

    public ATM(String initialPin) {
        this.balance = 0.0;
        this.pin = initialPin;
        this.transactionHistory = new ArrayList<>();
    }

    public void checkBalance() {
        JOptionPane.showMessageDialog(null, "Your current balance is: $" + balance);
        transactionHistory.add("Balance inquiry: $" + balance);
    }

    public void withdrawCash(double amount) {
        if (amount > balance) {
            JOptionPane.showMessageDialog(null, "Insufficient funds.");
            transactionHistory.add("Failed withdrawal: $" + amount);
        } else {
            balance -= amount;
            JOptionPane.showMessageDialog(null, "Please take your cash: $" + amount);
            transactionHistory.add("Withdrawal: $" + amount);
        }
    }

    public void depositCash(double amount) {
        balance += amount;
        JOptionPane.showMessageDialog(null, "Deposit successful: $" + amount);
        transactionHistory.add("Deposit: $" + amount);
    }

    public void changePIN(String oldPin, String newPin) {
        if (oldPin.equals(pin)) {
            pin = newPin;
            JOptionPane.showMessageDialog(null, "PIN changed.");
            transactionHistory.add("PIN changed.");
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect old PIN.");
            transactionHistory.add("Failed PIN change.");
        }
    }

    public void showTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No transactions yet.");
        } else {
            StringBuilder history = new StringBuilder();
            for (String transaction : transactionHistory) {
                history.append(transaction).append("\n");
            }
            JOptionPane.showMessageDialog(null, history.toString());
        }
    }

    public boolean validatePIN(String inputPin) {
        return inputPin.equals(pin);
    }
}
