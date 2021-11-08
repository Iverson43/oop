package hk.edu.polyu.comp.comp2021.clevis.model;

class Shape {

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

class Line extends Shape{

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

class Circle extends Shape{

    //The radian of a circle
    private double radian;

    Circle(String n, double x, double y, double r) {
        super(n, x, y);
        this.radian = r;
    }

    //Return the radian of a circle
    double getRadian() {return radian;}
}

class Rectangle extends Shape {

    // The height and width for a rectangle.
    private double width;
    private double height;

    Rectangle(String n, double x, double y, double w, double h) {
        super(n, x, y);
        this.width = w;
        this.height = h;
    };

    // Returns width and height of a Rectangle
    double getWidth() {return width;}
    double getHeight() {return height;}
}

class Square extends Rectangle{

    Square(String n, double x, double y, double l) {
        super(n, x, y, l, l);
    }

}
