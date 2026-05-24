package calculator.ui;

import calculator.logic.CalculatorLogic;
import java.awt.*;
import javax.swing.*;

public class CalculatorFrame extends JFrame {

    private JTextField display;
    private String firstNumber = "";
    private String operator = "";
    private boolean startNewNumber = false;

    private final CalculatorLogic logic = new CalculatorLogic();

    public CalculatorFrame() {
        setTitle("Mini Calculator");
        setSize(350, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();

        setVisible(true);
    }

    private void initComponents() {
        display = new JTextField();
        display.setPreferredSize(new Dimension(350, 80));
        display.setFont(new Font("Arial", Font.BOLD, 28));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        ///
        String[][] layout = {
        {"C", "DEL", "%", "/"},
        {"7", "8", "9", "*"},
        {"4", "5", "6", "-"},
        {"1", "2", "3", "+"}
};

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH;
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.weightx = 1;
    gbc.weighty = 1;

    for (int row = 0; row < layout.length; row++) {
        for (int col = 0; col < layout[row].length; col++) {

            String text = layout[row][col];

            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 22));
            button.addActionListener(e -> handleButtonClick(text));

            gbc.gridx = col;
            gbc.gridy = row;
            gbc.gridwidth = 1;

            panel.add(button, gbc);
        }
    }
    JButton dotButton = new JButton(".");
    dotButton.setFont(new Font("Arial", Font.BOLD, 22));
    dotButton.addActionListener(e -> handleButtonClick("."));

    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.gridwidth = 1;
    panel.add(dotButton, gbc);

    JButton zeroButton = new JButton("0");
    zeroButton.setFont(new Font("Arial", Font.BOLD, 22));
    zeroButton.addActionListener(e -> handleButtonClick("0"));

    gbc.gridx = 1;
    gbc.gridy = 4;
    gbc.gridwidth = 1;
    panel.add(zeroButton, gbc);

    JButton equalButton = new JButton("=");
    equalButton.setFont(new Font("Arial", Font.BOLD, 22));
    equalButton.addActionListener(e -> handleButtonClick("="));

    gbc.gridx = 2;
    gbc.gridy = 4;
    gbc.gridwidth = 2;

    panel.add(equalButton, gbc);


        setLayout(new BorderLayout(10, 10));
        add(display, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
    }

    private void handleButtonClick(String text) {
        if (text.matches("[0-9]")) {
            inputNumber(text);
        } else if (text.equals(".")) {
            inputDecimalPoint();
        } else if (text.equals("C")) {
            clear();
        } else if (text.equals("DEL")) {
            deleteLastCharacter();
        } else if (text.equals("=")) {
            calculateResult();
        } else {
            inputOperator(text);
        }
    }

    private void inputNumber(String number) {
        if (startNewNumber) {
            display.setText("");
            startNewNumber = false;
        }
        display.setText(display.getText() + number);
    }

    private void inputDecimalPoint() {
        if (startNewNumber) {
            display.setText("0");
            startNewNumber = false;
        }

        if (!display.getText().contains(".")) {
            if (display.getText().isEmpty()) {
                display.setText("0.");
            } else {
                display.setText(display.getText() + ".");
            }
        }
    }

    private void inputOperator(String op) {
        if (display.getText().isEmpty()) {
            showError("Please enter the first number");
            return;
        }

        firstNumber = display.getText();
        operator = op;
        startNewNumber = true;
    }

    private void calculateResult() {
        if (firstNumber.isEmpty() || operator.isEmpty() || display.getText().isEmpty()) {
            showError("Incomplete expression");
            return;
        }

        try {
            double a = Double.parseDouble(firstNumber);
            double b = Double.parseDouble(display.getText());

            double result = logic.calculate(a, b, operator);
            display.setText(logic.formatResult(result));

            firstNumber = "";
            operator = "";
            startNewNumber = true;

        } catch (NumberFormatException e) {
            showError("Invalid number");
        } catch (ArithmeticException | IllegalArgumentException e) {
            showError(e.getMessage());
        }
    }

    private void clear() {
        display.setText("");
        firstNumber = "";
        operator = "";
        startNewNumber = false;
    }

    private void deleteLastCharacter() {
        String currentText = display.getText();

        if (!currentText.isEmpty()) {
            display.setText(currentText.substring(0, currentText.length() - 1));
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }
}