package hk.edu.polyu.comp.comp2021.clevis.model;

public class Shape {

    // The name and anchor of a Shape
    private String name;
    private double x;
    private double y;

    Shape(String n, double a, double b) {
        this.name = n;
        this.x = a;
        this.y = b;
    };

    //Returns the name and coordination of a Shape
    String getName() {return name;}
    double getStartX() {return x;}
    double getStartY() {return y;}
}

