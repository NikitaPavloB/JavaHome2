package Task1;

import java.io.File;
// import java.io.FilenameFilter;

public class Task1 {

  public static void main(String[] args) {
    String folderPath = "."; // Путь к текущей папке

    countFileExtensions(folderPath);
  }

  public static void countFileExtensions(String folderPath) {
    File folder = new File(folderPath);

    if (!folder.isDirectory()) {
      System.out.println("Указанный путь не является директорией.");
      return;
    }

    File[] files = folder.listFiles();

    if (files == null || files.length == 0) {
      System.out.println("Папка пуста.");
      return;
    }

    // Определение типов файлов и их количество
    int count = 1;
    for (File file : files) {
      if (file.isFile()) {
        String extension = getFileExtension(file.getName());
        System.out.println(count + ". Расширение файла: " + extension);
        count++;
      }
    }
  }

  public static String getFileExtension(String fileName) {
    int dotIndex = fileName.lastIndexOf(".");
    if (dotIndex == -1) {
      return ""; // Если расширение отсутствует
    }
    return fileName.substring(dotIndex + 1);
  }
}
