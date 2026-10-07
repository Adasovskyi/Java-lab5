package task1;

import java.io.IOException;
import java.util.Scanner;

public class Task1App {
    static void main() {
        Scanner scanner = new Scanner(System.in);

        try (scanner) {
            FileService fileService = new FileService();
            System.out.println("Пошук рядка з максимальною кількістю слів");
            System.out.print("Введіть шлях до текстового файлу (наприклад, test-task1.txt): ");
            String filePath = scanner.nextLine().trim();
            String resultLine = fileService.findLineWithMaxWords(filePath);

            if (resultLine != null) {
                int wordCount = resultLine.trim().split("\\s+").length;
                System.out.println("\nРезультат знайдено!");
                System.out.println("Кількість слів: " + wordCount);
                System.out.println("Рядок: " + resultLine);
            } else {
                System.out.println("\nФайл порожній або не містить жодного слова.");
            }
        } catch (IOException e) {
            System.out.println("\nПомилка: " + e.getMessage());
        }
    }
}