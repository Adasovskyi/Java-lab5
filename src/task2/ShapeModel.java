package task2;

import java.util.Arrays;
import java.util.Comparator;

public class ShapeModel {
    private Shape[] shapes;

    public ShapeModel() {
        shapes = new Shape[0];
    }

    public Shape[] getShapes() {
        return shapes;
    }

    public double calcTotalArea() {
        double sum = 0;
        for (Shape shape : shapes) {
            sum += shape.calcArea();
        }
        return sum;
    }

    public double calcTotalAreaByType(Class<?> shapeType) {
        double sum = 0;
        for (Shape shape : shapes) {
            if (shapeType.isInstance(shape)) {
                sum += shape.calcArea();
            }
        }
        return sum;
    }

    public void setShapes(Shape[] shapes) {
        this.shapes = shapes;
    }
    public void sortByArea() {
        Arrays.sort(shapes, new Comparator<>() {
            @Override
            public int compare(Shape s1, Shape s2) {
                return Double.compare(s1.calcArea(), s2.calcArea());
            }
        });
    }

    public void sortByColor() {
        Arrays.sort(shapes, new Comparator<>() {
            @Override
            public int compare(Shape s1, Shape s2) {
                return s1.getShapeColor().compareToIgnoreCase(s2.getShapeColor());
            }
        });
    }
}
