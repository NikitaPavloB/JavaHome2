package Task5;

// 5) Дана строка sql-запроса "select * from students where ". Сформируйте часть WHERE этого запроса, используя StringBuilder. Данные для фильтрации приведены ниже в виде json-строки.
// Если значение null, то параметр не должен попадать в запрос.
// Параметры для фильтрации: {"name":"Ivanov", "country":"Russia", "city":"Moscow", "age":"null"}

public class Task5 {

  public static void main(String[] args) {
    String filterJson = "{\"name\":\"Ivanov\", \"country\":\"Russia\", \"city\":\"Moscow\", \"age\":\"null\"}";
    String whereClause = generateWhereClause(filterJson);
    String sqlQuery = "select * from students where " + whereClause;
    System.out.println(sqlQuery);
  }

  public static String generateWhereClause(String filterJson) {
    StringBuilder whereClauseBuilder = new StringBuilder();

    // Удаление символов {, }, "
    filterJson = filterJson.replace("{", "").replace("}", "").replace("\"", "");

    String[] filterPairs = filterJson.split(",");

    for (String pair : filterPairs) {
      String[] keyValue = pair.split(":");
      String key = keyValue[0].trim();
      String value = keyValue[1].trim();

      if (!value.equalsIgnoreCase("null")) {
        if (whereClauseBuilder.length() > 0) {
          whereClauseBuilder.append(" and ");
        }
        whereClauseBuilder.append(key).append(" = ").append(escapeValue(value));
      }
    }

    return whereClauseBuilder.toString();
  }

  public static String escapeValue(String value) {
    if (value.equalsIgnoreCase("null")) {
      return value;
    }
    return "'" + value + "'";
  }
}
