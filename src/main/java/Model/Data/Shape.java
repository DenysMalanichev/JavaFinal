package Model.Data;

public class Shape {
    private String shapeType;
    private double square;
    private double perimeter;
    private double centerX;
    private double centerY;

    public Shape() {
    }

    public Shape(String shapeType, double square, double perimeter, double centerX, double centerY) {
        this.shapeType = shapeType;
        this.square = square;
        this.perimeter = perimeter;
        this.centerX = centerX;
        this.centerY = centerY;
    }

    public double getCenterX() {
        return centerX;
    }

    public void setCenterX(double centerX) {
        this.centerX = centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public void setCenterY(double centerY) {
        this.centerY = centerY;
    }

    public String getShapeType() {
        return shapeType;
    }

    public void setShapeType(String shapeType) {
        this.shapeType = shapeType;
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    public double getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(double perimeter) {
        this.perimeter = perimeter;
    }

    @Override
    public String toString() {
        return  shapeType +
                " square = " + square +
                ", perimeter =" + perimeter +
                ", center x = " + centerX +
                ", center y = " + centerY;
    }
}
