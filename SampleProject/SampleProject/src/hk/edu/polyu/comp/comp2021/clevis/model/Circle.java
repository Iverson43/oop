package hk.edu.polyu.comp.comp2021.clevis.model;

public class Circle extends Shape{

    //The radian of a circle
    private double radian;

    Circle(String n, double x, double y, double r) {
        super(n, x, y);
        this.radian = r;
    }

    //Return the radian of a circle
    double getRadian() {return radian;}
}
