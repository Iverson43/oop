package hk.edu.polyu.comp.comp2021.clevis.model;

public class Line extends Shape{

    private double endX;
    private double endY;

    Line(String n, double x1, double y1, double x2, double y2) {
        super(n, x1, y1);
        this.endX = x2;
        this.endY = y2;
    }

    // Returns width and height of a Rectangle
    double getEndX() {return endX;}
    double getEndY() {return endY;}
}
