package Task2;

// 2 задача) Реализуйте алгоритм сортировки пузырьком числового массива, результат после каждой итерации запишите в лог-файл.

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Task2 {

  public static void main(String[] args) {
    int[] array = { 5, 2, 8, 12, 1, 9 };
    String logFilePath = "sorting_log.txt";

    try {
      bubbleSort(array, logFilePath);
    } catch (IOException e) {
      System.out.println("Ошибка при записи в лог-файл: " + e.getMessage());
    }
  }

  public static void bubbleSort(int[] array, String logFilePath) throws IOException {
    int n = array.length;
    boolean swapped;

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath))) {
      writer.write("Исходный массив: " + arrayToString(array));
      writer.newLine();

      for (int i = 0; i < n - 1; i++) {
        swapped = false;

        for (int j = 0; j < n - i - 1; j++) {
          if (array[j] > array[j + 1]) {
            // Обмен элементов
            int temp = array[j];
            array[j] = array[j + 1];
            array[j + 1] = temp;
            swapped = true;
          }
        }

        writer.write("Итерация " + (i + 1) + ": " + arrayToString(array));
        writer.newLine();

        // Если внутренний цикл не выполнил ни одной замены, массив уже отсортирован
        if (!swapped) {
          break;
        }
      }

      writer.write("Отсортированный массив: " + arrayToString(array));
      writer.newLine();
    }
  }

  public static String arrayToString(int[] array) {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < array.length; i++) {
      sb.append(array[i]);

      if (i != array.length - 1) {
        sb.append(", ");
      }
    }

    return sb.toString();
  }
}
