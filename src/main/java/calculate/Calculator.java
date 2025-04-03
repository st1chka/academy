package calculate;

import lombok.*;

import java.util.Scanner;
import java.util.logging.Logger;


@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Calculator {
    private static final Logger logger = Logger.getLogger( Calculator.class.getName() );

    private static final int ADD = 1;
    private static final int SUBTRACT = 2;
    private static final int MULTIPLY = 3;
    private static final int DIVIDE = 4;
    private static final int EXPONENTIATION = 5;


    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.start();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int operation = getOperation(scanner);
        double firstNumber = getNumber(scanner, "первое");
        double secondNumber = getNumber(scanner, "второе");
        scanner.close();
        String expression = String.valueOf(firstNumber + operation + secondNumber);
        try {
            double result = calculate(operation, firstNumber, secondNumber);
            printResult(result);
            logger.info(expression + " = " + result);
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }


    private int getOperation(Scanner scanner) {
        System.out.println("Введите " + ADD + " для сложения, " +
                SUBTRACT + " для вычитания, " +
                MULTIPLY + " для умножения, " +
                DIVIDE + " для деления:" +
                EXPONENTIATION + " для возведения в степень:");
        return scanner.nextInt();
    }

    private double getNumber(Scanner scanner, String order) {
        System.out.println("Введите " + order + " число:");
        return scanner.nextDouble();
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


}
