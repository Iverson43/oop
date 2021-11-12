package hk.edu.polyu.comp.comp2021.clevis.model;

import java.util.ArrayList;

public class Boundary {

    private final BoundaryLine[] lines;
    private final Shape s;

    Boundary(Shape s) {
        this.s = s;
        this.lines = this.setBoundary();
    }

    BoundaryLine[] setBoundary() {
        ArrayList<BoundaryLine> result = new ArrayList<>();
        if (s instanceof Line) {
            Coordination[] pointArr = s.getPoints().toArray(new Coordination[2]);
            result.add(new StraightLine(pointArr));
            return result.toArray(new BoundaryLine[1]);
        }
        return null;
    }
}

interface BoundaryLine {
    boolean isIntercept(StraightLine other);
}

class StraightLine implements BoundaryLine{

    enum LineType {
        VERTICAL, HORIZONTAL, DIAGONAL
    }

    // Store Coordination
    private Coordination c[];

    // If the line is diagonal
    private double slope;
    private double yIntercept;

    // If the line is horizontal or vertical
    private LineType type;

    StraightLine(Coordination coord[]) {
        this.setLine(coord);
    }

    // Initialize and change the line
    void setLine(Coordination coord[]) {
        if (coord[0].yDisplacement(coord[1]) == 0) {
            this.type = LineType.HORIZONTAL;
        }
        else if (coord[0].xDisplacement(coord[1]) == 0) {
            this.type = LineType.VERTICAL;
        }
        else {
            this.setProperties();
            this.type = LineType.DIAGONAL;
        }
        this.c = coord;
    }
    void setProperties() {
        if (type != LineType.DIAGONAL) {throw new IllegalArgumentException();}

        this.slope = c[0].slope(c[1]);
        yIntercept = c[0].getX() * this.slope - c[0].getY();
    }

    // Find Intercept Point between two straight lines.
    public Coordination interceptPoint(StraightLine other) {
        if (this.type == LineType.HORIZONTAL && other.type == LineType.VERTICAL)
            return new Coordination(other.c[0].getX(), this.c[0].getY());
        if (this.type == LineType.VERTICAL && other.type == LineType.HORIZONTAL)
            return new Coordination(other.c[0].getY(), this.c[0].getX());

        double x = (other.yIntercept - this.yIntercept) / (this.slope - other.slope);
        double y = this.slope * x - this.yIntercept;
        return new Coordination(x,y);
    }

    // Check if the intercept point is in a line
    boolean inRange(Coordination coord) {
        double x0 = coord.getX();
        double x1 = this.c[0].getX();
        double x2 = this.c[1].getX();
        double y0 = coord.getY();
        double y1 = this.c[0].getY();
        double y2 = this.c[1].getY();
        return ((x1 <= x0 && x0 <= x2) && (y1 <= y0 && y0 <= y2));
    }

    // Check if two lines intercept
    public boolean isIntercept(StraightLine other) {
         if (this.type == other.type && (other.type == LineType.HORIZONTAL || other.type == LineType.VERTICAL)) {
             return (this.inRange(other.c[0]) || this.inRange(other.c[1]) || other.inRange(this.c[0]) || other.inRange(this.c[1]));
         }
         else {
             Coordination intercept = this.interceptPoint(other);
             return (this.inRange(intercept) && other.inRange(intercept));
         }
    }
}

