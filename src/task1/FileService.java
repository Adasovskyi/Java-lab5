package task1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileService {

    public String findLineWithMaxWords(String filePath) throws IOException {
        String maxLine = null;
        int maxWords = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] words = line.trim().split("\\s+");

                if (words.length > maxWords) {
                    maxWords = words.length;
                    maxLine = line;
                }
            }
        }
        return maxLine;
    }
}
