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
}
