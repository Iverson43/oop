package hk.edu.polyu.comp.comp2021.clevis.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ShapeTest {

    @Test
    public void testRectangleConstructor(){
        Rectangle rectangle = new Rectangle("R01", 6,5,10,2);
        assertEquals("R01",rectangle.getName());
        assertEquals(6,rectangle.getStartX(),0);
        assertEquals(5,rectangle.getStartY(),0);
        assertEquals(10,rectangle.getWidth(),0);
        assertEquals(2,rectangle.getHeight(),0);
    }

    @Test
    public void testLineConstructor(){
        Line line = new Line("L01", 8.53,5,3.27,2);
        assertEquals("L01",line.getName());
        assertEquals(8.53,line.getStartX(),2);
        assertEquals(5,line.getStartY(),0);
        assertEquals(3.27,line.getEndX(),2);
        assertEquals(2,line.getEndY(),0);
    }
}
