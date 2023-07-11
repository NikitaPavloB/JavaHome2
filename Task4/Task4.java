package Task4;

// 4) К калькулятору из предыдущего ДЗ добавить логирование.

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Task4 {
  private static final Logger LOGGER = Logger.getLogger(Task4.class.getName());

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    LOGGER.log(Level.INFO, "Введите первое число: ");
    double num1 = scanner.nextDouble();

    LOGGER.log(Level.INFO, "Введите оператор (+, -, *, /): ");
    char operator = scanner.next().charAt(0);

    LOGGER.log(Level.INFO, "Введите второе число: ");
    double num2 = scanner.nextDouble();

    double result = 0;

    switch (operator) {
      case '+':
        result = num1 + num2;
        break;
      case '-':
        result = num1 - num2;
        break;
      case '*':
        result = num1 * num2;
        break;
      case '/':
        if (num2 != 0) {
          result = num1 / num2;
        } else {
          LOGGER.log(Level.SEVERE, "Ошибка: деление на ноль");
          return;
        }
        break;
      default:
        LOGGER.log(Level.SEVERE, "Ошибка: некорректный оператор");
        return;
    }

    LOGGER.log(Level.INFO, "Результат: " + result);

    scanner.close();
  }
}
