package pl.edu.agh.cs.lab5;

import org.junit.jupiter.api.Test;
import pl.edu.agh.cs.lab2.MapDirection;
import pl.edu.agh.cs.lab2.MoveDirection;
import pl.edu.agh.cs.lab2.Vector2d;
import pl.edu.agh.cs.lab3.Animal;
import pl.edu.agh.cs.lab4.RectangularMap;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    @Test
    void testCanMoveToUpperBorder() {
        RectangularMap map = new RectangularMap(10, 10);

        //before placing animal on upper right corner
        assertTrue(map.canMoveTo(new Vector2d(10, 10)));

        new Animal(map, new Vector2d(10, 10));

        //after placing animal on upper right corner
        assertFalse(map.canMoveTo(new Vector2d(10, 10)));
    }


    @Test
    void testCanMoveToFieldOutsideTheMap() {
        RectangularMap map = new RectangularMap(10, 10);

        assertFalse(map.canMoveTo(new Vector2d(-1, 0)));
        assertFalse(map.canMoveTo(new Vector2d(0, -1)));
    }

    @Test
    void testPlaceTwoAnimalsOnTheSameField() {
        RectangularMap map = new RectangularMap(10, 10);

        new Animal(map, new Vector2d(10, 10));
        assertThrows(IllegalArgumentException.class, () -> {
            new Animal(map, new Vector2d(10, 10));
        });
    }

    @Test
    void testPlaceOnFieldOutsideOfTheMap() {
        RectangularMap map = new RectangularMap(10, 10);

        assertThrows(IllegalArgumentException.class, () -> {
            new Animal(map, new Vector2d(-1, 10));
        });
    }

    @Test
    void testRunWithPossibilityToMoveOutsideTheMapAndForTwoAnimalsToCollide() {
        LinkedList<MoveDirection> directions = new LinkedList<>(Arrays.asList(MoveDirection.FORWARD, MoveDirection.BACKWARD,
                MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.FORWARD, MoveDirection.FORWARD));

        RectangularMap map = new RectangularMap(10, 10);
        Animal animal1 = new Animal(map);
        Animal animal2 = new Animal(map, new Vector2d(3, 4));

        map.run(directions);
        assertEquals(animal1.getOrientation(), MapDirection.SOUTH);
        assertEquals(animal1.getPosition(), new Vector2d(2, 0));
        assertEquals(animal2.getOrientation(), MapDirection.NORTH);
        assertEquals(animal2.getPosition(), new Vector2d(3, 7));
    }

    @Test
    void testIsOccupied() {
        RectangularMap map = new RectangularMap(10, 10);

        Animal animal1 = new Animal(map, new Vector2d(5, 5));

        assertTrue(map.isOccupied(animal1.getPosition()));
    }

    @Test
    void testObjectAtOnFieldWithAnimalAndRandomField() {
        RectangularMap map = new RectangularMap(10, 10);

        Animal animal = new Animal(map, new Vector2d(5, 5));

        assertEquals(Optional.of(animal), map.objectAt(animal.getPosition()));
        assertEquals(Optional.empty(), map.objectAt(new Vector2d(1, 1)));
    }
}