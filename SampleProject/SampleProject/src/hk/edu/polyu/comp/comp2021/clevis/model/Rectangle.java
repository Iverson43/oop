package hk.edu.polyu.comp.comp2021.clevis.model;

public class Rectangle extends Shape {

    // The height and width for a rectangle.
    private double width;
    private double height;

    Rectangle(double x, double y, double w, double h) {
        super(x, y);
        this.width = w;
        this.height = h;
    };

    // Returns width and height of a Rectangle
    double getWidth() {return width;}
    double getHeight() {return height;}
}
