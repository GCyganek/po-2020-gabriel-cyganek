package pl.edu.agh.cs.lab2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {

    @Test
    public void testNext() {
        MapDirection direction = MapDirection.NORTH;
        assertEquals(direction.next(), MapDirection.EAST);
        direction = direction.next();
        assertEquals(direction.next(), MapDirection.SOUTH);
        direction = direction.next();
        assertEquals(direction.next(), MapDirection.WEST);
        direction = direction.next();
        assertEquals(direction.next(), MapDirection.NORTH);
    }

    @Test
    public void testPrevious() {
        MapDirection direction = MapDirection.NORTH;
        assertEquals(direction.previous(), MapDirection.WEST);
        direction = direction.previous();
        assertEquals(direction.previous(), MapDirection.SOUTH);
        direction = direction.previous();
        assertEquals(direction.previous(), MapDirection.EAST);
        direction = direction.previous();
        assertEquals(direction.previous(), MapDirection.NORTH);
    }
}