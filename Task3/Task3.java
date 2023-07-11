package Task3;

// 3) Дана json-строка (можно сохранить в файл и читать из файла)
// [{"фамилия":"Иванов","оценка":"5","предмет":"Математика"},{"фамилия":"Петрова","оценка":"4","предмет":"Информатика"},{"фамилия":"Краснов","оценка":"5","предмет":"Физика"}]
// Написать метод(ы), который распарсит json и, используя StringBuilder, создаст строки вида: Студент [фамилия] получил [оценка] по предмету [предмет].

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Task3 {

  public static void main(String[] args) {
    String jsonString = readJsonFromFile("data.json");
    parseJsonAndPrintResult(jsonString);
  }

  public static String readJsonFromFile(String filePath) {
    try {
      return new String(Files.readAllBytes(Paths.get(filePath)));
    } catch (IOException e) {
      System.out.println("Ошибка при чтении JSON-файла: " + e.getMessage());
    }
    return null;
  }

  public static void parseJsonAndPrintResult(String jsonString) {
    if (jsonString == null) {
      return;
    }

    List<Student> students = new ArrayList<>();

    // Удаление лишних символов из JSON-строки
    jsonString = jsonString.replace("[{", "").replace("}]", "");

    // Разделение на отдельные записи студентов
    String[] studentEntries = jsonString.split("\\},\\{");

    // Парсинг каждой записи студента
    for (String entry : studentEntries) {
      String[] keyValuePairs = entry.split(",");

      String surname = "";
      String grade = "";
      String subject = "";

      for (String pair : keyValuePairs) {
        String[] keyValue = pair.split(":");
        String key = keyValue[0].trim().replace("\"", "");
        String value = keyValue[1].trim().replace("\"", "");

        if (key.equals("фамилия")) {
          surname = value;
        } else if (key.equals("оценка")) {
          grade = value;
        } else if (key.equals("предмет")) {
          subject = value;
        }
      }

      students.add(new Student(surname, grade, subject));
    }

    // Вывод результатов
    for (Student student : students) {
      System.out.println(student.toString());
    }
  }
}

class Student {
  private String surname;
  private String grade;
  private String subject;

  public Student(String surname, String grade, String subject) {
    this.surname = surname;
    this.grade = grade;
    this.subject = subject;
  }

  public String toString() {
    return "Студент " + surname + " получил " + grade + " по предмету " + subject + ".";
  }
}
