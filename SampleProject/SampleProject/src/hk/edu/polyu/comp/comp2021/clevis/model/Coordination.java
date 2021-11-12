package hk.edu.polyu.comp.comp2021.clevis.model;

public class Coordination {

    private double x;
    private double y;

    Coordination(double x, double y) {
        this.x = Math.round(x * 100.0) / 100.0;
        this.y = Math.round(y * 100.0) / 100.0;
    }

    public double[] getCoordination() {
        return new double[] {this.x,this.y};
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double displacement(Coordination other) {
        double result = Math.sqrt( Math.pow(this.getX() - other.getX(), 2) + Math.pow(this.getY() - other.getY(), 2));
        return Math.round(result * 100.0) / 100.0;
    }

    public double xDisplacement(Coordination other) {
        return Math.abs(this.getX() - this.getX());
    }

    public double yDisplacement(Coordination other) {
        return Math.abs(this.getY() - this.getY());
    }

    public double slope(Coordination other) {
        return (other.getY() - this.getY()) / (other.getX() - this.getX());
    }
}
