package hk.edu.polyu.comp.comp2021.clevis.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class BoundaryTest {

    Line l01 = new Line("l01", 5,6,7,8);
    Line l02 = new Line("l02", 4,4,8,9);
    Line l03 = new Line("l03", 0,0,1,1);
    Line l04 = new Line("l04", 0,9,0,10);

    @Test
    public void interceptTest() {
        assertTrue(l01.isIntercept(l02));
        assertFalse(l01.isIntercept(l03));
        assertFalse(l02.isIntercept(l03));
        assertFalse(l03.isIntercept(l04));
    }
}
