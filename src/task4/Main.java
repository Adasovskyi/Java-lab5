package task4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.URI;
import java.net.URL;

public class Main {

    static void main() {

        try (Scanner scanner = new Scanner(System.in)) {
            FileHandler fileHandler = new FileHandler();
            System.out.print("Введіть URL (наприклад, https://example.com): ");
            String urlString = scanner.nextLine();

            System.out.print("Введіть шлях та ім'я файлу для збереження (наприклад, result.txt): ");
            String filePath = scanner.nextLine();

            System.out.println("Отримання даних зі сторінки...");
            String htmlContent = fetchHtmlContent(urlString);

            Map<String, Integer> tagCounts = countTags(htmlContent);

            StringBuilder output = new StringBuilder();

            output.append("Теги в лексикографічному порядку:\n");
            Map<String, Integer> lexicographicalMap = new TreeMap<>(tagCounts);
            for (Map.Entry<String, Integer> entry : lexicographicalMap.entrySet()) {
                output.append("<").append(entry.getKey()).append(">: ").append(entry.getValue()).append("\n");
            }

            output.append("\nТеги за частотою появи:\n");
            List<Map.Entry<String, Integer>> frequencyList = new ArrayList<>(tagCounts.entrySet());
            frequencyList.sort(Map.Entry.comparingByValue());
            for (Map.Entry<String, Integer> entry : frequencyList) {
                output.append("<").append(entry.getKey()).append(">: ").append(entry.getValue()).append("\n");
            }

            System.out.println("\n" + output);

            fileHandler.saveResults(filePath, output.toString());
            System.out.println("Дані успішно збережено у файл: " + filePath);

        } catch (Exception e) {
            System.err.println("Помилка під час виконання програми: " + e.getMessage());
        }
    }

    private static String fetchHtmlContent(String urlString) throws Exception {
        StringBuilder content = new StringBuilder();

        URL url = URI.create(urlString).toURL();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    private static Map<String, Integer> countTags(String html) {
        Map<String, Integer> tagCounts = new HashMap<>();
        Pattern pattern = Pattern.compile("</?([a-zA-Z0-9]+)[^>]*>");
        Matcher matcher = pattern.matcher(html);

        while (matcher.find()) {
            String tagName = matcher.group(1).toLowerCase();
            tagCounts.put(tagName, tagCounts.getOrDefault(tagName, 0) + 1);
        }
        return tagCounts;
    }
}