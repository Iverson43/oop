package hk.edu.polyu.comp.comp2021.clevis.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class BoundaryTest {

    // Creating lines
    Line l01 = new Line("L01", 3,3,6,6);
    Line l02 = new Line("L02", 2,4,6,4);
    Line l03 = new Line("L03", 5,4.5,5,6);
    Line l04 = new Line("L04",2,9,4,3);

    //Creating rectangles
    Rectangle r01 = new Rectangle("r01", 5.5,1,2.5,6);
    Rectangle r02 = new Rectangle("R02", 7.56,0.5,10,2);
    Rectangle r03 = new Rectangle("R03",0,0,2,4);

    @Test
    public void isInterceptLL() { // Intercept between lines
        assertTrue(l01.isIntercept(l02));
        assertFalse(l02.isIntercept(l03));
        assertTrue(l01.isIntercept(l03));
        assertFalse(l01.isIntercept(l04));
    }

    @Test
    public void isInterceptRL() {
        assertTrue(r01.isIntercept(l01));
        assertTrue(r01.isIntercept(l02));
        assertFalse(r01.isIntercept(l03));
    }

    @Test
    public void isInterceptRR() {
        assertTrue(r01.isIntercept(r02));
        assertFalse(r01.isIntercept(r03));
        assertFalse(r02.isIntercept(r03));
    }



}
