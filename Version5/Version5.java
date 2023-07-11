package Version5;

// Версия 5-й задачи

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Version5 {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    Map<String, String> filterData = new LinkedHashMap<>();
    filterData.put("name", null);
    filterData.put("country", null);
    filterData.put("city", null);
    filterData.put("age", null);

    System.out.println("Введите значения для параметров фильтрации:");

    for (Map.Entry<String, String> entry : filterData.entrySet()) {
      String paramName = entry.getKey();
      System.out.print(paramName + ": ");
      String paramValue = scanner.nextLine();
      filterData.put(paramName, paramValue);
    }

    String whereClause = generateWhereClause(filterData);
    String sqlQuery = "select * from students where " + whereClause;
    System.out.println("SQL-запрос: " + sqlQuery);

    scanner.close();
  }

  public static String generateWhereClause(Map<String, String> filterData) {
    StringBuilder whereClauseBuilder = new StringBuilder();

    for (Map.Entry<String, String> entry : filterData.entrySet()) {
      String paramName = entry.getKey();
      String paramValue = entry.getValue();

      if (!"null".equalsIgnoreCase(paramValue)) {
        if (whereClauseBuilder.length() > 0) {
          whereClauseBuilder.append(" and ");
        }
        whereClauseBuilder.append(paramName).append(" = ").append(escapeValue(paramValue));
      }
    }

    return whereClauseBuilder.toString();
  }

  public static String escapeValue(String value) {
    if ("null".equalsIgnoreCase(value)) {
      return value;
    }
    return "'" + value + "'";
  }
}
