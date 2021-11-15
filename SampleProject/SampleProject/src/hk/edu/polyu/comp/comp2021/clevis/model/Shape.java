package hk.edu.polyu.comp.comp2021.clevis.model;

import java.util.ArrayList;


public abstract class Shape {

    // The name and anchor of a Shape
    private String name;
    private Coordination topLeft;
    private boolean islock;
    Shape(String n, double a, double b) {
        this.name = n;
        this.topLeft = new Coordination(a,b);

    };
    //Returns the name and coordination of a Shape
    String getName() {return name;}
    Coordination getTopLeft() {return topLeft;}
    boolean getlock() {return islock;}
    abstract ArrayList<Coordination> getPoints();

    public void move(double dx, double dy) {
        topLeft = new Coordination(topLeft.getX()+dx , topLeft.getY()+dy);
    }
    public void setlock(){
        this.islock = true;
    }
    public void unlock(){
        this.islock = false;
    }

}

class Line extends Shape{

    private Coordination bottomRight;
    private Boundary boundary;

    Line(String n, double x1, double y1, double x2, double y2) {
        super(n, x1, y1);
        this.bottomRight = new Coordination(x2,y2);
        this.boundary = new Boundary(this);
    }

    // Returns another end of a Line
    double getEndX() {return bottomRight.getX();}
    double getEndY() {return bottomRight.getY();}

    // Returns the coordination of two ends
    ArrayList<Coordination> getPoints() {
        ArrayList<Coordination> result = new ArrayList<Coordination>();
        result.add(this.getTopLeft());
        result.add(bottomRight);
        return result;
    }

    public void moveEnd(double dx, double dy) {
        bottomRight = new Coordination(bottomRight.getX()+dx, bottomRight.getY()+dy);
    }
}

class Circle extends Shape{

    //The radian of a circle
    private double radius;

    Circle(String n, double x, double y, double r) {
        super(n, x, y);
        this.radius = r;
    }

    //Return the radian of a circle
    double getRadius() {return radius;}


    //Return the coordination of centre.
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

    // Return the coordination of four corners,
    ArrayList<Coordination> getPoints() {
        ArrayList<Coordination> result = new ArrayList<>();
        result.add(this.getTopLeft());
        result.add(new Coordination(this.getTopLeft().getX()+width, this.getTopLeft().getY()));
        result.add(new Coordination(this.getTopLeft().getX(), this.getTopLeft().getY()+height));
        result.add(new Coordination(this.getTopLeft().getX()+width, this.getTopLeft().getY()+height));
        return result;
    }

}

class Square extends Rectangle{

    Square(String n, double x, double y, double l) {
        super(n, x, y, l, l);
    }

}
class Groupped extends Shape{
    ArrayList<Shape> lock;
    Groupped(String x){

        super(x, 0 , 0);
        lock = new ArrayList<Shape>();
        lock.add(this);
    }
    public void addintogroup(Shape y){
        lock.add(y);

    }
    public void ungroup(){
        for (int i = 0; i< lock.size();i++){
            lock.get(i).unlock();
        }
    }
    public void printtest(){
        for(int i=0; i< lock.size();i++){
            System.out.println(lock.get(i).getName());
            System.out.println(lock.get(i).getTopLeft().getX());
            System.out.println(lock.get(i).getTopLeft().getY());
            System.out.println(lock.get(i).getlock());
        }
    }
    public String getgpname(){
        return lock.get(0).getName();
    }
    ArrayList<Coordination> getPoints(){return null;};

}