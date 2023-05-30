package Model.Data;

public enum ShapeType {
    Square,
    Rectangle,
    Circle,
    Trapeze,
    Oval,
    Triangle,
    Diamond,
    Rhombus,
    Parallelogram,
    Pentagon,
    Hexagon,
    Octagon;
    public static boolean contains(String name) {
        for (ShapeType shape : values()) {
            if (shape.name().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
}
