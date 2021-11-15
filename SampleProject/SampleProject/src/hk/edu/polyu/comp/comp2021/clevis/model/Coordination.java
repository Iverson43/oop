package hk.edu.polyu.comp.comp2021.clevis.model;

public class Coordination {

    private double x;
    private double y;

    Coordination(double x, double y) {
        this.x = Math.round(x * 100.0) / 100.0;
        this.y = Math.round(y * 100.0) / 100.0;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double xDisplacement(Coordination other) {
        return Math.abs(this.getX() - other.getX());
    }

    public double yDisplacement(Coordination other) {
        return Math.abs(this.getY() - other.getY());
    }

    public double slope(Coordination other) {
        return (other.getY() - this.getY()) / (other.getX() - this.getX());
    }
}
