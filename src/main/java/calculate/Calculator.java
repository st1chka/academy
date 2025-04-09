package calculate;

import lombok.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

@Getter
@Setter
public class Calculator {
    private static final Logger logger = LogManager.getLogger(Calculator.class);

    private static final int ADD = 1;
    private static final int SUBTRACT = 2;
    private static final int MULTIPLY = 3;
    private static final int DIVIDE = 4;
    private static final int EXPONENTIATION = 5;


    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.start();
    }

    public Calculator() {
        // Пустой конструктор для создания объекта через new Calculator()
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int operation = getOperation(scanner);
        double firstNumber = getNumber(scanner, "первое");
        double secondNumber = getNumber(scanner, "второе");
        scanner.close();
        String expression = firstNumber + " " + getStringOperation(operation) + " " + secondNumber;
        try {
            double result = calculate(operation, firstNumber, secondNumber);
            printResult(result);
            logger.info(expression + " = " + result);
        } catch (IllegalArgumentException e) {
            logger.error("Ошибка: " + e.getMessage());
        }
    }

    private int getOperation(Scanner scanner) {
        while (true) {
            System.out.println("Введите " + ADD + " для сложения, " +
                    SUBTRACT + " для вычитания, " +
                    MULTIPLY + " для умножения, " +
                    DIVIDE + " для деления, " +
                    EXPONENTIATION + " для возведения в степень:");
            String input = scanner.nextLine();
            try {
                int operation = Integer.parseInt(input);
                if (operation >= 1 && operation <= 5) {
                    return operation;
                } else {
                    logger.error("Ошибка: число должно быть от 1 до 5. Пожалуйста, попробуйте снова.");
                }
            } catch (NumberFormatException e) {
                logger.error("Ошибка: введено не число. Пожалуйста, введите цифру от 1 до 5.");
            }
        }
    }

    private double getNumber(Scanner scanner, String order) {
        while (true) {
            System.out.println("Введите " + order + " число:");
            String input = scanner.nextLine();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                logger.error("Ошибка: введено не число. Пожалуйста, введите корректное число.");
            }
        }
    }

    private double calculate(int operation, double a, double b) {
        switch (operation) {
            case ADD:
                return a + b;
            case SUBTRACT:
                return a - b;
            case MULTIPLY:
                return a * b;
            case EXPONENTIATION:
                return Math.pow(a, b);
            case DIVIDE:
                if (b == 0) throw new IllegalArgumentException("Деление на ноль!");
                return a / b;
            default:
                throw new IllegalArgumentException("Неверная операция!");
        }
    }

    private void printResult(double result) {
        System.out.println("Результат: " + result);
    }

    public String getStringOperation(int number) {
        switch (number) {
            case 1:
                return "+";
            case 2:
                return "-";
            case 3:
                return "*";
            case 4:
                return "/";
            case 5:
                return "^";
            default:
                return "unknown operation";
        }
    }
}