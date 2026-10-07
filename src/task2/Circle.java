package task2;

public class Circle extends Shape {
    private final double radius;

    public Circle(String shapeColor, double radius) {
        super(shapeColor);
        this.radius = radius;
    }

    @Override
    public double calcArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public String draw() {
        return this + " --> Drawing circle";
    }

    @Override
    public String toString() {
        return String.format("Circle [Color: %s, Radius: %.2f, Area: %.2f]",
                shapeColor, radius, calcArea());
    }
}