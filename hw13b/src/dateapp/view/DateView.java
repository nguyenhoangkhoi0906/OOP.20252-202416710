package dateapp.view;

import java.awt.*;
import javax.swing.*;

public class DateView extends JFrame {
    private JTextField dateField;
    private JTextField inputField;
    private JButton previousDaysButton;
    private JButton nextDaysButton;
    private JButton previousMonthsButton;
    private JButton nextMonthsButton;

    public DateView() {
        setTitle("Date Display GUI");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        JLabel titleLabel = new JLabel("Date Display GUI", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        dateField = new JTextField();
        dateField.setFont(new Font("Arial", Font.BOLD, 20));
        dateField.setHorizontalAlignment(JTextField.CENTER);
        dateField.setEditable(false);

        JLabel inputLabel = new JLabel("Enter number x:");
        inputField = new JTextField();

        previousDaysButton = new JButton("x days before");
        nextDaysButton = new JButton("x days after");
        previousMonthsButton = new JButton("x months before");
        nextMonthsButton = new JButton("x months after");

        JPanel inputPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        inputPanel.add(inputLabel);
        inputPanel.add(inputField);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        buttonPanel.add(previousDaysButton);
        buttonPanel.add(nextDaysButton);
        buttonPanel.add(previousMonthsButton);
        buttonPanel.add(nextMonthsButton);

        JPanel mainPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.add(titleLabel);
        mainPanel.add(dateField);
        mainPanel.add(inputPanel);
        mainPanel.add(buttonPanel);

        add(mainPanel);
    }

    public String getInputValue() {
        return inputField.getText();
    }

    public void setDateText(String dateText) {
        dateField.setText(dateText);
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }

    public JButton getPreviousDaysButton() {
        return previousDaysButton;
    }

    public JButton getNextDaysButton() {
        return nextDaysButton;
    }

    public JButton getPreviousMonthsButton() {
        return previousMonthsButton;
    }

    public JButton getNextMonthsButton() {
        return nextMonthsButton;
    }
}