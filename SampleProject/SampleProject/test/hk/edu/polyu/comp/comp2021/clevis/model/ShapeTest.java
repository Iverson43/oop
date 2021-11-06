package hk.edu.polyu.comp.comp2021.clevis.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ShapeTest {

    @Test
    public void testRectangleConstructor(){
        Rectangle rectangle = new Rectangle(6,5,10,2);
        assertEquals(6,rectangle.getX(),0);
        assertEquals(5,rectangle.getY(),0);
        assertEquals(10,rectangle.getWidth(),0);
        assertEquals(2,rectangle.getHeight(),0);
    }

}
