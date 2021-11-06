package hk.edu.polyu.comp.comp2021.clevis.model;

public class Shape {

    // The anchor of a Shape
    private double x;
    private double y;

    Shape(double a, double b) {
        this.x = a;
        this.y = b;
    };

    //Returns the coordination of a Shape
    double getX() {return x;}
    double getY() {return y;}
}

