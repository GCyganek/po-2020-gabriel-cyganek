package pl.edu.agh.cs.lab2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    @Test
    public void testTwo2dVectorsEqual() {
        Vector2d vector = new Vector2d(1, 3);
        Vector2d otherVector = new Vector2d(1, 3);
        assertTrue(vector.equals(otherVector));
    }

    @Test
    public void testTwo2dVectorsNotEqual() {
        Vector2d vector = new Vector2d(1, 3);
        Vector2d otherVector = new Vector2d(5, 4);
        assertFalse(vector.equals(otherVector));
    }

    @Test
    public void testVector2dEqualsToItself() {
        Vector2d vector = new Vector2d(1, 3);
        assertTrue(vector.equals(vector));
    }

    @Test
    public void testVector2dEqualsOtherDataType() {
        Vector2d vector = new Vector2d(1, 3);
        int test = 5;
        assertFalse(vector.equals(test));
    }

    @Test
    public void testToString() {
        Vector2d vector = new Vector2d(1, 3);
        assertEquals(vector.toString(), "(1,3)");
    }

    @Test
    public void testPrecedesBothValuesLessOrEqual() {
        Vector2d vector = new Vector2d(1, 4);
        Vector2d otherVector = new Vector2d(5, 4);
        assertTrue(vector.precedes(otherVector));
    }

    @Test
    public void testPrecedesOneValueLessOrEqual() {
        Vector2d vector = new Vector2d(6, 3);
        Vector2d otherVector = new Vector2d(5, 4);
        assertFalse(vector.precedes(otherVector));
    }

    @Test
    public void testPrecedesBothValuesNotLessOrEqual() {
        Vector2d vector = new Vector2d(6, 5);
        Vector2d otherVector = new Vector2d(5, 4);
        assertFalse(vector.precedes(otherVector));
    }

    @Test
    public void testFollowsBothBiggerOrEqual() {
        Vector2d vector = new Vector2d(5, 6);
        Vector2d otherVector = new Vector2d(5, 4);
        assertTrue(vector.follows(otherVector));
    }

    @Test
    public void testFollowsOneValueBiggerOrEqual() {
        Vector2d vector = new Vector2d(5, 3);
        Vector2d otherVector = new Vector2d(5, 4);
        assertFalse(vector.follows(otherVector));
    }

    @Test
    public void testFollowsBothValuesNotBiggerOrEqual() {
        Vector2d vector = new Vector2d(1, 1);
        Vector2d otherVector = new Vector2d(5, 4);
        assertFalse(vector.follows(otherVector));
    }

    @Test
    public void testUpperRight() {
        Vector2d vector = new Vector2d(1, 5);
        Vector2d otherVector = new Vector2d(5, 2);
        assertEquals(vector.upperRight(otherVector),
                new Vector2d(Math.max(vector.x, otherVector.x), Math.max(vector.y, otherVector.y)));
    }

    @Test
    public void testLowerLeft() {
        Vector2d vector = new Vector2d(1, 5);
        Vector2d otherVector = new Vector2d(5, 2);
        assertEquals(vector.lowerLeft(otherVector),
                new Vector2d(Math.min(vector.x, otherVector.x), Math.min(vector.y, otherVector.y)));
    }

    @Test
    public void testAdd() {
        Vector2d vector = new Vector2d(1, 5);
        Vector2d otherVector = new Vector2d(5, 2);
        assertEquals(vector.add(otherVector),
                new Vector2d(vector.x + otherVector.x, vector.y + otherVector.y));
    }

    @Test
    public void testSubstract() {
        Vector2d vector = new Vector2d(1, 5);
        Vector2d otherVector = new Vector2d(5, 2);
        assertEquals(vector.substract(otherVector),
                new Vector2d(vector.x - otherVector.x, vector.y - otherVector.y));
    }

    @Test
    public void testOpposite() {
        Vector2d vector = new Vector2d(1, 5);
        assertEquals(vector.opposite(), new Vector2d(-vector.x, -vector.y));
    }
}