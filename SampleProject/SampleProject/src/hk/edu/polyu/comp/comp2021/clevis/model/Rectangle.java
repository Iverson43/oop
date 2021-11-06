package hk.edu.polyu.comp.comp2021.clevis.model;

public class Rectangle extends Shape {

    // The height and width for a rectangle.
    private double w;
    private double h;

    Rectangle(double a, double b, double x, double y) {
        super(x, y);
        this.w = a;
        this.h = b;
    };

    // Returns width and height of a Rectangle
    double getWidth() {return w;}
    double getHeight() {return h;}
}
