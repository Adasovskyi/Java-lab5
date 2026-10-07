package task2;

public class ShapeView {
    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printShapes(Shape[] shapes) {
        for (Shape shape : shapes) {
            System.out.println(shape.draw());
        }
        System.out.println();
    }
}