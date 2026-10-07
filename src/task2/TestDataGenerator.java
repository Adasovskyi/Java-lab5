package task2;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class TestDataGenerator {
    static void main() {
        Shape[] shapes = new Shape[]{
                new Rectangle("Red", 10.0, 5.0),
                new Circle("Blue", 7.0),
                new Triangle("Green", 6.0, 8.0),
                new Rectangle("Yellow", 4.0, 4.0),
                new Circle("Red", 3.0),
                new Triangle("Blue", 10.0, 12.0),
                new Rectangle("Green", 8.0, 3.0),
                new Circle("Yellow", 5.0),
                new Triangle("Red", 4.0, 7.0),
                new Rectangle("Blue", 12.0, 6.0)
        };

        String filePath = "shapes_test.dat";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(shapes);
            System.out.println("Тестовий файл '" + filePath + "' успішно створено!");
        } catch (IOException e) {
            System.out.println("Помилка при створенні файлу: " + e.getMessage());
        }
    }
}
