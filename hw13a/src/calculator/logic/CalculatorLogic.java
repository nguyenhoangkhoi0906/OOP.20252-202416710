package calculator.logic;

public class CalculatorLogic {

    public double calculate(double a, double b, String operator) {
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
            case "×":
                return a * b;
            case "/":
                if (b == 0) {
                    throw new ArithmeticException("Cannot divide by zero");
                }
                return a / b;
            case "%":
                if (b == 0) {
                    throw new ArithmeticException("Cannot modulo by zero");
                }
                return a % b;
            default:
                throw new IllegalArgumentException("Invalid operator");
        }
    }

    public String formatResult(double result) {
        if (result == (long) result) {
            return String.valueOf((long) result);
        }
        return String.valueOf(result);
    }
}