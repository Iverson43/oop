package hk.edu.polyu.comp.comp2021.clevis.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public abstract class Shape {

    // The name and anchor of a Shape
    private String name;
    private Coordination topLeft;

    Shape(String n, double a, double b) {
        this.name = n;
        this.topLeft = new Coordination(a,b);
    };

    //Returns the name and coordination of a Shape
    String getName() {return name;}
    double getStartX() {return topLeft.getX();}
    double getStartY() {return topLeft.getY();}
    Coordination getTopLeft() {return topLeft;}

    abstract ArrayList<Coordination> getPoints();

    public void move(double dx, double dy) {
        topLeft = new Coordination(topLeft.getX()+dx , topLeft.getY()+dy);
    }
}

class Line extends Shape{

    private Coordination bottomRight;

    Line(String n, double x1, double y1, double x2, double y2) {
        super(n, x1, y1);
        this.bottomRight = new Coordination(x2,y2);
    }

    // Returns width and height of a Rectangle
    double getEndX() {return bottomRight.getX();}
    double getEndY() {return bottomRight.getY();}

    ArrayList<Coordination> getPoints() {
        ArrayList<Coordination> result = new ArrayList<Coordination>();
        result.add(this.getTopLeft());
        result.add(bottomRight);
        return result;
    }
}

class Circle extends Shape{

    //The radian of a circle
    private double radian;

    Circle(String n, double x, double y, double r) {
        super(n, x, y);
        this.radian = r;
    }

    //Return the radian of a circle
    double getRadian() {return radian;}
    ArrayList<Coordination> getPoints() {
        ArrayList<Coordination> result = new ArrayList<>();
        result.add(this.getTopLeft());
        return result;
    }
}

class Rectangle extends Shape {

    // The height and width for a rectangle.
    private double width;
    private double height;

    Rectangle(String n, double x, double y, double w, double h) {
        super(n, x, y);
        this.width = w;
        this.height = h;
        this.toString();
    };

    // Returns width and height of a Rectangle
    double getWidth() {return width;}
    double getHeight() {return height;}

    ArrayList<Coordination> getPoints() {
        ArrayList<Coordination> result = new ArrayList<>();
        result.add(this.getTopLeft());
        result.add(new Coordination(this.getStartX()+width, this.getStartY()));
        result.add(new Coordination(this.getStartX(), this.getStartY()+height));
        result.add(new Coordination(this.getStartX()+width, this.getStartY()+height));
        return result;
    }

    @Override

    public String toString() {
        System.out.println(this.getName());
        System.out.println("Area: " + width*height);
        return null;
    }
}

class Square extends Rectangle{

    Square(String n, double x, double y, double l) {
        super(n, x, y, l, l);
    }

}
