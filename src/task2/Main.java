package task2;

public class Main{
    static void main() {
        ShapeModel model = new ShapeModel();
        ShapeView view = new ShapeView();
        ShapeController controller = new ShapeController(model, view);
        controller.execute();
    }
}
