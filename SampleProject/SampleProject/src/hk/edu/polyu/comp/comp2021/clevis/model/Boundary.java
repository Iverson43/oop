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
            result.add(new StraightLine(pointArr[0], pointArr[1]));
            return result.toArray(new BoundaryLine[1]);
        } else if (s instanceof Rectangle) {
            Coordination[] pointArr = s.getPoints().toArray(new Coordination[2]);
            int ptr1 = 0;
            int ptr2 = 1;
            for (int j=0;j<pointArr.length; j++){
                result.add(new StraightLine(pointArr[ptr1++],pointArr[ptr2]));
                if (ptr2>=3) ptr2 = 0;
                else ptr2 += 2;
            }
        }
        return result.toArray(new BoundaryLine[result.size()]);
    }

    public BoundaryLine[] getLines() {return lines;}

    public boolean isIntercept(Boundary other) {
        BoundaryLine[] a = this.getLines();
        BoundaryLine[] b = other.getLines();
        for (BoundaryLine i: a) {
            if (i instanceof StraightLine) {
                for (BoundaryLine j: b) {
                    if (j instanceof StraightLine) {
                        if (i.isIntercept((StraightLine) j)) return true;
                    }
                }
            }
        }
        return false;
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
    private Coordination a;
    private Coordination b;

    // If the line is diagonal
    private double slope;
    private double yIntercept;

    // If the line is horizontal or vertical
    private LineType type;

    StraightLine(Coordination a, Coordination b) {this.setLine(a,b);}

    // Initialize and change the line
    void setLine(Coordination a, Coordination b) {
        if (a.yDisplacement(b) == 0) {
            this.type = LineType.HORIZONTAL;
        }
        else if (a.xDisplacement(b) == 0) {
            this.type = LineType.VERTICAL;
        }
        else {
            this.type = LineType.DIAGONAL;
        }
        this.a = a;
        this.b = b;
        if (this.type == LineType.DIAGONAL) this.setProperties(a,b);
    }
    void setProperties(Coordination a, Coordination b) {
        if (type != LineType.DIAGONAL) {throw new IllegalArgumentException();}

        this.slope = a.slope(b);
        yIntercept = a.getX() * this.slope - a.getY();
    }

    // Find Intercept Point between two straight lines.
    public Coordination interceptPoint(StraightLine other) {

        double x=0;
        double y=0;

        if (this.type == LineType.VERTICAL) {
            x = this.a.getX();
        } else if (other.type == LineType.VERTICAL) {
            x = other.a.getX();
        }
        if (this.type == LineType.HORIZONTAL) {
            y = this.a.getY();
        } else if (other.type == LineType.HORIZONTAL) {
            y = other.a.getY();
        }

        if ((this.type == LineType.HORIZONTAL && other.type == LineType.VERTICAL)||(this.type == LineType.VERTICAL && other.type == LineType.HORIZONTAL))
            return new Coordination(x,y);

        else if (this.type == LineType.HORIZONTAL && other.type == LineType.DIAGONAL)
            x = y-other.yIntercept/other.slope;
        else if (this.type == LineType.DIAGONAL && other.type == LineType.HORIZONTAL)
            x = y-this.yIntercept/this.slope;
        else if (this.type == LineType.VERTICAL && other.type == LineType.DIAGONAL)
            y = x*other.slope+other.yIntercept;
        else if (this.type == LineType.DIAGONAL && other.type == LineType.VERTICAL) {
            y = x*this.slope+this.yIntercept;
        } else {
            x = (other.yIntercept-this.yIntercept) / (this.slope-other.yIntercept);
            y = this.slope * x - this.yIntercept;
        }
        return new Coordination(x,y);
    }

    // Check if the intercept point is in a line
    boolean inRange(Coordination coord) {
        double temp;
        double x0 = coord.getX();
        double x1 = this.a.getX();
        double x2 = this.b.getX();
        if (x1>x2) {
            temp = x1;
            x1 = x2;
            x2 = temp;
        }
        double y0 = coord.getY();
        double y1 = this.a.getY();
        double y2 = this.b.getY();
        if (y1>y2) {
            temp = y1;
            y1 = y2;
            y2 = temp;
        }
        return ((x1 <= x0 && x0 <= x2) && (y1 <= y0 && y0 <= y2));
    }


    // Check if two lines intercept
    public boolean isIntercept(StraightLine other) {
         if (this.type == other.type && (other.type == LineType.HORIZONTAL || other.type == LineType.VERTICAL)) {
             return (this.inRange(other.a) || this.inRange(other.b) || other.inRange(this.a) || other.inRange(this.b));
         }
         else {
             Coordination intercept = this.interceptPoint(other);
             return (this.inRange(intercept) && other.inRange(intercept));
         }
    }

    public boolean isIntercept(CircularLine other) {
        return false;
    }
}

class CircularLine implements BoundaryLine {
    int a;
    CircularLine() {
        this.a = 0;
    }

    public boolean isIntercept(StraightLine other) {
        return false;
    }
}
