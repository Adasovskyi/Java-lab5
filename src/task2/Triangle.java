package task2;

public class Triangle extends Shape {
    private final double base;
    private final double height;

    public Triangle(String shapeColor, double base, double height) {
        super(shapeColor);
        this.base = base;
        this.height = height;
    }

    @Override
    public double calcArea() {
        return 0.5 * base * height;
    }

    @Override
    public String draw() {
        return this + " --> Drawing triangle";
    }

    @Override
    public String toString() {
        return String.format("Triangle [Color: %s, Base: %.2f, Height: %.2f, Area: %.2f]",
                shapeColor, base, height, calcArea());
    }
}
