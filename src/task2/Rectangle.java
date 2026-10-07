package task2;

public class Rectangle extends Shape {
    private final double width;
    private final double height;

    public Rectangle(String shapeColor, double width, double height) {
        super(shapeColor);
        this.width = width;
        this.height = height;
    }

    @Override
    public double calcArea() {
        return width * height;
    }

    @Override
    public String draw() {
        return this + " --> Drawing rectangle";
    }

    @Override
    public String toString() {
        return String.format("Rectangle [Color: %s, Width: %.2f, Height: %.2f, Area: %.2f]",
                shapeColor, width, height, calcArea());
    }
}
