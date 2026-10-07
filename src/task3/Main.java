package task3;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    static void main() {
        char key = 'A';
        String originalText = "This is original text.";
        String fileName = "task3_encrypted_output.txt";

        System.out.println("Оригінальний текст: " + originalText);

        try (CipherFilterWriter writer = new CipherFilterWriter(new FileWriter(fileName), key)) {
            writer.write(originalText);
            System.out.println("Текст успішно зашифровано та збережено у файл: " + fileName);
        } catch (IOException e) {
            System.err.println("Помилка під час запису файлу: " + e.getMessage());
        }

        try (CipherFilterReader reader = new CipherFilterReader(new FileReader(fileName), key)) {
            StringBuilder decryptedText = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                decryptedText.append((char) c);
            }
            System.out.println("Розшифрований текст з файлу: " + decryptedText);
        } catch (IOException e) {
            System.err.println("Помилка під час читання файлу: " + e.getMessage());
        }
    }
}