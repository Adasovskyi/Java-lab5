package task2;

import java.io.IOException;
import java.util.Scanner;

public class ShapeController {
    private final ShapeModel model;
    private final ShapeView view;
    private final FileManager fileManager;

    public ShapeController(ShapeModel model, ShapeView view) {
        this.model = model;
        this.view = view;
        this.fileManager = new FileManager();
    }

    private boolean isDataLoaded() {
        return model.getShapes() != null && model.getShapes().length > 0;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            view.printMessage("\nМеню:");
            view.printMessage("1. Завантажити набір об'єктів з файлу (обов'язково спочатку)");
            view.printMessage("2. Зберегти набір об'єктів у файл");
            view.printMessage("3. Показати набір даних");
            view.printMessage("4. Знайти сумарну площу всіх фігур");
            view.printMessage("5. Знайти сумарну площу фігур заданого виду");
            view.printMessage("6. Впорядкувати фігури за площею (зростання)");
            view.printMessage("7. Впорядкувати фігури за кольором (алфавітний порядок)");
            view.printMessage("0. Вихід");
            view.printMessage("Оберіть опцію: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        view.printMessage("Введіть шлях та ім'я файлу для завантаження (напр., shapes_test.dat): ");
                        String loadPath = scanner.nextLine();
                        Shape[] loadedShapes = fileManager.loadFromFile(loadPath);
                        model.setShapes(loadedShapes);
                        view.printMessage("Дані успішно завантажено з файлу!");
                        break;
                    case 2:
                        if (!isDataLoaded()) {
                            view.printMessage("Немає даних для збереження. Спочатку завантажте їх.");
                            break;
                        }
                        view.printMessage("Введіть шлях та ім'я файлу для збереження: ");
                        String savePath = scanner.nextLine();
                        fileManager.saveToFile(model.getShapes(), savePath);
                        view.printMessage("Дані успішно збережено у файл!");
                        break;
                    case 3:
                        if (!isDataLoaded()) {
                            view.printMessage("Дані порожні. Завантажте файл.");
                            break;
                        }
                        view.printShapes(model.getShapes());
                        break;
                    case 4:
                        if (!isDataLoaded()) {
                            view.printMessage("Дані порожні. Завантажте файл.");
                            break;
                        }
                        view.printMessage(String.format("Сумарна площа всіх фігур: %.2f", model.calcTotalArea()));
                        break;
                    case 5:
                        if (!isDataLoaded()) {
                            view.printMessage("Дані порожні. Завантажте файл.");
                            break;
                        }
                        view.printMessage("Введіть вид фігури для пошуку (Circle, Rectangle, Triangle): ");
                        String type = scanner.nextLine();
                        Class<?> targetClass;

                        if (type.equalsIgnoreCase("Circle")) {
                            targetClass = Circle.class;
                        } else if (type.equalsIgnoreCase("Rectangle")) {
                            targetClass = Rectangle.class;
                        } else if (type.equalsIgnoreCase("Triangle")) {
                            targetClass = Triangle.class;
                        } else {
                            throw new IllegalArgumentException("Невідомий тип фігури!");
                        }

                        double area = model.calcTotalAreaByType(targetClass);
                        view.printMessage(String.format("Сумарна площа %s: %.2f", type, area));
                        break;
                    case 6:
                        if (!isDataLoaded()) {
                            view.printMessage("Дані порожні. Завантажте файл.");
                            break;
                        }
                        model.sortByArea();
                        view.printMessage("Фігури відсортовано за площею. Використайте опцію 3 для перегляду.");
                        break;
                    case 7:
                        if (!isDataLoaded()) {
                            view.printMessage("Дані порожні. Завантажте файл.");
                            break;
                        }
                        model.sortByColor();
                        view.printMessage("Фігури відсортовано за кольором. Використайте опцію 3 для перегляду.");
                        break;
                    case 0:
                        running = false;
                        view.printMessage("Завершення роботи.");
                        break;
                    default:
                        view.printMessage("Невірний вибір. Спробуйте ще раз.");
                }
            } catch (NumberFormatException e) {
                view.printMessage("Помилка: Введено не число. Будь ласка, введіть цифру від 0 до 7.");
            } catch (IOException e) {
                view.printMessage("Помилка роботи з файлом: " + e.getMessage());
            } catch (ClassNotFoundException e) {
                view.printMessage("Помилка при завантаженні об'єктів: Клас не знайдено або файл пошкоджений.");
            } catch (IllegalArgumentException e) {
                view.printMessage("Помилка вводу: " + e.getMessage());
            }
        }
    }
}