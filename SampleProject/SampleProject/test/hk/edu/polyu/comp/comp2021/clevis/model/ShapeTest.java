package hk.edu.polyu.comp.comp2021.clevis.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ShapeTest {

    Rectangle r01 = new Rectangle("R01", 6,5,10,2);
    Line l01 = new Line("L01", 8.53,5,3.27,2);
    Circle c01 = new Circle("C01", 9.01, 8.31, 3.56);
    Square s01 = new Square("S01", 7.21,5.01,10.45);

    @Test
    public void testRectangleConstructor(){
        assertEquals("R01",r01.getName());
        assertEquals(6,r01.getTopLeft().getX(),0);
        assertEquals(5,r01.getTopLeft().getY(),0);
        assertEquals(10,r01.getWidth(),0);
        assertEquals(2,r01.getHeight(),0);
    }

    @Test
    public void testLineConstructor(){
        assertEquals("L01",l01.getName());
        assertEquals(8.53,l01.getTopLeft().getX(),2);
        assertEquals(5,l01.getTopLeft().getY(),0);
        assertEquals(3.27,l01.getEndX(),2);
        assertEquals(2,l01.getEndY(),0);
    }

    @Test
    public void testCircleConstructor(){
        assertEquals("C01", c01.getName());
        assertEquals(9.01,c01.getTopLeft().getX(),2);
        assertEquals(8.31,c01.getTopLeft().getY(),0);
        assertEquals(3.56,c01.getRadius(),2);
    }

    @Test
    public void testSquareConstructor(){
        assertEquals("S01",s01.getName());
        assertEquals(7.21,s01.getTopLeft().getX(),0);
        assertEquals(5.01,s01.getTopLeft().getY(),0);
        assertEquals(10.45,s01.getWidth(),0);
        assertEquals(10.45,s01.getHeight(),0);
    }
}
